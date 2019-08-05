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
package com.rabobank.statementprocessor.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Account {

  /** The reference. */
  private long reference;

  /** The mutation. */
  private String mutation;

  /** The end balance. */
  private double endBalance;

  /** The description. */
  private String description;

  /** The account number. */
  private String accountNumber;

  /** The start balance. */
  private double startBalance;
}
