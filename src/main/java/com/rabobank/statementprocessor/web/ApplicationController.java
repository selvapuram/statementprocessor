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
package com.rabobank.statementprocessor.web;

import java.io.ByteArrayInputStream;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.rabobank.statementprocessor.constants.Constants;
import com.rabobank.statementprocessor.constants.DocumentationConstants;
import com.rabobank.statementprocessor.exception.RecordNotFoundException;
import com.rabobank.statementprocessor.model.dto.AcceptedFileType;
import com.rabobank.statementprocessor.model.dto.DownloadFileType;
import com.rabobank.statementprocessor.model.dto.Records;
import com.rabobank.statementprocessor.service.ValidateAccountService;
import com.rabobank.statementprocessor.utils.export.ExcelGenerator;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.Info;
import io.swagger.annotations.SwaggerDefinition;
import lombok.NoArgsConstructor;

/**
 * The Class ApplicationController.
 */
@SwaggerDefinition(info = @Info(title = "Validate Customer Statement", version = "v1.0",
  description = "This account validates the customer statement and generates the report"))
@Api(value = "RaboApplication", tags = "RaboApplication")
@RestController
@RequestMapping("/customers/statement")

/**
 * Instantiates a new ApplicationController.
 */
@NoArgsConstructor
public class ApplicationController {

  /** ValidateAccountService. */
  @Autowired
  private ValidateAccountService validateAccountService;

  @Value("${rabobank.customer.statement.download.xls.filename:records.xls}")
  private String downloadXLSFileName;

  /**
   * validates the input record
   *
   * @param versionId        the version identifier
   * @param authToken        the authorization token
   * @return It returns the <code>Records</code>.
   * @throws Exception 
   */
  @ApiOperation(value = "${swagger.validatestatement.ApiOperation.value}",
    notes = "${swagger.validatestatement.ApiOperation.notes}", response = Records.class,
    nickname = "validateStatement")
  @ResponseStatus(HttpStatus.OK)
  @ApiResponses(value = {@ApiResponse(code = 201, message = DocumentationConstants.RESPONSECODE_201),
    @ApiResponse(code = 400, message = DocumentationConstants.RESPONSECODE_400),
    @ApiResponse(code = 401, message = DocumentationConstants.RESPONSECODE_401),
    @ApiResponse(code = 403, message = DocumentationConstants.RESPONSECODE_403),
    @ApiResponse(code = 404, message = DocumentationConstants.RESPONSECODE_404),
    @ApiResponse(code = 409, message = DocumentationConstants.RESPONSECODE_409),
    @ApiResponse(code = 500, message = DocumentationConstants.RESPONSECODE_500)})
  @PostMapping(value = "/validate")
  public ResponseEntity<Records> validateStatement(
    @ApiParam(value = "${swagger.common.version.ApiParam.value}", required = false, example = "8.0",
      allowEmptyValue = true) @RequestHeader(value = "x-version-id", required = false) String versionId,
    @ApiParam(value = DocumentationConstants.AUTH_TOKEN_DESC, required = false, example = "xxxx.yyyy.zzzz",
      allowEmptyValue = false) @RequestHeader(value = "x-auth-token", required = false) String authToken,
    @ApiParam(value = "${swagger.validatestatement.filetype.ApiParam.value}", defaultValue = "CSV",
      allowableValues = "XML,CSV") @Valid @RequestParam(value = "inputFileType",
        required = true,
        defaultValue = "CSV") AcceptedFileType fileType)
    throws Exception {

    Records recordSet = validateAccountService.validateAccount(fileType);
    return new ResponseEntity<>(recordSet, HttpStatus.OK);
  }

  /**
   * validates the input record
   *
   * @param versionId        the version identifier
   * @param authToken        the authorization token
   * @return It returns the <code>Records</code>.
   * @throws Exception 
   */
  @ApiOperation(value = "${swagger.downloadstatement.ApiOperation.value}",
    notes = "${swagger.downloadstatement.ApiOperation.notes}",
    nickname = "downloadStatement")
  @ResponseStatus(HttpStatus.OK)
  @ApiResponses(value = {@ApiResponse(code = 201, message = DocumentationConstants.RESPONSECODE_201),
    @ApiResponse(code = 400, message = DocumentationConstants.RESPONSECODE_400),
    @ApiResponse(code = 401, message = DocumentationConstants.RESPONSECODE_401),
    @ApiResponse(code = 403, message = DocumentationConstants.RESPONSECODE_403),
    @ApiResponse(code = 404, message = DocumentationConstants.RESPONSECODE_404),
    @ApiResponse(code = 409, message = DocumentationConstants.RESPONSECODE_409),
    @ApiResponse(code = 500, message = DocumentationConstants.RESPONSECODE_500)})
  @GetMapping(value = "/download")
  public ResponseEntity<InputStreamResource> downloadStatement(
    @ApiParam(value = "${swagger.common.version.ApiParam.value}", required = false, example = "8.0",
      allowEmptyValue = true) @RequestHeader(value = "x-version-id", required = false) String versionId,
    @ApiParam(value = DocumentationConstants.AUTH_TOKEN_DESC, required = false, example = "xxxx.yyyy.zzzz",
      allowEmptyValue = false) @RequestHeader(value = "x-auth-token", required = false) String authToken,
    @ApiParam(value = "${swagger.validatestatement.filetype.ApiParam.value}", defaultValue = "CSV",
      allowableValues = "XML,CSV") @Valid @RequestParam(value = "inputFileType",
        required = true,
        defaultValue = "CSV") AcceptedFileType inputFileType,
    @ApiParam(value = "${swagger.downloadstatement.filetype.ApiParam.value}", defaultValue = "XLS",
      allowableValues = "XLS,PDF") @Valid @RequestParam(value = "outputFileType",
        required = true,
        defaultValue = "XLS") DownloadFileType outputFileType)
    throws Exception {

    Records recordSet = validateAccountService.validateAccount(inputFileType);
    if (recordSet.getCustomerRecords() != null && !recordSet.getCustomerRecords().isEmpty()) {
      ByteArrayInputStream in = ExcelGenerator.generate(recordSet.getCustomerRecords());
      HttpHeaders headers = new HttpHeaders();
      headers.add(HttpHeaders.CONTENT_TYPE, "application/ms-excel");
      headers.add(HttpHeaders.CONTENT_DISPOSITION, Constants.HEADER_ATTACH_PREFIX + downloadXLSFileName);
      return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
    } else {
      throw new RecordNotFoundException("Record Not Found");
    }

  }

}
