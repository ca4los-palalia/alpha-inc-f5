/**
 * 
 */
package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplsystems.stock.dao.EmailDAO;
import com.cplsystems.stock.domain.Email;

/**
 * @author Carlos Palalía López
 */

@Service
public class EmailService {

	@Autowired
	private EmailDAO emailDAO;

	public void save(Email email){
		emailDAO.save(email);
	}

	public void update(Email email){
		emailDAO.update(email);
	}
	public void delete(Email email){
		emailDAO.delete(email);
	}
	public Email getById(Long proyecto){
		return emailDAO.getById(proyecto);
	}

	public List<Email> getAll(){
		return emailDAO.getAll();
	}
	
}
