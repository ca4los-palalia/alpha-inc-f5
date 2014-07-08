/**
 * 
 */
package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplsystems.stock.dao.EstatusRequisicionDAO;
import com.cplsystems.stock.domain.EstatusRequisicion;

/**
 * @author Carlos Palalía López
 */

@Service
public class EstatusRequisicionService {

	@Autowired
	private EstatusRequisicionDAO estatusRequisicionDAO;

	public void save(EstatusRequisicion estatusRequisicion){
		estatusRequisicionDAO.save(estatusRequisicion);
	}

	public void delete(EstatusRequisicion estatusRequisicion){
		estatusRequisicionDAO.delete(estatusRequisicion);
	}

	public EstatusRequisicion getById(Long idEstatusRequisicion){
		return estatusRequisicionDAO.getById(idEstatusRequisicion);
	}

	public List<EstatusRequisicion> getAll(){
		return estatusRequisicionDAO.getAll();
	}
	
	public EstatusRequisicion getByNombre(String nombre){
		return estatusRequisicionDAO.getByNombre(nombre);
	}
	
	public EstatusRequisicion getByClave(String clave){
		return estatusRequisicionDAO.getByClave(clave);
	}

	public EstatusRequisicion getByEstado(boolean estado){
		return estatusRequisicionDAO.getByEstado(estado);
	}
}
