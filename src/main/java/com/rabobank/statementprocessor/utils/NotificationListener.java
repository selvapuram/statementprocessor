package com.rabobank.statementprocessor.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.rabobank.statementprocessor.events.IEventPublisher;
import com.rabobank.statementprocessor.model.events.EventType;

import lombok.NoArgsConstructor;

@Component
@NoArgsConstructor
public class NotificationListener extends JobExecutionListenerSupport {
  private static final Logger LOGGER = LoggerFactory.getLogger(NotificationListener.class);

  @Autowired
  private IEventPublisher eventPublisher;

  @Override
  public void afterJob(final JobExecution jobExecution) {
    if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
      /** just triggered custom event for future scopes */
      eventPublisher.publishEvent(EventType.BATCH_END, "batch process end");
      LOGGER.info("Batch Job completed");
    } else if (jobExecution.getStatus() == BatchStatus.FAILED) {
      LOGGER.error("Batch Job Failed");
    }
  }
}
