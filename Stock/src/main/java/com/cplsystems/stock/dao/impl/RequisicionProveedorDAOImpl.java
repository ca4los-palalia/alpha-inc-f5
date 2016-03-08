package com.cplsystems.stock.dao.impl;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.dao.RequisicionProveedorDAO;
import com.cplsystems.stock.domain.Proveedor;
import com.cplsystems.stock.domain.Requisicion;
import com.cplsystems.stock.domain.RequisicionProveedor;
import java.util.List;
import org.springframework.stereotype.Repository;

@Repository
public class RequisicionProveedorDAOImpl extends HibernateDAOSuportUtil implements RequisicionProveedorDAO {
	public void save(RequisicionProveedor requisicionProveedor) {
	}

	public void update(RequisicionProveedor requisicionProveedor) {
	}

	public void delete(RequisicionProveedor requisicionProveedor) {
	}

	public RequisicionProveedor getById(Long idRequisicionProveedor) {
		return null;
	}

	public List<RequisicionProveedor> getByRequisicion(Requisicion requisicion) {
		return null;
	}

	public List<RequisicionProveedor> getByProveedor(Proveedor Proveedor) {
		return null;
	}

	public List<RequisicionProveedor> getAll() {
		return null;
	}
}
