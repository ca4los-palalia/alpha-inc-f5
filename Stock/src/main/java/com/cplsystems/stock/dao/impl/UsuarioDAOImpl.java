package com.cplsystems.stock.dao.impl;

import com.cplsystems.stock.dao.UsuarioDAO;
import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.Usuarios;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UsuarioDAOImpl extends HibernateDaoSupport implements UsuarioDAO {
	@Autowired
	public void init(SessionFactory sessionFactory) throws DataAccessException {
		setSessionFactory(sessionFactory);
	}

	@Transactional(readOnly = true)
	public Usuarios getUsuarioByCredentials(String usuario, String password) {
		List<Usuarios> user = getHibernateTemplate().find(
				"FROM Usuarios as u LEFT JOIN FETCH u.organizacion as o WHERE u.benutzer = ? AND u.kennwort = ?",
				new Object[] { usuario, password });

		return user.size() > 0 ? (Usuarios) user.get(0) : null;
	}

	@Transactional
	public void save(Usuarios usuarios) {
		getHibernateTemplate().saveOrUpdate(usuarios);
	}

	@Transactional
	public void delete(Usuarios usuario) {
		getHibernateTemplate().delete(usuario);
	}

	@Transactional(readOnly = true)
	public List<Usuarios> getUsuariosByOrganizacion(Organizacion organizacion) {
		List<Usuarios> usuarios = getHibernateTemplate().find(
				"FROM Usuarios as u LEFT JOIN FETCH u.organizacion as o WHERE u.organizacion = ?AND u.client = ? AND u.owner = ?",
				new Object[] { organizacion, Boolean.valueOf(false), Boolean.valueOf(false) });

		return usuarios.size() > 0 ? usuarios : null;
	}

	@Transactional(readOnly = true)
	public List<Usuarios> getUsuariosByOrganizacionAll(Organizacion organizacion) {
		List<Usuarios> usuarios = getHibernateTemplate()
				.find("FROM Usuarios as u LEFT JOIN FETCH u.organizacion as o WHERE u.organizacion = ?", organizacion);

		return usuarios.size() > 0 ? usuarios : null;
	}

	@Transactional(readOnly = true)
	public boolean verificarNombreUsuario(String benutzer, Long idUsuario) {
		Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Usuarios.class);

		criteria.add(Restrictions.like("benutzer", "%" + benutzer + "%"));
		criteria.add(Restrictions.ne("idUsuario", idUsuario));
		return criteria.list().size() > 0;
	}

	@Transactional(readOnly = true)
	public Usuarios getClienteByOrganizacion(Organizacion organizacion) {
		List<Usuarios> usuarios = getHibernateTemplate().find(
				"FROM Usuarios as u WHERE u.organizacion = ? AND u.client = ?",
				new Object[] { organizacion, Boolean.valueOf(true) });

		return usuarios.size() > 0 ? (Usuarios) usuarios.get(0) : null;
	}

	@Transactional(readOnly = true)
	public Usuarios getOwner(Organizacion organizacion) {
		List<Usuarios> usuarios = getHibernateTemplate().find("FROM Usuarios as u WHERE u.owner = ? ",
				Boolean.valueOf(true));

		return usuarios.size() > 0 ? (Usuarios) usuarios.get(0) : null;
	}

	@Transactional(readOnly = true)
	public List<Usuarios> getAll() {
		List<Usuarios> usuarios = getHibernateTemplate().find("FROM Usuarios ");

		return usuarios.size() > 0 ? usuarios : null;
	}
}
