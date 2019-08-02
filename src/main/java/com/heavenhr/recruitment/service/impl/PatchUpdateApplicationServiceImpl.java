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

import com.heavenhr.recruitment.exception.ResourceNotFoundException;
import com.heavenhr.recruitment.model.dto.Application;
import com.heavenhr.recruitment.model.dto.ApplicationResponse;
import com.heavenhr.recruitment.process.IApplicationProcess;
import com.heavenhr.recruitment.service.PatchUpdateApplicationService;
import com.heavenhr.recruitment.utils.ApplicationFactory;

import lombok.NoArgsConstructor;

/**
 * The Class PatchUpdateApplicationServiceImpl.
 */
@Service
@NoArgsConstructor
public class PatchUpdateApplicationServiceImpl implements PatchUpdateApplicationService {

  /* The LOGGER for capturing the log */
  private static final Logger LOGGER = LoggerFactory.getLogger(PatchUpdateApplicationServiceImpl.class);

  @Autowired
  private IApplicationProcess applicationProcessor;

  /**
    * This method is used to load single application based on given Application identifier.
    * @param request 
    *        It contains the attributes required for the load of Application.
    * @return It returns the <code>ApplicationAPIResponse</code>.
   * @throws Exception 
    */
  @Override
  public ApplicationResponse process(Application request) throws Exception {
    LOGGER.debug("TODO");
    ApplicationResponse patchUpdateApplicationAPIResponse = ApplicationFactory.emptyResponse();

    try {
      patchUpdateApplicationAPIResponse = applicationProcessor.patchUpdateApplicationById(request.getId(),
        request.getOfferId(), request.getApplicationStatus());
      /* Catch runtime exception */
    } catch (RuntimeException | ResourceNotFoundException e) {
      LOGGER.error("Error on patch update", e);
      throw e;
    } finally {
      /* Final log */
    }
    return patchUpdateApplicationAPIResponse;
  }

}
