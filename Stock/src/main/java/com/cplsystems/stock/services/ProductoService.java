package com.cplsystems.stock.services;

import com.cplsystems.stock.dao.ProductoDAO;
import com.cplsystems.stock.domain.Producto;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {
	@Autowired
	private ProductoDAO productoDAO;

	public void save(Producto producto) throws DataAccessException {
		this.productoDAO.save(producto);
	}

	public void delete(Producto producto) throws DataAccessException {
		this.productoDAO.delete(producto);
	}

	public Producto getById(Long idProducto) throws DataAccessException {
		return this.productoDAO.getById(idProducto);
	}

	public List<Producto> getAll() throws DataAccessException {
		return this.productoDAO.getAll();
	}
	
	public List<Producto> getAllNativeSQL() throws DataAccessException {
		return this.productoDAO.getAllNativeSQL();
	}
	
	public List<Producto> getAllLimited() throws DataAccessException {
		return this.productoDAO.getAllLimited();
	}

	public List<Producto> getItemByKeyOrName(String claveProducto, String nombreProducto) {
		return this.productoDAO.getItemByKeyOrName(claveProducto, nombreProducto);
	}

	public List<String> getAllKeys() {
		return this.productoDAO.getAllKeys();
	}

	public List<Producto> getByClaveNombre(String buscarTexto) {
		return this.productoDAO.getByClaveNombre(buscarTexto);
	}

	public List<Producto> getPreciosMaximos() {
		return this.productoDAO.getPreciosMaximos();
	}

	public List<Producto> getPreciosMinimos() {
		return this.productoDAO.getPreciosMinimos();
	}

	public List<Producto> getPreciosPromedio() {
		return this.productoDAO.getPreciosPromedio();
	}

	public List<Producto> getByPrecio(String precio) {
		return this.productoDAO.getByPrecio(precio);
	}

	public Producto getByClaveNombrePrecioCosto(String buscarTexto) {
		return this.productoDAO.getByClaveNombrePrecioCosto(buscarTexto);
	}

	public Producto getByClave(String clave) {
		return this.productoDAO.getByClave(clave);
	}
}
