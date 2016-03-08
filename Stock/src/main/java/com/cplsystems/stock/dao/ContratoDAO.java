package com.cplsystems.stock.dao;

import com.cplsystems.stock.domain.Contrato;
import java.util.List;

public abstract interface ContratoDAO {
	public abstract void save(Contrato paramContrato);

	public abstract void delete(Contrato paramContrato);

	public abstract Contrato getById(Long paramLong);

	public abstract List<Contrato> getAll();
}
