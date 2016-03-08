package com.cplsystems.stock.services;

import com.cplsystems.stock.dao.CostosProductoDAO;
import com.cplsystems.stock.domain.CostosProducto;
import com.cplsystems.stock.domain.Producto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CostosProductoService {
	@Autowired
	private CostosProductoDAO costosProductoDAO;

	public void save(CostosProducto costosProducto) {
		this.costosProductoDAO.save(costosProducto);
	}

	public void delete(CostosProducto costosProducto) {
		this.costosProductoDAO.delete(costosProducto);
	}

	public CostosProducto getById(Long idCodigoBarrasProducto) {
		return this.costosProductoDAO.getById(idCodigoBarrasProducto);
	}

	public List<CostosProducto> getAll() {
		return this.costosProductoDAO.getAll();
	}

	public CostosProducto getByProducto(Producto producto) {
		return this.costosProductoDAO.getByProducto(producto);
	}
}
