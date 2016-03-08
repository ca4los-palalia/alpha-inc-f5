package com.cplsystems.stock.services;

import com.cplsystems.stock.dao.CodigoBarrasProductoDAO;
import com.cplsystems.stock.domain.CodigoBarrasProducto;
import com.cplsystems.stock.domain.Producto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CodigoBarrasProductoService {
	@Autowired
	private CodigoBarrasProductoDAO codigoBarrasProductoDAO;

	public void save(CodigoBarrasProducto codigoBarrasProducto) {
		this.codigoBarrasProductoDAO.save(codigoBarrasProducto);
	}

	public void delete(CodigoBarrasProducto codigoBarrasProducto) {
		this.codigoBarrasProductoDAO.delete(codigoBarrasProducto);
	}

	public CodigoBarrasProducto getById(Long idCodigoBarrasProducto) {
		return this.codigoBarrasProductoDAO.getById(idCodigoBarrasProducto);
	}

	public List<CodigoBarrasProducto> getAll() {
		return this.codigoBarrasProductoDAO.getAll();
	}

	public List<CodigoBarrasProducto> getByCodigo(String codigo) {
		return this.codigoBarrasProductoDAO.getByCodigo(codigo);
	}

	public List<CodigoBarrasProducto> getByProducto(Producto producto) {
		return this.codigoBarrasProductoDAO.getByProducto(producto);
	}
}
