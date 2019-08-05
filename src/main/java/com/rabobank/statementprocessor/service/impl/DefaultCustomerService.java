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

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.rabobank.statementprocessor.model.entity.Customer;
import com.rabobank.statementprocessor.repository.CustomerRepository;
import com.rabobank.statementprocessor.service.CustomerService;

/**
 * @author madhankumar
 *
 */
@Service("customerService")
public class DefaultCustomerService implements CustomerService {

  @Autowired
  CustomerRepository customerRepository;

  @Override
  public String login(String username, String password) {
    Optional<Customer> customer = customerRepository.login(username, password);
    if (customer.isPresent()) {
      String token = UUID.randomUUID().toString();
      Customer custom = customer.get();
      custom.setToken(token);
      customerRepository.save(custom);
      return token;
    }

    return "";
  }

  @Override
  public Optional<User> findByToken(String token) {
    Optional<Customer> customer = customerRepository.findByToken(token);
    if (customer.isPresent()) {
      Customer customer1 = customer.get();
      User user = new User(customer1.getUsername(), customer1.getPassword(), true, true, true, true,
        AuthorityUtils.createAuthorityList("USER"));
      return Optional.of(user);
    }
    return Optional.empty();
  }

  @Override
  public Customer findById(Long id) {
    Optional<Customer> customer = customerRepository.findById(id);
    return customer.orElse(null);
  }
}
