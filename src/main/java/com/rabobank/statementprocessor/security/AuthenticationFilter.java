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

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * @author madhankumar
 *
 */
public class AuthenticationFilter extends AbstractAuthenticationProcessingFilter {

  AuthenticationFilter(final RequestMatcher requiresAuth) {
    super(requiresAuth);
  }

  @Override
  public Authentication attemptAuthentication(HttpServletRequest httpServletRequest,
    HttpServletResponse httpServletResponse) throws AuthenticationException, IOException, ServletException {

    String token = httpServletRequest.getHeader("x-auth-token");
    Authentication requestAuthentication = new UsernamePasswordAuthenticationToken(token, token);
    return getAuthenticationManager().authenticate(requestAuthentication);

  }

  @Override
  protected void successfulAuthentication(final HttpServletRequest request, final HttpServletResponse response,
    final FilterChain chain, final Authentication authResult) throws IOException, ServletException {
    SecurityContextHolder.getContext().setAuthentication(authResult);
    chain.doFilter(request, response);
  }
}
