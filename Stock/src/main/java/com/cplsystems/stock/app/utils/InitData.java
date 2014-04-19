/**
 * 
 */
package com.cplsystems.stock.app.utils;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import com.cplsystems.stock.services.ProductoService;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
public class InitData {
	@Autowired
	private ProductoService productoService;

	@PostConstruct
	public void init() {
		insertProductos();
	}

	private void insertProductos() {

	}

}
