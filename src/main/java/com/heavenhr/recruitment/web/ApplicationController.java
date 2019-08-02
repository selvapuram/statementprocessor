package com.heavenhr.recruitment.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.heavenhr.recruitment.constants.OfferDocumentationConstants;
import com.heavenhr.recruitment.events.IEventPublisher;
import com.heavenhr.recruitment.model.dto.Application;
import com.heavenhr.recruitment.model.dto.ApplicationResponse;
import com.heavenhr.recruitment.model.dto.ApplicationStatus;
import com.heavenhr.recruitment.service.CreateApplicationService;
import com.heavenhr.recruitment.service.FindAllApplicationService;
import com.heavenhr.recruitment.service.FindApplicationService;
import com.heavenhr.recruitment.service.PatchUpdateApplicationService;
import com.heavenhr.recruitment.utils.ApplicationFactory;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Info;
import io.swagger.annotations.SwaggerDefinition;
import lombok.NoArgsConstructor;

/**
 * The Class ApplicationController.
 */
@SwaggerDefinition(info = @Info(title = "Application Entity", version = "v1.0", description = "Application Entity"))
@Api(value = "application", tags = "Applications")
@RestController
@RequestMapping("offers/{offerId}/applications")

/**
 * Instantiates a new Application controller.
 */
@NoArgsConstructor
public class ApplicationController {

  /** CreateApplicationService. */
  @Autowired
  private CreateApplicationService createApplicationService;

  /** FindAllApplicationService. */
  @Autowired
  private FindAllApplicationService findAllApplicationService;

  /** The FindApplicationService. */
  @Autowired
  private FindApplicationService findApplicationService;

  /** The patch update cart item service. */
  @Autowired
  private PatchUpdateApplicationService patchUpdateApplicationService;

  @Autowired
  private IEventPublisher eventPublisher;

  /**
   * Adding one or more applications to the offer.
   *
   * @param versionId        the version identifier
   * @param authToken        the authorization token
   * @param locale        the locale for processing
   * @param offerId        the offer identifier
   * @param application        the application to be added to the offer
   * @return It returns the <code>OfferResponse</code>.
   * @throws Exception 
   */
  @ApiOperation(value = "${swagger.createapplication.ApiOperation.value}",
    notes = "${swagger.createapplication.ApiOperation.notes}", response = Application.class,
    nickname = "createApplicationByOfferId")
  @ResponseStatus(HttpStatus.CREATED)
  @ApiResponses(value = {@ApiResponse(code = 201, message = OfferDocumentationConstants.RESPONSECODE_201),
    @ApiResponse(code = 400, message = OfferDocumentationConstants.RESPONSECODE_400),
    @ApiResponse(code = 401, message = OfferDocumentationConstants.RESPONSECODE_401),
    @ApiResponse(code = 403, message = OfferDocumentationConstants.RESPONSECODE_403),
    @ApiResponse(code = 404, message = OfferDocumentationConstants.RESPONSECODE_404),
    @ApiResponse(code = 409, message = OfferDocumentationConstants.RESPONSECODE_409),
    @ApiResponse(code = 500, message = OfferDocumentationConstants.RESPONSECODE_500)})
  @PostMapping(value = "")
  public ResponseEntity<ApplicationResponse> createApplication(
    @ApiParam(value = "${swagger.common.version.ApiParam.value}", required = false, example = "8.0",
      allowEmptyValue = true) @RequestHeader(value = "x-version-id", required = false) String versionId,
    @ApiParam(value = OfferDocumentationConstants.AUTH_TOKEN_DESC, required = false, example = "xxxx.yyyy.zzzz",
      allowEmptyValue = false) @RequestHeader(value = "x-auth-token", required = false) String authToken,
    @ApiParam(value = "${swagger.common.locale.ApiParam.value}", required = false, example = "en_US") @RequestParam(
      value = "locale",
      required = false, defaultValue = "en_US") String locale,
    @ApiParam(value = "${swagger.common.offerId.ApiParam.value}", required = true, example = "1",
      allowEmptyValue = false) @PathVariable(value = "offerId", required = true) long offerId,
    @ApiParam(value = "${swagger.createapplication.application.ApiParam.value}",
      required = true) @RequestBody(required = true) @Valid Application application)
    throws Exception {

    Application request = ApplicationFactory.buildRequest(application, offerId);
    ApplicationResponse applicationResponse = createApplicationService.process(request);
    eventPublisher.publishEvent(applicationResponse.getApplication(), ApplicationStatus.APPLIED,
      "Applied Event triggered");
    return new ResponseEntity<>(applicationResponse, HttpStatus.CREATED);
  }

