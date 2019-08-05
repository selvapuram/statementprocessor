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

  public static final String ERR_RESOURCE_NOT_PROCESSED = "1001";

  public static final String ERR_GENERIC_EXCEPTION = "9999";

  public static final String ERR_RESOURCE_NOT_FOUND = "1002";

  public static final String AUTH_HEADER = "x-auth-token";

  public static final String[] HEADERS = {"reference", "accountNumber", "description", "startBalance",
    "mutation", "endBalance"};

  public static final String KEY_XML_ROOT_ELEMENT = "record";

  public static final String KEY_FILE_TYPE = "fileType";

  public static final String SWITCHTOCSV = "SWITCHTOCSV";

  public static final String SWITCHTOXML = "SWITCHTOXML";

  public static final String PACKAGE_ENTITY = "com.rabobank.statementprocessor.model.entity";

  public static final String HEADER_CONTENT_DOWNLOAD = "Content-Disposition";

  public static final String HEADER_ATTACH_PREFIX = "attachment; filename=";

  public static final String INSERT_QUERY = "INSERT INTO account (REFERENCE,ACCOUNTNUMBER,DESCRIPTION,STARTBALANCE,MUTATION,ENDBALANCE, ISFAILEDRECORD) VALUES (:reference,:accountNumber,:description,:startBalance,:mutation,:endBalance,:failed)";

}
