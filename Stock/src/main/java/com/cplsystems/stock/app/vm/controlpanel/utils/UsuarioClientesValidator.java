/**
 * 
 */
package com.cplsystems.stock.app.vm.controlpanel.utils;

import java.util.Map;

import org.zkoss.bind.Property;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.validator.AbstractValidator;
import org.zkoss.zk.ui.util.Clients;

import com.cplsystems.stock.app.utils.StockUtils;
import com.cplsystems.stock.app.vm.controlpanel.UsuariosClientesVM;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
public class UsuarioClientesValidator extends AbstractValidator {
	private Map<String, Property> beanProps;
	private boolean usuarioExistente;

	public void validate(ValidationContext ctx) {
		beanProps = ctx.getProperties(ctx.getProperty().getBase());
		UsuariosClientesVM usuariosClientesVM = (UsuariosClientesVM) beanProps
				.get(".").getBase();
		if (usuariosClientesVM != null) {
			if (usuariosClientesVM.getUsuarioSeleccionado().getBenutzer() == null
					|| usuariosClientesVM.getUsuarioSeleccionado()
							.getBenutzer().isEmpty()) {
				this.addInvalidMessage(ctx, "");
				StockUtils.showSuccessmessage(
						"El nombre de usuario es un valor requerido",
						Clients.NOTIFICATION_TYPE_WARNING, 3000, null);
				return;
			}
			usuarioExistente = usuariosClientesVM.getUsuarioService()
					.verificarNombreUsuario(
							usuariosClientesVM.getUsuarioSeleccionado()
									.getBenutzer(),
							usuariosClientesVM.getUsuarioSeleccionado()
									.getIdUsuario());
			if (usuarioExistente) {
				StockUtils.showSuccessmessage(
						"El nombre de usuario que ha especificado ya se encuentra en uso, "
								+ "por favor ingrese uno diferente",
						Clients.NOTIFICATION_TYPE_WARNING, 0, null);
				this.addInvalidMessage(ctx, "");
				return;
			}

			if (usuariosClientesVM.getUsuarioSeleccionado().getPersona()
					.getApellidoPaterno() == null
					|| usuariosClientesVM.getUsuarioSeleccionado().getPersona()
							.getApellidoPaterno().isEmpty()
					|| usuariosClientesVM.getUsuarioSeleccionado().getPersona()
							.getApellidoMaterno() == null
					|| usuariosClientesVM.getUsuarioSeleccionado().getPersona()
							.getApellidoMaterno().isEmpty()
					|| usuariosClientesVM.getUsuarioSeleccionado()
							.getKennwort() == null
					|| usuariosClientesVM.getUsuarioSeleccionado()
							.getKennwort().isEmpty()
					|| usuariosClientesVM.getUsuarioSeleccionado()
							.getRetypeKennwort() == null
					|| usuariosClientesVM.getUsuarioSeleccionado()
							.getRetypeKennwort().isEmpty()
					|| usuariosClientesVM.getUsuarioSeleccionado().getPersona()
							.getContacto().getEmail().getEmail() == null
					|| usuariosClientesVM.getUsuarioSeleccionado().getPersona()
							.getContacto().getEmail().getEmail().isEmpty()) {
				StockUtils.showSuccessmessage(
						"Los campos marcados con (*) son obligatorios."
								+ "Verifique que la información es  correcta",
						Clients.NOTIFICATION_TYPE_WARNING, 3500, null);
				this.addInvalidMessage(ctx, "");
				return;
			}
			if (!usuariosClientesVM
					.getUsuarioSeleccionado()
					.getKennwort()
					.equals(usuariosClientesVM.getUsuarioSeleccionado()
							.getRetypeKennwort())) {
				StockUtils.showSuccessmessage(
						"La clave de acceso que ha definido no coincide con la comprobación. "
								+ "Verifique que ambas claves son correctas",
						Clients.NOTIFICATION_TYPE_WARNING, 4500, null);
				this.addInvalidMessage(ctx, "");
				return;
			}

		}
	}
}
