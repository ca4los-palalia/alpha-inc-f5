/**
 * 
 */
package com.cplsystems.incidencias.domain;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * 
 */
@Entity
@Table
public class BandejaSolicitudes implements Serializable {

    /**
	 * 
	 */
    private static final long serialVersionUID = 5494403456455364431L;

    public final static int STATUS_PENDIENTE = 0;
    public final static int STATUS_FINALIZADO = 1;

    private Integer idBandejaSolicitudes;
    private Calendar fechaSolicitud;
    private Calendar fechaEntrega;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getIdBandejaSolicitudes() {
	return idBandejaSolicitudes;
    }

    public void setIdBandejaSolicitudes(Integer idBandejaSolicitudes) {
	this.idBandejaSolicitudes = idBandejaSolicitudes;
    }

    @Temporal(TemporalType.DATE)
    @Column
    public Calendar getFechaSolicitud() {
	return fechaSolicitud;
    }

    public void setFechaSolicitud(Calendar fechaSolicitud) {
	this.fechaSolicitud = fechaSolicitud;
    }

    @Temporal(TemporalType.DATE)
    @Column
    public Calendar getFechaEntrega() {
	return fechaEntrega;
    }

    public void setFechaEntrega(Calendar fechaEntrega) {
	this.fechaEntrega = fechaEntrega;
    }

}
