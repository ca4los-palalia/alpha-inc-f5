/**
 * 
 */
package com.cplsystems.incidencias.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "FormaPago")
public class FormaPago {

	private Long idFormaPago;
	private String Nombre;
	private String descripcion;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idFormaPago", nullable = false)
	public Long getIdFormaPago() {
		return idFormaPago;
	}

	public void setIdFormaPago(Long idFormaPago) {
		this.idFormaPago = idFormaPago;
	}

	@Column(name = "Nombre", length = 250)
	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	@Column(name = "descripcion", length = 250)
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
