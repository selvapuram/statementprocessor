/*******************************************************************************
 * Copyright 2019 madhankumar
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package com.rabobank.statementprocessor.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "account")
@XmlRootElement(name = "record")
@XmlAccessorType(XmlAccessType.FIELD)
public class AccountEntity {

  /** the unique primary key id */
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "ID", nullable = false)
  @XmlTransient
  private long id;

  /** The reference. */
  @XmlAttribute(name = "reference")
  @Column(name = "REFERENCE", nullable = false)
  private long reference;

  /** The mutation. */
  @NotNull
  @XmlElement
  @Column(name = "MUTATION", nullable = false)
  private String mutation;

  /** The end balance. */
  @NotNull
  @XmlElement
  @Column(name = "ENDBALANCE", nullable = false)
  private double endBalance;

  /** The description. */
  @Column(name = "DESCRIPTION")
  @XmlElement
  private String description;

  /** The account number. */
  @NotNull
  @XmlElement
  @Column(name = "ACCOUNTNUMBER", nullable = false)
  private String accountNumber;

  /** The start balance. */
  @NotNull
  @XmlElement
  @Column(name = "STARTBALANCE", nullable = false)
  private double startBalance;

  @Column(name = "ISFAILEDRECORD", nullable = false)
  @XmlTransient
  private boolean failed;

}
