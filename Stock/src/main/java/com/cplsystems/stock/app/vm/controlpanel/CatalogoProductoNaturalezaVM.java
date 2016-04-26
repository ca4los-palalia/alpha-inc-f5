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
import com.cplsystems.stock.domain.ProductoNaturaleza;
import com.cplsystems.stock.domain.Usuarios;

@VariableResolver({ DelegatingVariableResolver.class })
public class CatalogoProductoNaturalezaVM extends ControlPanelMetaclass {
	private static final long serialVersionUID = -8141487067470696501L;

	@Init
	public void init() {
		productosNaturalezas = productoNaturalezaService.getAll();
		usuario = (Usuarios) sessionUtils.getFromSession("usuario");
		organizacion = (Organizacion) sessionUtils.getFromSession("FIRMA");
	}

	@Command
	@NotifyChange({ "productosNaturalezas" })
	public void save() {
		guardarProductoNaturaleza();
		productosNaturalezas = productoNaturalezaService.getAll();
		productoNaturaleza = new ProductoNaturaleza();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	@NotifyChange({ "*" })
	public void removerNaturaleza(@BindingParam("index") Integer index) {
		boolean continuarEliminacion = true;
		productoNaturaleza = ((ProductoNaturaleza) productosNaturalezas.get(index.intValue()));
		try {
			productoNaturalezaService.delete(productoNaturaleza);
		} catch (Exception e) {
			continuarEliminacion = false;
		}
		if (continuarEliminacion) {
			productosNaturalezas.remove(productoNaturaleza);
			productosNaturalezas = new ArrayList();
			productosNaturalezas = productoNaturalezaService.getAll();
			StockUtils.showSuccessmessage(
					"La naturaleza producto -" + productoNaturaleza.getNombre() + "- ha sido eliminado del catalogo",
					"info", Integer.valueOf(0), null);
		} else {
			StockUtils.showSuccessmessage("La naturaleza producto -" + productoNaturaleza.getNombre()
					+ "- esta siendo utilizado. No puede ser eliminado", "error", Integer.valueOf(0), null);
		}
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	@NotifyChange({ "*" })
	public void agregarNuevaNaturaleza() {
		if (productosNaturalezas == null) {
			productosNaturalezas = new ArrayList();
		}
		ProductoNaturaleza nueoGiro = new ProductoNaturaleza();
		productosNaturalezas.add(nueoGiro);
		mensajeDeCambios = "No olvide salvar sus cambios";
	}
	
	@Command
	@NotifyChange({ "productosNaturalezas" })
	public void onUploadExcelNaturaleza(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) throws IOException {
		productosNaturalezas = leerDatosDesdeExcelNaturaleza(getStreamMediaExcel(ctx), 0);
		if (productosNaturalezas.size() > 0) {
			for (ProductoNaturaleza item : productosNaturalezas) {
				productoNaturalezaService.save(item);
			}
			Messagebox.show(productosNaturalezas.size() + " Naturalezas de producto Importados");
		} else
			Messagebox.show("No se importaron Naturalezas de producto. El documento esta vacio");
	}
}
