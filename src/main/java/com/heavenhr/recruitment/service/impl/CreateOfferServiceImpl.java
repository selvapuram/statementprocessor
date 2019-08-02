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

import com.heavenhr.recruitment.model.dto.Offer;
import com.heavenhr.recruitment.model.dto.OfferResponse;
import com.heavenhr.recruitment.process.IOfferProcess;
import com.heavenhr.recruitment.service.CreateOfferService;
import com.heavenhr.recruitment.utils.OfferFactory;

import lombok.NoArgsConstructor;

/**
 * The Class CreateOfferServiceImpl.
 */
@Service
@NoArgsConstructor
public class CreateOfferServiceImpl implements CreateOfferService {

  /* The LOGGER for capturing the log */
  private static final Logger LOGGER = LoggerFactory.getLogger(CreateOfferServiceImpl.class);

  @Autowired
  private IOfferProcess offerProcessor;

  /**
   * This method is used to create Offer based on given Offer.
   *
   * @param request 
   *        It contains the attributes required for the creation of Offer.
   * @return It returns the <code>OfferResponse</code>.
   * @throws Exception 
   */
  @Override
  public OfferResponse process(Offer request) throws Exception {
    LOGGER.debug("Create Offer Service Process started");
    OfferResponse offerAPIResponse = OfferFactory.emptyResponse();
    try {
      /* Build and send response back */
      offerAPIResponse = offerProcessor.createOffer(request);
      /* Catch runtime exception */
    } catch (RuntimeException e) {
      LOGGER.error("Error on offer creation", e);
      throw e;
    } finally {
      /* Final log */
    }
    LOGGER.debug("Create Offer Service Process end");
    return offerAPIResponse;
  }
}