  /**
   * This method is used to find all Applications based on given OfferId.
   * 
   * @param offerId the offer identifier
   * @param authToken the authorization token
   * @param locale the locale for processing
   * @param page This parameter will be available for all the services that supports pagination.
   *  This parameter is used to mention the starting index of the items which going to responded by the API.
   * @param size This parameter will be available for all the services that supports pagination.
   *  This parameter is used to mention the number of maximum items that needs to be responded for the request.
   * @return It returns the <code>ApplicationResponse</code>.
   * @throws Exception 
   */
  @ApiOperation(value = "${swagger.findallapplications.ApiOperation.value}",
    notes = "${swagger.findallapplications.ApiOperation.notes}", response = ApplicationResponse.class,
    nickname = "getApplicationsByOfferId")
  @ResponseStatus(HttpStatus.OK)
  @ApiResponses(value = {@ApiResponse(code = 200, message = OfferDocumentationConstants.RESPONSECODE_200),
    @ApiResponse(code = 400, message = OfferDocumentationConstants.RESPONSECODE_400),
    @ApiResponse(code = 401, message = OfferDocumentationConstants.RESPONSECODE_401),
    @ApiResponse(code = 403, message = OfferDocumentationConstants.RESPONSECODE_403),
    @ApiResponse(code = 404, message = OfferDocumentationConstants.RESPONSECODE_404),
    @ApiResponse(code = 500, message = OfferDocumentationConstants.RESPONSECODE_500)})
  @GetMapping(value = "")
  public ResponseEntity<ApplicationResponse> findAllApplications(
    @ApiParam(value = "${swagger.common.offerId.ApiParam.value}", required = true, example = "1",
      allowEmptyValue = false) @PathVariable(value = "offerId", required = true) long offerId,
    @ApiParam(value = OfferDocumentationConstants.AUTH_TOKEN_DESC, required = false, example = "xxxx.yyyy.zzzz",
      allowEmptyValue = false) @RequestHeader(value = "x-auth-token", required = false) String authToken,
    @ApiParam(value = "${swagger.common.locale.ApiParam.value}", required = false, example = "en_US") @RequestParam(
      value = "locale",
      required = false, defaultValue = "en_US") String locale,
    @ApiParam(value = OfferDocumentationConstants.PAGE_DESC, required = false) @RequestParam(value = "page",
      required = true, defaultValue = "1") int page,
    @ApiParam(value = OfferDocumentationConstants.PAGE_SIZE_DESC,
      defaultValue = "50",
      required = true) @RequestParam(value = "size", required = false, defaultValue = "50") int size)
    throws Exception {

    Application request = ApplicationFactory.buildRequest(offerId);
    ApplicationResponse applicationResponse = findAllApplicationService.process(request);
    return new ResponseEntity<>(applicationResponse, HttpStatus.OK);
  }

