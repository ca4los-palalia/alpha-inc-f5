/**
 * 
 */
package com.cplsystems.stock.app.vm.proveedor;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.util.Clients;

import com.cplsystems.stock.app.vm.proveedor.utils.MenuButtonsActivated;
import com.cplsystems.stock.domain.Banco;
import com.cplsystems.stock.domain.Contacto;
import com.cplsystems.stock.domain.Contrato;
import com.cplsystems.stock.domain.CuentaPago;
import com.cplsystems.stock.domain.Direccion;
import com.cplsystems.stock.domain.Email;
import com.cplsystems.stock.domain.Estado;
import com.cplsystems.stock.domain.Moneda;
import com.cplsystems.stock.domain.Municipio;
import com.cplsystems.stock.domain.Pais;
import com.cplsystems.stock.domain.Persona;
import com.cplsystems.stock.domain.Producto;
import com.cplsystems.stock.domain.Proveedor;
import com.cplsystems.stock.domain.ProveedorProducto;
import com.cplsystems.stock.domain.Telefono;


/**
 * @author Carlos Palalia
 *
 */
public abstract class ProveedorMetaClass extends ProveedorVariables {

	private static final long serialVersionUID = -4078164796340868446L;

	@Init
	public void init(){
		initObjects();
		initProperties();
	}
	
	public void initObjects(){
		nuevoProveedor = new Proveedor();
		municipioProveedor = new Municipio();   
		estadoProveedor = new Estado(); 
		paisProveedor = new Pais();
		telefonoProveedor = new Telefono();
		telefonoContacto = new Telefono(); 
		emailProveedor = new Email();
		emailContacto =  new Email();
		contactoProveedor = new Contacto();
		contactoContacto = new Contacto();
		direccionProveedor = new Direccion();
		nuevoProveedor = new Proveedor();
		representanteLegal = new Persona();
		personaContacto = new Persona();
		monedaSeleccionada = new Moneda();
		bancoSeleccionado = new Banco();
		contrato = new Contrato();
		cuentaPago = new CuentaPago();
		buscarProveedor = new Proveedor();
		buscarProducto = new Producto();
		proveedorProducto = new ProveedorProducto();
		buscarProveedorAsociar = new Proveedor();
		botonMuenu1 = new MenuButtonsActivated();
		botonMuenu2 = new MenuButtonsActivated();
		botonMuenu3 = new MenuButtonsActivated();
		botonMuenu4 = new MenuButtonsActivated();
		botonMuenu5 = new MenuButtonsActivated();
		botonMuenu6 = new MenuButtonsActivated();
		botonMuenu7 = new MenuButtonsActivated();
	}
	
	public void initProperties(){
		contratos = contratoService.getAll();
		estados = estadoService.getAll();
		paises = paisService.getAll();
		municipios = municipioService.getAll();
		bancosDB = bancoService.getAll();
		monedasDB = monedaService.getAll();
		paisProveedor= paisService.findById(157L);
		generarBotonesMenu();
		
	}
	private void generarBotonesMenu(){
		botonMuenu1.setNombre("Limpiar Formulario");
		botonMuenu1.setVisible(true);
		botonMuenu1.setNumero(1);
		botonMuenu2.setNombre("Guardar proveedor");
		botonMuenu2.setVisible(true);
		botonMuenu2.setNumero(2);
		botonMuenu3.setNombre("Eliminar proveedor");
		botonMuenu3.setNumero(3);
		botonMuenu4.setNombre("Actualizar proveedor");
		botonMuenu4.setNumero(4);
		botonMuenu5.setNombre("Buscar proveedor");
		botonMuenu5.setNumero(5);
		botonMuenu6.setNombre("Agregar producto");
		botonMuenu6.setNumero(6);
		botonMuenu7.setNombre("Quitar producto");
		botonMuenu7.setNumero(7);
	}
	
