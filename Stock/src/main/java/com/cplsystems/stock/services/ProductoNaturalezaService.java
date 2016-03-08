package com.cplsystems.stock.services;

import com.cplsystems.stock.dao.ProductoNaturalezaDAO;
import com.cplsystems.stock.domain.ProductoNaturaleza;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoNaturalezaService {
	@Autowired
	private ProductoNaturalezaDAO productoNaturalezaDAO;

	public void save(ProductoNaturaleza productoNaturaleza) {
		this.productoNaturalezaDAO.save(productoNaturaleza);
	}

	public void update(ProductoNaturaleza productoNaturaleza) {
		this.productoNaturalezaDAO.update(productoNaturaleza);
	}

	public void delete(ProductoNaturaleza productoNaturaleza) {
		this.productoNaturalezaDAO.delete(productoNaturaleza);
	}

	public ProductoNaturaleza getById(Long idProductoNaturaleza) {
		return this.productoNaturalezaDAO.getById(idProductoNaturaleza);
	}

	public List<ProductoNaturaleza> getAll() {
		return this.productoNaturalezaDAO.getAll();
	}

	public ProductoNaturaleza getByNombre(String nombre) {
		return this.productoNaturalezaDAO.getByNombre(nombre);
	}

	public ProductoNaturaleza getBySimbolo(String simbolo) {
		return this.productoNaturalezaDAO.getBySimbolo(simbolo);
	}
}
