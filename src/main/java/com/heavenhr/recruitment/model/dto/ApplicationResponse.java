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
 * Gets the application.
 */
@Getter
/**
 * Sets the application.
 *
 * @param cart the new application
 */
@Setter
@ApiModel(value = "ApplicationResponse", description = "This model contains the response details of the application.")
@JsonInclude(value = Include.NON_EMPTY, content = Include.NON_NULL)
@NoArgsConstructor
public class ApplicationResponse implements Serializable {

  /**
   * The Constant serialVersionUID.
   */
  private static final long serialVersionUID = 6725439981552797839L;

  /**
   * The cart.
   */
  @ApiModelProperty(value = "${applicationresponse.application.ApiModelProperty.value}", required = false,
    readOnly = true)
  public Application application;

  /**
   * The carts.
   */
  @ApiModelProperty(value = "${applicationresponse.applications.ApiModelProperty.value}", required = false,
    readOnly = true)
  private List<Application> applications;

  /**
   * Page data consists of page, size, count and total count of the response data
   */
  @ApiModelProperty(value = "${applicationresponse.pageableInfo.ApiModelProperty.value}", required = false,
    readOnly = true)
  private PageableInfo pageableInfo;
}
