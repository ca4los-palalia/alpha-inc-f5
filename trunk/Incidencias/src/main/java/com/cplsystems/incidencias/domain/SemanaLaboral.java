/**
 * 
 */
package com.cplsystems.incidencias.domain;

 
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
 import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "SemanaLaboral")
public class SemanaLaboral {

	private Semana horariolaboral;
	private Dia dia;
	private Long idSemanaLaboral;
	private DatosOrganizacionales datosorganizacionales;
	private Integer totalHoras;

	@OneToMany
	@JoinColumn(name = "horariolaboral")
	public Semana getHorariolaboral() {
		return horariolaboral;
	}

	public void setHorariolaboral(Semana horariolaboral) {
		this.horariolaboral = horariolaboral;
	}

	@ManyToOne
	@JoinColumn(name = "dia")
	public Dia getDia() {
		return dia;
	}

	public void setDia(Dia dia) {
		this.dia = dia;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "IdSemanaLaboral", nullable = false)
	public Long getIdSemanaLaboral() {
		return idSemanaLaboral;
	}

	public void setIdSemanaLaboral(Long idSemanaLaboral) {
		this.idSemanaLaboral = idSemanaLaboral;
	}

	@ManyToOne
	@JoinColumn(name = "datosorganizacionales")
	public DatosOrganizacionales getDatosorganizacionales() {
		return datosorganizacionales;
	}

	public void setDatosorganizacionales(
			DatosOrganizacionales datosorganizacionales) {
		this.datosorganizacionales = datosorganizacionales;
	}

	@Column(name = "totalHoras", length = 250)
	public Integer getTotalHoras() {
		return totalHoras;
	}

	public void setTotalHoras(Integer totalHoras) {
		this.totalHoras = totalHoras;
	}

}
