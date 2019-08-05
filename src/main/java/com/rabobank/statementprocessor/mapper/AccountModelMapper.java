package com.rabobank.statementprocessor.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.rabobank.statementprocessor.model.dto.Account;
import com.rabobank.statementprocessor.model.entity.AccountEntity;

@Mapper(componentModel = "spring")
public interface AccountModelMapper {

  List<Account> entityToDto(List<AccountEntity> entities);
}
