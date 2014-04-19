/**
 * 
 */
package com.cplsystems.stock.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.dao.ProductoDAO;
import com.cplsystems.stock.domain.Producto;

/**
 * @author Carlos Palalía López
 */

@Repository
public class ProductoDAOImpl extends HibernateDAOSuportUtil implements
		ProductoDAO {

	@Transactional
	public void save(Producto producto) {
		getHibernateTemplate().save(producto);

	}

	@Transactional
	public void update(Producto producto) {
		getHibernateTemplate().saveOrUpdate(producto);

	}

	@Transactional
	public void delete(Producto producto) {
		getHibernateTemplate().delete(producto);

	}

	public Producto getById(Long idProducto) {
		return null;
	}

	public List<Producto> getAll() {
		return null;
	}

}
