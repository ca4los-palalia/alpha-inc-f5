/**
 * 
 */
package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplsystems.stock.dao.CotizacionProductoDAO;
import com.cplsystems.stock.domain.CotizacionProducto;


/**
 * @author Carlos Palalía López
 */

@Service
public class CotizacionProductoService {

	@Autowired
	private CotizacionProductoDAO cotizacionProductoDAO;
	
	public void save(CotizacionProducto cotizacionProducto ){
		cotizacionProductoDAO.save(cotizacionProducto);
	}
	public void update(CotizacionProducto cotizacionProducto ){
		cotizacionProductoDAO.update(cotizacionProducto);
	}
	public void delete(CotizacionProducto cotizacionProducto ){
		cotizacionProductoDAO.delete(cotizacionProducto);
	}
	public CotizacionProducto getById(Long idCotizacion){
		return cotizacionProductoDAO.getById(idCotizacion);
	}
	public List<CotizacionProducto> getAll(){
		return cotizacionProductoDAO.getAll();
	}
	
}
