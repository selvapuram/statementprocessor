package com.heavenhr.recruitment.web;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.heavenhr.recruitment.constants.OfferDocumentationConstants;
import com.heavenhr.recruitment.model.dto.Offer;
import com.heavenhr.recruitment.model.dto.OfferResponse;
import com.heavenhr.recruitment.service.CreateOfferService;
import com.heavenhr.recruitment.service.FindAllOfferService;
import com.heavenhr.recruitment.service.FindOfferService;
import com.heavenhr.recruitment.utils.OfferFactory;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Info;
import io.swagger.annotations.SwaggerDefinition;
import lombok.NoArgsConstructor;

/**
 * The Class OfferController.
 * This class controls the controls the request for the CRUD operations on the Offer Entity and all
 * its child entities.
 * 
 * @author madhankumar
 * @version 1.0.0
 */
@SwaggerDefinition(info = @Info(title = "Offer Entity", version = "v1.0", description = "Offer Entity"))
@Api(value = "offers", tags = "Offers")
@RestController
@RequestMapping("/offers")
@NoArgsConstructor
public class OfferController {

  /* The LOGGER */
  private static final Logger LOGGER = LoggerFactory.getLogger(OfferController.class);

  /* The create offer service. */
  @Autowired
  private CreateOfferService createCartService;

  /* The findOfferService. */
  @Autowired
  private FindOfferService findOfferService;

  /* The find all offer service. */
  @Autowired
  private FindAllOfferService findAllOfferService;

  /**
   * This method is used to create an offer based on given OfferRequest.
   *
   * @param versionId the version identifier
   * @param authToken the authorization token
   * @param locale the locale
   * @param offer the offer
   * @return It returns the <code>OfferResponse</code>.
   * @throws Exception 
   */
  @ApiOperation(value = "${swagger.createOffer.ApiOperation.value}",
    notes = "${swagger.createOffer.ApiOperation.notes}", response = OfferResponse.class,
    nickname = "createOffer")
  @ResponseStatus(HttpStatus.CREATED)
  @ApiResponses(value = {@ApiResponse(code = 201, message = OfferDocumentationConstants.RESPONSECODE_201),
    @ApiResponse(code = 400, message = OfferDocumentationConstants.RESPONSECODE_400),
    @ApiResponse(code = 401, message = OfferDocumentationConstants.RESPONSECODE_401),
    @ApiResponse(code = 403, message = OfferDocumentationConstants.RESPONSECODE_403),
    @ApiResponse(code = 404, message = OfferDocumentationConstants.RESPONSECODE_404),
    @ApiResponse(code = 409, message = OfferDocumentationConstants.RESPONSECODE_409),
    @ApiResponse(code = 500, message = OfferDocumentationConstants.RESPONSECODE_500)})
  @PostMapping(value = "")
  public ResponseEntity<OfferResponse> createOffer(
    @ApiParam(value = "${swagger.common.version.ApiParam.value}", example = "8.0",
      allowEmptyValue = true) @RequestHeader(value = "x-version-id", required = false) String versionId,
    @ApiParam(value = OfferDocumentationConstants.AUTH_TOKEN_DESC, required = false, example = "xxxx.yyyy.zzzz",
      allowEmptyValue = false) @RequestHeader(value = "x-auth-token", required = false) String authToken,
    @ApiParam(value = "${swagger.common.locale.ApiParam.value}", required = false, example = "en_US") @RequestParam(
      value = "locale",
      required = false, defaultValue = "en_US") String locale,
    @ApiParam(value = "${swagger.createOffer.offer.ApiParam.value}",
      required = true) @Valid @RequestBody Offer offer)
    throws Exception {
    LOGGER.debug("OfferController.createOffer");
    OfferResponse offerAPIResponse = createCartService.process(offer);
    return new ResponseEntity<>(offerAPIResponse, HttpStatus.CREATED);
  }

