package com.cplsystems.stock.services;

import com.cplsystems.stock.dao.PaisDAO;
import com.cplsystems.stock.domain.Pais;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaisService {
	@Autowired
	private PaisDAO paisDAO;

	public void save(Pais pais) {
		this.paisDAO.save(pais);
	}

	public void delete(Pais pais) {
		this.paisDAO.delete(pais);
	}

	public List<Pais> getAll() {
		return this.paisDAO.getAll();
	}

	public Pais findById(Long idPais) {
		return this.paisDAO.findById(idPais);
	}
}
