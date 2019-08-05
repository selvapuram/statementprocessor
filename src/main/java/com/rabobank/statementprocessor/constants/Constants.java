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
package com.rabobank.statementprocessor.constants;

/**
 * The Class Constants.
 *
 * @author madhankumar
 */
public class Constants {

  /** The Constant ERR_RESOURCE_NOT_PROCESSED. */
  public static final String ERR_RESOURCE_NOT_PROCESSED = "1001";

  /** The Constant ERR_GENERIC_EXCEPTION. */
  public static final String ERR_GENERIC_EXCEPTION = "9999";

  /** The Constant ERR_RESOURCE_NOT_FOUND. */
  public static final String ERR_RESOURCE_NOT_FOUND = "1002";

  /** The Constant AUTH_HEADER. */
  public static final String AUTH_HEADER = "x-auth-token";

  /** The Constant HEADERS. */
  public static final String[] HEADERS = {"reference", "accountNumber", "description", "startBalance",
    "mutation", "endBalance"};

  /** The Constant KEY_XML_ROOT_ELEMENT. */
  public static final String KEY_XML_ROOT_ELEMENT = "record";

  /** The Constant KEY_FILE_TYPE. */
  public static final String KEY_FILE_TYPE = "fileType";

  /** The Constant SWITCHTOCSV. */
  public static final String SWITCHTOCSV = "SWITCHTOCSV";

  /** The Constant SWITCHTOXML. */
  public static final String SWITCHTOXML = "SWITCHTOXML";

  /** The Constant PACKAGE_ENTITY. */
  public static final String PACKAGE_ENTITY = "com.rabobank.statementprocessor.model.entity";

  /** The Constant HEADER_CONTENT_DOWNLOAD. */
  public static final String HEADER_CONTENT_DOWNLOAD = "Content-Disposition";

  /** The Constant HEADER_ATTACH_PREFIX. */
  public static final String HEADER_ATTACH_PREFIX = "attachment; filename=";

  /** The Constant INSERT_QUERY. */
  public static final String INSERT_QUERY = "INSERT INTO account (REFERENCE,ACCOUNTNUMBER,DESCRIPTION,STARTBALANCE,MUTATION,ENDBALANCE, ISFAILEDRECORD) VALUES (:reference,:accountNumber,:description,:startBalance,:mutation,:endBalance,:failed)";

}
