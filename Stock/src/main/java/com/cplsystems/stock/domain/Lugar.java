package com.cplsystems.stock.domain;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table (name = "lugar")

public class Lugar {
	
	private Long idLugar;
	private String nombre;
	private Long idProyecto;
	private Proyecto proyecto;
	
	@Id
	@Column (name = "id_lugar", nullable = false)
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	public Long getIdLugar() {
		return idLugar;
	}
	
	public void setIdLugar(Long idLugar) {
		this.idLugar = idLugar;
	}
	@Column (name = "nombre", length =250)
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Column (name = "idProyecto", length =250)
	public Long getIdProyecto() {
		return idProyecto;
	}
	
	public void setIdProyecto(Long idProyecto) {
		this.idProyecto = idProyecto;
	}

	
	@ManyToOne
	@JoinColumn(name = "proyecto")
	public Proyecto getProyecto() {
		return proyecto;
	}

	public void setProyecto(Proyecto proyecto) {
		this.proyecto = proyecto;
	}
	
	
	
}

