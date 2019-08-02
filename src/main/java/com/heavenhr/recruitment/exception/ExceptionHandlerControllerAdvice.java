/*

Copyright 2019, madhankumar
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are
met:

 * Redistributions of source code must retain the above copyright
notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above
copyright notice, this list of conditions and the following disclaimer
in the documentation and/or other materials provided with the
distribution.
 * Neither the name of Google Inc. nor the names of its
contributors may be used to endorse or promote products derived from
this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,           
DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY           
THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

 */
package com.heavenhr.recruitment.exception;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.heavenhr.recruitment.constants.Constants;
import com.heavenhr.recruitment.model.common.ErrorModel;

/**
 * @author madhankumar
 *
 */
@ControllerAdvice
public class ExceptionHandlerControllerAdvice {

  @Autowired
  private MessageSource messageSource;

  @ExceptionHandler(ResourceNotFoundException.class)
  @ResponseStatus(value = HttpStatus.NOT_FOUND)
  public @ResponseBody ErrorModel handleResourceNotFound(final ResourceNotFoundException exception,
    final HttpServletRequest request) {

    ErrorModel error = ErrorModel.of(exception.getCode(),
      messageSource.getMessage(exception.getCode(), null, exception.getMessage(), Locale.ENGLISH));
    return error;
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public @ResponseBody ErrorModel handleDuplicateRecords(final DataIntegrityViolationException exception,
    final HttpServletRequest request) {

    ErrorModel error = ErrorModel.of(Constants.ERR_DUPLICATE_RECORD,
      messageSource.getMessage(Constants.ERR_DUPLICATE_RECORD, null, exception.getMessage(), Locale.ENGLISH));
    return error;
  }

  @ExceptionHandler(ConstraintViolationException.class)
  @ResponseStatus(value = HttpStatus.BAD_REQUEST)
  public @ResponseBody ErrorModel handleDuplicateRecords(final ConstraintViolationException exception,
    final HttpServletRequest request) {
    final ConstraintViolation<?> violation = exception.getConstraintViolations().iterator().next();
    String message = violation.getPropertyPath().toString() + "-" + violation.getMessage();
    ErrorModel error = ErrorModel.of(Constants.ERR_MANDATORY_FIELD, message);
    return error;
  }

  @ExceptionHandler(Exception.class)
  @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
  public @ResponseBody ErrorModel handleException(final Exception exception,
    final HttpServletRequest request) {
    ErrorModel error = ErrorModel.of(Constants.ERR_GENERIC_EXCEPTION, exception.toString());
    return error;
  }
}
