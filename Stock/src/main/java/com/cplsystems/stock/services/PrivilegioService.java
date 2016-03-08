package com.cplsystems.stock.services;

import com.cplsystems.stock.app.utils.UserPrivileges;
import com.cplsystems.stock.domain.PrivilegioDAO;
import com.cplsystems.stock.domain.Privilegios;
import com.cplsystems.stock.domain.Usuarios;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrivilegioService {
	@Autowired
	private PrivilegioDAO privilegioDAO;

	public void save(Privilegios privilegios) {
		this.privilegioDAO.save(privilegios);
	}

	public void delete(Privilegios privilegios) {
		this.privilegioDAO.delete(privilegios);
	}

	public List<Privilegios> getPrivilegiosByUsuario(Usuarios usuarios) {
		return this.privilegioDAO.getPrivilegiosByUsuario(usuarios);
	}

	public List<Privilegios> getUsuariosByPrivilegio(UserPrivileges cotizarAutorizar) {
		return this.privilegioDAO.getUsuariosByPrivilegio(cotizarAutorizar);
	}
}
