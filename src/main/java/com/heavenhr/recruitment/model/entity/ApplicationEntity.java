package com.heavenhr.recruitment.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.UpdateTimestamp;

import com.heavenhr.recruitment.model.dto.ApplicationStatus;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * ApplicationEntity
 */
@NoArgsConstructor
@Entity(name = "application")
@Table(name = "application", uniqueConstraints = {@UniqueConstraint(columnNames = {"candidateemail"})})
@Getter
@Setter
public class ApplicationEntity implements Serializable {

  /** The Constant serialVersionUID. */
  private static final long serialVersionUID = 1L;

  /**
   * id
   */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private long id;

  /**
   *  the offer. 
   */
  @JoinColumn(name = "offerid")
  private long offerId;

  @Enumerated(EnumType.STRING)
  @Column(name = "applicationstatus")
  private ApplicationStatus applicationStatus;

  /** The candidate email. */
  @Column(name = "candidateemail", length = 50)
  @NotBlank
  @Email
  private String candidateEmail;

  /** The resume text. */
  @Column(name = "resumetext")
  @NotBlank
  private String resumeText;

  /**
   *  the createdtime. 
   *  */
  @CreationTimestamp
  @Column(name = "createdtime")
  protected Date createdTime;

  /**
   *  the updatedtime. 
   *  */
  @UpdateTimestamp
  @Column(name = "updatedtime")
  protected Date updatedTime;

  /**
   *  The properties. 
   */
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "id.applicationId", orphanRemoval = true)
  @Fetch(value = FetchMode.SUBSELECT)
  private List<ApplicationPropertiesEntity> properties;

}
