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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

@Entity
@Table
public class ActaNacimiento implements Serializable {

    private static final long serialVersionUID = -6256254276251157687L;

    public final static int VIVO = 0;
    public final static int MUERTO = 1;

    public final static int MASCULINO = 0;
    public final static int FEMENINO = 1;
    public final static int INTER_SEX = 2;

    public final static int PADRE = 0;
    public final static int MADRE = 1;
    public final static int AMBOS = 2;
    public final static int PERSONA_DISTINTA = 3;

    private Integer idActaNacimiento;
    private String folio;
    private Estado estadoRegistro;
    private Ciudad ciudadRegistro;
    private Municipio municipioRegistro;
    private Libro libro;
    private String numeroActa;
    private Calendar fechaRegistro;
    private Persona juezOriginal;
    private Persona recienNacido;
    private String horaNacimiento;
    private String crip;
    private int presentado;
    private int declaradoPor;
    private Persona padre;
    private Persona madre;
    private Persona abueloPaterno;
    private Persona abueloMaterno;
    private Persona abuelaPaterna;
    private Persona abuelaMaterna;
    private Persona primerTestigo;
    private Persona segundoTestigo;
    private Persona personaDistintaDeclaracion;
    private String anotacionesActaNacimiento;
    private Estado estadoEstracto;
    private Ciudad ciudadEstracto;
    private Municipio municipioEstracto;
    private Persona juezRegistroCiivl;
    private TipoSolicitud tipoSolicitud;
    private Calendar fechaCreacion;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getIdActaNacimiento() {
	return idActaNacimiento;
    }

    public void setIdActaNacimiento(Integer idActaNacimiento) {
	this.idActaNacimiento = idActaNacimiento;
    }

    @Column
    public String getFolio() {
	return folio;
    }

    public void setFolio(String folio) {
	this.folio = folio;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "idEstado", insertable = false, updatable = false)
    public Estado getEstadoRegistro() {
	return estadoRegistro;
    }

    public void setEstadoRegistro(Estado estadoRegistro) {
	this.estadoRegistro = estadoRegistro;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "idCiudad", insertable = false, updatable = false)
    public Ciudad getCiudadRegistro() {
	return ciudadRegistro;
    }

    public void setCiudadRegistro(Ciudad ciudadRegistro) {
	this.ciudadRegistro = ciudadRegistro;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "idMunicipio")
    public Municipio getMunicipioRegistro() {
	return municipioRegistro;
    }

    public void setMunicipioRegistro(Municipio municipioRegistro) {
	this.municipioRegistro = municipioRegistro;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "idLibro")
    public Libro getLibro() {
	return libro;
    }

    public void setLibro(Libro libro) {
	this.libro = libro;
    }

    @Column
    public String getNumeroActa() {
	return numeroActa;
    }

    public void setNumeroActa(String numeroActa) {
	this.numeroActa = numeroActa;
    }

    @Column
    @Temporal(TemporalType.DATE)
    public Calendar getFechaRegistro() {
	return fechaRegistro;
    }

    public void setFechaRegistro(Calendar fechaRegistro) {
	this.fechaRegistro = fechaRegistro;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "idPersona", insertable = false, updatable = false)
    public Persona getJuezOriginal() {
	return juezOriginal;
    }

    public void setJuezOriginal(Persona juezOriginal) {
	this.juezOriginal = juezOriginal;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "idPersona", insertable = false, updatable = false)
    public Persona getRecienNacido() {
	return recienNacido;
    }

    public void setRecienNacido(Persona recienNacido) {
	this.recienNacido = recienNacido;
    }

    @Column
    public String getHoraNacimiento() {
	return horaNacimiento;
    }

    public void setHoraNacimiento(String horaNacimiento) {
	this.horaNacimiento = horaNacimiento;
    }

    @Column
    public String getCrip() {
	return crip;
    }

    public void setCrip(String crip) {
	this.crip = crip;
    }

    @Column
    public int getPresentado() {
	return presentado;
    }

    public void setPresentado(int presentado) {
	this.presentado = presentado;
    }

    @Column
    public int getDeclaradoPor() {
	return declaradoPor;
    }

