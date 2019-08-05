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
package com.rabobank.statementprocessor.utils;

import java.util.List;

import org.springframework.batch.item.file.transform.FieldSet;

import com.rabobank.statementprocessor.model.dto.Account;
import com.rabobank.statementprocessor.model.dto.Records;
import com.rabobank.statementprocessor.model.entity.AccountEntity;

/**
 * The Class AccountFactory.
 */
public class AccountFactory {

  /**
   * Instantiates a AccountFactory.
   */
  protected AccountFactory() {

  }

  public static AccountEntity buildEntity(FieldSet fieldSet) {
    final AccountEntity account = new AccountEntity();
    account.setReference(fieldSet.readLong("reference"));
    account.setAccountNumber(fieldSet.readString("accountNumber"));
    account.setDescription(fieldSet.readString("description"));
    account.setStartBalance(fieldSet.readDouble("startBalance"));
    account.setMutation(fieldSet.readString("mutation"));
    account.setEndBalance(fieldSet.readDouble("endBalance"));
    return account;
  }

  public static Records buildRecords(List<Account> records) {
    Records rs = new Records();
    rs.setCustomerRecords(records);
    return rs;
  }
}
