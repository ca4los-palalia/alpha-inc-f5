package com.cplsystems.stock.app.vm.perfil;

import org.zkoss.image.AImage;

import com.cplsystems.stock.app.vm.BasicStructure;
import com.cplsystems.stock.domain.Contacto;
import com.cplsystems.stock.domain.DevelopmentTool;
import com.cplsystems.stock.domain.Direccion;
import com.cplsystems.stock.domain.Email;
import com.cplsystems.stock.domain.Estado;
import com.cplsystems.stock.domain.Municipio;
import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.Pais;
import com.cplsystems.stock.domain.Persona;
import com.cplsystems.stock.domain.Telefono;
import com.cplsystems.stock.domain.Usuarios;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonPrimitive;

public abstract class PerfilVariables extends BasicStructure {
	private static final long serialVersionUID = 7810843192652008397L;
	
	protected Usuarios usuario;
	protected Organizacion organizacion	;
	protected boolean habilitarPaisEstadoMunicipio;
	
	
	protected JsonElement datosGlobalesJSON;;
	protected JsonParser parser;
	
	protected String keyJSon;
	protected JsonElement valueJSon;
	
	protected JsonObject obj;
	protected JsonArray array;
	protected JsonPrimitive valor;
	
	protected DevelopmentTool developmentConstruction;
	
	
	
	
	protected Contacto contactoUsuario;
	protected Contacto contactoOrganizacion;
	protected Email emailUsuario;
	protected Email emailOrganizacion;
	protected Telefono telefonoUsuario;
	protected Telefono telefonoOrganizacion;
	protected Pais paisUsuario;
	protected Pais paisOrganizacion;
	protected Estado estadoUsuario;
	protected Estado estadoOrganizacion;
	protected Municipio municipioUsuario;
	protected Municipio municipioOrganizacion;
	protected Direccion direccionUsuario;
	protected Direccion direccionOrganizacion;
	protected Persona personaUsuario;
	protected Email emailDevelopment;
	
	
	
	protected Direccion direccionJSon;
	
	protected int contadorCamposJson;
	protected boolean isDireccionObtenida;
	
	protected String typePassword;
	protected boolean verPassword;
	protected boolean verOrganizacion;
	protected AImage logotipoAImage;
	
	public Usuarios getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}

	public boolean isHabilitarPaisEstadoMunicipio() {
		return habilitarPaisEstadoMunicipio;
	}

	public void setHabilitarPaisEstadoMunicipio(boolean habilitarPaisEstadoMunicipio) {
		this.habilitarPaisEstadoMunicipio = habilitarPaisEstadoMunicipio;
	}

	public String getTypePassword() {
		return typePassword;
	}

	public void setTypePassword(String typePassword) {
		this.typePassword = typePassword;
	}

	public boolean isVerPassword() {
		return verPassword;
	}

	public void setVerPassword(boolean verPassword) {
		this.verPassword = verPassword;
	}

	public Organizacion getOrganizacion() {
		return organizacion;
	}

	public void setOrganizacion(Organizacion organizacion) {
		this.organizacion = organizacion;
	}

	public AImage getLogotipoAImage() {
		return logotipoAImage;
	}

	public void setLogotipoAImage(AImage logotipoAImage) {
		this.logotipoAImage = logotipoAImage;
	}

	public Contacto getContactoUsuario() {
		return contactoUsuario;
	}

	public void setContactoUsuario(Contacto contactoUsuario) {
		this.contactoUsuario = contactoUsuario;
	}

	public Contacto getContactoOrganizacion() {
		return contactoOrganizacion;
	}

	public void setContactoOrganizacion(Contacto contactoOrganizacion) {
		this.contactoOrganizacion = contactoOrganizacion;
	}

	public Email getEmailUsuario() {
		return emailUsuario;
	}

	public void setEmailUsuario(Email emailUsuario) {
		this.emailUsuario = emailUsuario;
	}

	public Email getEmailOrganizacion() {
		return emailOrganizacion;
	}

	public void setEmailOrganizacion(Email emailOrganizacion) {
		this.emailOrganizacion = emailOrganizacion;
	}

	public Telefono getTelefonoUsuario() {
		return telefonoUsuario;
	}

	public void setTelefonoUsuario(Telefono telefonoUsuario) {
		this.telefonoUsuario = telefonoUsuario;
	}

	public Telefono getTelefonoOrganizacion() {
		return telefonoOrganizacion;
	}

	public void setTelefonoOrganizacion(Telefono telefonoOrganizacion) {
		this.telefonoOrganizacion = telefonoOrganizacion;
	}

	public Pais getPaisUsuario() {
		return paisUsuario;
	}

	public void setPaisUsuario(Pais paisUsuario) {
		this.paisUsuario = paisUsuario;
	}

	public Pais getPaisOrganizacion() {
		return paisOrganizacion;
	}

	public void setPaisOrganizacion(Pais paisOrganizacion) {
		this.paisOrganizacion = paisOrganizacion;
	}

	public Estado getEstadoUsuario() {
		return estadoUsuario;
	}

	public void setEstadoUsuario(Estado estadoUsuario) {
		this.estadoUsuario = estadoUsuario;
	}

	public Estado getEstadoOrganizacion() {
		return estadoOrganizacion;
	}

	public void setEstadoOrganizacion(Estado estadoOrganizacion) {
		this.estadoOrganizacion = estadoOrganizacion;
	}

	public Municipio getMunicipioUsuario() {
		return municipioUsuario;
	}

	public void setMunicipioUsuario(Municipio municipioUsuario) {
		this.municipioUsuario = municipioUsuario;
	}

	public Municipio getMunicipioOrganizacion() {
		return municipioOrganizacion;
	}

	public void setMunicipioOrganizacion(Municipio municipioOrganizacion) {
		this.municipioOrganizacion = municipioOrganizacion;
	}

	public Direccion getDireccionUsuario() {
		return direccionUsuario;
	}

	public void setDireccionUsuario(Direccion direccionUsuario) {
		this.direccionUsuario = direccionUsuario;
	}

	public Direccion getDireccionOrganizacion() {
		return direccionOrganizacion;
	}

	public void setDireccionOrganizacion(Direccion direccionOrganizacion) {
		this.direccionOrganizacion = direccionOrganizacion;
	}

	public Persona getPersonaUsuario() {
		return personaUsuario;
	}

	public void setPersonaUsuario(Persona personaUsuario) {
		this.personaUsuario = personaUsuario;
	}

	public boolean isVerOrganizacion() {
		return verOrganizacion;
	}

	public void setVerOrganizacion(boolean verOrganizacion) {
		this.verOrganizacion = verOrganizacion;
	}

	public Email getEmailDevelopment() {
		return emailDevelopment;
	}

	public void setEmailDevelopment(Email emailDevelopment) {
		this.emailDevelopment = emailDevelopment;
	}
	
	
	
}