    public void setDeclaradoPor(int declaradoPor) {
	this.declaradoPor = declaradoPor;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "idPersona", insertable = false, updatable = false)
    public Persona getPadre() {
	return padre;
    }

    public void setPadre(Persona padre) {
	this.padre = padre;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "idPersona", insertable = false, updatable = false)
    public Persona getMadre() {
	return madre;
    }

    public void setMadre(Persona madre) {
	this.madre = madre;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "idPersona", insertable = false, updatable = false)
    public Persona getAbueloPaterno() {
	return abueloPaterno;
    }

    public void setAbueloPaterno(Persona abueloPaterno) {
	this.abueloPaterno = abueloPaterno;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "idPersona", insertable = false, updatable = false)
    public Persona getAbueloMaterno() {
	return abueloMaterno;
    }

    public void setAbueloMaterno(Persona abueloMaterno) {
	this.abueloMaterno = abueloMaterno;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "idPersona", insertable = false, updatable = false)
    public Persona getAbuelaPaterna() {
	return abuelaPaterna;
    }

    public void setAbuelaPaterna(Persona abuelaPaterna) {
	this.abuelaPaterna = abuelaPaterna;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "idPersona", insertable = false, updatable = false)
    public Persona getAbuelaMaterna() {
	return abuelaMaterna;
    }

    public void setAbuelaMaterna(Persona abuelaMaterna) {
	this.abuelaMaterna = abuelaMaterna;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "idPersona", insertable = false, updatable = false)
    public Persona getPrimerTestigo() {
	return primerTestigo;
    }

    public void setPrimerTestigo(Persona primerTestigo) {
	this.primerTestigo = primerTestigo;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "idPersona", insertable = false, updatable = false)
    public Persona getSegundoTestigo() {
	return segundoTestigo;
    }

    public void setSegundoTestigo(Persona segundoTestigo) {
	this.segundoTestigo = segundoTestigo;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "idPersona", insertable = false, updatable = false)
    public Persona getPersonaDistintaDeclaracion() {
	return personaDistintaDeclaracion;
    }

    public void setPersonaDistintaDeclaracion(Persona personaDistintaDeclaracion) {
	this.personaDistintaDeclaracion = personaDistintaDeclaracion;
    }

    @Column
    public String getAnotacionesActaNacimiento() {
	return anotacionesActaNacimiento;
    }

    public void setAnotacionesActaNacimiento(String anotacionesActaNacimiento) {
	this.anotacionesActaNacimiento = anotacionesActaNacimiento;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "idEstado", insertable = false, updatable = false)
    public Estado getEstadoEstracto() {
	return estadoEstracto;
    }

    public void setEstadoEstracto(Estado estadoEstracto) {
	this.estadoEstracto = estadoEstracto;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "idCiudad", insertable = false, updatable = false)
    public Ciudad getCiudadEstracto() {
	return ciudadEstracto;
    }

    public void setCiudadEstracto(Ciudad ciudadEstracto) {
	this.ciudadEstracto = ciudadEstracto;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "idPersona", insertable = false, updatable = false)
    public Persona getJuezRegistroCiivl() {
	return juezRegistroCiivl;
    }

    public void setJuezRegistroCiivl(Persona juezRegistroCiivl) {
	this.juezRegistroCiivl = juezRegistroCiivl;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "idMunicipio", insertable = false, updatable = false)
    public Municipio getMunicipioEstracto() {
	return municipioEstracto;
    }

    public void setMunicipioEstracto(Municipio municipioEstracto) {
	this.municipioEstracto = municipioEstracto;
    }

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "idTipoSolicitud")
    public TipoSolicitud getTipoSolicitud() {
	return tipoSolicitud;
    }

    public void setTipoSolicitud(TipoSolicitud tipoSolicitud) {
	this.tipoSolicitud = tipoSolicitud;
    }

    @Version
    @Temporal(TemporalType.TIMESTAMP)
    @Column
    public Calendar getFechaCreacion() {
	return fechaCreacion;
    }

    public void setFechaCreacion(Calendar fechaCreacion) {
	this.fechaCreacion = fechaCreacion;
    }

}
