package com.heavenhr.recruitment.model.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.heavenhr.recruitment.utils.LocalDateTimeConverter;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Instantiates a new offer entity.
 */
@Entity(name = "offer")
@Table(name = "offer", uniqueConstraints = {@UniqueConstraint(columnNames = {"jobtitle"})})
@Getter
@Setter
@NoArgsConstructor
public class OfferEntity implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /*
   * Id is not mandatory for some entites (i.e) some entites may have composite
   * primary key
   */

  /**
   * The id.
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private long id;

  /**  The job title. */
  @NotBlank
  @Column(name = "jobtitle")
  private String jobTitle;

  /** The created time. */
  @CreationTimestamp
  @Column(name = "createdtime")
  @Convert(converter = LocalDateTimeConverter.class)
  private LocalDateTime startDate;

  /** The properties. */
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "id.offerId", orphanRemoval = true)
  @Fetch(value = FetchMode.SUBSELECT)
  private List<OfferPropertiesEntity> properties;

  /**  The Payments. */
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "offerId", orphanRemoval = true)
  @Fetch(value = FetchMode.SUBSELECT)
  private List<ApplicationEntity> applications;

  @Transient
  private int noOfApplications;

}