	public String validarEntradaDatosProveedor(){
		String mensajes = "";
		
		if(nuevoProveedor.getNombre().equals(null) || nuevoProveedor.getNombre().isEmpty())
			mensajes = "Es necesario ingresar el nombre del proveedor";
		/*else if(nuevoProveedor.getRfc().equals(null) || nuevoProveedor.getRfc().isEmpty())
			mensajes = "RFC del proveedor requerido";*/
		
		else if(paisProveedor.getNombre() == null || paisProveedor.getNombre().isEmpty()){
			mensajes = "Es necesario seleccionar un país para la dirección del proveedor";
		}else if(estadoProveedor.getNombre() == null || estadoProveedor.getNombre().isEmpty()){
			mensajes = "Es necesario seleccionar un estado para la dirección del proveedor";
		}else if(municipioProveedor.getNombre() == null || municipioProveedor.getNombre().isEmpty()){
			mensajes = "Es necesario seleccionar un municipio para la dirección del proveedor";
		}else if(direccionProveedor.getColonia() == null || direccionProveedor.getColonia().isEmpty()){
			mensajes = "Es necesario ingresar el nombre de una colonia para la dirección del proveedor";
		}else if(direccionProveedor.getCalle() == null || direccionProveedor.getColonia().isEmpty()){
			mensajes = "Es necesario ingresar el nombre de una calle para la dirección del proveedor";
		}else if(direccionProveedor.getNumExt() == null || direccionProveedor.getNumExt().isEmpty()){
			mensajes = "Es necesario ingresar un numero externo domiciliario para la dirección del proveedor";
		}else if(emailProveedor.getEmail() == null || emailProveedor.getEmail().isEmpty()){
			mensajes = "Es necesario ingresar un correo electronico para contactar al proveedor";
		}else if(telefonoProveedor.getNumero() == null || telefonoProveedor.getNumero().isEmpty()){
			mensajes = "Es necesario ingresar un número telefonico para contactar al proveedor";
		}else if(personaContacto.getApellidoPaterno() == null || personaContacto.getApellidoPaterno().isEmpty()){
			mensajes = "Es necesario ingresar apellido paterno del contacto";
		}else if(personaContacto.getApellidoMaterno() == null || personaContacto.getApellidoMaterno().isEmpty()){
			mensajes = "Es necesario ingresar apellido materno del contacto";
		}else if(personaContacto.getNombre() == null || personaContacto.getNombre().isEmpty()){
			mensajes = "Es necesario ingresar nombre de pila del contacto";
		}else if(telefonoContacto.getNumero() == null || telefonoContacto.getNumero().isEmpty()){
			mensajes = "Es necesario ingresar un número telefonico del contacto";
		}else if(emailProveedor.getEmail() == null || emailProveedor.getEmail().isEmpty()){
			mensajes = "Es necesario ingresar un correo electrónico del contacto";
		}
		/*else if(contrato.getDiasPago().equals(null)){
			mensajes = "Es necesario ingresar dias pagos normales en el contrato";
		}else if(contrato.getFechaVigenciaInicio().equals(null)){
			
		}else if(contrato.getFechaVigenciaFin().equals(null)){
			
		}*/
		else if(monedaSeleccionada.equals(null)){
			mensajes = "Es necesario seleccionar un tipo de moneda para la cuenta pago";
		}else if(bancoSeleccionado.equals(null)){
			mensajes = "Es necesario seleccionar un banco para la cuenta pago";
		}else if(cuentaPago.getCuentaBancaria().equals(null)){
			mensajes = "Es necesario ingresar un número de cuenta bancaria";
		}
		return mensajes;
	}
	
	@Command
	@NotifyChange("*")
	public void nuevoProveedor(){
		municipioProveedor = new Municipio();
		paisProveedor = new Pais();
		estadoProveedor = new Estado();
		emailContacto = new Email();
		emailProveedor = new Email();
		estadoProveedor = new Estado();
		monedaSeleccionada = new Moneda();
		municipioProveedor = new Municipio();
		nuevoProveedor = new Proveedor();
		paisProveedor = new Pais();
		personaContacto = new Persona();
		telefonoContacto = new Telefono();
		telefonoProveedor = new Telefono();
	}
	
