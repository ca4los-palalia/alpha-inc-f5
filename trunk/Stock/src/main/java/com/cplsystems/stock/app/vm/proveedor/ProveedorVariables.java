/**
 * 
 */
package com.cplsystems.stock.app.vm.proveedor;

import java.util.List;

import com.cplsystems.stock.app.vm.BasicStructure;
import com.cplsystems.stock.app.vm.ServiceLayer;
import com.cplsystems.stock.domain.Contrato;
import com.cplsystems.stock.domain.Estado;
import com.cplsystems.stock.domain.Municipio;
import com.cplsystems.stock.domain.Pais;
import com.cplsystems.stock.domain.Proveedor;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 *
 */
public abstract class ProveedorVariables extends BasicStructure {

	private static final long serialVersionUID = -806765252363100225L;
	
	protected String nombreProveedor;
	protected String giro;
	protected String razonSocial;
	protected String calle;
	protected String colonia;
	protected String ciudad;
	protected String codigoPostal;
	protected String email;
	protected String paginaWeb;
	protected String rfc;
	protected String nombreContacto;
	protected String cargoContacto;
	protected String rfcContacto;
	protected String curpContacto;
	protected String comentario;
	protected String emailContacto;
	protected String buscarTexto;
	protected Integer telefonoContacto;
	protected Integer extencionContacto;
	protected Integer celularContacto;
	protected Integer fax;
	protected Integer telefono;
	protected Integer numeroExterior;
	protected Integer numeroInterior;
	protected String numeroRegistros;
	protected boolean visiblePerformSearch;
	protected boolean visibleDeleteRecord;
	protected boolean visibleNewRecord;
	protected boolean visibleSaveChanges;
	
	
	//protected List<Proveedor> currentFilerProveedor;
	protected List<Contrato> contratos;
	protected List<Estado> estados;
	protected List<Pais> paises;
	protected List<Municipio> municipios;
	protected List<Proveedor> proveedores;
	
	
	public String getNombreProveedor() {
		return nombreProveedor;
	}
	public void setNombreProveedor(String nombreProveedor) {
		this.nombreProveedor = nombreProveedor;
	}
	public String getGiro() {
		return giro;
	}
	public void setGiro(String giro) {
		this.giro = giro;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public String getCalle() {
		return calle;
	}
	public void setCalle(String calle) {
		this.calle = calle;
	}
	public Integer getNumeroExterior() {
		return numeroExterior;
	}
	public void setNumeroExterior(Integer numeroExterior) {
		this.numeroExterior = numeroExterior;
	}
	public Integer getNumeroInterior() {
		return numeroInterior;
	}
	public void setNumeroInterior(Integer numeroInterior) {
		this.numeroInterior = numeroInterior;
	}
	public String getColonia() {
		return colonia;
	}
	public void setColonia(String colonia) {
		this.colonia = colonia;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}
	public Integer getFax() {
		return fax;
	}
	public void setFax(Integer fax) {
		this.fax = fax;
	}
	public Integer getTelefono() {
		return telefono;
	}
	public void setTelefono(Integer telefono) {
		this.telefono = telefono;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPaginaWeb() {
		return paginaWeb;
	}
	public void setPaginaWeb(String paginaWeb) {
		this.paginaWeb = paginaWeb;
	}
	public String getRfc() {
		return rfc;
	}
	public void setRfc(String rfc) {
		this.rfc = rfc;
	}
	public String getNombreContacto() {
		return nombreContacto;
	}
	public void setNombreContacto(String nombreContacto) {
		this.nombreContacto = nombreContacto;
	}
	public String getCargoContacto() {
		return cargoContacto;
	}
	public void setCargoContacto(String cargoContacto) {
		this.cargoContacto = cargoContacto;
	}
	public String getRfcContacto() {
		return rfcContacto;
	}
	public void setRfcContacto(String rfcContacto) {
		this.rfcContacto = rfcContacto;
	}
	public String getCurpContacto() {
		return curpContacto;
	}
	public void setCurpContacto(String curpContacto) {
		this.curpContacto = curpContacto;
	}
	public Integer getTelefonoContacto() {
		return telefonoContacto;
	}
	public void setTelefonoContacto(Integer telefonoContacto) {
		this.telefonoContacto = telefonoContacto;
	}
	public Integer getExtencionContacto() {
		return extencionContacto;
	}
	public void setExtencionContacto(Integer extencionContacto) {
		this.extencionContacto = extencionContacto;
	}
	public String getEmailContacto() {
		return emailContacto;
	}
	public void setEmailContacto(String emailContacto) {
		this.emailContacto = emailContacto;
	}
	public Integer getCelularContacto() {
		return celularContacto;
	}
	public void setCelularContacto(Integer celularContacto) {
		this.celularContacto = celularContacto;
	}
	public String getComentario() {
		return comentario;
	}
	public void setComentario(String comentario) {
		this.comentario = comentario;
	}
	public List<Contrato> getContratos() {
		return contratos;
	}
	public void setContratos(List<Contrato> contratos) {
		this.contratos = contratos;
	}
	public List<Estado> getEstados() {
		return estados;
	}
	public void setEstados(List<Estado> estados) {
		this.estados = estados;
	}
	public List<Proveedor> getProveedores() {
		return proveedores;
	}
	public void setProveedores(List<Proveedor> proveedores) {
		this.proveedores = proveedores;
	}
	public List<Pais> getPaises() {
		return paises;
	}
	public void setPaises(List<Pais> paises) {
		this.paises = paises;
	}
	public List<Municipio> getMunicipios() {
		return municipios;
	}
	public void setMunicipios(List<Municipio> municipios) {
		this.municipios = municipios;
	}
	public String getBuscarTexto() {
		return buscarTexto;
	}
	public void setBuscarTexto(String buscarTexto) {
		this.buscarTexto = buscarTexto;
	}
	public boolean isVisiblePerformSearch() {
		return visiblePerformSearch;
	}
	public void setVisiblePerformSearch(boolean visiblePerformSearch) {
		this.visiblePerformSearch = visiblePerformSearch;
	}
	public boolean isVisibleDeleteRecord() {
		return visibleDeleteRecord;
	}
	public void setVisibleDeleteRecord(boolean visibleDeleteRecord) {
		this.visibleDeleteRecord = visibleDeleteRecord;
	}
	public boolean isVisibleNewRecord() {
		return visibleNewRecord;
	}
	public void setVisibleNewRecord(boolean visibleNewRecord) {
		this.visibleNewRecord = visibleNewRecord;
	}
	public boolean isVisibleSaveChanges() {
		return visibleSaveChanges;
	}
	public void setVisibleSaveChanges(boolean visibleSaveChanges) {
		this.visibleSaveChanges = visibleSaveChanges;
	}
	public String getNumeroRegistros() {
		return numeroRegistros;
	}
	public void setNumeroRegistros(String numeroRegistros) {
		this.numeroRegistros = numeroRegistros;
	}
	
	
	
}
