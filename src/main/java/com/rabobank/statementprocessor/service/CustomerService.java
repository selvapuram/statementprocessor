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
package com.rabobank.statementprocessor.service;

import java.util.Optional;

import org.springframework.security.core.userdetails.User;

import com.rabobank.statementprocessor.model.entity.Customer;

/**
 * @author madhankumar
 *
 */
public interface CustomerService {

  String login(String username, String password);

  Optional<User> findByToken(String token);

  Customer findById(Long id);
}
