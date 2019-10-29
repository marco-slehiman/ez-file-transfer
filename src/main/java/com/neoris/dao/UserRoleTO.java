package com.neoris.dao;

import java.io.Serializable;
import javax.persistence.*;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the USER_ROLES database table.
 *
 * @author marco.slehiman
 */
@Entity
@Table(name="USER_ROLES", schema = "APP")
@NamedQuery(name="UserRole.findAll", query="SELECT u FROM UserRoleTO u")
public class UserRoleTO implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The id. */
	@EmbeddedId
	private UserRoleTOPK id;

	/**
	 * Instantiates a new user role TO.
	 */
	public UserRoleTO() {
	}

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public UserRoleTOPK getId() {
		return this.id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(UserRoleTOPK id) {
		this.id = id;
	}

}