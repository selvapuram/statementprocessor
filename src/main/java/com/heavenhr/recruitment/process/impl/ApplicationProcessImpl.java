/*

Copyright 2019, madhankumar
All rights reserved.

Redistribution and use in source and binary forms, with or without
modification, are permitted provided that the following conditions are
met:

 * Redistributions of source code must retain the above copyright
notice, this list of conditions and the following disclaimer.
 * Redistributions in binary form must reproduce the above
copyright notice, this list of conditions and the following disclaimer
in the documentation and/or other materials provided with the
distribution.
 * Neither the name of Google Inc. nor the names of its
contributors may be used to endorse or promote products derived from
this software without specific prior written permission.

THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
"AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,           
DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY           
THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
(INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

 */
package com.heavenhr.recruitment.process.impl;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.heavenhr.recruitment.exception.ResourceNotFoundException;
import com.heavenhr.recruitment.model.dto.Application;
import com.heavenhr.recruitment.model.dto.ApplicationResponse;
import com.heavenhr.recruitment.model.dto.ApplicationStatus;
import com.heavenhr.recruitment.model.entity.ApplicationEntity;
import com.heavenhr.recruitment.model.mapper.ApplicationMapper;
import com.heavenhr.recruitment.process.IApplicationProcess;
import com.heavenhr.recruitment.repository.ApplicationRepository;
import com.heavenhr.recruitment.utils.ApplicationFactory;

import lombok.NoArgsConstructor;

// TODO: Auto-generated Javadoc
/**
 * The Class ApplicationProcessImpl.
 *
 * @author madhankumar
 */
@Service("applicationProcessor")

/**
 * Instantiates a new application process impl.
 */
@NoArgsConstructor
@Transactional(propagation = Propagation.REQUIRED, readOnly = false,
  rollbackFor = {Exception.class, DataIntegrityViolationException.class})
public class ApplicationProcessImpl implements IApplicationProcess {

  private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationProcessImpl.class);

  /** The application repository. */
  @Autowired
  private ApplicationRepository applicationRepository;

  /** The application mapper. */
  @Autowired
  private ApplicationMapper applicationMapper;

  /*
   * (non-Javadoc)
   * 
   * @see com.heavenhr.recruitment.process.IApplicationProcess#createApplication(com.heavenhr.recruitment.model.dto.Application)
   */
  @Override
  public ApplicationResponse createApplication(Application o) throws Exception {
    ApplicationEntity e = applicationRepository.save(applicationMapper.dtoAppToEntityApp(o));
    return ApplicationFactory.buildResponse(applicationMapper.entityAppToDTOApp(e));
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.heavenhr.recruitment.process.IApplicationProcess#findAllApplication(long, int, int)
   */
  @Override
  public ApplicationResponse findAllApplication(long offerId, int page, int size) throws ResourceNotFoundException {
    PageRequest pageableInfo = PageRequest.of(page, size);
    Page<ApplicationEntity> p = applicationRepository.findAllByOfferId(offerId, pageableInfo);
    if (p.hasContent()) {
      List<ApplicationEntity> l = p.getContent();
      return ApplicationFactory.buildResponse(applicationMapper.entityAppListToDtoAppList(l));
    } else {
      LOGGER.error("applications not found for the offer {} ", offerId);
      throw new ResourceNotFoundException("applications are not found for offer");
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.heavenhr.recruitment.process.IApplicationProcess#findApplicationById(long, long)
   */
  @Override
  public ApplicationResponse findApplicationById(long id, long offerId) throws ResourceNotFoundException {
    Optional<ApplicationEntity> e = applicationRepository.findByIdAndOfferId(id, offerId);
    if (e.isPresent()) {
      return ApplicationFactory.buildResponse(applicationMapper.entityAppToDTOApp(e.get()));
    } else {
      LOGGER.error("application not found {} for the offer {} ", id, offerId);
      throw new ResourceNotFoundException("application is not found");
    }
  }

  /*
   * (non-Javadoc)
   * 
   * @see com.heavenhr.recruitment.process.IApplicationProcess#patchUpdateApplicationById(long, long,
   * com.heavenhr.recruitment.model.dto.ApplicationStatus)
   */
  @Override
  public ApplicationResponse patchUpdateApplicationById(long id, long offerId, ApplicationStatus status)
    throws ResourceNotFoundException {
    Optional<ApplicationEntity> e = applicationRepository.findByIdAndOfferId(id, offerId);
    if (e.isPresent()) {
      ApplicationEntity aEntity = e.get();
      aEntity.setApplicationStatus(status);
      applicationRepository.save(aEntity);
      return ApplicationFactory.buildResponse(applicationMapper.entityAppToDTOApp(e.get()));
    } else {
      LOGGER.error("application not found {} for the offer {} ", id, offerId);
      throw new ResourceNotFoundException("application is not found");
    }
  }

}
