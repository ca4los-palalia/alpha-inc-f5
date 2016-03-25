package com.cplsystems.stock.app.vm.perfil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.zkoss.bind.BindContext;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.image.AImage;
import org.zkoss.image.Image;
import org.zkoss.json.JSONArray;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Messagebox;

import com.cplsystems.stock.app.utils.StockUtils;
import com.cplsystems.stock.domain.Contacto;
import com.cplsystems.stock.domain.DevelopmentTool;
import com.cplsystems.stock.domain.Direccion;
import com.cplsystems.stock.domain.Email;
import com.cplsystems.stock.domain.Estado;
import com.cplsystems.stock.domain.Municipio;
import com.cplsystems.stock.domain.Pais;
import com.cplsystems.stock.domain.Persona;
import com.cplsystems.stock.domain.Telefono;
import com.cplsystems.stock.domain.Usuarios;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@VariableResolver({ DelegatingVariableResolver.class })
public class PerfilVM extends PerfilMetaClass {
	private static final long serialVersionUID = 2445512192837362588L;

	@Init
	public void init() {
		usuario = (Usuarios) sessionUtils.getFromSession("usuario");
		organizacion = usuario.getOrganizacion();

		typePassword = "password";

		habilitarPaisEstadoMunicipio = true;

		if (paises == null || paises.size() == 0)
			paises = paisService.getAll();
		if (estados == null || estados.size() == 0)
			estados = estadoService.getAll();
		if (municipios == null || municipios.size() == 0)
			municipios = municipioService.getAll();

		if (usuario.getOwner()) {
			inicializarObjetosOrganizacion();
			verOrganizacion = true;
		}
		inicializarObjetosUsuario();

	}

	private void inicializarObjetosUsuario() {

		if (usuario.getPersona() == null)
			personaUsuario = new Persona();
		else
			personaUsuario = usuario.getPersona();

		if (personaUsuario.getDireccion() == null)
			direccionUsuario = new Direccion();
		else
			direccionUsuario = personaUsuario.getDireccion();

		if (direccionUsuario.getPais() == null)
			paisUsuario = new Pais();
		else
			paisUsuario = getPaisFromList(direccionUsuario.getPais().getIdPais());

		if (direccionUsuario.getEstado() == null)
			estadoUsuario = new Estado();
		else
			estadoUsuario = getEstadoFromList(direccionUsuario.getEstado().getIdEstado());

		if (direccionUsuario.getMunicipio() == null)
			municipioUsuario = new Municipio();
		else
			municipioUsuario = getMunicipioFromList(direccionUsuario.getMunicipio().getIdMunicipio());

		if (personaUsuario.getContacto() == null)
			contactoUsuario = new Contacto();
		else
			contactoUsuario = personaUsuario.getContacto();

		if (contactoUsuario.getEmail() == null)
			emailUsuario = new Email();
		else
			emailUsuario = contactoUsuario.getEmail();

		if (contactoUsuario.getTelefono() == null)
			telefonoUsuario = new Telefono();
		else
			telefonoUsuario = contactoUsuario.getTelefono();
		System.err.println();
	}

