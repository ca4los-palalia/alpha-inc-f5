/**
 * 
 */
package com.cplsystems.stock.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.dao.ProveedorDAO;
import com.cplsystems.stock.domain.Contacto;
import com.cplsystems.stock.domain.Contrato;
import com.cplsystems.stock.domain.Direccion;
import com.cplsystems.stock.domain.Persona;
import com.cplsystems.stock.domain.Proveedor;

/**
 * @author Carlos Palalía López
 */

@Repository
public class ProveedorDAOImpl extends HibernateDAOSuportUtil implements ProveedorDAO{

	@Transactional
	public void save(Proveedor proveedor) {
		getHibernateTemplate().saveOrUpdate(proveedor);
	}


	@Transactional
	public void delete(Proveedor proveedor) {
		getHibernateTemplate().delete(proveedor);
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Proveedor getById(Long idProveedor) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Proveedor.class);
		criteria.add(Restrictions.eq("idProveedor", idProveedor));
		List<Proveedor> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista.get(0) : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Proveedor> getByContacto(Contacto contacto) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Proveedor.class);
		criteria.add(Restrictions.eq("contacto", contacto));
		List<Proveedor> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Proveedor> getByContrato(Contrato contrato) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Proveedor.class);
		criteria.add(Restrictions.eq("contrato", contrato));
		List<Proveedor> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Proveedor> getByDireccionDevolucion(Direccion direccion) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Proveedor.class);
		criteria.add(Restrictions.eq("direccionDevolucion", direccion));
		List<Proveedor> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Proveedor> getByDireccionFiscal(Direccion direccion) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Proveedor.class);
		criteria.add(Restrictions.eq("direccionFiscal", direccion));
		List<Proveedor> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Proveedor> getByGerenteFinanzas(Persona persona) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Proveedor.class);
		criteria.add(Restrictions.eq("gerenteFinanzas", persona));
		List<Proveedor> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Proveedor> getByGerenteVentas(Persona persona) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Proveedor.class);
		criteria.add(Restrictions.eq("gerenteVentas", persona));
		List<Proveedor> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Proveedor> getByRepresentanteLegal(Persona persona) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Proveedor.class);
		criteria.add(Restrictions.eq("representanteLegal", persona));
		List<Proveedor> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Proveedor> getByRepresentanteClientes(Persona persona) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Proveedor.class);
		criteria.add(Restrictions.eq("representanteAteCliente", persona));
		List<Proveedor> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Proveedor> getAll() {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Proveedor.class);
		List<Proveedor> lista = criteria.list();
		return lista != null && !lista.isEmpty() ? lista : null;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public List<Proveedor> getBysClaveNombreRfc(String buscarTexto) {
		
		List<Proveedor> lista = null;
		
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().
				createCriteria(Proveedor.class);
		criteria.add(Restrictions.sqlRestriction("clave LIKE '%" + buscarTexto + "%'"));
		lista = criteria.list();
		
		if(lista.equals(null) || lista.size() < 1){
			Criteria criteria2 = getHibernateTemplate().getSessionFactory().openSession().
					createCriteria(Proveedor.class);
			criteria2.add(Restrictions.sqlRestriction("nombre LIKE '%" + buscarTexto + "%'"));
			lista = criteria2.list();
			
			
			if(lista.equals(null) || lista.size() < 1){
				Criteria criteria3 = getHibernateTemplate().getSessionFactory().openSession().
						createCriteria(Proveedor.class);
				criteria3.add(Restrictions.sqlRestriction("rfc LIKE '%" + buscarTexto + "%'"));
				lista = criteria3.list();
			}
		}
		
		return lista != null && !lista.isEmpty() ? lista : null;
	}

	
}
