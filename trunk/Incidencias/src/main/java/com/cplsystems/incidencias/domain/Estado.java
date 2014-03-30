/**
 * 
 */
package com.cplsystems.incidencias.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 
 * 
 */
@Entity
@Table
public class Estado implements Serializable {

    private static final long serialVersionUID = -3295089333411649052L;

    private Integer idEstado;
    private String nombre;
    private String abreviatura;
    private List<Ciudad> ciudades = new ArrayList<Ciudad>();

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getIdEstado() {
	return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
	this.idEstado = idEstado;
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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "estado")
    public List<Ciudad> getCiudades() {
	return ciudades;
    }

    public void setCiudades(List<Ciudad> ciudades) {
	this.ciudades = ciudades;
    }

}
