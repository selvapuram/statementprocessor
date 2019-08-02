package com.heavenhr.recruitment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.json.JSONException;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.heavenhr.recruitment.constants.Constants;
import com.heavenhr.recruitment.model.common.ErrorModel;
import com.heavenhr.recruitment.model.dto.ApplicationResponse;
import com.heavenhr.recruitment.utils.ObjectMapperUtil;

/**
 * The Class ApplicationControllerTest.
 * This class tests the controls the request for the CRUD operations on the Application Entity and all
 * its child entities.
 * 
 * @author madhankumar
 * @version 1.0.0
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ApplicationControllerTest extends AbstractTest {

  /** The data path. */
  String dataPath = "src/test/resources/data/ApplicationData.json";

  /** The offerJson json. */
  public static JSONObject applicationJson = null;

  private static final int OFFER_SUCCESS = 2;

  private static final int OFFER_ERROR = 200;

  /** The base url. */
  private String baseUrl = "/offers/" + OFFER_SUCCESS + "/applications";

  /** The base url. */
  private String baseErrorUrl = "/offers/" + OFFER_ERROR + "/applications";

  @Before
  public void setup() throws FileNotFoundException, IOException, JSONException {
    mvc = MockMvcBuilders.webAppContextSetup(context).build();
    applicationJson = new JSONObject(FileUtils.readFileToString(new File(dataPath), "UTF-8"));
  }

  @Test
  public void testCreateApplication() throws Exception {

    String uri = baseUrl + "/";
    MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(uri).header(Constants.AUTH_HEADER, token);
    byte[] offer = applicationJson.toString().getBytes();
    request.content(offer);

    MvcResult result = mvc.perform(request.contentType(MediaType.APPLICATION_JSON)).andReturn();

    String content = result.getResponse().getContentAsString();
    ApplicationResponse response = ObjectMapperUtil.convertToObject(content, ApplicationResponse.class);
    Assert.assertEquals("CreateApplicationResponse Code", result.getResponse().getStatus(), HttpStatus.CREATED.value());
    Assert.assertNotNull("Create Application", response);
  }

  @Test
  public void testCreateDuplicateApplication() throws Exception {

    String uri = baseUrl + "/";
    MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(uri).header(Constants.AUTH_HEADER, token);
    applicationJson.put("candidateEmail", "testuser1@testmail.com");
    byte[] offer = applicationJson.toString().getBytes();
    request.content(offer);

    MvcResult result = mvc.perform(request.contentType(MediaType.APPLICATION_JSON)).andReturn();

    String content = result.getResponse().getContentAsString();
    ErrorModel response = ObjectMapperUtil.convertToObject(content, ErrorModel.class);
    Assert.assertEquals("CreateApplicationResponse Code", result.getResponse().getStatus(),
      HttpStatus.BAD_REQUEST.value());
    Assert.assertNotNull("Create Application", response);
  }

  @Test
  public void testCreateEmptyEmail() throws Exception {

    String uri = baseUrl + "/";
    MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(uri).header(Constants.AUTH_HEADER, token);
    applicationJson.put("candidateEmail", "");
    byte[] offer = applicationJson.toString().getBytes();
    request.content(offer);

    MvcResult result = mvc.perform(request.contentType(MediaType.APPLICATION_JSON)).andReturn();

    String content = result.getResponse().getContentAsString();
    ErrorModel response = ObjectMapperUtil.convertToObject(content, ErrorModel.class);
    Assert.assertEquals("CreateApplicationResponse Code", result.getResponse().getStatus(),
      HttpStatus.BAD_REQUEST.value());
    Assert.assertNotNull("Create Application", response);
  }

  @Test
  public void testCreateEmptyResumeText() throws Exception {

    String uri = baseUrl + "/";
    MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(uri).header(Constants.AUTH_HEADER, token);
    applicationJson.put("resumeText", "");
    byte[] offer = applicationJson.toString().getBytes();
    request.content(offer);

    MvcResult result = mvc.perform(request.contentType(MediaType.APPLICATION_JSON)).andReturn();

    String content = result.getResponse().getContentAsString();
    ErrorModel response = ObjectMapperUtil.convertToObject(content, ErrorModel.class);
    Assert.assertEquals("CreateApplicationResponse Code", result.getResponse().getStatus(),
      HttpStatus.BAD_REQUEST.value());
    Assert.assertNotNull("Create Application", response);
  }

  @Test
  public void testViewApplication() throws Exception {

    String uri = baseUrl + "/" + "1";
    MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(uri).header(Constants.AUTH_HEADER, token);
    MvcResult result = mvc.perform(request.contentType(MediaType.APPLICATION_JSON)).andReturn();

    String content = result.getResponse().getContentAsString();
    ApplicationResponse response = ObjectMapperUtil.convertToObject(content, ApplicationResponse.class);
    Assert.assertEquals("ViewApplicationResponse Code", result.getResponse().getStatus(), HttpStatus.OK.value());
    Assert.assertNotNull("View Application", response);
  }

  @Test
  public void testViewApplicationNotFound() throws Exception {

    String uri = baseUrl + "/" + "100";
    MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(uri).header(Constants.AUTH_HEADER, token);
    MvcResult result = mvc.perform(request.contentType(MediaType.APPLICATION_JSON)).andReturn();

    String content = result.getResponse().getContentAsString();
    ErrorModel response = ObjectMapperUtil.convertToObject(content, ErrorModel.class);
    Assert.assertEquals("ViewApplicationResponse Code", result.getResponse().getStatus(), HttpStatus.NOT_FOUND.value());
    Assert.assertNotNull("View Application", response.getMessage());
  }

  @Test
  public void testViewAllApplication() throws Exception {
    String uri = baseUrl + "/";
    MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(uri).header(Constants.AUTH_HEADER, token);
    MvcResult result = mvc.perform(request.contentType(MediaType.APPLICATION_JSON)).andReturn();

    String content = result.getResponse().getContentAsString();
    ApplicationResponse response = ObjectMapperUtil.convertToObject(content, ApplicationResponse.class);
    Assert.assertEquals("ViewApplicationResponse Code", result.getResponse().getStatus(), HttpStatus.OK.value());
    Assert.assertNotNull("Find Applications", response);
  }

  @Test
  public void testViewAllApplicationNotFound() throws Exception {
    String uri = baseErrorUrl + "/";
    MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(uri).header(Constants.AUTH_HEADER, token);
    MvcResult result = mvc.perform(request.contentType(MediaType.APPLICATION_JSON)).andReturn();

    String content = result.getResponse().getContentAsString();
    ErrorModel response = ObjectMapperUtil.convertToObject(content, ErrorModel.class);
    Assert.assertEquals("ViewApplicationResponse Code", result.getResponse().getStatus(), HttpStatus.NOT_FOUND.value());
    Assert.assertNotNull("Find Applications", response.getMessage());
  }

  @Test
  public void testPatchApplication() throws Exception {

    String uri = baseUrl + "/" + "1?applicationStatus=HIRED";
    MockHttpServletRequestBuilder request = MockMvcRequestBuilders.patch(uri).header(Constants.AUTH_HEADER, token);
    MvcResult result = mvc.perform(request.contentType(MediaType.APPLICATION_JSON)).andReturn();

    String content = result.getResponse().getContentAsString();
    ApplicationResponse response = ObjectMapperUtil.convertToObject(content, ApplicationResponse.class);
    Assert.assertEquals("PatchApplicationResponse Code", result.getResponse().getStatus(), HttpStatus.OK.value());
    Assert.assertNotNull("Patch Application", response);
  }

  @Test
  public void testPatchApplicationNotFound() throws Exception {

    String uri = baseUrl + "/" + "100";
    MockHttpServletRequestBuilder request = MockMvcRequestBuilders.patch(uri).header(Constants.AUTH_HEADER, token);
    MvcResult result = mvc.perform(request.contentType(MediaType.APPLICATION_JSON)).andReturn();

    String content = result.getResponse().getContentAsString();
    ErrorModel response = ObjectMapperUtil.convertToObject(content, ErrorModel.class);
    Assert.assertEquals("ViewApplicationResponse Code", result.getResponse().getStatus(), HttpStatus.NOT_FOUND.value());
    Assert.assertNotNull("View Application", response.getMessage());
  }

}
