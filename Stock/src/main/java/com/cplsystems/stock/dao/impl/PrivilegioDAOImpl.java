package com.cplsystems.stock.dao.impl;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.app.utils.SessionUtils;
import com.cplsystems.stock.app.utils.UserPrivileges;
import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.PrivilegioDAO;
import com.cplsystems.stock.domain.Privilegios;
import com.cplsystems.stock.domain.Usuarios;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PrivilegioDAOImpl extends HibernateDAOSuportUtil implements PrivilegioDAO {
	@Autowired
	private SessionUtils sessionUtils;

	@Transactional
	public void save(Privilegios privilegios) {
		getHibernateTemplate().saveOrUpdate(privilegios);
	}

	@Transactional
	public void delete(Privilegios privilegios) {
		getHibernateTemplate().delete(privilegios);
	}

	@Transactional(readOnly = true)
	public List<Privilegios> getPrivilegiosByUsuario(Usuarios usuarios) {
		List<Privilegios> privilegios = getHibernateTemplate()
				.find("FROM Privilegios as p LEFT JOIN FETCH p.usuarios as u WHERE p.usuarios = ?", usuarios);

		return privilegios;
	}

	@Transactional(readOnly = true)
	public List<Privilegios> getUsuariosByPrivilegio(UserPrivileges privilegio) {
		List<Privilegios> usuarios = getHibernateTemplate().find(
				"FROM Privilegios as p LEFT JOIN FETCH p.usuarios as u LEFT JOIN FETCH u.persona.contacto.email as e WHERE p.userPrivileges = ? AND u.organizacion = ?",
				new Object[] { privilegio, (Organizacion) this.sessionUtils.getFromSession("FIRMA") });

		return usuarios;
	}
}
