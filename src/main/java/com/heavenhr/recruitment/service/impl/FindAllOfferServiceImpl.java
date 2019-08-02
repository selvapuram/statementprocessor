package com.heavenhr.recruitment.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heavenhr.recruitment.model.dto.Offer;
import com.heavenhr.recruitment.model.dto.OfferResponse;
import com.heavenhr.recruitment.process.IOfferProcess;
import com.heavenhr.recruitment.service.FindAllOfferService;
import com.heavenhr.recruitment.utils.OfferFactory;

import lombok.NoArgsConstructor;

/**
 * The Class FindAllOfferServiceImpl.
 */
@Service
@NoArgsConstructor
public class FindAllOfferServiceImpl implements FindAllOfferService {

  /* The LOGGER for capturing the log */
  private static final Logger LOGGER = LoggerFactory.getLogger(FindAllOfferServiceImpl.class);

  @Autowired
  private IOfferProcess offerProcessor;

  /**
   * This method is used to load all Offers.
   * It checks whether the Offer is present in given db or not,
   * if Offer doesn't exists in db, it will return an error message/ error code.
   * if item not present in given Offer, it will return error message according to that.
   * @param request 
   *        It contains the attributes required for the load all offers.
   * @return It returns the <code>OfferResponse</code>.
   * @throws Exception 
   */
  @Override
  public OfferResponse process(Offer request) throws Exception {
    LOGGER.debug("TODO");
    OfferResponse findAllOfferAPIResponse = OfferFactory.emptyResponse();

    try {
      findAllOfferAPIResponse = offerProcessor.findAllOffer();
      /* Catch runtime exception */
    } catch (RuntimeException e) {
      LOGGER.error("Error on finding offer", e);
      throw e;
    } finally {
      /* Final log */
    }
    return findAllOfferAPIResponse;
  }

}
