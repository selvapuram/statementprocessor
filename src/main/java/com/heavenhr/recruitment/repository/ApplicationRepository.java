package com.heavenhr.recruitment.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.heavenhr.recruitment.model.entity.ApplicationEntity;

/**
 * The interface ApplicationRepository.
 * 
 * This handles all DB calls to for the application table.
 * 
 * This repository interface to used to interact with database for 
 * database CRUD operations.
 * 
 * @author madhankumar
 */
public interface ApplicationRepository extends JpaRepository<ApplicationEntity, Long> {

  Page<ApplicationEntity> findAllByOfferId(long offerId, Pageable pageable);

  Optional<ApplicationEntity> findByIdAndOfferId(long id, long offerId);
}
