package com.cplsystems.stock.services;

import com.cplsystems.stock.dao.ModulosDAO;
import com.cplsystems.stock.domain.Modulos;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModulosService {
	@Autowired
	private ModulosDAO modulosDAO;

	public void save(Modulos modulos) {
		this.modulosDAO.save(modulos);
	}

	public void delete(Modulos modulos) {
		this.modulosDAO.delete(modulos);
	}

	public List<Modulos> getAll() {
		return this.modulosDAO.getAll();
	}
}
