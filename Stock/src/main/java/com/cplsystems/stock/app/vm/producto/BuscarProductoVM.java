package com.cplsystems.stock.app.vm.producto;

import com.cplsystems.stock.app.utils.StockUtils;
import com.cplsystems.stock.app.vm.BasicStructure;
import com.cplsystems.stock.domain.Producto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Window;

@VariableResolver({ DelegatingVariableResolver.class })
public class BuscarProductoVM extends BasicStructure {
	private static final long serialVersionUID = 3098239433101641553L;
	@Wire("#productosModalDialog")
	private Window productosModalDialog;
	private String claveProducto;
	private String nombreProducto;
	private Producto productoSeleccionado;
	private List<Producto> productos;
	private String globalCommandName;

	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view,
			@ExecutionArgParam("updateCommandFromItemFinder") String updateCommandFromItemFinder) {
		Selectors.wireComponents(view, this, false);
		globalCommandName = updateCommandFromItemFinder;
		productos = productoService.getAllLimited();
	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	@NotifyChange({ "productos" })
	@Command
	public void searchItemByKeyOrName(@BindingParam("compUserCp") Component comp) {
		productos = productoService.getItemByKeyOrName(claveProducto, nombreProducto);
		if (productos == null && (!claveProducto.equals("") || (nombreProducto != null && !nombreProducto.equals("")))) {
			StockUtils.showSuccessmessage("Los parametros especificados no generaron ningún resultado",
					Clients.NOTIFICATION_TYPE_INFO, 0,
					comp);
		}else if (productos == null && (claveProducto.equals("") && (nombreProducto == null || nombreProducto.equals("")))) {
			productos = productoService.getAllLimited();
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	public void transferProduct() {
		if (productoSeleccionado != null) {
			productosModalDialog.detach();
			Map<String, Object> args = new HashMap();
			args.put("productoSeleccionado", productoSeleccionado);
			if ((globalCommandName != null) && (!globalCommandName.isEmpty())) {
				BindUtils.postGlobalCommand(null, null, globalCommandName, args);
			} else {
				BindUtils.postGlobalCommand(null, null, "updateFromSelectedItem", args);
			}
		}
	}

	@Command
	public void closeDialog() {
		if (this.productosModalDialog != null) {
			this.productosModalDialog.detach();
		}
	}

	public String getClaveProducto() {
		return this.claveProducto;
	}

	public void setClaveProducto(String claveProducto) {
		this.claveProducto = claveProducto;
	}

	public String getNombreProducto() {
		return this.nombreProducto;
	}

	public void setNombreProducto(String nombreProducto) {
		this.nombreProducto = nombreProducto;
	}

	public Producto getProductoSeleccionado() {
		return this.productoSeleccionado;
	}

	public void setProductoSeleccionado(Producto productoSeleccionado) {
		this.productoSeleccionado = productoSeleccionado;
	}

	public List<Producto> getProductos() {
		return this.productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}
}
