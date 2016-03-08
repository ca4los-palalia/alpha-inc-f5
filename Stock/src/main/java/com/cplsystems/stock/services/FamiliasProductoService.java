package com.cplsystems.stock.services;

import com.cplsystems.stock.dao.FamiliasProductoDAO;
import com.cplsystems.stock.domain.FamiliasProducto;
import com.cplsystems.stock.domain.Producto;
import com.cplsystems.stock.domain.ProductoTipo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FamiliasProductoService {
	@Autowired
	private FamiliasProductoDAO familiasProductoDAO;

	public void save(FamiliasProducto familiasProducto) {
		this.familiasProductoDAO.save(familiasProducto);
	}

	public void delete(FamiliasProducto familiasProducto) {
		this.familiasProductoDAO.delete(familiasProducto);
	}

	public FamiliasProducto getById(Long idFamiliasProducto) {
		return this.familiasProductoDAO.getById(idFamiliasProducto);
	}

	public List<FamiliasProducto> getAll() {
		return this.familiasProductoDAO.getAll();
	}

	public List<FamiliasProducto> getByProducto(Producto producto) {
		return this.familiasProductoDAO.getByProducto(producto);
	}

	public List<FamiliasProducto> getByFamilia(ProductoTipo productoTipo) {
		return this.familiasProductoDAO.getByFamilia(productoTipo);
	}
}
