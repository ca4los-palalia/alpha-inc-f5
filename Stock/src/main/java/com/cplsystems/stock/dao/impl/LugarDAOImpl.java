package com.cplsystems.stock.dao.impl;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.dao.LugarDAO;
import com.cplsystems.stock.domain.Lugar;
import com.cplsystems.stock.domain.Proyecto;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class LugarDAOImpl extends HibernateDAOSuportUtil implements LugarDAO {
	public void save(Lugar lugar) {
	}

	public void update(Lugar lugar) {
	}

	public void delete(Lugar lugar) {
	}

	public Lugar getById(Long idLugar) {
		return null;
	}

	public Lugar getByIdProyecto(Proyecto proyecto) {
		return null;
	}

	public Lugar getByNombre(String nombre) {
		return null;
	}

	public List<Lugar> getAll() {
		return null;
	}
}
