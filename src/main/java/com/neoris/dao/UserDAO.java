package com.neoris.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import com.neoris.dao.DerbyDAO;

// TODO: Auto-generated Javadoc
/**
 * The Class UserDAO.
 * 
 * @author marco.slehiman
 */
public class UserDAO extends DerbyDAO {

    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new user DAO.
     */
    public UserDAO() {
        super();
    }

    /**
     * Creates the.
     *
     * @param userTO the user TO
     */
    public void create( UserTO userTO) {
        EntityManager em = getEntityManager();
        EntityTransaction et = em.getTransaction();
        et.begin();
        em.persist(userTO);
        et.commit();
     }

    /**
     * Gets the UserTO using JPA Query insted of find method.
     *
     * @param username the username
     * @return the by pk
     */
    public UserTO getByPk(String userName) {
        EntityManager em = getEntityManager();
        StringBuilder queryString = new StringBuilder();
        queryString.append("SELECT c ");
        queryString.append("FROM UserTO c ");
        queryString.append("WHERE c.userName = :userName ");
        Query query = em.createQuery(queryString.toString(), UserTO.class);
        query.setParameter("userName", userName);
        return (UserTO) query.getSingleResult();
    }

    /**
     * Exists.
     *
     * @param userTO the user TO
     * @return true, if successful
     */
    public boolean exists(UserTO userTO) {
        EntityManager em = getEntityManager();
        return em.find(UserTO.class, userTO.getUserName()) != null;
    }

    /**
     * Gets the all.
     *
     * @return the all
     */
    public List<UserTO> getAll() {
        EntityManager em = getEntityManager();
        TypedQuery<UserTO> query = em.createNamedQuery("UserTO.findAll", UserTO.class);
        return query.getResultList();
    }
    
    /**
     * Removes the.
     *
     * @param userName the user name
     */
    public void remove( String userName) {
       EntityManager em = getEntityManager();
       UserTO userTO = em.find(UserTO.class, userName);
       em.remove(userTO);
    }

    /**
     * Removes the.
     *
     * @param userName the user name
     */
    public void update( UserTO userTO) {
       EntityManager em = getEntityManager();
       EntityTransaction et = em.getTransaction();
       et.begin();
       userTO = em.merge(userTO);
       et.commit();
    }


}
