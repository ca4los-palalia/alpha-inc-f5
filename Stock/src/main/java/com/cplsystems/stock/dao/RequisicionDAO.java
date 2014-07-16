/**
 * 
 */
package com.cplsystems.stock.dao;

import java.util.List;

import com.cplsystems.stock.domain.Area;
import com.cplsystems.stock.domain.EstatusRequisicion;
import com.cplsystems.stock.domain.Persona;
import com.cplsystems.stock.domain.Requisicion;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 *
 */
public interface RequisicionDAO {

	public void save(final Requisicion requisicion);
	
	public void update (final Requisicion requisicion);
	
	public void delete (final Requisicion requisicion);
	
	public Requisicion getById(final Long idRequisicion);
	
	public Requisicion getByPersona(final Persona persona);
	
	public String getUltimoFolio();
	
	public List<Requisicion> getAll();
	
	public List<Requisicion> getByEstatusRequisicion(EstatusRequisicion estatusRequisicion);
	
	public Requisicion getByFolio(String folio);
	
	public List<Requisicion> getByUnidadResponsable(Area area);
	
}
