package com.heavenhr.recruitment.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Instantiates a new offer property PK.
 */
@NoArgsConstructor
@AllArgsConstructor
@Embeddable

/**
 * Gets the OfferPropertyPK.
 *
 * @return the OfferPropertyPK
 */
@Getter
@Setter
public class OfferPropertyPK implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = -2145684533692160272L;

  /**
   *  The o. 
   */
  @Column(name = "offerid")
  private long offerId;

  /**
   *  The name. 
   */
  @Column(name = "name")
  private String name;

}
