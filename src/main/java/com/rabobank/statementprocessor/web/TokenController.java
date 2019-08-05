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

package com.rabobank.statementprocessor.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.rabobank.statementprocessor.service.CustomerService;

/**
 * @author madhankumar
 *
 */
@RestController
public class TokenController {

  @Autowired
  private CustomerService customerService;

  @PostMapping("/token")
  public String getToken(@RequestParam("username") final String username,
    @RequestParam("password") final String password) {
    String token = customerService.login(username, password);
    if (StringUtils.isEmpty(token)) {
      return "{\"token\" : \"no token found\"}";
    }
    return "{\"token\" : \"" + token + "\"}";
  }
}
