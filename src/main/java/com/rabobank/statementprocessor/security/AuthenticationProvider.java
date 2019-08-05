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

package com.rabobank.statementprocessor.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.rabobank.statementprocessor.service.CustomerService;

/**
 * @author madhankumar
 *
 */
@Component
public class AuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

  @Autowired
  CustomerService customerService;

  @Override
  protected void additionalAuthenticationChecks(UserDetails userDetails,
    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {
    //
  }

  @Override
  protected UserDetails retrieveUser(String userName,
    UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

    Object token = usernamePasswordAuthenticationToken.getCredentials();
    return Optional
      .ofNullable(token)
      .map(String::valueOf)
      .flatMap(customerService::findByToken)
      .orElseThrow(() -> new UsernameNotFoundException("Cannot find user with authentication token=" + token));
  }
}
