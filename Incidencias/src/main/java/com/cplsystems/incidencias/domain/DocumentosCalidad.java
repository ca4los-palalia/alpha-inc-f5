/**
 * 
 */
package com.cplsystems.incidencias.domain;

import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "DocumentosCalidad")
public class DocumentosCalidad {
	private Long idDocumentoCalidad;
	private String ejercicio;
	private TipoDocumentoCalidad tipodocumentocalidad;
	private Documentacion documentacion;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdDocumentoCalidad", nullable = false)
	public Long getIdDocumentoCalidad() {
		return idDocumentoCalidad;
		
	}
	public void setIdDocumentoCalidad(Long idDocumentoCalidad) {
		this.idDocumentoCalidad = idDocumentoCalidad;
	}
	@Column(name = "ejercicio", length = 250)
	public String getEjercicio() {
		return ejercicio;
	}
	public void setEjercicio(String ejercicio) {
		this.ejercicio = ejercicio;
	}
	@OneToOne
	@JoinColumn(name = "tipodocumentocalidad")
	public TipoDocumentoCalidad getTipodocumentocalidad() {
		return tipodocumentocalidad;
	}
	public void setTipodocumentocalidad(TipoDocumentoCalidad tipodocumentocalidad) {
		this.tipodocumentocalidad = tipodocumentocalidad;
	}
	@OneToOne
	@JoinColumn(name = "documentacion")
	public Documentacion getDocumentacion() {
		return documentacion;
	}
	public void setDocumentacion(Documentacion documentacion) {
		this.documentacion = documentacion;
	}
	
	

}
