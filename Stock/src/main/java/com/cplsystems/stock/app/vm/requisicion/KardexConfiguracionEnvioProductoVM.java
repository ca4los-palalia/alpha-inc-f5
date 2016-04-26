package com.cplsystems.stock.app.vm.requisicion;

import java.util.ArrayList;
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

import com.cplsystems.stock.app.utils.StockUtils;
import com.cplsystems.stock.domain.Almacen;
import com.cplsystems.stock.domain.AlmacenEntrada;
import com.cplsystems.stock.domain.Kardex;
import com.cplsystems.stock.domain.Producto;

@VariableResolver({ DelegatingVariableResolver.class })
public class KardexConfiguracionEnvioProductoVM extends ControlMetaclass {
	private static final long serialVersionUID = -1363727052274326014L;
	
	
	@Wire("#configuracionEnvioProductosModalDialog")
	private Window configuracionEnvioProductosModalDialog;
	private String globalCommandName;
	
	private AlmacenEntrada almacenEntrada;
	private List<AlmacenEntrada> almacenEntradaList;
	private List<Almacen> almacenesList;
	private Producto producto;
	private String titleWindow;
	private boolean modificar;

	
	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view,
			@ExecutionArgParam("updateCommandFromItemFinder") String updateCommandFromItemFinder,
			@ExecutionArgParam("kardexInput") Kardex kardexInput,
			@ExecutionArgParam("productoInput") Producto pro,
			@ExecutionArgParam("almacenEntradaList") List<AlmacenEntrada> almacenEntradasIn) {
		super.init();
		Selectors.wireComponents(view, this, false);
		globalCommandName = updateCommandFromItemFinder;
		producto = kardexInput.getProducto();
		titleWindow = "Configurar de envio de Productos | [" + kardexInput.getProducto().getClave() + "] " + kardexInput.getProducto().getNombre() + " (" + kardexInput.getProducto().getEnExistencia() + " Artículos)";
		modificar = kardexInput.isConf();
		areas = areaService.getAll();
		almacenesList = almacenService.getAll();
		//hacer copnsulta IN para mostrar solo almacenes de areas corespondientes y no de toda la organizacion
		if(kardexInput.getAlmacenEntradaList() == null)
			almacenEntradaList = new ArrayList<>();
		else{
			almacenEntradaList = kardexInput.getAlmacenEntradaList();
			int restan = 0;
			for (AlmacenEntrada item : almacenEntradaList) {
				item.setAreas(areas);
				item.setArea(getAreaFromList(item.getArea().getIdArea()));
				item.getArea().setAlmacenesList(getAlmacenesByAreaFromList(almacenesList, item.getArea()));
				item.setAlmacen(getAlmacenFromList(item.getAlmacen().getIdAlmacen(), item.getArea().getAlmacenesList()));
				restan += item.getCantidad(); 
			}
			producto.setRestan(kardexInput.getProducto().getEnExistencia() - restan);
		}
			
		
		almacenEntrada = new AlmacenEntrada();
	}
	
	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}
	
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	public void transferProduct() {
		
		if (almacenEntradaList != null) {
			configuracionEnvioProductosModalDialog.detach();
			Map<String, Object> args = new HashMap();
			args.put("almacenEntradaList", almacenEntradaList);
			if ((globalCommandName != null) && (!globalCommandName.isEmpty())) {
				BindUtils.postGlobalCommand(null, null, globalCommandName, args);
			} else {
				BindUtils.postGlobalCommand(null, null, "updateFromSelectedItem", args);
			}
		}
		
		
	}
	
	@Command
	@NotifyChange({ "almacenEntrada", "almacenEntradaList" })
	public void buscarAlmacenesDeAreaSeleccionada(@BindingParam("index") Integer index){
		
		almacenEntrada = almacenEntradaList.get(index);
		almacenEntrada.getArea().setAlmacenesList(almacenService.getByArea(almacenEntrada.getArea()));

		
	}
	@Command
	@NotifyChange({ "almacenEntrada"})
	public void agreagarAlmacen(@BindingParam("index2") Integer index){
		almacenEntrada.setActivarCantidad(false);
	}
	@Command
	@NotifyChange({ "producto"})
	public void validarMaximoProductos(){
		
		
		
		
		int maximoTotal = (int) Math.round(producto.getEnExistencia());
		if(almacenEntrada.getCantidad() > maximoTotal){
			StockUtils.showSuccessmessage("La cantidad debe ser menor al restante de productos", Clients.NOTIFICATION_TYPE_WARNING, 0, null);
			almacenEntrada.setCantidad(null);
		}else if(almacenEntrada.getCantidad() != null && almacenEntrada.getCantidad() < 1){
			StockUtils.showSuccessmessage("La cantidad debe ser mayor a 0", Clients.NOTIFICATION_TYPE_WARNING, 0, null);
			almacenEntrada.setCantidad(null);
		}else if(excedeMaximo(maximoTotal)){
			StockUtils.showSuccessmessage("La cantidad de productos excede, con el maximo de " + maximoTotal, Clients.NOTIFICATION_TYPE_WARNING, 0, null);
			almacenEntrada.setCantidad(null);
		}
	}
	
	@Command
	@NotifyChange({ "almacenEntradaList"})
	public void quitarAlmacenEntradaParaEnvioDeProducto(@BindingParam("index3") Integer index) {
		List<AlmacenEntrada> clonacion = new ArrayList<>();
		for (AlmacenEntrada item : almacenEntradaList) {
			clonacion.add(item);
		} 
		
		AlmacenEntrada delete = clonacion.get(index);
		clonacion.remove(delete);
		
		almacenEntradaList.clear();
		almacenEntradaList = clonacion;
	}
	
	@Command
	@NotifyChange({ "almacenEntradaList" })
	public void agregarNuevoAlmacenParaEnvioDeProducto() {
		
	
			if (almacenEntradaList == null)
				almacenEntradaList = new ArrayList<>();
			
			
			if(producto.getRestan() == null)
				producto.setRestan(producto.getEnExistencia());
			
			if(producto.getRestan()!= null && producto.getRestan() > 0){
				if(camposVaciosEnLista()){
					almacenEntradaList.add(crearAlmacenEntradaVacia(null, null));
				}else
					StockUtils.showSuccessmessage("Verifique no existan campos vacios en los anteriores items", Clients.NOTIFICATION_TYPE_WARNING, 0, null);
			}else
				StockUtils.showSuccessmessage("No se pueden agregar mas envíos. Se ha agotado el limite de artículos", Clients.NOTIFICATION_TYPE_WARNING, 0, null);
			
		
	}
	
	@Command
	public void closeDialog() {
		if (configuracionEnvioProductosModalDialog != null) {
			configuracionEnvioProductosModalDialog.detach();
		}
	}
	
	
	private boolean camposVaciosEnLista(){
		boolean centinela = true;
		for (AlmacenEntrada item : almacenEntradaList) {
			if( (item.getArea() == null || item.getArea().getIdArea() == null ) || (item.getAlmacen() == null || item.getAlmacen().getIdAlmacen() == null  ) || item.getCantidad() == null ){
				centinela = false;
				break;
			}	
		}
		return centinela;
	}
	
	private boolean excedeMaximo(int maximo){
		int count = 0;
		for (AlmacenEntrada item : almacenEntradaList) {
			if(item.getCantidad() != null && item.getCantidad() > 0)
				count += item.getCantidad();
		}
		if(count > maximo)
			return true;
		else{
			producto.setRestan(maximo - count);
			return false;
		}
	}

	
	
	//----------------------------------------------------------
	
	public AlmacenEntrada getAlmacenEntrada() {
		return almacenEntrada;
	}

	public void setAlmacenEntrada(AlmacenEntrada almacenEntrada) {
		this.almacenEntrada = almacenEntrada;
	}

	public List<Almacen> getAlmacenesList() {
		return almacenesList;
	}

	public void setAlmacenesList(List<Almacen> almacenesList) {
		this.almacenesList = almacenesList;
	}

	public List<AlmacenEntrada> getAlmacenEntradaList() {
		return almacenEntradaList;
	}

	public void setAlmacenEntradaList(List<AlmacenEntrada> almacenEntradaList) {
		this.almacenEntradaList = almacenEntradaList;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public String getTitleWindow() {
		return titleWindow;
	}

	public void setTitleWindow(String titleWindow) {
		this.titleWindow = titleWindow;
	}

	public boolean isModificar() {
		return modificar;
	}

	public void setModificar(boolean modificar) {
		this.modificar = modificar;
	}
	
	
}
