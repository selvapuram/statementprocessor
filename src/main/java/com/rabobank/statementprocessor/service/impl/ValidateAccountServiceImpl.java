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
package com.rabobank.statementprocessor.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rabobank.statementprocessor.constants.Constants;
import com.rabobank.statementprocessor.events.IEventPublisher;
import com.rabobank.statementprocessor.exception.CustomValidateException;
import com.rabobank.statementprocessor.model.dto.AcceptedFileType;
import com.rabobank.statementprocessor.model.dto.Records;
import com.rabobank.statementprocessor.model.events.EventType;
import com.rabobank.statementprocessor.process.IValidationProcessor;
import com.rabobank.statementprocessor.service.ValidateAccountService;
import com.rabobank.statementprocessor.utils.AccountFactory;

import lombok.NoArgsConstructor;

/**
 * The Class ValidateAccountServiceImpl.
 */
@Service
@NoArgsConstructor
public class ValidateAccountServiceImpl implements ValidateAccountService {

  /* The LOGGER for capturing the log */
  private static final Logger LOGGER = LoggerFactory.getLogger(ValidateAccountServiceImpl.class);

  @Autowired
  private JobLauncher jobLauncher;

  @Autowired
  private Job job;

  @Autowired
  private IEventPublisher eventPublisher;

  @Autowired
  private IValidationProcessor validationProcessor;

  @Override
  public Records validateAccount(AcceptedFileType type) throws Exception {
    LOGGER.debug("validation batch started");
    eventPublisher.publishEvent(EventType.BATCH_START, "batch process start");
    validationProcessor.deleteAllRecords();
    JobParameters jobParameters = new JobParametersBuilder()
      .addLong("time", System.currentTimeMillis())
      .addString(Constants.KEY_FILE_TYPE, type.toString())
      .toJobParameters();
    final JobExecution execution = jobLauncher.run(job, jobParameters);
    final ExitStatus status = execution.getExitStatus();

    if (ExitStatus.COMPLETED.getExitCode().equals(status.getExitCode())) {
      LOGGER.info("validation job completed");
      validationProcessor.collectUniqueRecordAndUpdate();
      return AccountFactory.buildRecords(validationProcessor.findAllRecords());
    } else {
      LOGGER.error("validation job failed {}", status.getExitDescription());
      throw new CustomValidateException("validation job failed");
    }
  }

}
