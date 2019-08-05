package com.rabobank.statementprocessor.process;

import javax.sql.DataSource;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemStreamReader;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.xml.StaxEventItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.core.io.ClassPathResource;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

import com.rabobank.statementprocessor.constants.Constants;
import com.rabobank.statementprocessor.model.dto.AcceptedFileType;
import com.rabobank.statementprocessor.model.entity.AccountEntity;
import com.rabobank.statementprocessor.utils.NotificationListener;

@Configuration
@EnableBatchProcessing
public class BatchProcessor {

  /* creates a job builder */
  @Autowired
  private JobBuilderFactory jobBuilderFactory;

  /* creates a step builder */
  @Autowired
  private StepBuilderFactory stepBuilderFactory;

  @Value("${rabobank.customer.statement.source.csv}")
  private String sourceCSVFile;

  @Value("${rabobank.customer.statement.source.xml}")
  private String sourceXMLFile;

  @Bean
  @Scope(value = "step", proxyMode = ScopedProxyMode.INTERFACES)
  public ItemStreamReader<AccountEntity> csvReader() {
    return new FlatFileItemReaderBuilder<AccountEntity>()
      .name("accountItemReader")
      .resource(new ClassPathResource(sourceCSVFile))
      .encoding("Cp1252")
      .linesToSkip(1)
      .delimited()
      .names(Constants.HEADERS)
      .lineMapper(lineMapper())
      .fieldSetMapper(new BeanWrapperFieldSetMapper<AccountEntity>() {
        {
          setTargetType(AccountEntity.class);
        }
      })
      .build();
  }

  @Bean
  @Scope(value = "step", proxyMode = ScopedProxyMode.INTERFACES)
  public ItemStreamReader<AccountEntity> xmlReader() {
    StaxEventItemReader<AccountEntity> reader = new StaxEventItemReader<>();
    reader.setResource(new ClassPathResource(sourceXMLFile));
    reader.setFragmentRootElementName(Constants.KEY_XML_ROOT_ELEMENT);

    Jaxb2Marshaller recordMarshaller = new Jaxb2Marshaller();
    recordMarshaller.setPackagesToScan(Constants.PACKAGE_ENTITY);
    recordMarshaller.setMappedClass(AccountEntity.class);
    reader.setUnmarshaller(recordMarshaller);

    return reader;
  }

  @Bean
  public LineMapper<AccountEntity> lineMapper() {

    final DefaultLineMapper<AccountEntity> defaultLineMapper = new DefaultLineMapper<>();
    final DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
    lineTokenizer.setDelimiter(",");
    lineTokenizer.setStrict(false);
    lineTokenizer
      .setNames(Constants.HEADERS);

    defaultLineMapper.setLineTokenizer(lineTokenizer);
    defaultLineMapper.setFieldSetMapper(fieldSetMapper());

    return defaultLineMapper;
  }

  @Bean
  public AccountProcessor processor() {
    return new AccountProcessor();
  }

  @Bean
  public AccountFieldSetMapper fieldSetMapper() {
    return new AccountFieldSetMapper();
  }

  @Bean
  public NotificationListener listener() {
    return new NotificationListener();
  }

  @Bean
  public JdbcBatchItemWriter<AccountEntity> writer(final DataSource dataSource) {
    return new JdbcBatchItemWriterBuilder<AccountEntity>()
      .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
      .sql(Constants.INSERT_QUERY)
      .dataSource(dataSource)
      .build();
  }

  @Bean
  public Job importAccountEntityJob(NotificationListener listener,
    Step conditionalStep1, Step step1, Step step2) {
    return jobBuilderFactory.get("importAccountEntityJob")
      .incrementer(new RunIdIncrementer())
      .listener(listener())
      .flow(conditionalStep1).on(Constants.SWITCHTOCSV).to(step1)
      .from(conditionalStep1).on(Constants.SWITCHTOXML).to(step2)
      .end()
      .build();
  }

  @Bean
  public Step conditionalStep1(StepBuilderFactory steps) {
    return steps.get("conditionalStep1")
      .tasklet((contribution, chunkContext) -> {
        String type = (String) chunkContext.getStepContext().getJobParameters().get(Constants.KEY_FILE_TYPE);
        if (type.endsWith(AcceptedFileType.XML.toString())) {
          contribution.setExitStatus(new ExitStatus(Constants.SWITCHTOXML));
        } else {
          contribution.setExitStatus(new ExitStatus(Constants.SWITCHTOCSV));
        }
        return RepeatStatus.FINISHED;
      }).build();
  }

  @Bean
  public Step step1(
    JdbcBatchItemWriter<AccountEntity> writer) {
    return stepBuilderFactory.get("step1")
      .<AccountEntity, AccountEntity>chunk(10)
      .reader(csvReader())
      .processor(processor())
      .writer(writer)
      .build();
  }

  @Bean
  public Step step2(
    JdbcBatchItemWriter<AccountEntity> writer) {
    return stepBuilderFactory.get("step2")
      .<AccountEntity, AccountEntity>chunk(10)
      .reader(xmlReader())
      .processor(processor())
      .writer(writer)
      .build();
  }

}
