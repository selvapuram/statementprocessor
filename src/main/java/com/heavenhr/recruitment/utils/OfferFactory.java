package com.heavenhr.recruitment.utils;

import java.util.List;

import com.heavenhr.recruitment.model.dto.Offer;
import com.heavenhr.recruitment.model.dto.OfferResponse;

/**
 * The Class OfferFactory.
 */
public class OfferFactory {

  /**
   * Instantiates a new OfferFactory.
   */
  protected OfferFactory() {

  }

  /**
   * Builds the response.
   *
   * @param o the o
   * @return the offer response
   */
  public static OfferResponse buildResponse(Offer o) {
    OfferResponse os = new OfferResponse();
    os.setOffer(o);
    return os;
  }

  public static OfferResponse buildResponse(Offer o, int count) {
    OfferResponse os = new OfferResponse();
    o.setNoOfApplications(count);
    os.setOffer(o);
    return os;
  }

  /**
   * Builds the response.
   *
   * @param l the l
   * @return the offer response
   */
  public static OfferResponse buildResponse(List<Offer> l) {
    OfferResponse os = new OfferResponse();
    os.setOffers(l);
    return os;
  }

  public static OfferResponse emptyResponse() {
    return new OfferResponse();
  }

  public static Offer buildRequest(long id) {
    Offer req = new Offer();
    req.setId(id);
    return req;
  }

  public static Offer buildEmptyRequest() {
    Offer req = new Offer();
    return req;
  }

}
