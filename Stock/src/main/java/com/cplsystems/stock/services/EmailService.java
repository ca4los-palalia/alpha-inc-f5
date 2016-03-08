package com.cplsystems.stock.services;

import com.cplsystems.stock.dao.EmailDAO;
import com.cplsystems.stock.domain.Email;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	@Autowired
	private EmailDAO emailDAO;

	public void save(Email email) throws DataAccessException {
		this.emailDAO.save(email);
	}

	public void delete(Email email) throws DataAccessException {
		this.emailDAO.delete(email);
	}

	public Email getById(Long proyecto) throws DataAccessException {
		return this.emailDAO.getById(proyecto);
	}

	public List<Email> getAll() throws DataAccessException {
		return this.emailDAO.getAll();
	}

	public Email getUltimoRegistroEmail() throws DataAccessException {
		return this.emailDAO.getUltimoRegistroEmail();
	}
}
