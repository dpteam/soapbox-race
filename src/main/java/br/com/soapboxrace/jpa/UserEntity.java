/*
 * Created on 12 mar 2016 ( Time 16:39:25 )
 * Generated by Telosys Tools Generator ( version 2.1.1 )
 */
// This Bean has a basic Primary Key (not composite) 

package br.com.soapboxrace.jpa;

import java.io.Serializable;

//import javax.validation.constraints.* ;
//import org.hibernate.validator.constraints.* ;

import java.util.List;

import javax.persistence.*;

/**
 * Persistent class for entity stored in table "USER"
 *
 * @author Telosys Tools Generator
 *
 */

@Entity
@Table(name = "USER")
// Define named queries here
@NamedQueries({ @NamedQuery(name = "UserEntity.countAll", query = "SELECT COUNT(x) FROM UserEntity x") })
public class UserEntity implements Serializable {

	private static final long serialVersionUID = 1L;

	// ----------------------------------------------------------------------
	// ENTITY PRIMARY KEY ( BASED ON A SINGLE FIELD )
	// ----------------------------------------------------------------------
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	private Long id;

	// ----------------------------------------------------------------------
	// ENTITY DATA FIELDS
	// ----------------------------------------------------------------------
	@Column(name = "EMAIL", length = 200)
	private String email;

	@Column(name = "PASSWORD", length = 32)
	private String password;

	// ----------------------------------------------------------------------
	// ENTITY LINKS ( RELATIONSHIP )
	// ----------------------------------------------------------------------
	@OneToMany(mappedBy = "user", targetEntity = PersonaEntity.class)
	private List<PersonaEntity> listOfPersona;

	// ----------------------------------------------------------------------
	// CONSTRUCTOR(S)
	// ----------------------------------------------------------------------
	public UserEntity() {
		super();
	}

	// ----------------------------------------------------------------------
	// GETTER & SETTER FOR THE KEY FIELD
	// ----------------------------------------------------------------------
	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	// ----------------------------------------------------------------------
	// GETTERS & SETTERS FOR FIELDS
	// ----------------------------------------------------------------------
	// --- DATABASE MAPPING : EMAIL ( VARCHAR )
	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return this.email;
	}

	// --- DATABASE MAPPING : PASSWORD ( VARCHAR )
	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return this.password;
	}

	// ----------------------------------------------------------------------
	// GETTERS & SETTERS FOR LINKS
	// ----------------------------------------------------------------------
	public void setListOfPersona(List<PersonaEntity> listOfPersona) {
		this.listOfPersona = listOfPersona;
	}

	public List<PersonaEntity> getListOfPersona() {
		return this.listOfPersona;
	}

	// ----------------------------------------------------------------------
	// toString METHOD
	// ----------------------------------------------------------------------
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		sb.append(id);
		sb.append("]:");
		sb.append(email);
		sb.append("|");
		sb.append(password);
		return sb.toString();
	}

}
