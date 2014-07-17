/**
 * 
 */
package com.cplsystems.stock.app.vm;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.util.Clients;

import com.cplsystems.stock.app.utils.SessionUtils;
import com.cplsystems.stock.app.utils.StockConstants;
import com.cplsystems.stock.app.utils.StockUtils;
import com.cplsystems.stock.domain.Usuarios;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class LoginVM extends BasicStructure {

	private static final long serialVersionUID = -2184499179368861931L;
	private String user;
	private String password;

	@NotifyChange({ "user", "password" })
	@Command
	public void authenticateUser() {
		Usuarios usuario = usuarioService.getUsuarioByCredentials(user,
				password);
		PERFORM_AUTENTICATION: {
			if (usuario == null) {
				StockUtils.showSuccessmessage(
						"Las credenciales de acceso son incorrectas. "
								+ "Por favor intente nuevamente",
						Clients.NOTIFICATION_TYPE_ERROR, 2000, null);
				user = "";
				password = "";
				return;
			} else {
				mailService.sendHTMLMail("csr.plz@gmail.com",
						"csr.plz@gmail.com", "DEMO", "KEINE AHNUNG");
				sessionUtils.addToSession(SessionUtils.USUARIO, usuario);
				sessionUtils.addToSession(SessionUtils.FIRMA,
						usuario.getOrganizacion());
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
