package com.neoris.dao;

import java.io.Serializable;
import javax.persistence.*;


// TODO: Auto-generated Javadoc
/**
 * The persistent class for the USERS database table.
 * 
 * @author marco.slehiman
 *
 */
@Entity

@Table( name="USERS", schema = "APP")
@NamedQuery(name="UserTO.findAll", query="SELECT u FROM UserTO u")
public class UserTO implements Serializable {
	
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The user name. */
	@Id
	@Column(name="USER_NAME")
	private String userName;

	/** The user pass. */
	@Column(name="USER_PASS")
	private String userPass;

	/**
	 * Instantiates a new user TO.
	 */
   public UserTO() {
   }

   /**
    * Instantiates a new user TO.
    *
    * @param userName the user name
    */
   public UserTO(String userName) {
      setUserName(userName);
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
	 * Gets the user pass.
	 *
	 * @return the user pass
	 */
	public String getUserPass() {
		return this.userPass;
	}

	/**
	 * Sets the user pass.
	 *
	 * @param userPass the new user pass
	 */
	public void setUserPass(String userPass) {
		this.userPass = userPass;
	}
	
	@Override
	public boolean equals (Object object) {
	   if(object instanceof UserTO) {
	      UserTO userTO = (UserTO) object;
	      return userName !=null && userName.equals(userTO.getUserName());
	      
	   } else {
	      return false;
	   }
	}

}