	public void guardarProveedor(){
		
		if(!guardadoEmailProveedor){
			emailService.save(emailProveedor);
			guardadoEmailProveedor = true;
		}
		
		if(!guardadoEmailContacto){
			emailService.save(emailContacto);
			guardadoEmailContacto = true;
		}
		
		if(!guardadoTelefonoProveedor){
			telefonoService.save(telefonoProveedor);
			guardadoTelefonoProveedor = true;
		}
		if(!guardadoTelefonoContacto){
			telefonoService.save(telefonoContacto);
			guardadoTelefonoContacto = true;
		}
		
		if(!guardadoContactoProveedor){
			contactoProveedor.setTelefono(telefonoProveedor);
			contactoProveedor.setEmail(emailProveedor);
			contactoService.save(contactoProveedor);
			guardadoContactoProveedor = true;
		}
		
		if(!guardadoContactoContacto){
			contactoContacto.setEmail(emailContacto);
			contactoContacto.setTelefono(telefonoContacto);
			contactoService.save(contactoContacto);
			guardadoContactoContacto = true;
		}
		
		if(!guardadoDireccionProveedor){
			direccionProveedor.setMunicipio(municipioProveedor);
			direccionProveedor.setEstado(estadoProveedor);
			direccionProveedor.setPais(paisProveedor);
			direccionProveedor.setNumExt(direccionProveedor.getNumExt().toUpperCase());
			direccionProveedor.setNumInt(direccionProveedor.getNumInt().toUpperCase());
			direccionService.save(direccionProveedor);
			guardadoDireccionProveedor = true;
		}
		
		
		if(!guardadorepResentanteLegalProveedor){
			representanteLegal.setNombre(nuevoProveedor.getNombre());
			representanteLegal.setContacto(contactoProveedor);
			representanteLegal.setDireccion(direccionProveedor);
			personaService.save(representanteLegal);
			guardadorepResentanteLegalProveedor = true;
		}
		
		if(!guardadoPersonaContacto){
			personaContacto.setCurp(personaContacto.getCurp().toUpperCase());
			personaContacto.setRfc(personaContacto.getRfc().toUpperCase());
			personaContacto.setContacto(contactoContacto);
			personaService.save(personaContacto);
			guardadoPersonaContacto = true;
		}
		
		if(!guardadoNuevoProveedor){
			nuevoProveedor.setRfc(nuevoProveedor.getRfc().toUpperCase());
			nuevoProveedor.setRepresentanteAteCliente(personaContacto);
			nuevoProveedor.setContacto(contactoContacto);
			nuevoProveedor.setRepresentanteLegal(representanteLegal);
			nuevoProveedor.setDireccionFiscal(direccionProveedor);
			nuevoProveedor.setClave(
					nuevoProveedor.getNombre().substring(0, 2).toUpperCase()
					+ nuevoProveedor.getRfc().substring(nuevoProveedor.getRfc().length()-2).toUpperCase()
					+ estadoProveedor.getNombre().substring(0, 1).toUpperCase());
			
			nuevoProveedor.setFechaActualizacion(Calendar.getInstance());
			proveedorService.save(nuevoProveedor);
			guardadoNuevoProveedor = true;
		}
		
		if(!guardadoCuentaPago){
			cuentaPago.setProveedor(nuevoProveedor);
			cuentaPago.setBanco(bancoSeleccionado);
			cuentaPago.setMoneda(monedaSeleccionada);
			cuentasPagoService.save(cuentaPago);
			guardadoCuentaPago = true;
		}
		
	}

	public void actualizarProveedorCambios(){
		for (Proveedor proveedor : proveedoresLista) {
			proveedorService.update(proveedor);
		}
	}
	
