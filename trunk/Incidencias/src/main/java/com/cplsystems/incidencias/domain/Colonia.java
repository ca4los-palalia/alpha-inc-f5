/**
 * 
 */
package com.cplsystems.incidencias.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * 
 * 
 */
@Entity
@Table
public class Colonia implements Serializable {

    private static final long serialVersionUID = -804654154747570504L;
    private Integer idColonia;
    private String nombre;
    private String abreviatura;
    private Municipio municipio;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getIdColonia() {
	return idColonia;
    }

    public void setIdColonia(Integer idColonia) {
	this.idColonia = idColonia;
    }

    @Column
    public String getNombre() {
	return nombre;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    @Column
    public String getAbreviatura() {
	return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
	this.abreviatura = abreviatura;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idMunicipio")
    public Municipio getMunicipio() {
	return municipio;
    }

    public void setMunicipio(Municipio municipio) {
	this.municipio = municipio;
    }

}
