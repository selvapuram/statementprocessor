package com.heavenhr.recruitment.service;

import com.heavenhr.recruitment.model.dto.Application;
import com.heavenhr.recruitment.model.dto.ApplicationResponse;

/**
 * The Interface FindApplicationService.
 * This Interface is used to return a loaded application response based on request
 */
public interface FindApplicationService extends IRecruitmentService<Application, ApplicationResponse> {
}
