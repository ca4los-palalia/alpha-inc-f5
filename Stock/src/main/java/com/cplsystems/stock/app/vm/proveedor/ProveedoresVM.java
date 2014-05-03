/**
 * 
 */
package com.cplsystems.stock.app.vm.proveedor;


import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Messagebox;



@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ProveedoresVM extends ProveedorMetaClass {

	private static final long serialVersionUID = -4963362932578502507L;

	@Init
	public void init(){
		contratos = contratoService.getAll();
		estados = estadoService.getAll();
		paises = paisService.getAll();
		municipios = municipioService.getAll();
		numeroRegistros = "";
	}
	
	
	@SuppressWarnings("static-access")
	@Command
	@NotifyChange("*")
    public void newRecord(){
		if(validarEntradaDatosProveedor()){
			guardarProveedor();
		}else
			stockUtils.showSuccessmessage(
					"Campos requeridos incompletos, verifique y vuelva a intentar",
					Clients.NOTIFICATION_TYPE_WARNING, 0);
    }
	
	@SuppressWarnings("static-access")
	@Command
	@NotifyChange("*")
	public void saveChanges(){
		
		if(proveedores != null && proveedores.size() > 0){
			actualizarProveedorCambios();
			stockUtils.showSuccessmessage(
					"La lista de proveedores ha sido actualizada",
					Clients.NOTIFICATION_TYPE_INFO, 0);
		}else
			stockUtils.showSuccessmessage(
					"La lista no contiene proveedores",
					Clients.NOTIFICATION_TYPE_WARNING, 0);
		
	}
	
	@SuppressWarnings("static-access")
	@Command
	@NotifyChange("*")
	public void deleteRecord(){
		if(proveedorSelected != null){
			
			Messagebox
			.show("¿Está seguro de remover este proveedor?, esta acción es irreversible",
					"Question", Messagebox.OK | Messagebox.CANCEL,
					Messagebox.QUESTION, new EventListener<Event>() {
						public void onEvent(Event event) throws Exception {
							if (((Integer) event.getData()).intValue() == Messagebox.OK) {
								
								proveedorService.delete(proveedorSelected);
								
								stockUtils.showSuccessmessage(
										proveedorSelected.getNombre() + " ha sido eliminado",
										Clients.NOTIFICATION_TYPE_INFO, 0);
								proveedorSelected = null;
								return;
							}
						}
					});
			
		}else{
			stockUtils.showSuccessmessage(
					"Seleccione un proveedor para llevar acabo la eliminación",
					Clients.NOTIFICATION_TYPE_WARNING, 0);
		}
	}
	
	@SuppressWarnings("static-access")
	@Command
	@NotifyChange("*")
	public void performSearch(){
		if(buscarTexto != null && !buscarTexto.isEmpty()){
			if(buscarTexto.equals("*"))
				proveedores = proveedorService.getAll();	
			else
				proveedores = proveedorService.getBysClaveNombreRfc(buscarTexto);
			if(proveedores != null){
				String mensaje = "";
				if(proveedores.size() == 1)
					mensaje = "proveedor";
				else if(proveedores.size() > 1)
					mensaje = "proveedores";
				
				
				
				if(buscarTexto.equals("*"))
					stockUtils.showSuccessmessage(
							"Tu búsqueda obtuvo todos los proveedores",
							Clients.NOTIFICATION_TYPE_INFO, 0);
				else
					stockUtils.showSuccessmessage(
							"Tu búsqueda -" + buscarTexto + "- obtuvo "
							+ proveedores.size() + " " + mensaje,
							Clients.NOTIFICATION_TYPE_INFO, 0);
				numeroRegistros = proveedores.size() + " " + mensaje;
				
			}else
				stockUtils.showSuccessmessage(
						"Tu búsqueda -" + buscarTexto + "- no obtuvo ningún resultado",
						Clients.NOTIFICATION_TYPE_WARNING, 0);
			
		}else{
			stockUtils.showSuccessmessage(
					"Tu búsqueda no obtuvo ningún resultado",
					Clients.NOTIFICATION_TYPE_ERROR, 0);
		}
	}
}
