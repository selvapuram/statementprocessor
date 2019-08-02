/**
 * 
 */
package com.heavenhr.recruitment.constants;

/**
 * @author jayakanthan.m
 *
 */
public class OfferDocumentationConstants {

  /**
   *  The Constant is descripted about the VERSION . 
   */
  public static final String VERSION_ID = "Holds the value of an API version number. "
    + "Using this parameter, you can access different versions of the API. "
    + "The version format is <i><font face=\"courier\">major.minor.patch.</font></i> For example, "
    + "<i><font face=\"courier\">1.0.0.</font></i>";

  /**
   *  The Constant is descripted about the field name . 
   */
  public static final String LOCALE = "Indicates the locale in which the API response and the error messages "
    + "will be responded. The locale should be mentioned in the "
    + "<a href=\"http://www.oracle.com/technetwork/java/javase/java8locales-2095355.html\">"
    + "Java standard locale format</a>.";
  /**
   *  The Constant is identifier of OFFER id . 
   */
  public static final String OFFER_ID = "It refers the identifier of the offer. It is a value of long type and "
    + "it's minimum value is 1 and maximum value is undetermined";

  /**
   *  The Constant is OFFERREQUESTBODY . 
   */

  public static final String OFFERREQUESTBODY = "Indicates offer request contains the entire offer data to be updated";

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

  public static final String OFFER_PROPERTIES = "Custom properties map, "
    + "holds all additional properties which needs to be added to a offer"
    + "apart from the available default properties. "
    + "It is of JSON type whose Keys denoting the offer property-name and Values denoting the property values. "
    + "Eg., customProperties : {'segment':'gold','offerRollNumber':'123'}";

  /** This variable holds the description for header <b>x-auth-token</b> */
  public static final String AUTH_TOKEN_DESC = "Holds a valid authorization token generated "
    + "using the Authorization microservice for a user. "
    + "The Auth Token (JWT format) represents claims containing "
    + "the roles and privileges of the user accessing this API. "
    + "The JSON Web Token (JWT) is an open standard (RFC 7519) "
    + "that defines a compact and self-contained way for securely "
    + "transmitting information between parties as a JSON object. "
    + "Since the token is a required field for this API, "
    + "it has to be generated beforehand by invoking the /auth/sessions "
    + "API provided by Authorization microservice. The output is three "
    + "Base64 strings separated by dots that can be easily passed in "
    + "the HTML and HTTP environments while being more compact when compared to"
    + " XML-based standards such as SAML. Thus, the generated token "
    + "has to be passed to this service and the request "
    + "will be processed only if the following conditions are met:"
    + "<ul><li>Token validation against the secret key</li>"
    + "<li>Expiry time should be in the future</li>"
    + "<li>Necessary privileges are available for the user to perform the "
    + "action on the subjected resource</li></ul>";

  /** This variable holds the description for request parameter <b>page</b> */
  public static final String PAGE_DESC = "Specifies the page number to be retrieved where the size of the page "
    + "must be specified by the parameter size, offset, or pageSize. "
    + "The first page starts with the value \"1\".";

  /** This variable holds the description for request parameter <b>size/pageSize/offset</b> */
  public static final String PAGE_SIZE_DESC = "Indicates the number of documents being "
    + "retrieved on the corresponding page specified by the page parameter.";

  OfferDocumentationConstants() {
    super();
  }

}
