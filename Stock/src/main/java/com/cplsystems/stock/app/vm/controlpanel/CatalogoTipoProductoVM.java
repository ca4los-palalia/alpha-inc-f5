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
import com.cplsystems.stock.domain.ProductoTipo;
import com.cplsystems.stock.domain.Usuarios;

@VariableResolver({ DelegatingVariableResolver.class })
public class CatalogoTipoProductoVM extends ControlPanelMetaclass {
	private static final long serialVersionUID = -8141487067470696501L;

	@Init
	public void init() {
		productoTipoDB = productoTipoService.getAll();
		usuario = (Usuarios) sessionUtils.getFromSession("usuario");
		organizacion = (Organizacion) sessionUtils.getFromSession("FIRMA");
	}

	@Command
	@NotifyChange({ "*" })
	public void save() {
		guardarProductoTipo();
	}
	
	@Command
	@NotifyChange({ "productoTipoDB, mensajeDeCambios" })
	public void eliminarTipoProducto(@BindingParam("index") Integer index) {
		productoTipoSelected = ((ProductoTipo) productoTipoDB.get(index.intValue()));
		boolean continuarEliminacion = true;
		if (productoTipoDB != null) {
			try {
				productoTipoService.delete(productoTipoSelected);
			} catch (Exception e) {
				continuarEliminacion = false;
			}
			if (continuarEliminacion) {
				productoTipoDB.clear();
				productoTipoDB = productoTipoService.getAll();

				StockUtils.showSuccessmessage(productoTipoSelected.getNombre() + " ha sido eliminado", "info",
						Integer.valueOf(0), null);
			} else {
				StockUtils.showSuccessmessage("La familia -" + productoTipoSelected.getNombre()
						+ "- esta siendo utilizado. No puede ser eliminado", "error", Integer.valueOf(0), null);
			}
		} else {
			StockUtils.showSuccessmessage("Debe seleccionar un tipo de producto para proceder con la eliminaci�n",
					"warning", Integer.valueOf(0), null);
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	@NotifyChange({ "*" })
	public void agregarNuevoTipoProducto() {
		if (productoTipoDB == null) {
			productoTipoDB = new ArrayList();
		}
		productoTipoDB.add(crearColumnaVaciaTipoProducto());
		mensajeDeCambios = "No olvide salvar sus cambios";
	}
	
	@Command
	@NotifyChange({ "productoTipoDB" })
	public void onUploadExcelTipoProducto(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) throws IOException {
		productoTipoDB = leerDatosDesdeExcelTipoProductos(getStreamMediaExcel(ctx), 0);
		if (productoTipoDB.size() > 0) {
			for (ProductoTipo item : productoTipoDB) {
				item.setOrganizacion(organizacion);
				item.setUsuario(usuario);
				item.setFechaActualizacion(stockUtils.getFechaActualConHora());
				productoTipoService.save(item);
			}
			Messagebox.show(productoTipoDB.size() + " Tipos de producto Importados");
		} else
			Messagebox.show("No se importaron Tipos de producto. El documento esta vacio");
	}
}
