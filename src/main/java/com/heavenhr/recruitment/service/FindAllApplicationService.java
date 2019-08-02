/*******************************************************************************
 * Copyright 2018-2030 Skava. 
 * All rights reserved.The Skava system, including 
 * without limitation, all software and other elements
 * thereof, are owned or controlled exclusively by
 * Skava and protected by copyright, patent, and 
 * other laws. Use without permission is prohibited.
 * 
 *  For further information contact Skava at info@skava.com.
 ******************************************************************************/
package com.heavenhr.recruitment.service;

import com.heavenhr.recruitment.model.dto.Application;
import com.heavenhr.recruitment.model.dto.ApplicationResponse;

/**
 * The Interface FindAllApplicationService.
 * This Interface is used to return a loaded application response based on request
 */
public interface FindAllApplicationService extends IRecruitmentService<Application, ApplicationResponse> {
}
