/**
 * 
 */
package com.cplsystems.incidencias.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
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
public class DireccionPersona implements Serializable {

    private static final long serialVersionUID = -2951235383149251187L;

    private Integer idDireccionPersona;
    private int numeroInterio;
    private int numeroExterior;
    private String calle;
    private String codigoPostal;
    private Estado estado;
    private Ciudad ciudad;
    private Municipio municipio;
    private Colonia colonia;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getIdDireccionPersona() {
	return idDireccionPersona;
    }

    public void setIdDireccionPersona(Integer idDireccionPersona) {
	this.idDireccionPersona = idDireccionPersona;
    }

    @Column
    public int getNumeroInterio() {
	return numeroInterio;
    }

    public void setNumeroInterio(int numeroInterio) {
	this.numeroInterio = numeroInterio;
    }

    @Column
    public int getNumeroExterior() {
	return numeroExterior;
    }

    public void setNumeroExterior(int numeroExterior) {
	this.numeroExterior = numeroExterior;
    }

    @Column
    public String getCalle() {
	return calle;
    }

    public void setCalle(String calle) {
	this.calle = calle;
    }

    @Column
    public String getCodigoPostal() {
	return codigoPostal;
    }

    public void setCodigoPostal(String codigoPostal) {
	this.codigoPostal = codigoPostal;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "idEstado")
    public Estado getEstado() {
	return estado;
    }

    public void setEstado(Estado estado) {
	this.estado = estado;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "idCiudad")
    public Ciudad getCiudad() {
	return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
	this.ciudad = ciudad;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "idMunicipio")
    public Municipio getMunicipio() {
	return municipio;
    }

    public void setMunicipio(Municipio municipio) {
	this.municipio = municipio;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "idColonia")
    public Colonia getColonia() {
	return colonia;
    }

    public void setColonia(Colonia colonia) {
	this.colonia = colonia;
    }

}
