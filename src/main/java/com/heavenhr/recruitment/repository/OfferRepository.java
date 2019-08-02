package com.heavenhr.recruitment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.heavenhr.recruitment.model.entity.OfferEntity;

/**
 * The interface OfferRepository.
 * 
 * This handles all DB calls to for the offer table.
 * 
 * This repository interface to used to interact with database for 
 * database CRUD operations.
 * 
 * @author madhankumar
 */
public interface OfferRepository extends JpaRepository<OfferEntity, Long> {

  /**
   * Find all applications count.
   *
   * @param offerId the offer id
   * @return the long
   */
  @Query("select size(o.applications) from offer o where o.id=:offerId")
  int findAllApplicationsCount(@Param("offerId") long offerId);
}
