package com.cplsystems.stock.domain;

import com.cplsystems.stock.app.utils.UserPrivileges;
import java.util.List;

public abstract interface PrivilegioDAO {
	public abstract void save(Privilegios paramPrivilegios);

	public abstract void delete(Privilegios paramPrivilegios);

	public abstract List<Privilegios> getPrivilegiosByUsuario(Usuarios paramUsuarios);

	public abstract List<Privilegios> getUsuariosByPrivilegio(UserPrivileges paramUserPrivileges);
}
