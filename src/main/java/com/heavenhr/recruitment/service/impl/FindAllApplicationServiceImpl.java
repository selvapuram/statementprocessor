package com.heavenhr.recruitment.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.heavenhr.recruitment.constants.Constants;
import com.heavenhr.recruitment.model.dto.Application;
import com.heavenhr.recruitment.model.dto.ApplicationResponse;
import com.heavenhr.recruitment.process.IApplicationProcess;
import com.heavenhr.recruitment.service.FindAllApplicationService;
import com.heavenhr.recruitment.utils.ApplicationFactory;

import lombok.NoArgsConstructor;

/**
 * The Class FindAllApplicationServiceImpl.
 */
@Service
@NoArgsConstructor
public class FindAllApplicationServiceImpl implements FindAllApplicationService {

  /* The LOGGER for capturing the log */
  private static final Logger LOGGER = LoggerFactory.getLogger(FindAllApplicationServiceImpl.class);

  @Autowired
  private IApplicationProcess applicationProcessor;

  /**
   * This method is used to load all Applications.
   * It checks whether the Application is present in given db or not,
   * if Application doesn't exists in db, it will return an error message/ error code.
   * if item not present in given Application, it will return error message according to that.
   * @param request 
   *        It contains the attributes required for the load all applications.
   * @return It returns the <code>ApplicationResponse</code>.
   */
  @Override
  public ApplicationResponse process(Application request) throws Exception {
    LOGGER.debug("TODO");
    ApplicationResponse findAllApplicationAPIResponse = ApplicationFactory.emptyResponse();

    try {
      // TODO: Validation

      findAllApplicationAPIResponse = applicationProcessor.findAllApplication(request.getOfferId(),
        Constants.PAGE_DEFAULT, Constants.PAGE_DEFAULT_SIZE);
      /* Catch runtime exception */
    } catch (RuntimeException e) {
      LOGGER.error("Error on finding application", e);
      throw e;
    } finally {
      /* Final log */
    }
    return findAllApplicationAPIResponse;
  }

}
