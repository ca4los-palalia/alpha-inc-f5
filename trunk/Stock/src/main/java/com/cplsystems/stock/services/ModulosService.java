/**
 * 
 */
package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplsystems.stock.dao.ModulosDAO;
import com.cplsystems.stock.domain.Modulos;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
@Service
public class ModulosService {

	@Autowired
	private ModulosDAO modulosDAO;

	public void save(final Modulos modulos) {
		modulosDAO.save(modulos);
	}

	public void delete(final Modulos modulos) {
		modulosDAO.delete(modulos);
	}

	public List<Modulos> getAll() {
		return modulosDAO.getAll();
	}
}
