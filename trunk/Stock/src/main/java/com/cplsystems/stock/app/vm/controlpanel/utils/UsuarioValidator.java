/**
 * 
 */
package com.cplsystems.stock.app.vm.controlpanel.utils;

import java.io.Serializable;
import java.util.Map;

import org.zkoss.bind.Property;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.validator.AbstractValidator;
import org.zkoss.zk.ui.util.Clients;

import com.cplsystems.stock.app.utils.StockUtils;
import com.cplsystems.stock.app.vm.controlpanel.UsuarioVM;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
public class UsuarioValidator extends AbstractValidator implements Serializable {

	private static final long serialVersionUID = 876922188396805982L;
	private Map<String, Property> beanProps;

	public void validate(ValidationContext ctx) {
		beanProps = ctx.getProperties(ctx.getProperty().getBase());
		UsuarioVM usuarioVM = (UsuarioVM) beanProps.get(".").getBase();

		if (usuarioVM.getOrganizacion().getNombre() == null
				|| usuarioVM.getOrganizacion().getNombre().isEmpty()
				|| usuarioVM.getOrganizacion().getCalle() == null
				|| usuarioVM.getOrganizacion().getCalle().isEmpty()
				|| usuarioVM.getOrganizacion().getRfc() == null
				|| usuarioVM.getOrganizacion().getRfc().isEmpty()
				|| usuarioVM.getUsuario().getBenutzer() == null
				|| usuarioVM.getUsuario().getBenutzer().isEmpty()
				|| usuarioVM.getUsuario().getKennwort() == null
				|| usuarioVM.getUsuario().getKennwort().isEmpty()
				|| usuarioVM.getUsuario().getRetypeKennwort() == null
				|| usuarioVM.getUsuario().getRetypeKennwort().isEmpty()) {
			StockUtils.showSuccessmessage(
					"Los campos marcados con (*) son necesarios",
					Clients.NOTIFICATION_TYPE_ERROR, 3000, null);
			this.addInvalidMessage(ctx, "");
			return;
		}

		if (!usuarioVM.getUsuario().getKennwort()
				.equals(usuarioVM.getUsuario().getRetypeKennwort())) {
			StockUtils.showSuccessmessage(
					"La contraseña no pudo ser verificada, "
							+ "ya que los valores ingresados no coinciden",
					Clients.NOTIFICATION_TYPE_ERROR, 3000, null);
			this.addInvalidMessage(ctx, "");
			return;
		}
	}
}
