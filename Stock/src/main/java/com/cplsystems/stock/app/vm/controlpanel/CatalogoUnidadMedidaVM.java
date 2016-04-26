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
import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.Unidad;
import com.cplsystems.stock.domain.Usuarios;

@VariableResolver({ DelegatingVariableResolver.class })
public class CatalogoUnidadMedidaVM extends ControlPanelMetaclass {
	private static final long serialVersionUID = -8141487067470696501L;

	@Init
	public void init() {
		unidadesDB = unidadService.getAll();
		usuario = (Usuarios) sessionUtils.getFromSession("usuario");
		organizacion = (Organizacion) sessionUtils.getFromSession("FIRMA");
	}

	@Command
	@NotifyChange({ "*" })
	public void save() {
		guardarUnidades();
	}
	
	@Command
	@NotifyChange({ "*" })
	public void removerUnidad(@BindingParam("index") Integer index) {
		boolean continuarEliminacion = true;
		unidad = ((Unidad) unidadesDB.get(index.intValue()));
		try {
			unidadService.delete(unidad);
		} catch (Exception e) {
			continuarEliminacion = false;
		}
		if (continuarEliminacion) {
			unidadesDB.remove(unidad);
			StockUtils.showSuccessmessage("La unidad de medida ha sido eliminada", "info", Integer.valueOf(0), null);
		} else {
			StockUtils.showSuccessmessage(
					"La unidad de medida -" + unidad.getNombre() + "- esta siendo utilizado. No puede ser eliminado",
					"error", Integer.valueOf(0), null);
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	@NotifyChange({ "*" })
	public void agregarNuevaUnidad() {
		if (unidadesDB == null) {
			unidadesDB = new ArrayList();
		}
		Unidad nuevaUnidad = new Unidad();
		unidadesDB.add(nuevaUnidad);
		mensajeDeCambios = "No olvide salvar sus cambios";
	}
	
	@Command
	@NotifyChange({ "unidadesDB" })
	public void onUploadExcelUnidadMedida(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) throws IOException {
		unidadesDB = leerDatosDesdeExcelUnidadMedida(getStreamMediaExcel(ctx), 0);
		if (unidadesDB.size() > 0) {
			for (Unidad item : unidadesDB) {
				item.setOrganizacion(organizacion);
				item.setUsuario(usuario);
				unidadService.save(item);
			}
			Messagebox.show(unidadesDB.size() + " Unidades de medida Importados");
		} else
			Messagebox.show("No se importaron Unidades de medida. El documento esta vacio");
	}
}
