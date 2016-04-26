package com.cplsystems.stock.app.vm.controlpanel;

import java.io.IOException;
import java.util.ArrayList;

import org.zkoss.bind.BindContext;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Messagebox;

import com.cplsystems.stock.app.utils.StockUtils;
import com.cplsystems.stock.app.vm.controlpanel.utils.ControlPanelMetaclass;
import com.cplsystems.stock.domain.Giro;
import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.Usuarios;

@VariableResolver({ DelegatingVariableResolver.class })
public class CatalogoGiroVM extends ControlPanelMetaclass {
	private static final long serialVersionUID = -8141487067470696501L;

	@Init
	public void init() {
		giros = giroService.getAll();
		usuario = (Usuarios) sessionUtils.getFromSession("usuario");
		organizacion = (Organizacion) sessionUtils.getFromSession("FIRMA");
	}

	@Command
	@NotifyChange({ "*" })
	public void save() {
		guardarGiros();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	@NotifyChange({ "*" })
	public void removerGiro(@BindingParam("index") Integer index) {
		boolean continuarEliminacion = true;
		giro = ((Giro) giros.get(index.intValue()));
		try {
			giroService.delete(giro);
		} catch (Exception e) {
			continuarEliminacion = false;
		}
		if (continuarEliminacion) {
			giros.remove(giro);
			giros = new ArrayList();
			giros = giroService.getAll();
			StockUtils.showSuccessmessage("El giro -" + giro.getNombre() + "- ha sido eliminado del catalogo", "info",
					Integer.valueOf(0), null);
		} else {
			StockUtils.showSuccessmessage(
					"El giro -" + giro.getNombre() + "- esta siendo utilizado. No puede ser eliminado", "error",
					Integer.valueOf(0), null);
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	@NotifyChange({ "*" })
	public void agregarNuevoGiro() {
		if (giros == null) {
			giros = new ArrayList();
		}
		Giro nueoGiro = new Giro();
		giros.add(nueoGiro);
		mensajeDeCambios = "No olvide salvar sus cambios";
	}
	
	@Command
	@NotifyChange({ "giros" })
	public void onUploadExcelGiro(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) throws IOException {
		giros = leerDatosDesdeExcelGiros(getStreamMediaExcel(ctx), 0);
		if (giros.size() > 0) {
			for (Giro item : giros) {
				item.setOrganizacion(organizacion);
				giroService.save(item);
			}
			Messagebox.show(giros.size() + " Giros Importados");
		} else
			Messagebox.show("No se importaron Giros. El documento esta vacio");
	}
}
