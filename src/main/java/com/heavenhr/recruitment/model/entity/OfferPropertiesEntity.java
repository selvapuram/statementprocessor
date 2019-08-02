package com.heavenhr.recruitment.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Instantiates a new cart properties entity.
 */
@NoArgsConstructor
@Entity(name = "offerproperties")
@Table(name = "offerproperties")

/**
 * Gets the value.
 *
 * @return the value
 */
@Getter

/**
 * Sets the value.
 *
 * @param value the new value
 */
@Setter
public class OfferPropertiesEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  /** The created date time. */
  @Getter
  @Setter
  @EmbeddedId
  protected OfferPropertyPK id;

  /**
   *  The value. 
   */
  @Column(name = "value")
  private String value;

  /**
   * Gets the name.
   *
   * @return the name
   */
  public String getName() {
    return this.id.getName();
  }
}
