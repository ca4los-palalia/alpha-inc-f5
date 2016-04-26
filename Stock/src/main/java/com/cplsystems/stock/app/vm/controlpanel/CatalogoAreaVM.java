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
import com.cplsystems.stock.domain.Almacen;
import com.cplsystems.stock.domain.Area;
import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.Usuarios;

@VariableResolver({ DelegatingVariableResolver.class })
public class CatalogoAreaVM extends ControlPanelMetaclass {
	private static final long serialVersionUID = -8141487067470696501L;
	
	@Init
	public void init() {
		areas = areaService.getAll();
		usuario = (Usuarios) sessionUtils.getFromSession("usuario");
		organizacion = (Organizacion) sessionUtils.getFromSession("FIRMA");
	}

	@Command
	@NotifyChange({ "almacenesList" })
	public void getALmacenesDeArea() {
		almacenesList = almacenService.getByArea(area);
	}

	@Command
	@NotifyChange({ "*" })
	public void save() {
		guardarArea();
	}


	@Command
	@NotifyChange({ "*" })
	public void eliminarAreaIndex(@BindingParam("index") Integer index) {
		area = ((Area) areas.get(index.intValue()));
		boolean continuarEliminacion = true;
		if (areas != null) {
			try {
				areaService.delete(area);
			} catch (Exception e) {
				continuarEliminacion = false;
			}
			if (continuarEliminacion) {
				areas.clear();
				areas = areaService.getAll();

				StockUtils.showSuccessmessage("El �rea -" + area.getNombre() + "- ha sido eliminado", "info",
						Integer.valueOf(0), null);
			} else {
				StockUtils.showSuccessmessage(
						"El Banco -" + area.getNombre() + "- esta siendo utilizado. No puede ser eliminado", "error",
						Integer.valueOf(0), null);
			}
		} else {
			StockUtils.showSuccessmessage("Debe seleccionar un �rea para proceder con la eliminaci�n", "warning",
					Integer.valueOf(0), null);
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	@NotifyChange({ "*" })
	public void agregarNuevaArea() {
		if (areas == null) {
			areas = new ArrayList();
		}
		areas.add(crearColumnaVaciaAreaEstandar());
	}
	
	@NotifyChange({ "areas", "almacenesList" })
	@Command
	public void onUploadExcelArea(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) throws IOException {
		areas = leerDatosDesdeExcelArea(getDataExcel(getStreamMediaExcel(ctx), 0));
		if (areas.size() > 0) {
			for (Area item : areas) {
				areaService.save(item);
			}
			almacenesList = leerDatosDesdeExcelAlmacenes(getDataExcel(getStreamMediaExcel(ctx), 1));
			if (almacenesList.size() > 0) {
				for (Almacen item : almacenesList) {
					item.setOrganizacion(organizacion);
					almacenService.save(item);
				}
			}
			Messagebox.show(areas.size() + " Areas Importadas con sus respectivos almacenes");
		} else
			Messagebox.show("No se importaron areas. El documento esta vacio");
	}

}
