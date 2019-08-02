/*

Copyright 2019, madhankumar
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are
met:

 * Redistributions of source code must retain the above copyright
notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above
copyright notice, this list of conditions and the following disclaimer
in the documentation and/or other materials provided with the
distribution.
 * Neither the name of Google Inc. nor the names of its
contributors may be used to endorse or promote products derived from
this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,           
DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY           
THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

 */
package com.heavenhr.recruitment.model.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * @author madhankumar
 *
 */
@Getter
@Setter
@ApiModel(value = "Offer", description = "This model contains the offer details.")
@JsonInclude(value = Include.NON_DEFAULT)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Offer implements Serializable {
  private static final long serialVersionUID = 1565282169175618294L;

  @ApiModelProperty(value = "${Offer.id.ApiModelProperty.value}", required = true, readOnly = true)
  /** The id. */
  private long id;

  @ApiModelProperty(value = "${Offer.startDate.ApiModelProperty.value}", required = true, readOnly = true)
  /** The start date. */
  @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
  private LocalDateTime startDate;

  /** The no of applications. */
  @ApiModelProperty(value = "${Offer.noOfApplications.ApiModelProperty.value}", required = true, readOnly = true)
  @JsonInclude(value = Include.NON_NULL)
  private int noOfApplications;

  /** The job title. */
  @ApiModelProperty(value = "${Offer.jobTitle.ApiModelProperty.value}", required = true)
  private String jobTitle;

}
