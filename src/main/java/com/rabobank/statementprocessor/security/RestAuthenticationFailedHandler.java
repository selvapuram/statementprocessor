package com.rabobank.statementprocessor.security;

import java.io.IOException;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.rabobank.statementprocessor.model.common.ErrorModel;
import com.rabobank.statementprocessor.utils.ObjectMapperUtil;

@Component
public class RestAuthenticationFailedHandler implements AuthenticationFailureHandler {

  public static final String ERR_403 = "1003";

  @Autowired
  private MessageSource messageSource;

  @Override
  public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
    AuthenticationException ex) throws IOException, ServletException {
    response.setStatus(HttpStatus.FORBIDDEN.value());
    response.setContentType(MediaType.APPLICATION_JSON_VALUE);

    ErrorModel errModel = ErrorModel.of(ex.getMessage(),
      messageSource.getMessage(ERR_403, null, HttpStatus.FORBIDDEN.getReasonPhrase(), Locale.ENGLISH));

    response.getOutputStream()
      .println(ObjectMapperUtil.getInstance().writeValueAsString(errModel));

  }
}
