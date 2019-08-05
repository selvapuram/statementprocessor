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

package com.rabobank.statementprocessor.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.rabobank.statementprocessor.model.entity.Customer;

/**
 * @author madhankumar
 *
 */
@Repository
public interface CustomerRepository extends CrudRepository<Customer, Long> {

  @Query(value = "SELECT u FROM customer u where u.username = ?1 and u.password = ?2 ")
  Optional<Customer> login(String username, String password);

  Optional<Customer> findByToken(String token);
}
