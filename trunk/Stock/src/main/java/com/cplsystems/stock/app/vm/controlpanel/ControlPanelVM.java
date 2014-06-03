/**
 * 
 */
package com.cplsystems.stock.app.vm.controlpanel;

import java.util.ArrayList;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.util.Clients;

import com.cplsystems.stock.app.utils.StockConstants;
import com.cplsystems.stock.app.vm.controlpanel.utils.ControlPanelVariables;
import com.cplsystems.stock.app.vm.controlpanel.utils.SelectedTabsControlPanel;
import com.cplsystems.stock.domain.Area;
import com.cplsystems.stock.domain.Banco;
import com.cplsystems.stock.domain.Moneda;
import com.cplsystems.stock.domain.Posicion;
import com.cplsystems.stock.domain.ProductoTipo;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 *
 */

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ControlPanelVM extends ControlPanelVariables{

	private static final long serialVersionUID = -8141487067470696501L;
	
	
	@Init
	public void init() {
		
		selectTab = new SelectedTabsControlPanel();
		activarBotonesAreas();
		areas = areaService.getAll();
		posiciones = posicionService.getAll();
		bancosDB = bancoService.getAll();
		monedasDB = monedaService.getAll();
		productoTipoDB = productoTipoService.getAll();
		recargarAreas();
		recargarPosiciones();
		recargarBanco();
		recargarMonedas();
		recargarProductoTipo();
		
		activarBotonesAreas();
	}
	
	@Command
	@NotifyChange("*")
	public void save(){
		
		if(selectTab.isTab01()){
			guardarArea();
		}else if(selectTab.isTab02()){
			guardarPuesto();
		}else if(selectTab.isTab03()){
			guardarBanco();
		}else if(selectTab.isTab04()){
			guardarMoneda();
		}else if(selectTab.isTab05()){
			guardarProductoTipo();
		}
	}
	
	@Command
	@NotifyChange("*")
	public void delete(){
		if(selectTab.isTab01()){
			eliminarArea();
		}else if(selectTab.isTab02()){
			eliminarPuesto();
		}else if(selectTab.isTab03()){
			eliminarBanco();
		}else if(selectTab.isTab04()){
			eliminarMoneda();
		}else if(selectTab.isTab05()){
			eliminarTipoProducto();
		}
	}
	
	
	@SuppressWarnings("static-access")
	private void guardarArea(){
		
		for (Area areaRecord : areas) {
			try {
				areaRecord.setToolTipIndice("Seleccionar área");
				areaRecord.setToolTipNombre("Clic sobre esta columna para editar nombre");
				
				if(areaRecord.isNuevoRegistro()){
					areaRecord.setNuevoRegistro(false);
					areaRecord.setIdArea(null);
					areaService.save(areaRecord);
				}else{
					if(!areaRecord.getNombre().equals(""))
						areaService.update(areaRecord);
				}
					
			} catch (Exception e) {}
		}
		areas.clear();
		areas = areaService.getAll();
		
		if(!areas.get(areas.size() - 1).getNombre().equals(""))
			areas.add(crearColumnaVaciaArea());
		
		stockUtils.showSuccessmessage(
				"Catalogo de áreas actualizado",
				Clients.NOTIFICATION_TYPE_INFO, 0);
		
	}
	
	@SuppressWarnings("static-access")
	private void guardarPuesto(){
		
		for (Posicion posicionRecord : posiciones) {
			try {
				posicionRecord.setToolTipIndice("Seleccionar puesto");
				posicionRecord.setToolTipNombre("Clic sobre esta columna para editar nombre");
				
				if(posicionRecord.isNuevoRegistro()){
					posicionRecord.setNuevoRegistro(false);
					posicionRecord.setIdPosicion(null);
					posicionService.save(posicionRecord);
				}else{
					if(!posicionRecord.getNombre().equals(""))
						posicionService.update(posicionRecord);
				}
					
			} catch (Exception e) {}
		}
		posiciones.clear();
		posiciones = posicionService.getAll();
		
		if(!posiciones.get(posiciones.size() - 1).getNombre().equals(""))
			posiciones.add(crearColumnaVaciaP());
		
		stockUtils.showSuccessmessage(
				"Catalogo de puestos actualizados",
				Clients.NOTIFICATION_TYPE_INFO, 0);
		
	}
	
	@SuppressWarnings("static-access")
	private void guardarBanco(){
		
		for (Banco bancoRecord : bancosDB) {
			try {
				bancoRecord.setToolTipIndice(StockConstants.TOOL_TIP_ROW_SELECTED_BANCO);
				bancoRecord.setToolTipNombre(StockConstants.TOOL_TIP_ROW_EDICION_NOMBRE);
				
				if(bancoRecord.isNuevoRegistro()){
					bancoRecord.setNuevoRegistro(false);
					bancoRecord.setIdBanco(null);
					bancoService.save(bancoRecord);
				}else{
					if(!bancoRecord.getNombre().equals(""))
						bancoService.update(bancoRecord);
				}
			} catch (Exception e) {}
		}
		bancosDB.clear();
		bancosDB = bancoService.getAll();
		
		if(!bancosDB.get(bancosDB.size() - 1).getNombre().equals(""))
			bancosDB.add(crearColumnaVaciaBanco());
		
		stockUtils.showSuccessmessage(
				"Catalogo de bancos actualizados",
				Clients.NOTIFICATION_TYPE_INFO, 0);
	}

	@SuppressWarnings("static-access")
	private void guardarMoneda(){
		
		for (Moneda monedaRecord : monedasDB) {
			try {
				monedaRecord.setToolTipIndice(StockConstants.TOOL_TIP_ROW_SELECTED_MONEDA);
				monedaRecord.setToolTipNombre(StockConstants.TOOL_TIP_ROW_EDICION_NOMBRE);
				
				if(monedaRecord.isNuevoRegistro()){
					monedaRecord.setNuevoRegistro(false);
					monedaRecord.setIdMoneda(null);
					monedaService.save(monedaRecord);
				}else{
					if(!monedaRecord.getNombre().equals(""))
						monedaService.update(monedaRecord);
				}
			} catch (Exception e) {}
		}
		monedasDB.clear();
		monedasDB = monedaService.getAll();
		
		if(!monedasDB.get(monedasDB.size() - 1).getNombre().equals(""))
			monedasDB.add(crearColumnaVaciaMonedas());
		
		stockUtils.showSuccessmessage(
				"Catalogo de monedas actualizados",
				Clients.NOTIFICATION_TYPE_INFO, 0);
	}
	
	@SuppressWarnings("static-access")
	private void guardarProductoTipo(){
		
		for (ProductoTipo productoTipoRecord : productoTipoDB) {
			try {
				productoTipoRecord.setToolTipIndice(StockConstants.TOOL_TIP_ROW_SELECTED_TIPO_PRODUCTO);
				productoTipoRecord.setToolTipNombre(StockConstants.TOOL_TIP_ROW_EDICION_NOMBRE);
				
				if(productoTipoRecord.isNuevoRegistro()){
					productoTipoRecord.setNuevoRegistro(false);
					productoTipoRecord.setIdProductoTipo(null);
					productoTipoService.save(productoTipoRecord);
				}else{
					if(!productoTipoRecord.getNombre().equals(""))
						productoTipoService.update(productoTipoRecord);
				}
			} catch (Exception e) {}
		}
		productoTipoDB.clear();
		productoTipoDB = productoTipoService.getAll();
		
		if(!productoTipoDB.get(productoTipoDB.size() - 1).getNombre().equals(""))
			productoTipoDB.add(crearColumnaVaciaTipoProducto());
		
		stockUtils.showSuccessmessage(
				"Catalogo de tipo de productos actualizado",
				Clients.NOTIFICATION_TYPE_INFO, 0);
	}
	
	@SuppressWarnings("static-access")
	private void eliminarArea(){
		if(area != null){
			/*Messagebox
			.show("¿Está seguro de remover esta área?, esta acción es irreversible",
			"Question", Messagebox.OK | Messagebox.CANCEL,
			Messagebox.QUESTION, new EventListener() {
				public void onEvent(Event event)
						throws Exception {
					if (((Integer) event.getData()).intValue() == Messagebox.OK) {*/

						areaService.delete(area);
						
						areas.clear();
						areas = areaService.getAll();
						if(areas != null && !areas.get(areas.size() - 1).getNombre().equals(""))
							areas.add(crearColumnaVaciaArea());
						else{
							Area nuevo = crearColumnaVaciaArea();
							areas.add(nuevo);
						}
							
						
						stockUtils.showSuccessmessage(
								area.getNombre() + " ha sido eliminado",
								Clients.NOTIFICATION_TYPE_INFO, 0);
					/*}
				}
			});*/
		}else{
			stockUtils.showSuccessmessage(
					"Debe seleccionar un área para proceder con la eliminación",
					Clients.NOTIFICATION_TYPE_WARNING, 0);
		}
	}
	
	@SuppressWarnings("static-access")
	private void eliminarPuesto(){
		if(posicion != null){
			
			posicionService.delete(posicion);
			
			posiciones.clear();
			posiciones = posicionService.getAll();
			if(posiciones != null && !posiciones.get(posiciones.size() - 1).getNombre().equals(""))
				posiciones.add(crearColumnaVaciaP());
			else{
				Posicion nuevo = crearColumnaVaciaP();
				posiciones.add(nuevo);
			}
			stockUtils.showSuccessmessage(
					posicion.getNombre() + " ha sido eliminado",
					Clients.NOTIFICATION_TYPE_INFO, 0);
		}else{
			stockUtils.showSuccessmessage(
					"Debe seleccionar un puesto para proceder con la eliminación",
					Clients.NOTIFICATION_TYPE_WARNING, 0);
		}
	}
	
	@SuppressWarnings("static-access")
	private void eliminarBanco(){
		if(bancosDB != null){
			
			bancoService.delete(bancoSeleccionado);
			
			bancosDB.clear();
			bancosDB = bancoService.getAll();
			if(bancosDB != null && !bancosDB.get(bancosDB.size() - 1).getNombre().equals(""))
				bancosDB.add(crearColumnaVaciaBanco());
			else{
				Banco nuevo = crearColumnaVaciaBanco();
				bancosDB.add(nuevo);
			}
			stockUtils.showSuccessmessage(
					bancoSeleccionado.getNombre() + " ha sido eliminado",
					Clients.NOTIFICATION_TYPE_INFO, 0);
		}else{
			stockUtils.showSuccessmessage(
					"Debe seleccionar un banco para proceder con la eliminación",
					Clients.NOTIFICATION_TYPE_WARNING, 0);
		}
	}
	
	@SuppressWarnings("static-access")
	private void eliminarMoneda(){
		if(monedasDB != null){
			monedaService.delete(monedaSeleccionada);
			monedasDB.clear();
			monedasDB = monedaService.getAll();
			if(monedasDB != null && !monedasDB.get(monedasDB.size() - 1).getNombre().equals(""))
				monedasDB.add(crearColumnaVaciaMonedas());
			else{
				Moneda nuevo = crearColumnaVaciaMonedas();
				monedasDB.add(nuevo);
			}
			stockUtils.showSuccessmessage(
					bancoSeleccionado.getNombre() + " ha sido eliminado",
					Clients.NOTIFICATION_TYPE_INFO, 0);
		}else{
			stockUtils.showSuccessmessage(
					"Debe seleccionar una moneda para proceder con la eliminación",
					Clients.NOTIFICATION_TYPE_WARNING, 0);
		}
	}

	@SuppressWarnings("static-access")
	private void eliminarTipoProducto(){
		if(productoTipoDB != null){
			
			productoTipoService.delete(productoTipoSelected);
			
			productoTipoDB.clear();
			productoTipoDB = productoTipoService.getAll();
			if(productoTipoDB != null && !productoTipoDB.get(productoTipoDB.size() - 1).getNombre().equals(""))
				productoTipoDB.add(crearColumnaVaciaTipoProducto());
			else{
				ProductoTipo nuevo = crearColumnaVaciaTipoProducto();
				productoTipoDB.add(nuevo);
			}
			stockUtils.showSuccessmessage(
					productoTipoSelected.getNombre() + " ha sido eliminado",
					Clients.NOTIFICATION_TYPE_INFO, 0);
		}else{
			stockUtils.showSuccessmessage(
					"Debe seleccionar un tipo de producto para proceder con la eliminación",
					Clients.NOTIFICATION_TYPE_WARNING, 0);
		}
	}
	
	private Area crearColumnaVaciaArea(){
		Area areaVacia = new Area();
		
		if(areas != null)
			areaVacia.setIdArea(Long.valueOf(String.valueOf(areas.size() + 1)));
		else{
			areas = new ArrayList<Area>();
			areaVacia.setIdArea(1L);
		}
			
		areaVacia.setNuevoRegistro(true);
		areaVacia.setNombre("");
		areaVacia.setToolTipIndice("Seleccionar área");
		areaVacia.setToolTipNombre("Clic sobre esta columna para editar nombre");
		return areaVacia;
	}

	private Posicion crearColumnaVaciaP(){
		Posicion objeto = new Posicion();
		
		if(posiciones != null)
			objeto.setIdPosicion(Long.valueOf(String.valueOf(posiciones.size() + 1)));
		else{
			posiciones = new ArrayList<Posicion>();
			objeto.setIdPosicion(1L);
		}
			
		objeto.setNuevoRegistro(true);
		objeto.setNombre("");
		objeto.setToolTipIndice("Seleccionar una posicion");
		objeto.setToolTipNombre("Clic sobre esta columna para editar nombre");
		return objeto;
	}
	
	private Banco crearColumnaVaciaBanco(){
		Banco objeto = new Banco();
		
		if(bancosDB != null)
			objeto.setIdBanco(Long.valueOf(String.valueOf(bancosDB.size() + 1)));
		else{
			bancosDB = new ArrayList<Banco>();
			objeto.setIdBanco(1L);
		}
			
		objeto.setNuevoRegistro(true);
		objeto.setNombre("");
		objeto.setToolTipIndice(StockConstants.TOOL_TIP_ROW_SELECTED_BANCO);
		objeto.setToolTipNombre(StockConstants.TOOL_TIP_ROW_EDICION_NOMBRE);
		return objeto;
	}
	
	private Moneda crearColumnaVaciaMonedas(){
		Moneda objeto = new Moneda();
		
		if(monedasDB != null)
			objeto.setIdMoneda(Long.valueOf(String.valueOf(monedasDB.size() + 1)));
		else{
			monedasDB = new ArrayList<Moneda>();
			objeto.setIdMoneda(1L);
		}
		objeto.setNuevoRegistro(true);
		objeto.setNombre("");
		objeto.setToolTipIndice(StockConstants.TOOL_TIP_ROW_SELECTED_TIPO_PRODUCTO);
		objeto.setToolTipNombre(StockConstants.TOOL_TIP_ROW_EDICION_NOMBRE);
		return objeto;
	}
	
	private ProductoTipo crearColumnaVaciaTipoProducto(){
		ProductoTipo objeto = new ProductoTipo();
		
		if(productoTipoDB != null)
			objeto.setIdProductoTipo(Long.valueOf(String.valueOf(productoTipoDB.size() + 1)));
		else{
			productoTipoDB = new ArrayList<ProductoTipo>();
			objeto.setIdProductoTipo(1L);
		}
		objeto.setNuevoRegistro(true);
		objeto.setNombre("");
		objeto.setToolTipIndice(StockConstants.TOOL_TIP_ROW_SELECTED_TIPO_PRODUCTO);
		objeto.setToolTipNombre(StockConstants.TOOL_TIP_ROW_EDICION_NOMBRE);
		return objeto;
	}
	
	
	
	
	//--------------------------
	
	
	private void recargarAreas(){
		if(areas == null){
			areas.add(crearColumnaVaciaArea());
		}else{
			try {
				if(!areas.get(areas.size() - 1).getNombre().equals("")){
					areas.add(crearColumnaVaciaArea());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void recargarPosiciones(){
		if(posiciones == null || posiciones.size() == 0){
			try {
				posiciones = new ArrayList<Posicion>();
				posiciones.add(crearColumnaVaciaP());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			try {
				if(!posiciones.get(posiciones.size() - 1).getNombre().equals("")){
					posiciones.add(crearColumnaVaciaP());
				}
					
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void recargarBanco(){
		if(bancosDB == null || bancosDB.size() == 0){
			try {
				bancosDB = new ArrayList<Banco>();
				bancosDB.add(crearColumnaVaciaBanco());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			try {
				if(!bancosDB.get(bancosDB.size() - 1).getNombre().equals("")){
					bancosDB.add(crearColumnaVaciaBanco());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void recargarMonedas(){
		if(monedasDB == null || monedasDB.size() == 0){
			try {
				monedasDB = new ArrayList<Moneda>();
				monedasDB.add(crearColumnaVaciaMonedas());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			try {
				if(!monedasDB.get(monedasDB.size() - 1).getNombre().equals("")){
					monedasDB.add(crearColumnaVaciaMonedas());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void recargarProductoTipo(){
		if(productoTipoDB == null || productoTipoDB.size() == 0){
			try {
				productoTipoDB = new ArrayList<ProductoTipo>();
				productoTipoDB.add(crearColumnaVaciaTipoProducto());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}else{
			try {
				if(!productoTipoDB.get(productoTipoDB.size() - 1).getNombre().equals("")){
					productoTipoDB.add(crearColumnaVaciaTipoProducto());
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	@Command
	@NotifyChange("*")
	public void selectTabArea(){
		activarBotonesAreas();	
	}
	
	@Command
	@NotifyChange("*")
	public void selectTabPuesto(){
		activarBotonesPuestos();
	}
	
	@Command
	@NotifyChange("*")
	public void selectTabBanco(){
		activarBotonesBancos();
	}
	
	@Command
	@NotifyChange("*")
	public void selectTabMoneda(){
		activarBotonesMonedas();
	}
	
	@Command
	@NotifyChange("*")
	public void selectTabProducto(){
		activarBotonesProductos();
	}
	
	@Command
	@NotifyChange("*")
	public void selectTabContratos(){
		activarBotonesContrato();
	}
	
	private void activarBotonesAreas(){
		selectTab.setTab01(true);
		selectTab.setTab02(false);
		selectTab.setTab03(false);
		selectTab.setTab04(false);
		selectTab.setTab05(false);
		selectTab.setTab06(false);
		selectTab.setToolTipSave(StockConstants.TOOL_TIP_SAVE_AREA);
		selectTab.setToolTipDelete(StockConstants.TOOL_TIP_DELETE_AREA);
	}
	
	private void activarBotonesPuestos(){
		selectTab.setTab01(false);
		selectTab.setTab02(true);
		selectTab.setTab03(false);
		selectTab.setTab04(false);
		selectTab.setTab05(false);
		selectTab.setTab06(false);
		selectTab.setToolTipSave(StockConstants.TOOL_TIP_SAVE_PUESTO);
		selectTab.setToolTipDelete(StockConstants.TOOL_TIP_DELETE_PUESTO);
	}
	
	private void activarBotonesBancos(){
		selectTab.setTab01(false);
		selectTab.setTab02(false);
		selectTab.setTab03(true);
		selectTab.setTab04(false);
		selectTab.setTab05(false);
		selectTab.setTab06(false);
		selectTab.setToolTipSave(StockConstants.TOOL_TIP_SAVE_BANCO);
		selectTab.setToolTipDelete(StockConstants.TOOL_TIP_DELETE_BANCO);
	}
	
	private void activarBotonesMonedas(){
		selectTab.setTab01(false);
		selectTab.setTab02(false);
		selectTab.setTab03(false);
		selectTab.setTab04(true);
		selectTab.setTab05(false);
		selectTab.setTab06(false);
		selectTab.setToolTipSave(StockConstants.TOOL_TIP_SAVE_MONEDA);
		selectTab.setToolTipDelete(StockConstants.TOOL_TIP_DELETE_MONEDA);
	}
	
	private void activarBotonesProductos(){
		selectTab.setTab01(false);
		selectTab.setTab02(false);
		selectTab.setTab03(false);
		selectTab.setTab04(false);
		selectTab.setTab06(false);
		selectTab.setTab05(true);
		selectTab.setToolTipSave(StockConstants.TOOL_TIP_SAVE_PRODUCTO);
		selectTab.setToolTipDelete(StockConstants.TOOL_TIP_DELETE_PRODUCTO);
	}
	
	private void activarBotonesContrato(){
		selectTab.setTab01(false);
		selectTab.setTab02(false);
		selectTab.setTab03(false);
		selectTab.setTab04(false);
		selectTab.setTab05(false);
		selectTab.setTab06(true);
		selectTab.setToolTipSave(StockConstants.TOOL_TIP_SAVE_PRODUCTO);
		selectTab.setToolTipDelete(StockConstants.TOOL_TIP_DELETE_PRODUCTO);
	}
}
