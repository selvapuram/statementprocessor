package com.heavenhr.recruitment.service;

import com.heavenhr.recruitment.model.dto.Offer;
import com.heavenhr.recruitment.model.dto.OfferResponse;

/**
 * The Interface CreateOfferService.
 * This Interface is used to return a created offer response based on request
 */
public interface CreateOfferService extends IRecruitmentService<Offer, OfferResponse> {
}
