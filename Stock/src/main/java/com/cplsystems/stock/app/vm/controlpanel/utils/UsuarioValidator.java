package com.cplsystems.stock.app.vm.controlpanel.utils;

import com.cplsystems.stock.app.utils.StockUtils;
import com.cplsystems.stock.app.vm.controlpanel.UsuarioVM;
import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.Usuarios;
import java.io.Serializable;
import java.util.Map;
import org.zkoss.bind.Property;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.validator.AbstractValidator;

public class UsuarioValidator extends AbstractValidator implements Serializable {
	private static final long serialVersionUID = 876922188396805982L;
	private Map<String, Property> beanProps;

	public void validate(ValidationContext ctx) {
		this.beanProps = ctx.getProperties(ctx.getProperty().getBase());
		UsuarioVM usuarioVM = (UsuarioVM) ((Property) this.beanProps.get(".")).getBase();
		/*
		if ((usuarioVM.getOrganizacion().getNombre() == null) || (usuarioVM.getOrganizacion().getNombre().isEmpty())
				|| (usuarioVM.getOrganizacion().getCalle() == null)
				|| (usuarioVM.getOrganizacion().getCalle().isEmpty()) || (usuarioVM.getOrganizacion().getRfc() == null)
				|| (usuarioVM.getOrganizacion().getRfc().isEmpty()) || (usuarioVM.getUsuario().getBenutzer() == null)
				|| (usuarioVM.getUsuario().getBenutzer().isEmpty()) || (usuarioVM.getUsuario().getKennwort() == null)
				|| (usuarioVM.getUsuario().getKennwort().isEmpty())
				|| (usuarioVM.getUsuario().getRetypeKennwort() == null)
				|| (usuarioVM.getUsuario().getRetypeKennwort().isEmpty())) {
			StockUtils.showSuccessmessage("Los campos marcados con (*) son necesarios", "error", Integer.valueOf(3000),
					null);

			addInvalidMessage(ctx, "");
			return;
		}
		
		*/
		
		if ((usuarioVM.getOrganizacion().getNombre() == null) || (usuarioVM.getOrganizacion().getNombre().isEmpty())
				|| (usuarioVM.getOrganizacion().getDireccion().getCalle() == null)
				|| (usuarioVM.getOrganizacion().getDireccion().getCalle().isEmpty()) || (usuarioVM.getOrganizacion().getRfc() == null)
				|| (usuarioVM.getOrganizacion().getRfc().isEmpty()) || (usuarioVM.getUsuario().getBenutzer() == null)
				|| (usuarioVM.getUsuario().getBenutzer().isEmpty()) || (usuarioVM.getUsuario().getKennwort() == null)
				|| (usuarioVM.getUsuario().getKennwort().isEmpty())
				|| (usuarioVM.getUsuario().getRetypeKennwort() == null)
				|| (usuarioVM.getUsuario().getRetypeKennwort().isEmpty())) {
			StockUtils.showSuccessmessage("Los campos marcados con (*) son necesarios", "error", Integer.valueOf(3000),
					null);

			addInvalidMessage(ctx, "");
			return;
		}
		
		if (!usuarioVM.getUsuario().getKennwort().equals(usuarioVM.getUsuario().getRetypeKennwort())) {
			StockUtils.showSuccessmessage(
					"La contrase�a no pudo ser verificada, ya que los valores ingresados no coinciden", "error",
					Integer.valueOf(3000), null);

			addInvalidMessage(ctx, "");
			return;
		}
	}
}
