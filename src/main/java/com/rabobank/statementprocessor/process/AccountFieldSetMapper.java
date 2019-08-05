package com.rabobank.statementprocessor.process;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;

import com.rabobank.statementprocessor.model.entity.AccountEntity;
import com.rabobank.statementprocessor.utils.AccountFactory;

@Component
public class AccountFieldSetMapper implements FieldSetMapper<AccountEntity> {

  @Override
  public AccountEntity mapFieldSet(FieldSet fieldSet) {
    return AccountFactory.buildEntity(fieldSet);
  }
}
