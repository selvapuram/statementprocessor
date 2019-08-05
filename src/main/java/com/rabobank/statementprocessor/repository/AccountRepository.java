package com.rabobank.statementprocessor.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rabobank.statementprocessor.model.entity.AccountEntity;

public interface AccountRepository extends JpaRepository<AccountEntity, Long> {
  Optional<List<AccountEntity>> findAllByReference(long reference);
}