  /**
   * Find application by OfferId.
   *
   * @param offerId the Offer id
   * @param applicationId the Application id
   * @param versionId the version id
   * @param authToken the auth token
   * @param locale the locale
   * @return the response entity
   * @throws Exception 
   */
  @ApiOperation(value = "${swagger.findapplication.ApiOperation.value}",
    notes = "${swagger.findapplication.ApiOperation.notes}", response = ApplicationResponse.class,
    nickname = "findApplicationByIdAndOfferId")
  @ResponseStatus(HttpStatus.OK)
  @ApiResponses(value = {@ApiResponse(code = 200, message = OfferDocumentationConstants.RESPONSECODE_200),
    @ApiResponse(code = 400, message = OfferDocumentationConstants.RESPONSECODE_400),
    @ApiResponse(code = 401, message = OfferDocumentationConstants.RESPONSECODE_401),
    @ApiResponse(code = 403, message = OfferDocumentationConstants.RESPONSECODE_403),
    @ApiResponse(code = 404, message = OfferDocumentationConstants.RESPONSECODE_404),
    @ApiResponse(code = 500, message = OfferDocumentationConstants.RESPONSECODE_500)})
  @GetMapping(value = "/{applicationId}")
  public ResponseEntity<ApplicationResponse> findApplicationByIdAndOfferId(
    @ApiParam(value = "${swagger.common.offerId.ApiParam.value}", required = true, example = "1",
      allowEmptyValue = false) @PathVariable(
        value = "offerId", required = true) long offerId,
    @ApiParam(value = "${swagger.common.applicationId.ApiParam.value}", required = true) @PathVariable(
      value = "applicationId",
      required = true) long id,
    @ApiParam(value = "${swagger.common.version.ApiParam.value}", required = false) @RequestHeader(
      value = "x-version-id",
      required = false) String versionId,
    @ApiParam(value = OfferDocumentationConstants.AUTH_TOKEN_DESC, required = false, example = "xxxx.yyyy.zzzz",
      allowEmptyValue = false) @RequestHeader(value = "x-auth-token", required = false) String authToken,
    @ApiParam(value = "${swagger.common.locale.ApiParam.value}", required = false, example = "en_US") @RequestParam(
      value = "locale",
      required = false, defaultValue = "en_US") String locale)
    throws Exception {

    Application request = ApplicationFactory.buildRequest(id, offerId);
    ApplicationResponse apiResponse = findApplicationService.process(request);
    return new ResponseEntity<>(apiResponse, HttpStatus.OK);
  }

  /**
   * Patch update application.
   *
   * @param offerId the Offer id
   * @param applicationId the Application id
   * @param versionId the version id
   * @param authToken the auth token
   * @param locale the locale
   * @param applicationstatus the ApplicationStatus
   * @return the response entity
   * @throws Exception 
   */
  @ApiOperation(value = "${swagger.patchupdateapplication.ApiOperation.value}",
    notes = "${swagger.patchupdateapplication.ApiOperation.notes}", response = ApplicationResponse.class,
    nickname = "updateApplicationByIdAndOfferId")
  @ResponseStatus(HttpStatus.OK)
  @ApiResponses(value = {@ApiResponse(code = 200, message = OfferDocumentationConstants.RESPONSECODE_200),
    @ApiResponse(code = 400, message = OfferDocumentationConstants.RESPONSECODE_400),
    @ApiResponse(code = 401, message = OfferDocumentationConstants.RESPONSECODE_401),
    @ApiResponse(code = 403, message = OfferDocumentationConstants.RESPONSECODE_403),
    @ApiResponse(code = 404, message = OfferDocumentationConstants.RESPONSECODE_404),
    @ApiResponse(code = 500, message = OfferDocumentationConstants.RESPONSECODE_500)})
  @PatchMapping(value = "/{applicationId}")
  public ResponseEntity<ApplicationResponse> patchUpdateApplication(
    @ApiParam(value = "${swagger.common.offerId.ApiParam.value}", required = true, example = "1",
      allowEmptyValue = false) @PathVariable(
        value = "offerId", required = true) long offerId,
    @ApiParam(value = "${swagger.common.applicationId.ApiParam.value}", required = true) @PathVariable(
      value = "applicationId",
      required = true) long id,
    @ApiParam(value = "${swagger.common.version.ApiParam.value}", required = true) @RequestHeader(
      value = "x-version-id",
      required = false) String versionId,
    @ApiParam(value = OfferDocumentationConstants.AUTH_TOKEN_DESC, required = false, example = "xxxx.yyyy.zzzz",
      allowEmptyValue = false) @RequestHeader(value = "x-auth-token", required = false) String authToken,
    @ApiParam(value = "${swagger.common.locale.ApiParam.value}", required = false, example = "en_US") @RequestParam(
      value = "locale",
      required = false, defaultValue = "en_US") String locale,
    @ApiParam(value = "${swagger.patchupdateapplication.applicationStatus.ApiParam.value}", defaultValue = "INVITED",
      allowableValues = "APPLIED, INVITED, REJECTED, HIRED") @Valid @RequestParam(value = "applicationStatus",
        required = true,
        defaultValue = "APPLIED") ApplicationStatus applicationStatus)
    throws Exception {

    Application request = ApplicationFactory.buildRequest(id, offerId, applicationStatus);
    ApplicationResponse apiResponse = patchUpdateApplicationService.process(request);
    eventPublisher.publishEvent(apiResponse.getApplication(), applicationStatus,
      "Applied Event triggered");
    return new ResponseEntity<>(apiResponse, HttpStatus.OK);
  }

}
