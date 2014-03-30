/**
 * 
 */
package com.cplsystems.incidencias.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * 
 */
@Entity
@Table
public class TipoSolicitud implements Serializable {

    private static final long serialVersionUID = -1477242227733157185L;

    public final static int ACTA_NACIMIENTO = 0;
    public final static int ACTA_MATRIMONIO = 1;
    public final static int TRAMITE_CURP = 2;
    public final static int PAGO_PREDIAL = 3;
    public final static int COMPROBANTE_DOMICILIO = 4;
    public final static int CARTA_ANTECEDENTES_PENALES = 5;
    public final static int OTROS = 6;
    public final static int ACTA_DIFUNCION = 7;

    private int idTipoSolicitud;
    private String nombre;
    private String descripcion;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    public int getIdTipoSolicitud() {
	return idTipoSolicitud;
    }

    public void setIdTipoSolicitud(int idTipoSolicitud) {
	this.idTipoSolicitud = idTipoSolicitud;
    }

    @Column
    public String getNombre() {
	return nombre;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    @Column
    public String getDescripcion() {
	return descripcion;
    }

    public void setDescripcion(String descripcion) {
	this.descripcion = descripcion;
    }

}
