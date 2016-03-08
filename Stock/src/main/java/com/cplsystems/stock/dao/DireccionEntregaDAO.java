package com.cplsystems.stock.dao;

import com.cplsystems.stock.domain.Direccion;
import com.cplsystems.stock.domain.DireccionEntrega;
import java.util.List;

public abstract interface DireccionEntregaDAO {
	public abstract void save(DireccionEntrega paramDireccionEntrega);

	public abstract void delete(DireccionEntrega paramDireccionEntrega);

	public abstract DireccionEntrega getById(Long paramLong);

	public abstract List<DireccionEntrega> getByDireccion(Direccion paramDireccion);

	public abstract List<DireccionEntrega> getAll();
}
