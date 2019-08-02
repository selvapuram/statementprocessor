package com.heavenhr.recruitment.service;

import com.heavenhr.recruitment.model.dto.Offer;
import com.heavenhr.recruitment.model.dto.OfferResponse;

/**
 * The Interface FindOfferService.
 * This Interface is used to return a loaded offer response based on request
 */
public interface FindOfferService extends IRecruitmentService<Offer, OfferResponse> {
}
