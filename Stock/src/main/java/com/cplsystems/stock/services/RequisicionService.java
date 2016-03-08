package com.cplsystems.stock.services;

import com.cplsystems.stock.dao.RequisicionDAO;
import com.cplsystems.stock.domain.Area;
import com.cplsystems.stock.domain.EstatusRequisicion;
import com.cplsystems.stock.domain.Persona;
import com.cplsystems.stock.domain.Requisicion;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequisicionService {
	@Autowired
	private RequisicionDAO requisicionDAO;

	public void save(Requisicion requisicion) {
		this.requisicionDAO.save(requisicion);
	}

	public void update(Requisicion requisicion) {
		this.requisicionDAO.update(requisicion);
	}

	public void delete(Requisicion requisicion) {
		this.requisicionDAO.delete(requisicion);
	}

	public Requisicion getById(Long idRequisicion) {
		return this.requisicionDAO.getById(idRequisicion);
	}

	public Requisicion getByPersona(Persona persona) {
		return this.requisicionDAO.getByPersona(persona);
	}

	public String getUltimoFolio() {
		return this.requisicionDAO.getUltimoFolio();
	}

	public List<Requisicion> getAll() {
		return this.requisicionDAO.getAll();
	}

	public List<Requisicion> getByEstatusRequisicion(EstatusRequisicion estatusRequisicion) {
		return this.requisicionDAO.getByEstatusRequisicion(estatusRequisicion);
	}

	public Requisicion getByFolio(String folio) {
		return this.requisicionDAO.getByFolio(folio);
	}

	public List<Requisicion> getByUnidadResponsable(Area area) {
		return this.requisicionDAO.getByUnidadResponsable(area);
	}

	public List<Requisicion> getRequisicionesConListaDeEstatusFolioArea(List<EstatusRequisicion> estatusRequisiciones,
			String folio, Area area) {
		return this.requisicionDAO.getRequisicionesConListaDeEstatusFolioArea(estatusRequisiciones, folio, area);
	}
}
