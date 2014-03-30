/**
 * 
 */
package com.cplsystems.incidencias.domain;

import java.io.Serializable;
import java.util.Calendar;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * 
 * 
 */
@Entity
@Table
public class Persona implements Serializable {

    private static final long serialVersionUID = -7730423686284589445L;
    private Integer idPersona;
    private String nombre;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String nacionalidad;
    private String parentesco;
    private Calendar fechaNacimiento;
    private DireccionPersona direccionPersona;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getIdPersona() {
	return idPersona;
    }

    public void setIdPersona(Integer idPersona) {
	this.idPersona = idPersona;
    }

    @Column
    public String getNombre() {
	return nombre;
    }

    public void setNombre(String nombre) {
	this.nombre = nombre;
    }

    @Column
    public String getApellidoPaterno() {
	return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
	this.apellidoPaterno = apellidoPaterno;
    }

    @Column
    public String getApellidoMaterno() {
	return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
	this.apellidoMaterno = apellidoMaterno;
    }

    @Column
    public String getNacionalidad() {
	return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
	this.nacionalidad = nacionalidad;
    }

    @Column
    public String getParentesco() {
	return parentesco;
    }

    public void setParentesco(String parentesco) {
	this.parentesco = parentesco;
    }

    @Temporal(TemporalType.DATE)
    public Calendar getFechaNacimiento() {
	return fechaNacimiento;
    }

    public void setFechaNacimiento(Calendar fechaNacimiento) {
	this.fechaNacimiento = fechaNacimiento;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "idDireccionPersona")
    public DireccionPersona getDireccionPersona() {
	return direccionPersona;
    }

    public void setDireccionPersona(DireccionPersona direccionPersona) {
	this.direccionPersona = direccionPersona;
    }

}
