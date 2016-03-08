package com.cplsystems.stock.app.vm;

import com.cplsystems.stock.app.utils.SessionUtils;
import com.cplsystems.stock.app.utils.SistemaOperativo;
import com.cplsystems.stock.app.utils.StockConstants;
import com.cplsystems.stock.app.utils.StockUtils;
import com.cplsystems.stock.domain.Usuarios;
import com.cplsystems.stock.services.UsuarioService;
import java.io.File;
import java.io.PrintStream;
import java.util.Calendar;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;

@VariableResolver({ DelegatingVariableResolver.class })
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
			Usuarios usuario = this.usuarioService.getUsuarioByCredentials(this.user, this.password);
			if (usuario == null) {
				StockUtils.showSuccessmessage(
						"Las credenciales de acceso son incorrectas. Por favor intente nuevamente", "error",
						Integer.valueOf(2000), null);

				this.user = "";
				this.password = "";
				return;
			}
			this.sessionUtils.addToSession("usuario", usuario);
			this.sessionUtils.addToSession("FIRMA", usuario.getOrganizacion());

			this.stockUtils.redirect("/home.zul");

			System.err
					.println(this.so.getDirectorioDeInicioDelUsuario() + "Slash: " + this.so.getSeparadorDeArchivos());
			File folder = new File(StockConstants.CARPETA_ARCHIVOS_COTIZACIONES);
			if (!folder.exists()) {
				folder.mkdirs();
			}
			folder = new File(StockConstants.CARPETA_ARCHIVOS_LOGOTIPOS);
			if (!folder.exists()) {
				folder.mkdirs();
			}
			folder = new File(StockConstants.CARPETA_ARCHIVOS_ORDEN_COMPRA);
			if (!folder.exists()) {
				folder.mkdirs();
			}
			folder = new File(StockConstants.CARPETA_ARCHIVOS_PRODUCTOS);
			if (!folder.exists()) {
				folder.mkdirs();
			}
			folder = new File(StockConstants.CARPETA_ARCHIVOS_PROVEEDORES);
			if (!folder.exists()) {
				folder.mkdirs();
			}
			folder = new File(StockConstants.CARPETA_ARCHIVOS_REQUISICIONES);
			if (!folder.exists()) {
				folder.mkdirs();
			}
			folder = new File(StockConstants.CARPETA_ARCHIVOS_USUARIOS);
			if (!folder.exists()) {
				folder.mkdirs();
			}
			folder = new File(StockConstants.LAYOUT);
			if (!folder.exists()) {
				folder.mkdirs();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void copiarLayouts(String origen, String destino) {
		String copiarLayouts = generarUrlString(origen);

		File archivoLayout = new File(destino);
		if (!archivoLayout.exists()) {
			fileCopy(copiarLayouts, destino);
		}
	}

	public String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
