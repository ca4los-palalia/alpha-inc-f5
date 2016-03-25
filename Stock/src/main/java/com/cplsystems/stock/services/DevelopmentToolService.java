package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.cplsystems.stock.dao.DevelopmentToolDAO;
import com.cplsystems.stock.domain.DevelopmentTool;

@Service
public class DevelopmentToolService {
	@Autowired
	private DevelopmentToolDAO developmentToolDAO;

	public void save(DevelopmentTool developmentTool) throws DataAccessException {
		this.developmentToolDAO.save(developmentTool);
	}

	public DevelopmentTool getById(Long idDevelopmentTool) throws DataAccessException {
		return this.developmentToolDAO.getById(idDevelopmentTool);
	}

	public List<DevelopmentTool> getAll() throws DataAccessException {
		return this.developmentToolDAO.getAll();
	}
}
