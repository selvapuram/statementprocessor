package com.heavenhr.recruitment.process;

import com.heavenhr.recruitment.exception.ResourceNotFoundException;
import com.heavenhr.recruitment.model.dto.Application;
import com.heavenhr.recruitment.model.dto.ApplicationResponse;
import com.heavenhr.recruitment.model.dto.ApplicationStatus;

/**
 * The Interface IApplicationProcess.
 */
public interface IApplicationProcess {

  /**
   * Creates the application.
   *
   * @param o the o
   * @return the application response
   */
  ApplicationResponse createApplication(Application o) throws Exception;

  /**
   * Find all application.
   *
   * @return the application response
   * @throws ResourceNotFoundException 
   */
  ApplicationResponse findAllApplication(long offerId, int page, int size) throws ResourceNotFoundException;

  /**
   * Find application by id.
   *
   * @param id the id
   * @return the application response
   */
  ApplicationResponse findApplicationById(long id, long offerId) throws ResourceNotFoundException;

  /**
   * Patch update application by id.
   *
   * @param id the id
   * @param offerId the offer id
   * @param status the status
   * @return the application response
   */
  ApplicationResponse patchUpdateApplicationById(long id, long offerId, ApplicationStatus status)
    throws ResourceNotFoundException;
}
