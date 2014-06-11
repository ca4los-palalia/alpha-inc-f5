package com.cplsystems.stock.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table
public class Producto {

	private Long idProducto;
	
	private boolean activo;
	private String clave;
	private String codigoBarras;
	private String descripcion;
	private String marca;
	private String modelo;
	private String nombre;
	private Float precio;
	private ProductoTipo productoTipo;
	private Boolean seleccionar;
	private Unidad unidad;
	private Integer enExistencia;
	
	
	
	
	

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idProducto", nullable = false)
	public Long getIdProducto() {
		return idProducto;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

	@Column(name = "clave", length = 250)
	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	@Column(name = "descripcion", columnDefinition = "TEXT")
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "nombre", length = 250)
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	@Column
	public Float getPrecio() {
		return precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}

	@Column
	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	@Column
	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	@Column
	public String getCodigoBarras() {
		return codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "productoTipo")
	public ProductoTipo getProductoTipo() {
		return productoTipo;
	}

	@Column
	public boolean getActivo() {
		return activo;
	}
	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	public void setProductoTipo(ProductoTipo productoTipo) {
		this.productoTipo = productoTipo;
	}
	@Column
	public Integer getEnExistencia() {
		return enExistencia;
	}

	public void setEnExistencia(Integer enExistencia) {
		this.enExistencia = enExistencia;
	}
	@Transient
	public Boolean isSeleccionar() {
		return seleccionar;
	}

	public void setSeleccionar(Boolean seleccionar) {
		this.seleccionar = seleccionar;
	}

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "unidad")
	public Unidad getUnidad() {
		return unidad;
	}

	public void setUnidad(Unidad unidad) {
		this.unidad = unidad;
	}
	
	
}
