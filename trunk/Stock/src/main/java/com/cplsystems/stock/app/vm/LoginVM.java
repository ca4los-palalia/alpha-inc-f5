/**
 * 
 */
package com.cplsystems.stock.app.vm;

import org.zkoss.bind.annotation.Command;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zul.Messagebox;

import com.cplsystems.stock.app.utils.SessionUtils;
import com.cplsystems.stock.app.utils.StockConstants;
import com.cplsystems.stock.domain.Usuario;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class LoginVM extends BasicStructure {

	private String user;
	private String password;

	@Command
	public void authenticateUser() {
		Usuario usuario = usuarioService
				.getUsuarioByCredentials(user, password);
		PERFORM_AUTENTICATION: {
			if (usuario == null) {
				Messagebox.show("Las credenciales de acceso son incorrectas, "
						+ "por favor intente nuevamente", "Error",
						Messagebox.OK, Messagebox.ERROR);
				return;
			} else {
				sessionUtils.addToSession(SessionUtils.USUARIO, usuario);
				stockUtils.redirect(StockConstants.GLOBAL_PAGES.HOME_URL);
			}
		}
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
