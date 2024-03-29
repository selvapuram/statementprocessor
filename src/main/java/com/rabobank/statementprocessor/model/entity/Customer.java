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

package com.rabobank.statementprocessor.model.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

/**
 * The Class Customer.
 *
 * @author madhankumar
 */
@Entity(name = "customer")

/**
 * Gets the token.
 *
 * @return the token
 */
@Getter

/**
 * Sets the token.
 *
 * @param token the new token
 */
@Setter
@Table(name = "customer")
public class Customer {

  /** The id. */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  /** The user name. */
  private String username;

  /** The password. */
  private String password;

  /** The token. */
  private String token;
}
