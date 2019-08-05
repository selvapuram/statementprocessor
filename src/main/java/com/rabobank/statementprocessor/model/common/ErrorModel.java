/*******************************************************************************
 * Copyright 2019 madhankumar
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package com.rabobank.statementprocessor.model.common;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author madhankumar
 *
 */
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class ErrorModel implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 7819101233649625249L;

  @ApiModelProperty(value = "${ErrorModel.timestamp.ApiModelProperty.value}", required = true, readOnly = true)
  private long timestamp;

  @ApiModelProperty(value = "${ErrorModel.timestamp.ApiModelProperty.value}", required = true, readOnly = true)
  private String message;

  @ApiModelProperty(value = "${ErrorModel.timestamp.ApiModelProperty.value}", required = true, readOnly = true)
  private String code;

  public static ErrorModel of(String code, String message) {
    ErrorModel err = new ErrorModel();
    err.setCode(code).setMessage(message).setTimestamp(new Date().getTime());
    return err;
  }

}
