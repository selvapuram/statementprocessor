package com.heavenhr.recruitment.process;

import com.heavenhr.recruitment.exception.ResourceNotFoundException;
import com.heavenhr.recruitment.model.dto.Offer;
import com.heavenhr.recruitment.model.dto.OfferResponse;

/**
 * The Interface IOfferProcess.
 */
public interface IOfferProcess {

  /**
   * Creates the offer.
   *
   * @param o the o
   * @return the offer response
   * @throws Exception 
   */
  OfferResponse createOffer(Offer o) throws Exception;

  /**
   * Find all offer.
   *
   * @return the offer response
   * @throws ResourceNotFoundException 
   */
  OfferResponse findAllOffer() throws ResourceNotFoundException;

  /**
   * Find offer by id.
   *
   * @param id the id
   * @return the offer response
   * @throws ResourceNotFoundException 
   */
  OfferResponse findOfferById(long id) throws ResourceNotFoundException;
}
