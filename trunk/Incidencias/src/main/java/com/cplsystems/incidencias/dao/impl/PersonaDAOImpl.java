/**
 * 
 */
package com.cplsystems.incidencias.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.cplsystems.incidencias.dao.PersonaDAO;
import com.cplsystems.incidencias.domain.Persona;

/**
 * 
 * 
 */
@Repository
public class PersonaDAOImpl extends HibernateDaoSupport implements PersonaDAO {

    @Autowired
    public void init(SessionFactory sessionFactory) {
	super.setSessionFactory(sessionFactory);
    }

    public void save(Persona persona) {
	getHibernateTemplate().save(persona);
    }

}
