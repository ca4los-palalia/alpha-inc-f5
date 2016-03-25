package com.cplsystems.stock.dao;

import com.cplsystems.stock.domain.DevelopmentTool;
import java.util.List;

public abstract interface DevelopmentToolDAO {
	public abstract void save(DevelopmentTool developmentTool);
	public abstract DevelopmentTool getById(Long idDevelopmentTool);
	public abstract List<DevelopmentTool> getAll();
}
