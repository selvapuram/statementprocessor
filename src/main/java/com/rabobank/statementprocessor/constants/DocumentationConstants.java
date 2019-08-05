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
/**
 * 
 */
package com.rabobank.statementprocessor.constants;

/**
 * @author jayakanthan.m
 *
 */
public class DocumentationConstants {

  /**
   *  The Constant is descripted about the VERSION . 
   */
  public static final String VERSION_ID = "Holds the value of an API version number. "
    + "Using this parameter, you can access different versions of the API. "
    + "The version format is <i><font face=\"courier\">major.minor.patch.</font></i> For example, "
    + "<i><font face=\"courier\">1.0.0.</font></i>";

  public static final String RESPONSECODE_200 = "Success.";
  public static final String RESPONSECODE_201 = "Created.";
  public static final String RESPONSECODE_202 = "Accepted.";
  public static final String RESPONSECODE_400 = "Bad Request.";
  public static final String RESPONSECODE_401 = "Unauthorized.";
  public static final String RESPONSECODE_403 = "Forbidden.";
  public static final String RESPONSECODE_404 = "The resource you are trying to reach is not found.";
  public static final String RESPONSECODE_422 = "Unprocessable Entity.";
  public static final String RESPONSECODE_409 = "Conflict Request";
  public static final String RESPONSECODE_500 = "Internal Server Error occured.";

  /** This variable holds the description for header <b>x-auth-token</b> */
  public static final String AUTH_TOKEN_DESC = "Holds a valid authorization token generated "
    + "using the Authorization microservice for a user. "
    + "The Auth Token uuid format represents claims containing "
    + "the roles and privileges of the user accessing this API. "
    + "that defines a compact and self-contained way for securely "
    + "Since the token is a required field for this API, "
    + "it has to be generated beforehand by invoking the /token "
    + "has to be passed to this service and the request "
    + "will be processed only if the following conditions are met:"
    + "<ul><li>Token validation against the username and password</li>"
    + "<li>Necessary privileges are available for the user to perform the "
    + "action on the subjected resource</li></ul>";

  DocumentationConstants() {
    super();
  }

}
