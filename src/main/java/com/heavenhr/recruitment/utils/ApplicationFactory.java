package com.heavenhr.recruitment.utils;

import java.util.List;

import com.heavenhr.recruitment.model.dto.Application;
import com.heavenhr.recruitment.model.dto.ApplicationResponse;
import com.heavenhr.recruitment.model.dto.ApplicationStatus;

/**
 * The Class ApplicationFactory.
 */
public class ApplicationFactory {

  /**
   * Instantiates a ApplicationFactory.
   */
  protected ApplicationFactory() {

  }

  /**
   * Builds the response.
   *
   * @param o the o
   * @return the application response
   */
  public static ApplicationResponse buildResponse(Application o) {
    ApplicationResponse os = new ApplicationResponse();
    os.setApplication(o);
    return os;
  }

  /**
   * Builds the response.
   *
   * @param l the l
   * @return the application response
   */
  public static ApplicationResponse buildResponse(List<Application> l) {
    ApplicationResponse os = new ApplicationResponse();
    os.setApplications(l);
    return os;
  }

  /**
   * Empty response.
   *
   * @return the application response
   */
  public static ApplicationResponse emptyResponse() {
    return new ApplicationResponse();
  }

  /**
   * Builds the request.
   *
   * @param id the id
   * @param offerId the offer id
   * @return the application
   */
  public static Application buildRequest(long id, long offerId, ApplicationStatus status) {
    Application req = new Application();
    req.setId(id);
    req.setOfferId(offerId);
    req.setApplicationStatus(status);
    return req;
  }

  /**
   * Builds the request.
   *
   * @param offerId the offer id
   * @return the application
   */
  public static Application buildRequest(long offerId) {
    Application req = new Application();
    req.setOfferId(offerId);
    return req;
  }

  /**
   * Builds the request.
   *
   * @param id the id
   * @param offerId the offer id
   * @return the application
   */
  public static Application buildRequest(long id, long offerId) {
    Application req = new Application();
    req.setId(id);
    req.setOfferId(offerId);
    return req;
  }

  /**
   * Builds the request.
   *
   * @param req the req
   * @param offerId the offer id
   * @return the application
   */
  public static Application buildRequest(Application req, long offerId) {
    if (req != null) {
      req.setOfferId(offerId);
      req.setApplicationStatus(ApplicationStatus.APPLIED);
    }
    return req;
  }

  /**
   * Builds the empty request.
   *
   * @return the application
   */
  public static Application buildEmptyRequest() {
    Application req = new Application();
    return req;
  }

}
