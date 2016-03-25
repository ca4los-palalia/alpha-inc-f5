package com.cplsystems.stock.dao;

import java.util.List;

import com.cplsystems.stock.domain.Almacen;
import com.cplsystems.stock.domain.AlmacenEntrada;
import com.cplsystems.stock.domain.Area;
import com.cplsystems.stock.domain.Cotizacion;
import com.cplsystems.stock.domain.OrdenCompra;

public abstract interface AlmacenEntradaDAO {

	public void save(AlmacenEntrada almacenEntrada);

	void delete(AlmacenEntrada almacenEntrada);

	public AlmacenEntrada getById(Long idAlmacenEntrada);

	public List<AlmacenEntrada> getAll();

	public List<AlmacenEntrada> getByArea(Area area);

	public List<AlmacenEntrada> getByCotizacion(Cotizacion cotizacion);

	public List<AlmacenEntrada> getByOrdenCompra(OrdenCompra ordenCompra);

	public List<AlmacenEntrada> getByAlmacen(Almacen almacen);

	
}
