package com.rabobank.statementprocessor.process;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import com.rabobank.statementprocessor.model.entity.AccountEntity;

public class AccountProcessor implements ItemProcessor<AccountEntity, AccountEntity> {

  @Autowired
  private IValidationProcessor validationProcessor;

  @Override
  public AccountEntity process(AccountEntity accountEntity) throws Exception {
    return validationProcessor.validateAndReport(accountEntity);
  }
}
