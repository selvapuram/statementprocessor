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
package com.rabobank.statementprocessor.exception;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.rabobank.statementprocessor.constants.Constants;
import com.rabobank.statementprocessor.model.common.ErrorModel;

/**
 * @author madhankumar
 *
 */
@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

  @Autowired
  private MessageSource messageSource;

  @ExceptionHandler(CustomValidateException.class)
  @ResponseStatus(value = HttpStatus.UNPROCESSABLE_ENTITY)
  public @ResponseBody ErrorModel handleValidationException(final CustomValidateException exception,
    final HttpServletRequest request) {

    return ErrorModel.of(exception.getCode(),
      messageSource.getMessage(exception.getCode(), null, exception.getMessage(), Locale.ENGLISH));
  }

  @ExceptionHandler(RecordNotFoundException.class)
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  public @ResponseBody ErrorModel handleRecordNotFoundException(final CustomValidateException exception,
    final HttpServletRequest request) {

    return ErrorModel.of(exception.getCode(),
      messageSource.getMessage(exception.getCode(), null, exception.getMessage(), Locale.ENGLISH));
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public @ResponseBody ErrorModel handleException(final Exception exception,
    final HttpServletRequest request) {
    return ErrorModel.of(Constants.ERR_GENERIC_EXCEPTION, exception.toString());
  }
}
