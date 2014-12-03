/**
 * 
 */
package com.cplsystems.stock.app.vm;

import java.io.File;
import java.util.Calendar;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.util.Clients;

import com.cplsystems.stock.app.utils.SessionUtils;
import com.cplsystems.stock.app.utils.SistemaOperativo;
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
	private Calendar fechaEstablecida;
	private SistemaOperativo so = new SistemaOperativo();

	@NotifyChange({ "user", "password" })
	@Command
	public void authenticateUser() {
		try {
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
					sessionUtils.addToSession(SessionUtils.USUARIO, usuario);
					sessionUtils.addToSession(SessionUtils.FIRMA,
							usuario.getOrganizacion());
					stockUtils.redirect(StockConstants.GLOBAL_PAGES.HOME_URL);
					/*fechaEstablecida = stockUtils.convertirStringToCalendar(5, 9, 2014);
					int comparacion = Calendar.getInstance().compareTo(fechaEstablecida);
					if(comparacion < 1){
						sessionUtils.addToSession(SessionUtils.USUARIO, usuario);
						sessionUtils.addToSession(SessionUtils.FIRMA,
								usuario.getOrganizacion());
						stockUtils.redirect(StockConstants.GLOBAL_PAGES.HOME_URL);
					}else
						StockUtils.showSuccessmessage(
								"El sistema ha sido bloqueado. "
										+ "Consulte al administrador",
								Clients.NOTIFICATION_TYPE_ERROR, 0, null);*/
						System.err.println(so.getDirectorioDeInicioDelUsuario() + "Slash: " + so.getSeparadorDeArchivos());
						File folder = new File(StockConstants.CARPETA_ARCHIVOS_COTIZACIONES);
						if(!folder.exists())
							folder.mkdirs();
						folder = new File(StockConstants.CARPETA_ARCHIVOS_LOGOTIPOS);
						if(!folder.exists())
							folder.mkdirs();
						folder = new File(StockConstants.CARPETA_ARCHIVOS_ORDEN_COMPRA);
						if(!folder.exists())
							folder.mkdirs();
						folder = new File(StockConstants.CARPETA_ARCHIVOS_PRODUCTOS);
						if(!folder.exists())
							folder.mkdirs();
						folder = new File(StockConstants.CARPETA_ARCHIVOS_PROVEEDORES);
						if(!folder.exists())
							folder.mkdirs();
						folder = new File(StockConstants.CARPETA_ARCHIVOS_REQUISICIONES);
						if(!folder.exists())
							folder.mkdirs();
						folder = new File(StockConstants.CARPETA_ARCHIVOS_USUARIOS);
						if(!folder.exists())
							folder.mkdirs();
						folder = new File(StockConstants.LAYOUT);
						if(!folder.exists())
							folder.mkdirs();
				}
			}	
		} catch (Exception e) {
			e.printStackTrace();
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
