package com.heavenhr.recruitment.model.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.heavenhr.recruitment.model.dto.Offer;
import com.heavenhr.recruitment.model.entity.OfferEntity;

/**
 * The Interface OfferMapper.
 */
@Mapper(componentModel = "spring")
public interface OfferMapper {

  /**
   * Dto to entity.
   *
   * @param offerDTO the offer DTO
   * @return the offer entity
   */
  OfferEntity dtoToEntity(Offer offerDTO);

  /**
   * Entity to DTO.
   *
   * @param offerDTO the offer DTO
   * @return the offer
   */
  Offer entityToDTO(OfferEntity offerDTO);

  /**
   * Entity list to dto list.
   *
   * @param l the entity
   * @return the list
   */
  List<Offer> entityListToDtoList(List<OfferEntity> l);

}
