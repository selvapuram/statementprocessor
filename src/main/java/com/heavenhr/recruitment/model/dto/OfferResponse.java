/**
 * ****************************************************************************
 *  Copyright 2018-2030 Skava.
 *  All rights reserved.The Skava system, including
 *  without limitation, all software and other elements
 *  thereof, are owned or controlled exclusively by
 *  Skava and protected by copyright, patent, and
 *  other laws. Use without permission is prohibited.
 *
 *   For further information contact Skava at info@skava.com.
 * ****************************************************************************
 */
package com.heavenhr.recruitment.model.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.heavenhr.recruitment.model.common.PageableInfo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Gets the offer.
 */
@Getter
/**
 * Sets the offer.
 *
 * @param cart the new offer
 */
@Setter
@ApiModel(value = "OfferResponse", description = "This model contains the response details of the offer.")
@JsonInclude(value = Include.NON_EMPTY, content = Include.NON_NULL)
@NoArgsConstructor
public class OfferResponse implements Serializable {

  /**
   * The Constant serialVersionUID.
   */
  private static final long serialVersionUID = 6725439981552797839L;

  /**
   * The cart.
   */
  @ApiModelProperty(value = "${OfferResponse.offer.ApiModelProperty.value}", required = false, readOnly = true)
  public Offer offer;

  /**
   * The carts.
   */
  @ApiModelProperty(value = "${OfferResponse.offers.ApiModelProperty.value}", required = false, readOnly = true)
  private List<Offer> offers;

  /**
   * Page data consists of page, size, count and total count of the response data
   */
  @ApiModelProperty(value = "${OfferResponse.pageableInfo.ApiModelProperty.value}", required = false, readOnly = true)
  private PageableInfo pageableInfo;
}
