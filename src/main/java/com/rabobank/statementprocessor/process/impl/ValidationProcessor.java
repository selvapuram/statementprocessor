package com.rabobank.statementprocessor.process.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.rabobank.statementprocessor.exception.RecordNotFoundException;
import com.rabobank.statementprocessor.mapper.AccountModelMapper;
import com.rabobank.statementprocessor.model.dto.Account;
import com.rabobank.statementprocessor.model.entity.AccountEntity;
import com.rabobank.statementprocessor.process.Command;
import com.rabobank.statementprocessor.process.IValidationProcessor;
import com.rabobank.statementprocessor.repository.AccountRepository;
import com.rabobank.statementprocessor.utils.MathUtil;

import lombok.NoArgsConstructor;

@Service
@NoArgsConstructor
public class ValidationProcessor implements IValidationProcessor {

  @Autowired
  private AccountRepository accountRepository;

  @Value("${rabobank.customer.statement.message.duplicate}")
  private String duplicateRecordError;

  @Value("${rabobank.customer.statement.message.imbalance}")
  private String imbalancedRecordError;

  private Map<Character, Command> operator;

  @Autowired
  private AccountModelMapper accountModelMapper;

  @Override
  public AccountEntity validateAndReport(AccountEntity ae) throws Exception {
    double calcEndBalance = ae.getStartBalance();
    double actualEndBalance = MathUtil.round(ae.getEndBalance(), MathUtil.ROUND_SCALE);
    if (ae.getMutation() != null) {
      char operation = ae.getMutation().charAt(0);
      double mutation = Double.parseDouble(ae.getMutation().substring(1));
      if (this.operator.get(operation) != null) {
        Command c = this.operator.get(operation);
        calcEndBalance = MathUtil.round(c.execute(calcEndBalance, mutation), MathUtil.ROUND_SCALE);
      }
      if (calcEndBalance != actualEndBalance) {
        ae.setDescription(imbalancedRecordError);
        ae.setFailed(true);
      }
    }
    return ae;
  }

  @PostConstruct
  public void init() {
    this.operator = new HashMap<>();
    this.operator.put(Command.ADD.getOperation(), Command.ADD);
    this.operator.put(Command.SUBTRACT.getOperation(), Command.SUBTRACT);
  }

  @Override
  public List<Account> findAllRecords() throws Exception {
    List<AccountEntity> recordList = accountRepository.findAll();
    if (!recordList.isEmpty()) {
      return accountModelMapper.entityToDto(recordList);
    } else {
      throw new RecordNotFoundException("Resource not found");
    }
  }

  @Override
  public void deleteAllRecords() throws Exception {
    accountRepository.deleteAll();

  }

  @Override
  public void collectUniqueRecordAndUpdate() throws Exception {
    List<AccountEntity> recordList = accountRepository.findAll();
    Map<Long, Long> recordGroup = recordList.stream()
      .collect(Collectors.groupingBy(AccountEntity::getReference, Collectors.counting()));

    recordGroup.forEach((k, v) -> {
      if (v > 1) {
        Optional<List<AccountEntity>> ae = accountRepository.findAllByReference(k);
        if (ae.isPresent()) {
          List<AccountEntity> al = ae.get();
          al = al.subList(1, al.size());
          al.stream().forEach(e -> {
            if (e.isFailed()) {
              e.setDescription(duplicateRecordError + " and " + e.getDescription());
            } else {
              e.setDescription(duplicateRecordError);
            }
          });
          accountRepository.saveAll(al);
        }
      }
    });
  }

}