  /**
   * This method is used to load single Offer based on given offerId.
   *
   * @param versionId the version id
   * @param authToken the auth token
   * @param locale the locale
   * @param id It refers to load of Offer.
   * @return It returns the <code>OfferResponse</code>.
   * @throws Exception 
   */
  @ApiOperation(value = "${swagger.getoffer.ApiOperation.value}",
    notes = "${swagger.getoffer.ApiOperation.notes}", response = OfferResponse.class,
    nickname = "getOfferById")
  @ResponseStatus(HttpStatus.OK)
  @ApiResponses(value = {@ApiResponse(code = 200, message = OfferDocumentationConstants.RESPONSECODE_200),
    @ApiResponse(code = 400, message = OfferDocumentationConstants.RESPONSECODE_400),
    @ApiResponse(code = 401, message = OfferDocumentationConstants.RESPONSECODE_401),
    @ApiResponse(code = 403, message = OfferDocumentationConstants.RESPONSECODE_403),
    @ApiResponse(code = 404, message = OfferDocumentationConstants.RESPONSECODE_404),
    @ApiResponse(code = 500, message = OfferDocumentationConstants.RESPONSECODE_500)})
  @GetMapping(value = "/{offerId}")
  public ResponseEntity<OfferResponse> getOfferById(
    @ApiParam(value = "${swagger.common.version.ApiParam.value}", example = "8.0",
      allowEmptyValue = false) @RequestHeader(value = "x-version-id", required = false) String versionId,
    @ApiParam(value = OfferDocumentationConstants.AUTH_TOKEN_DESC, required = false, example = "xxxx.yyyy.zzzz",
      allowEmptyValue = false) @RequestHeader(value = "x-auth-token", required = false) String authToken,
    @ApiParam(value = "${swagger.common.locale.ApiParam.value}", required = false,
      example = "en_US") @RequestParam(
        value = "locale",
        required = false, defaultValue = "en_US") String locale,
    @ApiParam(value = "${swagger.getoffer.offerId.ApiParam.value}", required = true, example = "1",
      allowEmptyValue = false) @PathVariable(value = "offerId", required = true) long id)
    throws Exception {
    Offer request = OfferFactory.buildRequest(id);
    OfferResponse offerAPIResponse = findOfferService.process(request);
    return new ResponseEntity<>(offerAPIResponse, HttpStatus.OK);
  }

  /**
   * Find all offers.
   *
   * @param authToken the auth token used to validate the user information
   * @param locale the locale
   * @param page the page
   * @param size the size
   * @return the response entity
   * @throws Exception 
   */
  @ApiOperation(value = "${swagger.findalloffers.ApiOperation.value}",
    notes = "${swagger.findalloffers.ApiOperation.notes}", response = OfferResponse.class,
    nickname = "getOffers")
  @ResponseStatus(HttpStatus.OK)
  @ApiResponses(value = {@ApiResponse(code = 200, message = OfferDocumentationConstants.RESPONSECODE_200),
    @ApiResponse(code = 400, message = OfferDocumentationConstants.RESPONSECODE_400),
    @ApiResponse(code = 401, message = OfferDocumentationConstants.RESPONSECODE_401),
    @ApiResponse(code = 403, message = OfferDocumentationConstants.RESPONSECODE_403),
    @ApiResponse(code = 404, message = OfferDocumentationConstants.RESPONSECODE_404),
    @ApiResponse(code = 500, message = OfferDocumentationConstants.RESPONSECODE_500)})
  @GetMapping(value = "")
  public ResponseEntity<OfferResponse> findAllOffers(
    @ApiParam(value = OfferDocumentationConstants.AUTH_TOKEN_DESC, required = false) @RequestHeader(
      value = "x-auth-token",
      required = false) String authToken,
    @ApiParam(value = "${swagger.common.locale.ApiParam.value}", required = false) @RequestParam(value = "locale",
      required = false,
      defaultValue = "en_US") String locale,
    @ApiParam(value = OfferDocumentationConstants.PAGE_DESC, required = false) @RequestParam(value = "page",
      required = false, defaultValue = "1") int page,
    @ApiParam(value = OfferDocumentationConstants.PAGE_SIZE_DESC, required = false) @RequestParam(value = "size",
      required = false, defaultValue = "10") int size)
    throws Exception {
    Offer request = OfferFactory.buildEmptyRequest();
    OfferResponse offerAPIResponse = findAllOfferService.process(request);
    return new ResponseEntity<>(offerAPIResponse, HttpStatus.OK);
  }
}
