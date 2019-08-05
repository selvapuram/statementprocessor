package com.rabobank.statementprocessor.process;

import java.util.List;

import com.rabobank.statementprocessor.model.dto.Account;
import com.rabobank.statementprocessor.model.entity.AccountEntity;

public interface IValidationProcessor {
  AccountEntity validateAndReport(AccountEntity ae) throws Exception;

  List<Account> findAllRecords() throws Exception;

  void deleteAllRecords() throws Exception;

  void collectUniqueRecordAndUpdate() throws Exception;

}
