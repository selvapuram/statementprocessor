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
import com.heavenhr.recruitment.service.FindApplicationService;
import com.heavenhr.recruitment.utils.ApplicationFactory;

import lombok.NoArgsConstructor;

/**
 * The Class FindApplicationServiceImpl.
 */
@Service
@NoArgsConstructor
public class FindApplicationServiceImpl implements FindApplicationService {

  /* The LOGGER for capturing the log */
  private static final Logger LOGGER = LoggerFactory.getLogger(FindApplicationServiceImpl.class);

  @Autowired
  private IApplicationProcess applicationProcessor;

  /**
    * This method is used to load single application based on given Application identifier.
    * @param request 
    *        It contains the attributes required for the load of Application.
    * @return It returns the <code>ApplicationAPIResponse</code>.
    */
  @Override
  public ApplicationResponse process(Application request) throws Exception {
    LOGGER.debug("TODO");
    ApplicationResponse findApplicationAPIResponse = ApplicationFactory.emptyResponse();

    try {
      // TODO: validation
      findApplicationAPIResponse = applicationProcessor.findApplicationById(request.getId(), request.getOfferId());
      /* Catch runtime exception */
    } catch (RuntimeException e) {
      LOGGER.error("Error on finding application", e);
      throw e;
    } finally {
      /* Final log */
    }
    return findApplicationAPIResponse;
  }

}
