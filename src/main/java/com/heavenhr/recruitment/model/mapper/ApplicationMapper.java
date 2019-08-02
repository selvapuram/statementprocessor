package com.heavenhr.recruitment.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.heavenhr.recruitment.model.dto.Application;
import com.heavenhr.recruitment.model.entity.ApplicationEntity;

/**
 * The Interface ApplicationMapper.
 */
@Mapper(componentModel = "spring")
public interface ApplicationMapper {

  /**
   * Dto to entity.
   *
   * @param applicationDTO the application DTO
   * @return the application entity
   */
  ApplicationEntity dtoAppToEntityApp(Application applicationDTO);

  /**
   * Entity to DTO.
   *
   * @param applicationDTO the application DTO
   * @return the application
   */
  Application entityAppToDTOApp(ApplicationEntity applicationDTO);

  /**
   * Entity list to dto list.
   *
   * @param l the entity
   * @return the list
   */
  List<Application> entityAppListToDtoAppList(List<ApplicationEntity> l);

}
