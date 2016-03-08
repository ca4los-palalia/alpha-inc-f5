package com.cplsystems.stock.app.vm.producto;

import com.cplsystems.stock.app.utils.StockUtils;
import com.cplsystems.stock.app.vm.BasicStructure;
import com.cplsystems.stock.domain.Producto;
import com.cplsystems.stock.services.ProductoService;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
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
		this.globalCommandName = updateCommandFromItemFinder;
	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	@NotifyChange({ "productos" })
	@Command
	public void searchItemByKeyOrName() {
		this.productos = this.productoService.getItemByKeyOrName(this.claveProducto, this.nombreProducto);
		if (this.productos == null) {
			StockUtils.showSuccessmessage("Los parametros especificados no generaron ning�n resultado", "warning",
					Integer.valueOf(2000), null);
		}
	}

	@Command
	public void transferProduct() {
		if (this.productoSeleccionado != null) {
			this.productosModalDialog.detach();
			Map<String, Object> args = new HashMap();
			args.put("productoSeleccionado", this.productoSeleccionado);
			if ((this.globalCommandName != null) && (!this.globalCommandName.isEmpty())) {
				BindUtils.postGlobalCommand(null, null, this.globalCommandName, args);
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
