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
import com.cplsystems.stock.domain.Banco;
import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.Usuarios;

@VariableResolver({ DelegatingVariableResolver.class })
public class CatalogoBancosVM extends ControlPanelMetaclass {
	private static final long serialVersionUID = -8141487067470696501L;

	@Init
	public void init() {
		bancosDB = bancoService.getAll();
		usuario = (Usuarios) sessionUtils.getFromSession("usuario");
		organizacion = (Organizacion) sessionUtils.getFromSession("FIRMA");
	}

	@Command
	@NotifyChange({ "*" })
	public void save() {
		guardarBanco();
	}
	
	@Command
	@NotifyChange({ "*" })
	public void eliminarBanco(@BindingParam("index") Integer index) {
		bancoSeleccionado = ((Banco) bancosDB.get(index.intValue()));
		boolean continuarEliminacion = true;
		if (bancosDB != null) {
			try {
				bancoService.delete(bancoSeleccionado);
			} catch (Exception e) {
				continuarEliminacion = false;
			}
			if (continuarEliminacion) {
				bancosDB.clear();
				bancosDB = bancoService.getAll();

				StockUtils.showSuccessmessage("El Banco -" + bancoSeleccionado.getNombre() + "- ha sido eliminado",
						"info", Integer.valueOf(0), null);
			} else {
				StockUtils.showSuccessmessage("El Banco -" + bancoSeleccionado.getNombre()
						+ "- esta siendo utilizado. No puede ser eliminado", "error", Integer.valueOf(0), null);
			}
		} else {
			StockUtils.showSuccessmessage("Debe seleccionar un banco para proceder con la eliminaci�n", "warning",
					Integer.valueOf(0), null);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	@NotifyChange({ "*" })
	public void agregarNuevoBanco() {
		if (bancosDB == null) {
			bancosDB = new ArrayList();
		}
		bancosDB.add(crearColumnaVaciaBanco());
	}
	
	@Command
	@NotifyChange({ "bancosDB" })
	public void onUploadExcelBanco(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) throws IOException {
		bancosDB = leerDatosDesdeExcelBanco(getStreamMediaExcel(ctx), 0);
		if (bancosDB.size() > 0) {
			for (Banco item : bancosDB) {
				item.setFechaActualizacion(stockUtils.getFechaActualConHora());
				item.setOrganizacion(organizacion);
				item.setUsuario(usuario);
				bancoService.save(item);
			}
			Messagebox.show(bancosDB.size() + " Bancos Importados");
		} else
			Messagebox.show("No se importaron bancos. El documento esta vacio");
	}

	// ---------------------------------------------------------------------------------------------
}
