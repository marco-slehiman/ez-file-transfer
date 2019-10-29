package com.neoris.facade;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import com.neoris.dao.UserDAO;
import com.neoris.dao.UserTO;
import com.neoris.facade.ManagedBeanBase;

// TODO: Auto-generated Javadoc
/**
 * The Class Users.
 */
@ManagedBean(name = "users")
@SessionScoped
public class Users extends ManagedBeanBase implements PageNavigation {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** The data. */
	private List<UserTO> data = null;

	/** The rows by page. */
	private int rowsByPage = 15;

	/** The current page. */
	private int currentPage = 1;

	/** The user name. */
	private String userName;

	/** The user TO used to edit or remove selected user and create new one . */
	private UserTO userTO;

	/**
	 * Instantiates a new users.
	 */
	public Users() {
		retrieveData();
	}

	/**
	 * Retrieve data.
	 */
	public void retrieveData() {
		UserDAO userDAO = new UserDAO();
		this.data = userDAO.getAll();
		// String username = super.getUserName();
		// UserTO user = new UserTO(super.getUserName());
		// System.out.println( data.indexOf(user));
		boolean salida = data.remove(new UserTO(super.getUserName()));
		System.out.println(salida);
		System.out.println(data.size());
	}

	/**
	 * Buscar.
	 *
	 * @return the string
	 */
	public String search() {
		retrieveData();
		// Testing
		return "users";
	}

	/**
	 * Gets the data.
	 *
	 * @return the data
	 */
	public List<UserTO> getData() {
		return this.data;
	}

	/**
	 * Creation.
	 *
	 * @return the string
	 */
	public String cancel() {
		return "cancel";
	}

	/**
	 * Creates the.
	 *
	 * @return the string
	 */
	public String create() {
		UserDAO userDAO = new UserDAO();
		userDAO.create(userTO);
		this.retrieveData();
		return "done";
	}

	/**
	 * Creation.
	 *
	 * @return the string
	 */
	public String creation() {
		userTO = new UserTO();
		return "done";
	}

	/**
	 * Edition.
	 *
	 * @return the string
	 */
	public String edition() {
		UserDAO userDAO = new UserDAO();
		this.userTO = userDAO.getByPk(this.userName);
		if (this.userTO == null) {
			retrieveData();
			return "error";
		} else {
			return "done";
		}

	}

	/**
	 * Creation.
	 *
	 * @return the string
	 */
	public String remove() {
		UserDAO userDAO = new UserDAO();
		userDAO.remove(userName);
		return "done";
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neoris.facade.PageNavigation#getCurrentPage()
	 */
	@Override
	public int getCurrentPage() {
		// TODO Auto-generated method stub
		return currentPage;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neoris.facade.PageNavigation#getPageCount()
	 */
	@Override
	public int getPageCount() {
		return data == null || data.isEmpty() ? 0
				: data.size() % getRowsByPage() == 0 ? data.size() / getRowsByPage()
						: data.size() / getRowsByPage() + 1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neoris.facade.PageNavigation#getRowsByPage()
	 */
	@Override
	public int getRowsByPage() {
		return rowsByPage;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neoris.facade.PageNavigation#isTheFirstPage()
	 */
	public boolean isTheFirstPage() {
		return getCurrentPage() == 1;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neoris.facade.PageNavigation#getCurrentRow()
	 */
	public int getCurrentRow() {
		return (getCurrentPage() - 1) * getRowsByPage();
	}

	/**
	 * Gets the user TO.
	 *
	 * @return the user TO
	 */
	public UserTO getUserTO() {
		return userTO;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neoris.facade.PageNavigation#isTheLastPage()
	 */
	public boolean isTheLastPage() {
		return getCurrentPage() == getPageCount();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neoris.facade.PageNavigation#setCurrentPage(int)
	 */
	@Override
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neoris.facade.PageNavigation#setRowsByPage(int)
	 */
	@Override
	public void setRowsByPage(int rowsByPage) {
		this.rowsByPage = rowsByPage;

	}

	/**
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	public String getUserName() {
		return userName;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.neoris.facade.PageNavigation#page()
	 */
	public String page() {
		return "page";
	}

	/**
	 * Sets the user name.
	 *
	 * @param userName
	 *            the new user name
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Sets the user TO.
	 *
	 * @param userTO
	 *            the new user TO
	 */
	public void setUserTO(UserTO userTO) {
		this.userTO = userTO;
	}

	/**
	 * Sets the user TO.
	 *
	 * @param userTO
	 *            the new user TO
	 */
	public String update() {
		UserDAO userDAO = new UserDAO();
		userDAO.update(userTO);
		this.retrieveData();
		return "done";
	}

}