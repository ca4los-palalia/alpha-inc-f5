/**
 * 
 */
package com.cplsystems.stock.app.vm.proveedor;

import com.cplsystems.stock.domain.Contacto;
import com.cplsystems.stock.domain.Direccion;
import com.cplsystems.stock.domain.Email;
import com.cplsystems.stock.domain.Persona;
import com.cplsystems.stock.domain.Proveedor;
import com.cplsystems.stock.domain.Telefono;


/**
 * @author Carlos Palalia
 *
 */
public abstract class ProveedorMetaClass extends ProveedorVariables {

	private static final long serialVersionUID = -4078164796340868446L;

	
	public boolean validarEntradaDatosProveedor(){
		
		boolean validado = false;
		if(nombreProveedor != null && !nombreProveedor.equals(""))
			validado = true;
		else
			validado = false;
		
		
		if(calle != null && !calle.equals(""))
			validado = true;
		else
			validado = false;
		
		
		if(numeroExterior != null && numeroExterior > 0)
			validado = true;
		else
			validado = false;
		
		
		if(colonia != null && !colonia.equals(""))
			validado = true;
		else
			validado = false;
		
		
		if(municipio != null && !municipio.equals(""))
			validado = true;
		else
			validado = false;
		
		
		if(estado != null)
			validado = true;
		else
			validado = false;
		
		
		if(codigoPostal != null && !codigoPostal.equals(""))
			validado = true;
		else
			validado = false;
		
		
		if(rfc != null && !rfc.equals(""))
			validado = true;
		else
			validado = false;
		

		if(telefono != null && telefono > 0)
			validado = true;
		else
			validado = false;
		
	
		/*giro
		razonSocial
		numeroInterior
		ciudad
		pais
		fax		
		email
		contrato
		paginaWeb*/
		
		return validado;
	}
	
	
	public void guardarProveedor(){
		
		Proveedor nuevoProveedor = new Proveedor();
		nuevoProveedor.setClave(null);
		nuevoProveedor.setComentario(comentario);
		nuevoProveedor.setCuentaCargo(null);
		nuevoProveedor.setGiro(giro);
		nuevoProveedor.setNombre(nombreProveedor);
		nuevoProveedor.setPassword(null);
		nuevoProveedor.setRazonSocial(razonSocial);
		nuevoProveedor.setRfc(rfc);
		nuevoProveedor.setStatus(null);
		nuevoProveedor.setContrato(contrato);
		
		Direccion direccionProveedor = new Direccion();
		
		direccionProveedor.setCalle(calle);
		direccionProveedor.setColonia(colonia);
		direccionProveedor.setCp(codigoPostal);
		direccionProveedor.setCuidad(ciudad);
		direccionProveedor.setNumExt(String.valueOf(numeroExterior));
		direccionProveedor.setNumInt(String.valueOf(numeroInterior));
		direccionProveedor.setEstado(estado);
		direccionProveedor.setMunicipio(municipio);
		direccionProveedor.setPais(pais);
		direccionService.save(direccionProveedor);
		direccionProveedor = direccionService.getUltimoRegistroDireccion();
		
		
		
		
		Email email = new Email();
		email.setEmail(emailContacto);
		emailService.save(email);
		email = emailService.getUltimoRegistroEmail();

		Telefono telefono  = new Telefono();
		telefono.setNumero(String.valueOf(telefonoContacto));
		telefono.setExtension(String.valueOf(extencionContacto));
		telefonoService.save(telefono);
		telefono = telefonoService.getUltimoregistroEmail();
		
		Contacto contacto = new Contacto();
		contacto.setEmail(email);
		contacto.setTelefono(telefono);
		contactoService.save(contacto);
		contacto = contactoService.getUltimoRegistroContacto();
		
		
		Direccion direccionContacto = new Direccion();
		direccionService.save(direccionContacto);
		direccionContacto = direccionService.getUltimoRegistroDireccion();
		
		
		Persona representanteACLiente = new Persona();
		representanteACLiente.setNombre(nombreContacto);
		representanteACLiente.setContacto(contacto);
		representanteACLiente.setDireccion(direccionContacto);
		personaService.save(representanteACLiente);
		representanteACLiente = personaService.getUltimoRegistroPersona();
		
		nuevoProveedor.setRepresentanteAteCliente(representanteACLiente);
		
		proveedorService.save(nuevoProveedor);
	}

	public void actualizarProveedorCambios(){
		for (Proveedor proveedor : proveedores) {
			proveedorService.save(proveedor);
		}
	}
}