	@SuppressWarnings("static-access")
	@Command
	@NotifyChange("*")	
	public void asignarProductosAProveedor(){
		Integer actualizados = 0;
		Integer noActualizados = 0;
		String mensaje = "";
		String mensajeNoActualizado = "";
		
		if(proveedoresAsociacionSelected != null){
			if(productosDB != null){
				

				for (Producto productoSeleccionado : productosDB) {
					if(productoSeleccionado.isSeleccionar()){
						
						boolean guardarProducto = true;
						for (ProveedorProducto proveedorProducto : proveedoresAsociacionSelected.getProveedorProducto()) {
							if(proveedorProducto.getProducto() == null){
								guardarProducto = false;
								break;
							}else if(proveedorProducto.getProducto().getClave().equals(productoSeleccionado.getClave())){
								guardarProducto = false;
								break;
							}
						}
						
						if(guardarProducto){
							ProveedorProducto nuevoProveedorProducto = new ProveedorProducto();
							nuevoProveedorProducto.setProveedor(proveedoresAsociacionSelected);
							nuevoProveedorProducto.setProducto(productoSeleccionado);
							proveedoresAsociacionSelected.getProveedorProducto().add(nuevoProveedorProducto);//agregar nuevo producto a la lista de proveedor para ser mostrada
							proveedorProductoService.save(nuevoProveedorProducto);
							actualizados ++;
						}else
							noActualizados ++;
							
					}
				}
				for (Producto producto : productosDB) {
					if(producto.isSeleccionar()){
						producto.setSeleccionar(false);
					}
				}
				if(noActualizados > 0)
					mensajeNoActualizado = "Se detectaron productos existentes para este proveedor [" + noActualizados + "].";
				
				mensaje = getMensajeAsignacionProductoAProveedor(
						actualizados, "asignado") +". " + mensajeNoActualizado + "";
				
				
				stockUtils.showSuccessmessage(
						mensaje,
						Clients.NOTIFICATION_TYPE_INFO, 0);
			}else{
				stockUtils.showSuccessmessage(
						"La lista de productos se encuentra vacia",
						Clients.NOTIFICATION_TYPE_ERROR, 0);
			}
		}else{
			stockUtils.showSuccessmessage(
					"No ha sido seleccionado un proveedor",
					Clients.NOTIFICATION_TYPE_WARNING, 0);
		}
		
	}
	
	
	@SuppressWarnings("static-access")
	@Command
	@NotifyChange("*")
	public void quitarProductosDeProveedor(){
		Integer registrosRemovidos = 0;
		
		if(proveedoresAsociacionSelected != null){
			
			if(proveedoresAsociacionSelected.getProveedorProducto() != null ){
				List<ProveedorProducto> removerProductos = new ArrayList<ProveedorProducto>();
				
				for (ProveedorProducto proveedorProducto : proveedoresAsociacionSelected.getProveedorProducto()) {
					if(proveedorProducto.getProducto().isSeleccionar()){
						removerProductos.add(proveedorProducto);
						
						proveedorProductoService.delete(proveedorProducto);
						registrosRemovidos ++;
					}
					proveedorProducto.getProducto().setSeleccionar(false);
				}
				proveedoresAsociacionSelected = proveedorService.getById(proveedoresAsociacionSelected.getIdProveedor());
				String mensaje = "";
				
				if(registrosRemovidos > 0)
					mensaje = "Se removieron " + registrosRemovidos 
							+ " productos del proveedor -" 
							+ proveedoresAsociacionSelected.getNombre() + "-";
				else
					mensaje = "Ningun producto fue removido para el proveedor -" 
					+ proveedoresAsociacionSelected.getNombre() + "-";
					
					
					stockUtils.showSuccessmessage(mensaje,
							Clients.NOTIFICATION_TYPE_INFO, 0);
			}else
				stockUtils.showSuccessmessage(
						"-" + proveedoresAsociacionSelected.getNombre() + "- no cuenta con productos. Nada que eliminar",
						Clients.NOTIFICATION_TYPE_WARNING, 0);
		}else{
			stockUtils.showSuccessmessage(
					"No ha sido seleccionado un proveedor",
					Clients.NOTIFICATION_TYPE_WARNING, 0);
		}
	}
	
	
	
	private String getMensajeAsignacionProductoAProveedor(Integer contador, String nombreAccionSingular){
		String mensaje = "";
		if(contador > 1)
			mensaje = contador
				+ " productos han sido " + nombreAccionSingular + "s al proveedor -"
				+ proveedoresAsociacionSelected.getNombre() +"-";
		else if(contador == 0)
			mensaje = "Ningún producto fue " + nombreAccionSingular + " al proveedor -"
					+ proveedoresAsociacionSelected.getNombre() +"-";
		else if(contador == 1)
			mensaje = contador
				+ " producto ha sido " + nombreAccionSingular + " al proveedor -"
				+ proveedoresAsociacionSelected.getNombre() +"-";
		
		return mensaje;
	}
}
