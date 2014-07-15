/**
 * 
 */
package com.cplsystems.stock.dao;

import java.util.List;

import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.Usuarios;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
public interface UsuarioDAO {

	Usuarios getUsuarioByCredentials(String usuario, String password);

	void save(Usuarios usuarios);

	void delete(Usuarios usuario);

	List<Usuarios> getUsuariosByOrganizacion(Organizacion organizacion);

	boolean verificarNombreUsuario(String benutzer, Long idUsuario);

	Usuarios getClienteByOrganizacion(Organizacion organizacion);

	Usuarios getOwner(Organizacion organizacion);

}
