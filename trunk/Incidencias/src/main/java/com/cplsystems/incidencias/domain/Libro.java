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
public class Libro implements Serializable {

    private static final long serialVersionUID = 703991426910067179L;

    private Integer idLibro;
    private String nombre;
    private String descripcion;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getIdLibro() {
	return idLibro;
    }

    public void setIdLibro(Integer idLibro) {
	this.idLibro = idLibro;
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
