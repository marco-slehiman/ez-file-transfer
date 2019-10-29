package com.neoris.dao;

import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;



// TODO: Auto-generated Javadoc
/**
 * The Class DerbyDAO.
 *
 * @author marco.slehiman
 */
public class DerbyDAO implements Serializable {


    /** The Constant serialVersionUID. */
    private static final long serialVersionUID = 1L;
    
    /** The entity factory. */
    private static EntityManagerFactory entityFactory;
    
    /** The entity manager. */
    private static EntityManager entityManager = null;

    /**
     * Instantiates a new derby DAO.
     */
    public DerbyDAO() {
        if (entityFactory == null) {
            entityFactory = Persistence.createEntityManagerFactory("ezFileTransferPersistenceUnit");
            entityManager = entityFactory.createEntityManager();
        }
    }

    /**
     * Gets the entity manager.
     *
     * @return the entity manager
     */
    protected EntityManager getEntityManager() {
        return entityManager;

    }


}
