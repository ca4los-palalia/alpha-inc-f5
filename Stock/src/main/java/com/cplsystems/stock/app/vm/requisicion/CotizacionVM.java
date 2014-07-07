/**
 * 
 */
package com.cplsystems.stock.app.vm.requisicion;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.util.Clients;

import com.cplsystems.stock.app.vm.requisicion.utils.DesgloceTotal;
import com.cplsystems.stock.app.vm.requisicion.utils.RequisicionVariables;
import com.cplsystems.stock.domain.EstatusRequisicion;
import com.cplsystems.stock.domain.Requisicion;
import com.cplsystems.stock.domain.RequisicionProducto;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class CotizacionVM extends RequisicionVariables {

	private static final long serialVersionUID = 2584088569805199520L;
	public static final String REQUISICION_GLOBALCOMMAND_NAME_FOR_UPDATE = "updateCommandFromItemFinder";

	@Init
	public void init() {
		super.init();
		
		
		//requisiciones = requisicionService.getByEstatusRequisicion(estado);
		proveedoresLista = requisicionProductoService.getAllDistinctByProveedor();
		
		/*
		Float subtotal = new Float(0.0);
		
		if(requisicionProductos !=null ){
			desgloceTotal = new DesgloceTotal();
			
			for (RequisicionProducto sumar : requisicionProductos) {
				subtotal += sumar.getTotalProductoPorUnidad();
			}

			desgloceTotal.setSubtotal(subtotal);
			desgloceTotal.setIva(subtotal * new Float(0.16));
			desgloceTotal.setTotal((subtotal) + (subtotal * new Float(0.16)));
		}
			*/
		
		
	}
	@Command
	@NotifyChange("*")
	public void obtenerListaDeProductosProveedorSeleccionado(){
		if(proveedorSelected != null){
			/*EstatusRequisicion estado = estatusRequisicionService.getByClave("RQ");
			requisicionProductos = requisicionProductoService.getRequisicionesConEstadoEspecifico(estado);*/
			requisicionProductos = requisicionProductoService.getByProveedor(proveedorSelected);
		}
	}
	
	
	/*
	@Command
	@NotifyChange("proveedorProductos")
	public void cargarProveedoresProducto(){
		if(requisicionProductoSeleccionado != null){
			if(requisicionProductoSeleccionado.getProducto() != null)
				proveedorProductos = proveedorProductoService.getByProducto(requisicionProductoSeleccionado.getProducto());
		}
	}
	
	
	@Command
	@NotifyChange("requisicionProductos")
	public void seleccionarProveedor() {
		if(proveedorProducto != null){
			for (RequisicionProducto item : requisicionProductos) {
				if(requisicionProductoSeleccionado.equals(item)){
					item.setProveedor(proveedorProducto.getProveedor());
					break;
				}	
			}
		}
	}
	
	@Command
	@NotifyChange("requisicionProductos")
	public void filtrarProductoPorRequisicion(){
		if(requisicion != null)
			requisicionProductos = requisicionProductoService.getByRequisicion(requisicion);
	}
	
	@SuppressWarnings("static-access")
	@Command
	public void autorizar() {
		if(requisicionProductos != null){
			Integer count = 0;
			
			for (RequisicionProducto item : requisicionProductos) {
				if(item.getProveedor() != null){
					requisicionProductoService.save(item);
					count ++;
				}
			}
			if(count > 0){
				String mensaje = "";
				if(count == 1)
					mensaje = "se ha actualizado el proveedor para " + count + " producto";	
				else
					mensaje = "se han actualizado los proveedores para " + count + " productos";
				stockUtils.showSuccessmessage(
						mensaje,
						Clients.NOTIFICATION_TYPE_INFO, 0, null);
			}
				
		}
		
	}

	@SuppressWarnings("static-access")
	@Command
	@NotifyChange("*")
	public void removerProductoDeListaGeneralDeProductos(){
		requisicionProductoService.delete(requisicionProductoSeleccionado);
		requisicionProductos.remove(requisicionProductoSeleccionado);
		
		stockUtils.showSuccessmessage("El producto -" + 
				requisicionProductoSeleccionado.getProducto().getNombre() + "- ha sido removido de la requisición -" + requisicionProductoSeleccionado.getRequisicion().getFolio() + "-",
				Clients.NOTIFICATION_TYPE_INFO, 0, null);
	}
	
	@SuppressWarnings("static-access")
	@Command
	@NotifyChange("*")
	public void cancelarRequisicion(){
		EstatusRequisicion estado = estatusRequisicionService.getByClave("RQC");
		Requisicion rq = requisicionProductoSeleccionado.getRequisicion();
		rq.setEstatusRequisicion(estado);
		requisicionService.save(rq);
		
		stockUtils.showSuccessmessage("La requisición -" + requisicionProductoSeleccionado.getRequisicion().getFolio() + "- ha sido cancelada",
				Clients.NOTIFICATION_TYPE_INFO, 0, null);
		estado = estatusRequisicionService.getByClave("RQ");
		requisiciones = requisicionService.getByEstatusRequisicion(estado);
		requisicionProductos = requisicionProductoService.getRequisicionesConEstadoEspecifico(estado);
	}*/
	
}
