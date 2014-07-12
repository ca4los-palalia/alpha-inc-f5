/**
 * 
 */
package com.cplsystems.stock.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
@Entity
@Table
public class Organizacion implements Serializable {

	private static final long serialVersionUID = 4373490847821834679L;
	private Long idOrganizacion;
	private String nombre;
	private String rfc;
	private String logotipo;
	private String calle;
	private String codigoPostal;
	private String pais;

	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdOrganizacion() {
		return idOrganizacion;
	}

	public void setIdOrganizacion(Long idOrganizacion) {
		this.idOrganizacion = idOrganizacion;
	}

	@Column
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column
	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	@Column
	public String getLogotipo() {
		return logotipo;
	}

	public void setLogotipo(String logotipo) {
		this.logotipo = logotipo;
	}

	@Column
	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	@Column
	public String getCodigoPostal() {
		return codigoPostal;
	}

	public void setCodigoPostal(String codigoPostal) {
		this.codigoPostal = codigoPostal;
	}

	@Column
	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

}
