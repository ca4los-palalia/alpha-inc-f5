/**
 * 
 */
package com.cplsystems.stock.dao;

import java.util.List;

import com.cplsystems.stock.domain.EstatusRequisicion;

/**
 * @author Carlos Palalía López
 */

public interface EstatusRequisicionDAO {

	public void save(EstatusRequisicion estatusRequisicion);

	public void delete(EstatusRequisicion estatusRequisicion);

	public EstatusRequisicion getById(Long idEstatusRequisicion);

	public List<EstatusRequisicion> getAll();
	
	public EstatusRequisicion getByNombre(String nombre);
	
	public EstatusRequisicion getByClave(String clave);
	
	public EstatusRequisicion getByEstado(boolean estado);
}
