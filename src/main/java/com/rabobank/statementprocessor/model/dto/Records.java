package com.rabobank.statementprocessor.model.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class Records.
 */
public class Records {

  /**
   * Gets the customer records.
   *
   * @return the customer records
   */
  @Getter

  /**
   * Sets the customer records.
   *
   * @param customerRecords the new customer records
   */
  @Setter
  private List<Account> customerRecords;
}
