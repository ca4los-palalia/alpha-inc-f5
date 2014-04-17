/**
 * 
 */
package com.cplsystems.stock.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.dao.EmailDAO;
import com.cplsystems.stock.domain.Email;

/**
 * @author Carlos Palalía López
 */

@Repository
public class EmailDAOImpl extends HibernateDAOSuportUtil implements EmailDAO{

	public void save(Email email) {
		
	}

	public void update(Email email) {
		
	}

	public void delete(Email email) {
		
	}

	public Email getById(Long idEmail) {
		return null;
	}

	public List<Email> getAll() {
		return null;
	}

   
}
