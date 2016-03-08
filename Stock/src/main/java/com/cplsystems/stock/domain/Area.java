package com.cplsystems.stock.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table
public class Area implements Serializable {
	private static final long serialVersionUID = -7164588155326780268L;
	private Long idArea;
	private String nombre;
	private String descripcion;
	private String toolTipIndice;
	private String toolTipNombre;
	private Organizacion organizacion;
	private Usuarios usuario;
	private String fechaActualizacion;
	private boolean nuevoRegistro;
	private List<Almacen> almacen;

	public Area() {
		this.almacen = new ArrayList();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	public Long getIdArea() {
		return this.idArea;
	}

	public void setIdArea(Long idArea) {
		this.idArea = idArea;
	}

	@Column
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column
	public String getToolTipIndice() {
		return this.toolTipIndice;
	}

	public void setToolTipIndice(String toolTipIndice) {
		this.toolTipIndice = toolTipIndice;
	}

	@Column
	public String getToolTipNombre() {
		return this.toolTipNombre;
	}

	public void setToolTipNombre(String toolTipNombre) {
		this.toolTipNombre = toolTipNombre;
	}

	@Transient
	public boolean isNuevoRegistro() {
		return this.nuevoRegistro;
	}

	public void setNuevoRegistro(boolean nuevoRegistro) {
		this.nuevoRegistro = nuevoRegistro;
	}

	@OneToOne
	@JoinColumn(name = "organizacion")
	public Organizacion getOrganizacion() {
		return this.organizacion;
	}

	public void setOrganizacion(Organizacion organizacion) {
		this.organizacion = organizacion;
	}

	@OneToOne
	@JoinColumn(name = "usuario")
	public Usuarios getUsuario() {
		return this.usuario;
	}

	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}

	@Column
	public String getFechaActualizacion() {
		return this.fechaActualizacion;
	}

	public void setFechaActualizacion(String fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "area")
	public List<Almacen> getAlmacen() {
		return this.almacen;
	}

	public void setAlmacen(List<Almacen> almacen) {
		this.almacen = almacen;
	}
}
