/**
 * 
 */
package com.cplsystems.stock.app.vm.requisicion;

import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.util.Clients;

import com.cplsystems.stock.app.vm.requisicion.utils.DesgloceTotal;
import com.cplsystems.stock.app.vm.requisicion.utils.RequisicionVariables;
import com.cplsystems.stock.domain.Area;
import com.cplsystems.stock.domain.CofiaPartidaGenerica;
import com.cplsystems.stock.domain.EstatusRequisicion;
import com.cplsystems.stock.domain.Producto;
import com.cplsystems.stock.domain.Requisicion;
import com.cplsystems.stock.domain.RequisicionProducto;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class concentradoVM extends RequisicionVariables {

	private static final long serialVersionUID = 2584088569805199520L;
	public static final String REQUISICION_GLOBALCOMMAND_NAME_FOR_UPDATE = "updateCommandFromItemFinder";

	@Init
	public void init() {
		super.init();
		requisicion = new Requisicion();
		EstatusRequisicion estado = estatusRequisicionService.getByClave("RQ");
		requisiciones = requisicionService.getByEstatusRequisicion(estado);

		requisicionProductos = requisicionProductoService
				.getRequisicionesConEstadoEspecifico(estado);
		Float subtotal = new Float(0.0);

		if (requisicionProductos != null) {
			desgloceTotal = new DesgloceTotal();

			for (RequisicionProducto sumar : requisicionProductos) {
				subtotal += sumar.getTotalProductoPorUnidad();
			}

			desgloceTotal.setSubtotal(subtotal);
			desgloceTotal.setIva(subtotal * new Float(0.16));
			desgloceTotal.setTotal((subtotal) + (subtotal * new Float(0.16)));
		}

	}

	@Command
	@NotifyChange("proveedorProductos")
	public void cargarProveedoresProducto() {
		if (requisicionProductoSeleccionado != null) {
			if (requisicionProductoSeleccionado.getProducto() != null)
				proveedorProductos = proveedorProductoService
						.getByProducto(requisicionProductoSeleccionado
								.getProducto());
		}
	}

	@Command
	@NotifyChange("requisicionProductos")
	public void seleccionarProveedor() {
		if (proveedorProducto != null) {
			for (RequisicionProducto item : requisicionProductos) {
				if (requisicionProductoSeleccionado.equals(item)) {
					item.setProveedor(proveedorProducto.getProveedor());
					break;
				}
			}
		}
	}

	private List<RequisicionProducto> buscarPorClaveProducto(String buscar) {
		List<RequisicionProducto> rp = null;
		Producto pr = productoService.getByClave(buscar);
		if (pr != null)
			rp = requisicionProductoService.getByProducto(pr);
		return rp;
	}

	private List<RequisicionProducto> buscarPorFolioRequisicion(String buscar) {
		List<RequisicionProducto> rp = null;
		Requisicion rq = requisicionService.getByFolio(buscar);
		if (rq != null) 
			rp = requisicionProductoService
					.getByRequisicion(rq);
		return rp;
	}

	private List<RequisicionProducto> buscarPorPartidaGenerica(String buscar) {
		List<RequisicionProducto> rp = null;
		CofiaPartidaGenerica cpg = cofiaPartidaGenericaService
				.getByNombre(buscar);
		if (cpg != null) 
			rp = requisicionProductoService
			
					.getByConfiaPartidaGenerica(cpg);
		return rp;
	}
	
	private List<RequisicionProducto> buscarPorAreaUr(String buscar){
		List<RequisicionProducto> rp = null;
		Area areaBuscar = areaService
				.getByNombre(buscar);
		if (areaBuscar != null){
			Requisicion rq = requisicionService.getByFolio(buscar);
			rq = requisicionService
					.getByUnidadResponsable(areaBuscar);
			requisicionProductos = requisicionProductoService
					.getByRequisicion(rq);
		}
		return rp;	
	}
	
	
	
	@SuppressWarnings("static-access")
	@Command
	@NotifyChange("requisicionProductos")
	public void filtrarProductoPorRequisicion() {
		String mensaje = "";
		if (requisicion == null)
			requisicion = new Requisicion();

		if (requisicion.getBuscarRequisicion() != null
				&& !requisicion.getBuscarRequisicion().isEmpty()) {
			
			requisicionProductos = buscarPorClaveProducto(requisicion.getBuscarRequisicion());
			
			if(requisicionProductos == null){
				requisicionProductos = buscarPorFolioRequisicion(requisicion.getBuscarRequisicion());
				
				if(requisicionProductos == null){
					requisicionProductos = buscarPorPartidaGenerica(requisicion.getBuscarRequisicion());
					if(requisicionProductos == null){
						requisicionProductos = buscarPorAreaUr(requisicion.getBuscarRequisicion());
					}
				}
			}
			if(requisicionProductos == null)
				stockUtils.showSuccessmessage("Tu busqueda -" + requisicion.getBuscarRequisicion() + "- no encontro algun resultado",
						Clients.NOTIFICATION_TYPE_INFO, 0, null);
		}else
			stockUtils.showSuccessmessage("No se ingreso algun parametro de busque, asegurese de escribir alguna de las formas de busqueda recomendadas",
					Clients.NOTIFICATION_TYPE_INFO, 0, null);
		
	}

	@SuppressWarnings("static-access")
	@Command
	public void autorizar() {
		if (requisicionProductos != null) {
			Integer count = 0;

			for (RequisicionProducto item : requisicionProductos) {
				if (item.getProveedor() != null) {
					requisicionProductoService.save(item);
					count++;
				}
			}
			if (count > 0) {
				String mensaje = "";
				if (count == 1)
					mensaje = "se ha actualizado el proveedor para " + count
							+ " producto";
				else
					mensaje = "se han actualizado los proveedores para "
							+ count + " productos";
				stockUtils.showSuccessmessage(mensaje,
						Clients.NOTIFICATION_TYPE_INFO, 0, null);
			}

		}

	}

	@SuppressWarnings("static-access")
	@Command
	@NotifyChange("*")
	public void removerProductoDeListaGeneralDeProductos() {
		requisicionProductoService.delete(requisicionProductoSeleccionado);
		requisicionProductos.remove(requisicionProductoSeleccionado);

		stockUtils.showSuccessmessage("El producto -"
				+ requisicionProductoSeleccionado.getProducto().getNombre()
				+ "- ha sido removido de la requisición -"
				+ requisicionProductoSeleccionado.getRequisicion().getFolio()
				+ "-", Clients.NOTIFICATION_TYPE_INFO, 0, null);
	}

	@SuppressWarnings("static-access")
	@Command
	@NotifyChange("*")
	public void cancelarRequisicion() {
		EstatusRequisicion estado = estatusRequisicionService.getByClave("RQC");
		Requisicion rq = requisicionProductoSeleccionado.getRequisicion();
		rq.setEstatusRequisicion(estado);
		requisicionService.save(rq);

		stockUtils.showSuccessmessage("La requisición -"
				+ requisicionProductoSeleccionado.getRequisicion().getFolio()
				+ "- ha sido cancelada", Clients.NOTIFICATION_TYPE_INFO, 0,
				null);
		estado = estatusRequisicionService.getByClave("RQ");
		requisiciones = requisicionService.getByEstatusRequisicion(estado);
		requisicionProductos = requisicionProductoService
				.getRequisicionesConEstadoEspecifico(estado);
	}

}
