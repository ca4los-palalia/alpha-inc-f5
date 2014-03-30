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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * 
 * 
 */
@Entity
@Table
public class Ciudad implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idCiudad;
    private String nombre;
    private String abreviatura;
    private Estado estado;
    private List<Municipio> municipios = new ArrayList<Municipio>();

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getIdCiudad() {
	return idCiudad;
    }

    public void setIdCiudad(Integer idCiudad) {
	this.idCiudad = idCiudad;
    }

    public String getNombre() {
	return nombre;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    public String getAbreviatura() {
	return abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
	this.abreviatura = abreviatura;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idEstado")
    public Estado getEstado() {
	return estado;
    }

    public void setEstado(Estado estado) {
	this.estado = estado;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "ciudad")
    public List<Municipio> getMunicipios() {
	return municipios;
    }

    public void setMunicipios(List<Municipio> municipios) {
	this.municipios = municipios;
    }

}
