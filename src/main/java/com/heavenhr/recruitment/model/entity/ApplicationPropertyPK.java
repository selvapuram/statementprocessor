package com.heavenhr.recruitment.model.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Instantiates a new application property PK.
 */
@NoArgsConstructor
@AllArgsConstructor
@Embeddable

/**
 * Gets the ApplicationPropertyPK.
 *
 * @return the ApplicationPropertyPK
 */
@Getter
@Setter
public class ApplicationPropertyPK implements Serializable {

  /** Generated Serial Version UID   */
  private static final long serialVersionUID = 2273340692334460457L;

  /**
   *  The applicationId. 
   */
  @Column(name = "applicationid", nullable = false)
  private long applicationId;

  /**
   *  The name. 
   */
  @Column(name = "name")
  private String name;

}
