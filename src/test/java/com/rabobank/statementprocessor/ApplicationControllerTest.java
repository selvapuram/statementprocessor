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
package com.rabobank.statementprocessor;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.json.JSONException;
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

import com.rabobank.statementprocessor.constants.Constants;
import com.rabobank.statementprocessor.model.dto.AcceptedFileType;
import com.rabobank.statementprocessor.model.dto.Records;
import com.rabobank.statementprocessor.utils.ObjectMapperUtil;

/**
 * The Class ApplicationControllerTest.
 * This class tests the controls the validate the CSV and XML file
 * 
 * @author madhankumar
 * @version 1.0.0
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ApplicationControllerTest extends AbstractTest {

  /** The base url. */
  private String baseUrl = "/customers/statement/validate";

  @Before
  public void setup() throws FileNotFoundException, IOException, JSONException {
    mvc = MockMvcBuilders.webAppContextSetup(context).build();
  }

  @Test
  public void testValidateCSVApplication() throws Exception {

    String uri = baseUrl + "/";
    MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(uri).header(Constants.AUTH_HEADER, token)
      .param("inputFileType", AcceptedFileType.CSV.toString());
    MvcResult result = mvc.perform(request.contentType(MediaType.APPLICATION_JSON)).andReturn();

    String content = result.getResponse().getContentAsString();
    Records response = ObjectMapperUtil.convertToObject(content, Records.class);
    Assert.assertEquals("Application Response Code", result.getResponse().getStatus(), HttpStatus.OK.value());
    Assert.assertNotNull("Application Response", response);

  }

  @Test
  public void testValidateXMLApplication() throws Exception {

    String uri = baseUrl + "/";
    MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post(uri).header(Constants.AUTH_HEADER, token)
      .param("inputFileType", AcceptedFileType.XML.toString());
    MvcResult result = mvc.perform(request.contentType(MediaType.APPLICATION_JSON)).andReturn();

    String content = result.getResponse().getContentAsString();
    Records response = ObjectMapperUtil.convertToObject(content, Records.class);
    Assert.assertEquals("Application Response Code", result.getResponse().getStatus(), HttpStatus.OK.value());
    Assert.assertNotNull("Application Response", response);

  }

}
