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
 * Instantiates a new applications properties entity.
 */
@NoArgsConstructor
@Entity(name = "applicationproperties")
@Table(name = "applicationproperties")
public class ApplicationPropertiesEntity implements Serializable {

  private static final long serialVersionUID = 1L;

  /** The created date time. */
  @Getter
  @Setter
  @EmbeddedId
  protected ApplicationPropertyPK id;

  /**
   *  The value. 
   */
  @Getter
  @Setter
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
