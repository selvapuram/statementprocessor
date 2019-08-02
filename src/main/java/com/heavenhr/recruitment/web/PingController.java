package com.heavenhr.recruitment.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.Info;
import io.swagger.annotations.SwaggerDefinition;

/**
 * The Class PingController.
 */
@SwaggerDefinition(info = @Info(title = "Ping", version = "v1.0", description = "Recruitment Service Ping"))
@Api(value = "recruitment", tags = "Ping")
@RestController
@RequestMapping("")
public class PingController {

  /**
   * Default Constructor.
   */
  public PingController() {
    super();
  }

  /**
   * Ping.
   *
   * @return the string with current timestamp
   */
  @ApiOperation(value = "${swagger.ping.value}",
    notes = "${swagger.ping.notes}", nickname = "ping")
  @GetMapping(value = "/ping")
  public String ping() {
    return "{\"operation\": \"Recruitment service ping\", \"timeStamp\": " + System.currentTimeMillis() + "}";
  }
}
