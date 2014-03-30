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
public class Municipio implements Serializable {

    private static final long serialVersionUID = -804654154747570504L;
    private Integer idMunicipio;
    private String nombre;
    private String abreviatura;
    private Ciudad ciudad;
    private List<Colonia> colonias = new ArrayList<Colonia>();

    @Id
    @Column(nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getIdMunicipio() {
	return idMunicipio;
    }

    public void setIdMunicipio(Integer idMunicipio) {
	this.idMunicipio = idMunicipio;
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
    @JoinColumn(name = "idCiudad")
    public Ciudad getCiudad() {
	return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
	this.ciudad = ciudad;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "municipio")
    public List<Colonia> getColonias() {
	return colonias;
    }

    public void setColonias(List<Colonia> colonias) {
	this.colonias = colonias;
    }

}
