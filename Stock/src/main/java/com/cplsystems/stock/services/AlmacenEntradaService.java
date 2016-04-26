package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.cplsystems.stock.dao.AlmacenEntradaDAO;
import com.cplsystems.stock.domain.Almacen;
import com.cplsystems.stock.domain.AlmacenEntrada;
import com.cplsystems.stock.domain.Area;
import com.cplsystems.stock.domain.Cotizacion;
import com.cplsystems.stock.domain.OrdenCompra;
import com.cplsystems.stock.domain.Producto;
import com.cplsystems.stock.domain.Proveedor;

@Service
public class AlmacenEntradaService {
	@Autowired
	private AlmacenEntradaDAO almacenEntradaDAO;

	public void save(AlmacenEntrada almacenEntrada) throws DataAccessException {
		this.almacenEntradaDAO.save(almacenEntrada);
	}

	public void delete(AlmacenEntrada almacenEntrada) throws DataAccessException {
		this.almacenEntradaDAO.delete(almacenEntrada);
	}

	public AlmacenEntrada getById(Long idAlmacenEntrada) throws DataAccessException {
		return this.almacenEntradaDAO.getById(idAlmacenEntrada);
	}

	public List<AlmacenEntrada> getAll() throws DataAccessException {
		return this.almacenEntradaDAO.getAll();
	}
	
	public List<AlmacenEntrada> getByArea(Area area) throws DataAccessException {
		return this.almacenEntradaDAO.getByArea(area);
	}
	public List<AlmacenEntrada> getByCotizacion(Cotizacion cotizacion) throws DataAccessException {
		return this.almacenEntradaDAO.getByCotizacion(cotizacion);
	}
	public List<AlmacenEntrada> getByOrdenCompra(OrdenCompra ordenCompra) throws DataAccessException {
		return this.almacenEntradaDAO.getByOrdenCompra(ordenCompra);
	}
	public List<AlmacenEntrada> getByAlmacen(Almacen almacen) throws DataAccessException {
		return this.almacenEntradaDAO.getByAlmacen(almacen);
	}
	
	public List<AlmacenEntrada> getByOrdenCompraProductoProveedor(OrdenCompra ordenCompra, Producto producto, Proveedor proveedor) throws DataAccessException {
		return this.almacenEntradaDAO.getByOrdenCompraProductoProveedor(ordenCompra, producto, proveedor);
	}
}
