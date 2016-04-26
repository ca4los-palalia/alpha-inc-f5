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
import com.cplsystems.stock.domain.Moneda;
import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.Usuarios;

@VariableResolver({ DelegatingVariableResolver.class })
public class CatalogoMonedaVM extends ControlPanelMetaclass {
	private static final long serialVersionUID = -8141487067470696501L;

	@Init
	public void init() {
		monedasDB = monedaService.getAll();
		usuario = (Usuarios) sessionUtils.getFromSession("usuario");
		organizacion = (Organizacion) sessionUtils.getFromSession("FIRMA");
	}

	@Command
	@NotifyChange({ "*" })
	public void save() {
		guardarMoneda();
	}
	
	@Command
	@NotifyChange({ "*" })
	public void eliminarMoneda(@BindingParam("index") Integer index) {
		monedaSeleccionada = ((Moneda) monedasDB.get(index.intValue()));
		boolean continuarEliminacion = true;
		if (monedasDB != null) {
			try {
				monedaService.delete(monedaSeleccionada);
			} catch (Exception e) {
				continuarEliminacion = false;
			}
			if (continuarEliminacion) {
				monedasDB.clear();
				monedasDB = monedaService.getAll();

				StockUtils.showSuccessmessage("La divisa -" + monedaSeleccionada.getNombre() + "- ha sido eliminado",
						"info", Integer.valueOf(0), null);
			} else {
				StockUtils
						.showSuccessmessage(
								"La divisa -" + monedaSeleccionada.getSimbolo() + " " + monedaSeleccionada.getNombre()
										+ "- esta siendo utilizado. No puede ser eliminado",
								"error", Integer.valueOf(0), null);
			}
		} else {
			StockUtils.showSuccessmessage("Debe seleccionar una divisa para proceder con la eliminaci�n", "warning",
					Integer.valueOf(0), null);
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	@NotifyChange({ "*" })
	public void agregarNuevaDivisa() {
		if (monedasDB == null) {
			monedasDB = new ArrayList();
		}
		monedasDB.add(crearColumnaVaciaMonedas());
		mensajeDeCambios = "No olvide salvar sus cambios";
	}
	
	@Command
	@NotifyChange({ "monedasDB" })
	public void onUploadExcelMoneda(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) throws IOException {
		monedasDB = leerDatosDesdeExcelMoneda(getStreamMediaExcel(ctx), 0);
		if (monedasDB.size() > 0) {
			for (Moneda item : monedasDB) {
				item.setFechaActualizacion(stockUtils.getFechaActualConHora());
				item.setOrganizacion(organizacion);
				item.setUsuario(usuario);
				monedaService.save(item);
			}
			Messagebox.show(monedasDB.size() + " Divisas Importadas");
		} else
			Messagebox.show("No se importaron divisas. El documento esta vacio");
	}
}
