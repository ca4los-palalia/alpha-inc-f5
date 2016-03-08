package com.cplsystems.stock.services;

import com.cplsystems.stock.dao.EstatusRequisicionDAO;
import com.cplsystems.stock.domain.EstatusRequisicion;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EstatusRequisicionService {
	@Autowired
	private EstatusRequisicionDAO estatusRequisicionDAO;

	public void save(EstatusRequisicion estatusRequisicion) {
		this.estatusRequisicionDAO.save(estatusRequisicion);
	}

	public void delete(EstatusRequisicion estatusRequisicion) {
		this.estatusRequisicionDAO.delete(estatusRequisicion);
	}

	public EstatusRequisicion getById(Long idEstatusRequisicion) {
		return this.estatusRequisicionDAO.getById(idEstatusRequisicion);
	}

	public List<EstatusRequisicion> getAll() {
		return this.estatusRequisicionDAO.getAll();
	}

	public EstatusRequisicion getByNombre(String nombre) {
		return this.estatusRequisicionDAO.getByNombre(nombre);
	}

	public EstatusRequisicion getByClave(String clave) {
		return this.estatusRequisicionDAO.getByClave(clave);
	}

	public EstatusRequisicion getByEstado(boolean estado) {
		return this.estatusRequisicionDAO.getByEstado(estado);
	}
}
