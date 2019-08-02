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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.heavenhr.recruitment.exception.ResourceNotFoundException;
import com.heavenhr.recruitment.model.dto.Offer;
import com.heavenhr.recruitment.model.dto.OfferResponse;
import com.heavenhr.recruitment.model.entity.OfferEntity;
import com.heavenhr.recruitment.model.mapper.OfferMapper;
import com.heavenhr.recruitment.process.IOfferProcess;
import com.heavenhr.recruitment.repository.OfferRepository;
import com.heavenhr.recruitment.utils.OfferFactory;

import lombok.NoArgsConstructor;

/**
 * @author madhankumar
 *
 */
@Service("offerProcessor")
@NoArgsConstructor
@Transactional(propagation = Propagation.REQUIRED, readOnly = false,
  rollbackFor = {Exception.class, DataIntegrityViolationException.class})
public class OfferProcessImpl implements IOfferProcess {

  private static final Logger LOGGER = LoggerFactory.getLogger(OfferProcessImpl.class);

  @Autowired
  private OfferRepository offerRepository;

  @Autowired
  private OfferMapper offerMapper;

  @Override
  public OfferResponse createOffer(Offer o) throws Exception {
    OfferEntity e = offerRepository.save(offerMapper.dtoToEntity(o));
    e.setNoOfApplications(getNoOfApplications(e));
    return OfferFactory.buildResponse(offerMapper.entityToDTO(e));
  }

  @Override
  public OfferResponse findAllOffer() throws ResourceNotFoundException {
    List<OfferEntity> l = offerRepository.findAll();
    if (!l.isEmpty()) {
      l = getNoOfApplications(l);
      return OfferFactory.buildResponse(offerMapper.entityListToDtoList(l));
    } else {
      LOGGER.error("no offers in database");
      throw new ResourceNotFoundException("no offer records are present");
    }
  }

  @Override
  public OfferResponse findOfferById(long id) throws ResourceNotFoundException {
    Optional<OfferEntity> e = offerRepository.findById(id);
    if (e.isPresent()) {
      OfferEntity o = e.get();
      o.setNoOfApplications(getNoOfApplications(o));
      return OfferFactory.buildResponse(offerMapper.entityToDTO(o));
    } else {
      LOGGER.error("offer not found {} ", id);
      throw new ResourceNotFoundException("offer is not found");
    }
  }

  public int getNoOfApplications(OfferEntity o) {
    return offerRepository.findAllApplicationsCount(o.getId());
  }

  public List<OfferEntity> getNoOfApplications(List<OfferEntity> o) {
    o.stream().forEach(e -> e.setNoOfApplications(offerRepository.findAllApplicationsCount(e.getId())));
    return o;
  }

}
