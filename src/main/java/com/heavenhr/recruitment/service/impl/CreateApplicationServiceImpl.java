/*******************************************************************************
 * Copyright 2018-2030 Skava. 
 * All rights reserved.The Skava system, including 
 * without limitation, all software and other elements
 * thereof, are owned or controlled exclusively by
 * Skava and protected by copyright, patent, and 
 * other laws. Use without permission is prohibited.
 * 
 *  For further information contact Skava at info@skava.com.
 ******************************************************************************/
package com.heavenhr.recruitment.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heavenhr.recruitment.model.dto.Application;
import com.heavenhr.recruitment.model.dto.ApplicationResponse;
import com.heavenhr.recruitment.process.IApplicationProcess;
import com.heavenhr.recruitment.service.CreateApplicationService;
import com.heavenhr.recruitment.utils.ApplicationFactory;

import lombok.NoArgsConstructor;

/**
 * The Class CreateApplicationServiceImpl.
 */
@Service
@NoArgsConstructor
public class CreateApplicationServiceImpl implements CreateApplicationService {

  /* The LOGGER for capturing the log */
  private static final Logger LOGGER = LoggerFactory.getLogger(CreateApplicationServiceImpl.class);

  @Autowired
  private IApplicationProcess applicationProcessor;

  /**
   * This method is used to create Application based on given Application.
   *
   * @param request 
   *        It contains the attributes required for the creation of Application.
   * @return It returns the <code>ApplicationResponse</code>.
   * @throws Exception 
   */
  @Override
  public ApplicationResponse process(Application request) throws Exception {
    LOGGER.debug("Create Application Service Process started");
    ApplicationResponse applicationAPIResponse = ApplicationFactory.emptyResponse();
    try {
      /* Build and send response back */
      applicationAPIResponse = applicationProcessor.createApplication(request);
      /* Catch runtime exception */
    } catch (RuntimeException e) {
      LOGGER.error("Error on application creation", e);
      throw e;
    } finally {
      /* Final log */
    }
    LOGGER.debug("Create Application Service Process end");
    return applicationAPIResponse;
  }
}
