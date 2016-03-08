package com.cplsystems.stock.dao;

import com.cplsystems.stock.domain.Modulos;
import java.util.List;

public abstract interface ModulosDAO {
	public abstract void save(Modulos paramModulos);

	public abstract void delete(Modulos paramModulos);

	public abstract List<Modulos> getAll();
}
