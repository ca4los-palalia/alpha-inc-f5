/**
 * 
 */
package com.cplsystems.stock.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.cplsystems.stock.dao.UsuarioDAO;
import com.cplsystems.stock.domain.Usuario;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
@Repository
public class UsuarioDAOImpl extends HibernateDaoSupport implements UsuarioDAO {

	@SuppressWarnings("unchecked")
	@Transactional(readOnly = true)
	public Usuario getUsuarioByCredentials(String usuario, String password) {
		List<Usuario> user = getHibernateTemplate()
				.find("FROM Usuario as u WHERE u.usuario = ? "
						+ "AND u.password = ?", usuario, password);
		return user.size() > 0 ? user.get(0) : null;
	}
}
