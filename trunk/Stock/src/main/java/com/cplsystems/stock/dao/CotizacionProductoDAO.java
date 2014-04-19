/**
 * 
 */
package com.cplsystems.stock.dao;

import java.util.List;

import com.cplsystems.stock.domain.CotizacionProducto;

/**
 * @author Carlos Palalía López
 */

public interface CotizacionProductoDAO {

	public void save(CotizacionProducto cotizacionProducto);

	public void delete(CotizacionProducto cotizacionProducto);

	public CotizacionProducto getById(Long idCotizacion);

	public List<CotizacionProducto> getAll();
}
