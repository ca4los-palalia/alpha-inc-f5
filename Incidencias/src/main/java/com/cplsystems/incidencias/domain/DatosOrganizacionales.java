/**

 * 
 */
package com.cplsystems.incidencias.domain;

import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "DatosOrganizacionales")
public class DatosOrganizacionales {

	private Long idDatosOrganizacionales;
	private Calendar fechaContratacion;
	private Calendar fechaCambios;
	private Float percepcionMensual;
	private Float otrasPercepciones;
	private Float Contribuciones;
	private Float planUtilizacion;
	private Float porHora;
	private String numeroAfiliacionIMSS;
	private Boolean evaluacion;
	private Boolean reporteTiempo;
	private Boolean usaTargetaTiempo;
	private String clabe;
	private String cuenta;
	private String numeroTarjeta;
	private Persona persona;
	private TipoSueldo tiposueldo;
	private TipoNomina tiponomina;
	private Categoria categoria;
	private FormaPago formapago;
	private TipoPersonal tipopersonal;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdDatosOrganizacionales", nullable = false)
	public Long getIdDatosOrganizacionales() {
		return idDatosOrganizacionales;
	}

	public void setIdDatosOrganizacionales(Long idDatosOrganizacionales) {
		this.idDatosOrganizacionales = idDatosOrganizacionales;
	}

	@Column(name = "fechaContratacion", length = 250)
	public Calendar getFechaContratacion() {
		return fechaContratacion;
	}

	public void setFechaContratacion(Calendar fechaContratacion) {
		this.fechaContratacion = fechaContratacion;
	}

	@Column(name = "fechaCambios", length = 250)
	public Calendar getFechaCambios() {
		return fechaCambios;
	}

	public void setFechaCambios(Calendar fechaCambios) {
		this.fechaCambios = fechaCambios;
	}

	@Column(name = "percepcionMensual", length = 250)
	public Float getPercepcionMensual() {
		return percepcionMensual;
	}

	public void setPercepcionMensual(Float percepcionMensual) {
		this.percepcionMensual = percepcionMensual;
	}

	@Column(name = "otrasPercepciones", length = 250)
	public Float getOtrasPercepciones() {
		return otrasPercepciones;
	}

	public void setOtrasPercepciones(Float otrasPercepciones) {
		this.otrasPercepciones = otrasPercepciones;
	}

	@Column(name = "Contribuciones", length = 250)
	public Float getContribuciones() {
		return Contribuciones;
	}

	public void setContribuciones(Float contribuciones) {
		Contribuciones = contribuciones;
	}

	@Column(name = "PlanUtilizacion", length = 250)
	public Float getPlanUtilizacion() {
		return planUtilizacion;
	}

	public void setPlanUtilizacion(Float planUtilizacion) {
		this.planUtilizacion = planUtilizacion;
	}

	@Column(name = "porHora", length = 250)
	public Float getPorHora() {
		return porHora;
	}

	public void setPorHora(Float porHora) {
		this.porHora = porHora;
	}

	@Column(name = "numeroAfiliacionIMSS", length = 250)
	public String getNumeroAfiliacionIMSS() {
		return numeroAfiliacionIMSS;
	}

	public void setNumeroAfiliacionIMSS(String numeroAfiliacionIMSS) {
		this.numeroAfiliacionIMSS = numeroAfiliacionIMSS;
	}

	@Column(name = "evaluacion", length = 250)
	public Boolean getEvaluacion() {
		return evaluacion;
	}

	public void setEvaluacion(Boolean evaluacion) {
		this.evaluacion = evaluacion;
	}

	@Column(name = "reproteTiempo", length = 250)
	public Boolean getReporteTiempo() {
		return reporteTiempo;
	}

	public void setReporteTiempo(Boolean reporteTiempo) {
		this.reporteTiempo = reporteTiempo;
	}

	@Column(name = "usaTargetaTiempo", length = 250)
	public Boolean getUsaTargetaTiempo() {
		return usaTargetaTiempo;
	}

	public void setUsaTargetaTiempo(Boolean usaTargetaTiempo) {
		this.usaTargetaTiempo = usaTargetaTiempo;
	}

	@Column(name = "clabe", length = 250)
	public String getClabe() {
		return clabe;
	}

	public void setClabe(String clabe) {
		this.clabe = clabe;
	}

	@Column(name = "cuenta", length = 250)
	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	@Column(name = "numeroTarjeta", length = 250)
	public String getNumeroTarjeta() {
		return numeroTarjeta;
	}

	public void setNumeroTarjeta(String numeroTarjeta) {
		this.numeroTarjeta = numeroTarjeta;
	}

	@ManyToOne
	@JoinColumn(name = "datosorganizacionales")
	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	@ManyToOne
	@JoinColumn(name = "tiposueldo")
	public TipoSueldo getTiposueldo() {
		return tiposueldo;
	}

	public void setTiposueldo(TipoSueldo tiposueldo) {
		this.tiposueldo = tiposueldo;
	}

	@ManyToOne
	@JoinColumn(name = "tiponomina")
	public TipoNomina getTiponomina() {
		return tiponomina;
	}

	public void setTiponomina(TipoNomina tiponomina) {
		this.tiponomina = tiponomina;
	}

	@ManyToOne
	@JoinColumn(name = "categoria")
	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@ManyToOne
	@JoinColumn(name = "formapago")
	public FormaPago getFormapago() {
		return formapago;
	}

	public void setFormapago(FormaPago formapago) {
		this.formapago = formapago;
	}

	@ManyToOne
	@JoinColumn(name = "tipopersonal")
	public TipoPersonal getTipopersonal() {
		return tipopersonal;
	}

	public void setTipopersonal(TipoPersonal tipopersonal) {
		this.tipopersonal = tipopersonal;
	}

}
