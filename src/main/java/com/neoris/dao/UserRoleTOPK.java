package com.neoris.dao;

import java.io.Serializable;
import javax.persistence.*;

// TODO: Auto-generated Javadoc
/**
 * The primary key class for the USER_ROLES database table.
 *
 * @author marco.slehiman
 */
@Embeddable
public class UserRoleTOPK implements Serializable {
	
	/** The Constant serialVersionUID. */
	//default serial version id, required for serializable classes.
	private static final long serialVersionUID = 1L;

	/** The user name. */
	@Column(name="USER_NAME")
	private String userName;

	/** The role name. */
	@Column(name="ROLE_NAME")
	private String roleName;

	/**
	 * Instantiates a new user role TOPK.
	 */
	public UserRoleTOPK() {
	}
	
	/**
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	public String getUserName() {
		return this.userName;
	}
	
	/**
	 * Sets the user name.
	 *
	 * @param userName the new user name
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	/**
	 * Gets the role name.
	 *
	 * @return the role name
	 */
	public String getRoleName() {
		return this.roleName;
	}
	
	/**
	 * Sets the role name.
	 *
	 * @param roleName the new role name
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	public boolean equals(Object other) {
		if (this == other) {
			return true;
		}
		if (!(other instanceof UserRoleTOPK)) {
			return false;
		}
		UserRoleTOPK castOther = (UserRoleTOPK)other;
		return 
			this.userName.equals(castOther.userName)
			&& this.roleName.equals(castOther.roleName);
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		final int prime = 31;
		int hash = 17;
		hash = hash * prime + this.userName.hashCode();
		hash = hash * prime + this.roleName.hashCode();
		
		return hash;
	}
}