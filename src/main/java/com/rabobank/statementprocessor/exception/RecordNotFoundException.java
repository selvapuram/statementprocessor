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
package com.rabobank.statementprocessor.exception;

import com.rabobank.statementprocessor.constants.Constants;

import lombok.Getter;

/**
 * The Class RecordNotFoundException.
 *
 * @author madhankumar
 */
public class RecordNotFoundException extends Exception {

  /**
   * 
   */
  private static final long serialVersionUID = -5353950818724716137L;

  @Getter
  private String code;

  /**
   * Instantiates a new resource not found exception.
   */
  public RecordNotFoundException() {
    super();
    this.code = Constants.ERR_RESOURCE_NOT_FOUND;
  }

  /**
   * Instantiates a new resource not found exception.
   *
   * @param message the message
   */
  public RecordNotFoundException(final String message) {
    super(message);
    this.code = Constants.ERR_RESOURCE_NOT_FOUND;
  }

}
