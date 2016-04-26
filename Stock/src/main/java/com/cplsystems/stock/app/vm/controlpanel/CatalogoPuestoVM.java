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
import com.cplsystems.stock.domain.Posicion;
import com.cplsystems.stock.domain.Usuarios;

@VariableResolver({ DelegatingVariableResolver.class })
public class CatalogoPuestoVM extends ControlPanelMetaclass {
	private static final long serialVersionUID = -8141487067470696501L;

	@Init
	public void init() {
		usuario = (Usuarios) sessionUtils.getFromSession("usuario");
		organizacion = (Organizacion) sessionUtils.getFromSession("FIRMA");
		posiciones = posicionService.getAll();
	}

	@Command
	@NotifyChange({ "*" })
	public void save() {
		guardarPuesto();
	}
	
	@Command
	@NotifyChange({ "*" })
	public void eliminarPuesto(@BindingParam("index") Integer index) {
		posicion = ((Posicion) posiciones.get(index.intValue()));
		boolean continuarEliminacion = true;
		if (posicion != null) {
			try {
				posicionService.delete(posicion);
			} catch (Exception e) {
				continuarEliminacion = false;
			}
			if (continuarEliminacion) {
				posiciones.clear();
				posiciones = posicionService.getAll();
				StockUtils.showSuccessmessage(posicion.getNombre() + " ha sido eliminado", "info", Integer.valueOf(0),
						null);
			} else {
				StockUtils.showSuccessmessage(
						"El puesto -" + posicion.getNombre() + "- esta siendo utilizado. No puede ser eliminado",
						"error", Integer.valueOf(0), null);
			}
		} else {
			StockUtils.showSuccessmessage("Debe seleccionar un puesto para proceder con la eliminaci�n", "warning",
					Integer.valueOf(0), null);
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	@NotifyChange({ "*" })
	public void agregarNuevoPuesto() {
		if (posiciones == null) {
			posiciones = new ArrayList();
		}
		posiciones.add(crearColumnaVaciaP());
	}
	
	@Command
	@NotifyChange({ "posiciones" })
	public void onUploadExcelPosicion(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) throws IOException {
		posiciones = leerDatosDesdeExcelPosicion(getStreamMediaExcel(ctx), 0);
		if (posiciones.size() > 0) {
			for (Posicion item : posiciones) {
				item.setFechaActualizacion(stockUtils.getFechaActualConHora());
				item.setOrganizacion(organizacion);
				item.setUsuario(usuario);
				posicionService.save(item);
			}
			Messagebox.show(posiciones.size() + " Puestos Importados");
		} else
			Messagebox.show("No se importaron posiciones. El documento esta vacio");
	}
}
