/*******************************************************************************
 * Copyright 2019 madhankumar
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package com.rabobank.statementprocessor;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.error.ErrorMvcAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@EnableJpaRepositories("com.rabobank.statementprocessor.repository")
@EntityScan("com.rabobank.statementprocessor.model.entity")
@EnableTransactionManagement(proxyTargetClass = true)
@SpringBootApplication(scanBasePackages = "com.rabobank.statementprocessor",
  exclude = {ErrorMvcAutoConfiguration.class, SecurityAutoConfiguration.class})
@PropertySource("classpath:swagger.properties")
@EnableAspectJAutoProxy(proxyTargetClass = true)
public class RaboBankApplication {

  /**
   * Rabobank_API_VERSION
   */
  private static final String RABOBANK_API_VERSION = "1.0.0";

  public static void main(String[] args) {
    SpringApplication.run(RaboBankApplication.class, args);
  }

  /**
   * Api.
   * @return the docket
   */
  @Bean
  public Docket api() {
    Set<String> contentType = new HashSet<>();
    contentType.add("application/json");
    return new Docket(DocumentationType.SWAGGER_2).select()
      .apis(RequestHandlerSelectors.basePackage("com.rabobank.statementprocessor.web")).paths(PathSelectors.any())
      .build()
      .produces(contentType)
      .apiInfo(apiInfo());
  }

  /**
   * @return
   *        The API Information for the statementprocessor service.
   */
  private static ApiInfo apiInfo() {
    return new ApiInfo("Rabobank Services",
      "The Rabobank microservice provides simple statementprocessor which validates the xml or csv data",
      RABOBANK_API_VERSION, null, null, null, null,
      Collections.emptyList());
  }

  /*
   * @Bean
   * public StepScope stepScope() {
   * final StepScope stepScope = new StepScope();
   * stepScope.setAutoProxy(true);
   * return stepScope;
   * }
   */
}
