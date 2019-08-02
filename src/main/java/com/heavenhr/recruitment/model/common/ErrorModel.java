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
package com.heavenhr.recruitment.model.common;

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
