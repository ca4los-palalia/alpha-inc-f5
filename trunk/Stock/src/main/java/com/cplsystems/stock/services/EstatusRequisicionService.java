/**
 * 
 */
package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplsystems.stock.dao.EstatusRequisicionDAO;
import com.cplsystems.stock.domain.EstatusRequisicion;
import com.cplsystems.stock.domain.EstatusRequisicion;

/**
 * @author Carlos Palalía López
 */

@Service
public class EstatusRequisicionService {

	@Autowired
	private EstatusRequisicionDAO EstatusRequisicionDAO;

	public void save(EstatusRequisicion estatusRequisicion){
		EstatusRequisicionDAO.save(estatusRequisicion);
	}

	public void delete(EstatusRequisicion estatusRequisicion){
		EstatusRequisicionDAO.delete(estatusRequisicion);
	}

	public EstatusRequisicion getById(Long idEstatusRequisicion){
		return EstatusRequisicionDAO.getById(idEstatusRequisicion);
	}

	public List<EstatusRequisicion> getAll(){
		return EstatusRequisicionDAO.getAll();
	}
	
	public EstatusRequisicion getByNombre(String nombre){
		return EstatusRequisicionDAO.getByNombre(nombre);
	}
	
	public EstatusRequisicion getByClave(String clave){
		return EstatusRequisicionDAO.getByClave(clave);
	}

	public EstatusRequisicion getByEstado(boolean estado){
		return EstatusRequisicionDAO.getByEstado(estado);
	}
}
