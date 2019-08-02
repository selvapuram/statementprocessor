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
import com.heavenhr.recruitment.model.dto.Offer;
import com.heavenhr.recruitment.model.dto.OfferResponse;
import com.heavenhr.recruitment.process.IOfferProcess;
import com.heavenhr.recruitment.service.FindOfferService;
import com.heavenhr.recruitment.utils.OfferFactory;

import lombok.NoArgsConstructor;

/**
 * The Class FindOfferServiceImpl.
 */
@Service
@NoArgsConstructor
public class FindOfferServiceImpl implements FindOfferService {

  /* The LOGGER for capturing the log */
  private static final Logger LOGGER = LoggerFactory.getLogger(FindOfferServiceImpl.class);

  @Autowired
  private IOfferProcess offerProcessor;

  /**
    * This method is used to load single offer based on given Offer identifier.
    * @param request 
    *        It contains the attributes required for the load of Offer.
    * @return It returns the <code>OfferAPIResponse</code>.
   * @throws Exception 
    */
  @Override
  public OfferResponse process(Offer request) throws Exception {
    LOGGER.debug("TODO");
    OfferResponse findOfferAPIResponse = OfferFactory.emptyResponse();

    try {
      findOfferAPIResponse = offerProcessor.findOfferById(request.getId());
      /* Catch runtime exception */
    } catch (RuntimeException | ResourceNotFoundException e) {
      LOGGER.error("Error on finding offer", e);
      throw e;
    } finally {
      /* Final log */
    }
    return findOfferAPIResponse;
  }

}
