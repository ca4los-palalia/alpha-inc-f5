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
public class TipoPersona implements Serializable {

    private static final long serialVersionUID = 6123958248521710048L;

    public final static int FUNCIONARO = 0;
    public final static int CIUDADANO = 1;

    private Integer idTipoPersona;
    private String nombre;
    private String descripcion;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getIdTipoPersona() {
	return idTipoPersona;
    }

    public void setIdTipoPersona(Integer idTipoPersona) {
	this.idTipoPersona = idTipoPersona;
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