	private void inicializarObjetosOrganizacion() {
		if (organizacion.getDireccion() == null)
			direccionOrganizacion = new Direccion();
		else
			direccionOrganizacion = organizacion.getDireccion();

		if (direccionOrganizacion.getPais() == null)
			paisOrganizacion = new Pais();
		else
			paisOrganizacion = getPaisFromList(direccionOrganizacion.getPais().getIdPais());

		if (direccionOrganizacion.getEstado() == null)
			estadoOrganizacion = new Estado();
		else
			estadoOrganizacion = getEstadoFromList(direccionOrganizacion.getEstado().getIdEstado());

		if (direccionOrganizacion.getMunicipio() == null)
			municipioOrganizacion = new Municipio();
		else
			municipioOrganizacion = getMunicipioFromList(direccionOrganizacion.getMunicipio().getIdMunicipio());

		if (organizacion.getContacto() == null)
			contactoOrganizacion = new Contacto();
		else
			contactoOrganizacion = organizacion.getContacto();

		if (contactoOrganizacion.getEmail() == null)
			emailOrganizacion = new Email();
		else
			emailOrganizacion = contactoOrganizacion.getEmail();

		if (contactoOrganizacion.getTelefono() == null)
			telefonoOrganizacion = new Telefono();
		else
			telefonoOrganizacion = contactoOrganizacion.getTelefono();

		if (organizacion.getLogotipo() != null)
			logotipoAImage = organizacion.getLogotipoAImage();

		if (organizacion.getDevelopmentTool() != null) {
			DevelopmentTool tool = organizacion.getDevelopmentTool();

			try {
				parser = new JsonParser();
				JsonObject jobject = parser.parse(stockUtils.Desencriptar(tool.getValue())).getAsJsonObject();

				emailDevelopment = new Email();
				emailDevelopment.setEmail(suprimirComillas(String.valueOf(jobject.get("user"))));
				emailDevelopment.setTipo(suprimirComillas(String.valueOf(jobject.get("pass"))));

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else
			emailDevelopment = new Email();

	}

	@Command
	@NotifyChange({ "municipios" })
	public void getMunicipiosSelectedEstadoItem() {
		municipios = municipioService.getByEstado(usuario.getPersona().getDireccion().getEstado());
	}

	@Command
	@NotifyChange({ "direccionUsuario", "paisUsuario", "estadoUsuario", "municipioUsuario",
			"habilitarPaisEstadoMunicipio" })
	public void getInformacionDireccionFromCPUsuario(@BindingParam("compUserCp") Component comp) {
		if (direccionUsuario.getCp() != null && !direccionUsuario.getCp().equals("")) {
			datosGlobalesJSON = inicializarConexionJsonUrl(direccionUsuario.getCp());
			direccionJSon = new Direccion();
			contadorCamposJson = 0;

			dumpProgramaJSONElement(datosGlobalesJSON);

			if (contadorCamposJson == 4) {
				direccionUsuario.setCuidad(direccionJSon.getMunicipio().getNombre());
				direccionUsuario.setColonia(direccionJSon.getColonia());
				direccionUsuario.setEstado(direccionJSon.getEstado());
				direccionUsuario.setMunicipio(direccionJSon.getMunicipio());

				paisUsuario = getPaisFromList(157L);
				estadoUsuario = direccionJSon.getEstado();
				municipioUsuario = direccionJSon.getMunicipio();
				habilitarPaisEstadoMunicipio = true;
			} else {
				habilitarPaisEstadoMunicipio = false;
				StockUtils.showSuccessmessage("Codigo postal no encontrado", Clients.NOTIFICATION_TYPE_WARNING, 0,
						comp);
			}
		} else {
			StockUtils.showSuccessmessage("Ingrese un codigo postal", Clients.NOTIFICATION_TYPE_WARNING, 0, comp);
		}
	}

	@Command
	@NotifyChange({ "direccionOrganizacion", "paisOrganizacion", "estadoOrganizacion", "municipioOrganizacion" })
	public void getInformacionDireccionFromCPOrganizacion(@BindingParam("compOrgCp") Component comp) {

		if (direccionOrganizacion.getCp() != null && !direccionOrganizacion.getCp().equals("")) {
			datosGlobalesJSON = inicializarConexionJsonUrl(direccionOrganizacion.getCp());
			direccionJSon = new Direccion();
			contadorCamposJson = 0;

			dumpProgramaJSONElement(datosGlobalesJSON);

			if (contadorCamposJson == 4) {

				direccionOrganizacion.setCuidad(direccionJSon.getMunicipio().getNombre());
				direccionOrganizacion.setColonia(direccionJSon.getColonia());
				direccionOrganizacion.setEstado(direccionJSon.getEstado());
				direccionOrganizacion.setMunicipio(direccionJSon.getMunicipio());

				paisOrganizacion = getPaisFromList(157L);
				estadoOrganizacion = direccionJSon.getEstado();
				municipioOrganizacion = direccionJSon.getMunicipio();
			} else
				StockUtils.showSuccessmessage("Codigo postal no encontrado", Clients.NOTIFICATION_TYPE_WARNING, 0,
						comp);
		} else
			StockUtils.showSuccessmessage("Ingrese un codigo postal", Clients.NOTIFICATION_TYPE_WARNING, 0, comp);
	}

	@Command
	@NotifyChange("logotipoAImage")
	public void uploadLogo(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx,
			@BindingParam("compOrgCp") Component comp) {

		UploadEvent upEvent = null;
		Object objUploadEvent = ctx.getTriggerEvent();
		if (objUploadEvent != null && (objUploadEvent instanceof UploadEvent)) {
			upEvent = (UploadEvent) objUploadEvent;
		}
		if (upEvent != null) {
			Media media = upEvent.getMedia();
			// int lengthofImage = media.getByteData().length;
			if (media instanceof Image) {
				/*
				 * if (lengthofImage > 500 * 1024) { Messagebox.show(
				 * "Please Select a Image of size less than 500Kb."); return; }
				 * else {
				 */
				logotipoAImage = (AImage) media;
				// usuario.getOrganizacion().setLogotipo(usuario.getOrganizacion().getLogotipoAImage().getByteData());
				StockUtils.showSuccessmessage("la imagen se ha cargado con exito", Clients.NOTIFICATION_TYPE_INFO, 0,
						comp);
				// }
			} else {
				Messagebox.show("The selected File is not an image.");
			}
		}
	}

	@Command
	@NotifyChange("typePassword")
	public void changeTypePassword() {
		if (verPassword) {
			typePassword = "text";
		} else
			typePassword = "password";
	}

	@Command
	@NotifyChange({ "organizacion", "usuario" })
	public void guardarCambios(@BindingParam("compSaveData") Component comp) {

		String mensaje = validarFormularioUsuario();

		if (mensaje.equals("")) {

			mensaje = validarDireccionUsuario();

			if (mensaje.equals("")) {
				String continuar = "";
				if (verOrganizacion) {
					if (developmentConstruction == null)
						developmentConstruction = new DevelopmentTool();
					developmentConstruction = crearCorreoDeServicioEmail(emailDevelopment.getEmail(),
							emailDevelopment.getTipo(), developmentConstruction);
					if (developmentConstruction.getDescripcion() == null
							|| developmentConstruction.getDescripcion().equals("")) {
						continuar = validarEnvioDeCorreo(developmentConstruction, emailDevelopment);
						if (continuar.equals("")) {

						}
					} else
						continuar = developmentConstruction.getDescripcion();
				}

				if (continuar.equals("")) {
					emailService.save(emailUsuario);
					telefonoService.save(telefonoUsuario);

					contactoUsuario.setEmail(emailUsuario);
					contactoUsuario.setTelefono(telefonoUsuario);
					contactoService.save(contactoUsuario);

					direccionUsuario.setPais(paisUsuario);
					direccionUsuario.setEstado(estadoUsuario);
					direccionUsuario.setMunicipio(municipioUsuario);
					direccionService.save(direccionUsuario);

					personaUsuario.setContacto(contactoUsuario);
					personaUsuario.setDireccion(direccionUsuario);
					personaService.save(personaUsuario);

					usuario.setPersona(personaUsuario);
					usuarioService.save(usuario);

					// ------------------------------------------------
					if (verOrganizacion) {
						emailService.save(emailOrganizacion);
						telefonoService.save(telefonoOrganizacion);

						contactoOrganizacion.setEmail(emailOrganizacion);
						contactoOrganizacion.setTelefono(telefonoOrganizacion);
						contactoService.save(contactoOrganizacion);

						direccionOrganizacion.setPais(paisOrganizacion);
						direccionOrganizacion.setEstado(estadoOrganizacion);
						direccionOrganizacion.setMunicipio(municipioOrganizacion);
						direccionService.save(direccionOrganizacion);

						developmentToolService.save(developmentConstruction);

						organizacion.setDevelopmentTool(developmentConstruction);
						organizacion.setContacto(contactoOrganizacion);
						organizacion.setDireccion(direccionOrganizacion);
						if (logotipoAImage != null)
							organizacion.setLogotipo(logotipoAImage.getByteData());
						organizacionService.save(organizacion);
					}
					StockUtils.showSuccessmessage("Informaciòn almacenada", Clients.NOTIFICATION_TYPE_INFO, 0, comp);
				} else
					StockUtils.showSuccessmessage(continuar, Clients.NOTIFICATION_TYPE_WARNING, 0, comp);
			}else
				StockUtils.showSuccessmessage(mensaje, Clients.NOTIFICATION_TYPE_WARNING, 0, comp);
		} else
			StockUtils.showSuccessmessage(mensaje, Clients.NOTIFICATION_TYPE_WARNING, 0, comp);
	}

}
