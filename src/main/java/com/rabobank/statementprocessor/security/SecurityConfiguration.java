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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.OrRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

/**
 * @author madhankumar
 *
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

  private static final RequestMatcher PROTECTED_URLS = new OrRequestMatcher(
    new AntPathRequestMatcher("/customers/statement/**"));

  AuthenticationProvider provider;

  @Autowired
  RestAuthenticationFailedHandler restAuthenticationFailedHandler;

  public SecurityConfiguration(final AuthenticationProvider authenticationProvider) {
    super();
    this.provider = authenticationProvider;
  }

  @Override
  protected void configure(final AuthenticationManagerBuilder auth) {
    auth.authenticationProvider(provider);
  }

  @Override
  public void configure(final WebSecurity webSecurity) {
    webSecurity.ignoring().antMatchers("/token/**", "/ping/**");
  }

  @Override
  public void configure(HttpSecurity http) throws Exception {
    http.sessionManagement()
      .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
      .and()
      .headers().frameOptions().sameOrigin()
      .and()
      .exceptionHandling()
      .and()
      .authenticationProvider(provider)
      .addFilterBefore(authenticationFilter(), AnonymousAuthenticationFilter.class)
      .authorizeRequests()
      .requestMatchers(PROTECTED_URLS)
      .authenticated()
      .and()
      .csrf().disable()
      .formLogin().disable()
      .httpBasic().disable()
      .logout().disable();

  }

  @Bean
  AuthenticationFilter authenticationFilter() throws Exception {
    final AuthenticationFilter filter = new AuthenticationFilter(PROTECTED_URLS);
    filter.setAuthenticationManager(authenticationManager());
    filter.setAuthenticationFailureHandler(restAuthenticationFailedHandler);
    // filter.setAuthenticationSuccessHandler(successHandler());
    return filter;
  }

  @Bean
  AuthenticationEntryPoint forbiddenEntryPoint() {
    return new HttpStatusEntryPoint(HttpStatus.FORBIDDEN);
  }
}
