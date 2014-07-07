/**
 * 
 */
package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplsystems.stock.dao.RequisicionDAO;
import com.cplsystems.stock.domain.EstatusRequisicion;
import com.cplsystems.stock.domain.Persona;
import com.cplsystems.stock.domain.Requisicion;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
@Service
public class RequisicionService {

	@Autowired
	private RequisicionDAO requisicionDAO;

	public void save(final Requisicion requisicion) {
		requisicionDAO.save(requisicion);
	}

	public void update(final Requisicion requisicion) {
		requisicionDAO.update(requisicion);
	}

	public void delete(final Requisicion requisicion) {
		requisicionDAO.delete(requisicion);
	}

	public Requisicion getById(final Long idRequisicion) {
		return requisicionDAO.getById(idRequisicion);
	}

	public Requisicion getByPersona(final Persona persona) {
		return requisicionDAO.getByPersona(persona);
	}

	public String getUltimoFolio() {
		return requisicionDAO.getUltimoFolio();
	}

	public List<Requisicion> getAll() {
		return requisicionDAO.getAll();
	}

	public List<Requisicion> getByEstatusRequisicion(
			EstatusRequisicion estatusRequisicion) {
		return requisicionDAO.getByEstatusRequisicion(estatusRequisicion);
	}
}