/*******************************************************************************
 * Copyright ©2002-2018 Skava - All rights reserved.
 * 
 * All information contained herein is, and remains the property of Skava.
 * Skava including, without limitation, all software and other elements thereof, 
 * are owned or controlled exclusively by Skava and protected by copyright, patent
 * and other laws. Use without permission is prohibited. 
 * Unauthorized copying of this file, via any medium is strictly prohibited
 *    
 * For further information contact Skava at info@skava.com.
 ******************************************************************************/
package com.heavenhr.recruitment.model.common;

import java.io.Serializable;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Holds the pageable data for the response.
 * Page denotes the requested page number.
 * Size denotes the number of records a page can have.
 * Count denotes the actual number of records the response have.
 * TotalCount denotes the total number of records available for the request.
 * 
 * @author madhankumar
 *
 */

@Getter
@Setter

/**
 * Instantiates a new pageable info.
 */
@NoArgsConstructor

/**
 * Instantiates a new pageable info.
 *
 * @param page the page
 * @param size the size
 * @param count the count
 * @param totalCount the total count
 */
@AllArgsConstructor
@Accessors(chain = true)
@ApiModel(
  description = "This model contains attributes to perform pagination operation.")
public class PageableInfo implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**  page number of the response. */
  @ApiModelProperty(value = "${PageableInfo.page.ApiModelProperty.value}", required = false, readOnly = true)
  private int page;

  /** The size. */
  @ApiModelProperty(value = "${PageableInfo.size.ApiModelProperty.value}", required = false, readOnly = true)
  /** max size of the page */
  private int size;

  /** The count. */
  @ApiModelProperty(value = "${PageableInfo.count.ApiModelProperty.value}", required = false, readOnly = true)
  /** actual number of records the response holds */
  private int count;

  /** The total count. */
  @ApiModelProperty(value = "${PageableInfo.totalCount.ApiModelProperty.value}", required = false, readOnly = true)
  /** total records available for the request */
  private long totalCount;

}
