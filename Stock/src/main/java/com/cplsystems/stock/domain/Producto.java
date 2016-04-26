package com.cplsystems.stock.domain;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table
public class Producto implements Serializable {
	private static final long serialVersionUID = 8065024909123508528L;
	private Long idProducto;
	private boolean activo;
	private String clave;
	private String codigoBarras;
	private String descripcion;
	private String marca;
	private String modelo;
	private String nombre;
	private Float precio;
	private Float precio2;
	private Float precio3;
	private Float precio4;
	private Float precio5;
	private Float precio6;
	private Float precio7;
	private Float precio8;
	private Float precio9;
	private Float precio10;
	private Integer factor1;
	private Integer factor2;
	private Integer factor3;
	private Integer factor4;
	private Integer factor5;
	private Integer factor6;
	private Integer factor7;
	private Integer factor8;
	private Integer factor9;
	private Integer factor10;
	private Float margen1;
	private Float margen2;
	private Float margen3;
	private Float margen4;
	private Float margen5;
	private Float margen6;
	private Float margen7;
	private Float margen8;
	private Float margen9;
	private Float margen10;
	private Float costoMaximo;
	private Calendar costoMaximoFecha;
	private Float costoReposicion;
	private Calendar costoReposicionFecha;
	private Float costoCapa;
	private Calendar costoCapaFecha;
	private Float costoUltimo;
	private Calendar costoUltimoFecha;
	private Float costoPromedio;
	private Float precioPublico;
	private boolean seleccionar;
	private boolean cambioNaturaleza;
	private Integer enExistencia;
	private Unidad unidad;
	private ProductoNaturaleza productoNaturaleza;
	private Moneda moneda;
	private Long minimo;
	private Long maximo;
	private Organizacion organizacion;
	private Usuarios usuario;
	private String fechaActualizacion;
	private ClaveArmonizada claveArmonizada;
	private Integer restan;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idProducto", nullable = false)
	public Long getIdProducto() {
		return this.idProducto;
	}

	public void setIdProducto(Long idProducto) {
		this.idProducto = idProducto;
	}

	@Column(name = "clave", length = 250)
	public String getClave() {
		return this.clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	@Column(name = "descripcion", columnDefinition = "TEXT")
	public String getDescripcion() {
		return this.descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column(name = "nombre", length = 250)
	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column
	public Float getPrecio() {
		return this.precio;
	}

	public void setPrecio(Float precio) {
		this.precio = precio;
	}

	@Column
	public String getMarca() {
		return this.marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	@Column
	public String getModelo() {
		return this.modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	@Column
	public String getCodigoBarras() {
		return this.codigoBarras;
	}

	public void setCodigoBarras(String codigoBarras) {
		this.codigoBarras = codigoBarras;
	}

	@Column
	public boolean getActivo() {
		return this.activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	@Column
	public Integer getEnExistencia() {
		return this.enExistencia;
	}

	public void setEnExistencia(Integer enExistencia) {
		this.enExistencia = enExistencia;
	}

	@Transient
	public boolean isSeleccionar() {
		return this.seleccionar;
	}

	public void setSeleccionar(boolean seleccionar) {
		this.seleccionar = seleccionar;
	}

	@Column
	public Float getPrecio2() {
		return this.precio2;
	}

	public void setPrecio2(Float precio2) {
		this.precio2 = precio2;
	}

	@Column
	public Float getPrecio3() {
		return this.precio3;
	}

	public void setPrecio3(Float precio3) {
		this.precio3 = precio3;
	}

	@Column
	public Float getPrecio4() {
		return this.precio4;
	}

	public void setPrecio4(Float precio4) {
		this.precio4 = precio4;
	}

	@Column
	public Float getPrecio5() {
		return this.precio5;
	}

	public void setPrecio5(Float precio5) {
		this.precio5 = precio5;
	}

	@Column
	public Float getPrecio6() {
		return this.precio6;
	}

	public void setPrecio6(Float precio6) {
		this.precio6 = precio6;
	}

	@Column
	public Float getPrecio7() {
		return this.precio7;
	}

	public void setPrecio7(Float precio7) {
		this.precio7 = precio7;
	}

	@Column
	public Float getPrecio8() {
		return this.precio8;
	}

	public void setPrecio8(Float precio8) {
		this.precio8 = precio8;
	}

	@Column
	public Float getPrecio9() {
		return this.precio9;
	}

	public void setPrecio9(Float precio9) {
		this.precio9 = precio9;
	}

	@Column
	public Float getPrecio10() {
		return this.precio10;
	}

	public void setPrecio10(Float precio10) {
		this.precio10 = precio10;
	}

	@Column
	public Integer getFactor1() {
		return this.factor1;
	}

	public void setFactor1(Integer factor1) {
		this.factor1 = factor1;
	}

	@Column
	public Integer getFactor2() {
		return this.factor2;
	}

	public void setFactor2(Integer factor2) {
		this.factor2 = factor2;
	}

	@Column
	public Integer getFactor3() {
		return this.factor3;
	}

	public void setFactor3(Integer factor3) {
		this.factor3 = factor3;
	}

	@Column
	public Integer getFactor4() {
		return this.factor4;
	}

	public void setFactor4(Integer factor4) {
		this.factor4 = factor4;
	}

	@Column
	public Integer getFactor5() {
		return this.factor5;
	}

	public void setFactor5(Integer factor5) {
		this.factor5 = factor5;
	}

	@Column
	public Integer getFactor6() {
		return this.factor6;
	}

	public void setFactor6(Integer factor6) {
		this.factor6 = factor6;
	}

	@Column
	public Integer getFactor7() {
		return this.factor7;
	}

	public void setFactor7(Integer factor7) {
		this.factor7 = factor7;
	}

	@Column
	public Integer getFactor8() {
		return this.factor8;
	}

	public void setFactor8(Integer factor8) {
		this.factor8 = factor8;
	}

	@Column
	public Integer getFactor9() {
		return this.factor9;
	}

	public void setFactor9(Integer factor9) {
		this.factor9 = factor9;
	}

	@Column
	public Integer getFactor10() {
		return this.factor10;
	}

	public void setFactor10(Integer factor10) {
		this.factor10 = factor10;
	}

	@Column
	public Float getMargen1() {
		return this.margen1;
	}

	public void setMargen1(Float margen1) {
		this.margen1 = margen1;
	}

	@Column
	public Float getMargen2() {
		return this.margen2;
	}

	public void setMargen2(Float margen2) {
		this.margen2 = margen2;
	}

	@Column
	public Float getMargen3() {
		return this.margen3;
	}

	public void setMargen3(Float margen3) {
		this.margen3 = margen3;
	}

	@Column
	public Float getMargen4() {
		return this.margen4;
	}

	public void setMargen4(Float margen4) {
		this.margen4 = margen4;
	}

	@Column
	public Float getMargen5() {
		return this.margen5;
	}

	public void setMargen5(Float margen5) {
		this.margen5 = margen5;
	}

	@Column
	public Float getMargen6() {
		return this.margen6;
	}

	public void setMargen6(Float margen6) {
		this.margen6 = margen6;
	}

	@Column
	public Float getMargen7() {
		return this.margen7;
	}

	public void setMargen7(Float margen7) {
		this.margen7 = margen7;
	}

	@Column
	public Float getMargen8() {
		return this.margen8;
	}

	public void setMargen8(Float margen8) {
		this.margen8 = margen8;
	}

	@Column
	public Float getMargen9() {
		return this.margen9;
	}

	public void setMargen9(Float margen9) {
		this.margen9 = margen9;
	}

	@Column
	public Float getMargen10() {
		return this.margen10;
	}

	public void setMargen10(Float margen10) {
		this.margen10 = margen10;
	}

	@Column
	public Float getCostoMaximo() {
		return this.costoMaximo;
	}

	public void setCostoMaximo(Float costoMaximo) {
		this.costoMaximo = costoMaximo;
	}

	@Column
	public Calendar getCostoMaximoFecha() {
		return this.costoMaximoFecha;
	}

	public void setCostoMaximoFecha(Calendar costoMaximoFecha) {
		this.costoMaximoFecha = costoMaximoFecha;
	}

	@Column
	public Float getCostoReposicion() {
		return this.costoReposicion;
	}

	public void setCostoReposicion(Float costoReposicion) {
		this.costoReposicion = costoReposicion;
	}

	@Column
	public Calendar getCostoReposicionFecha() {
		return this.costoReposicionFecha;
	}

	public void setCostoReposicionFecha(Calendar costoReposicionFecha) {
		this.costoReposicionFecha = costoReposicionFecha;
	}

	@Column
	public Float getCostoCapa() {
		return this.costoCapa;
	}

	public void setCostoCapa(Float costoCapa) {
		this.costoCapa = costoCapa;
	}

	@Column
	public Calendar getCostoCapaFecha() {
		return this.costoCapaFecha;
	}

	public void setCostoCapaFecha(Calendar costoCapaFecha) {
		this.costoCapaFecha = costoCapaFecha;
	}

	@Column
	public Float getCostoUltimo() {
		return this.costoUltimo;
	}

	public void setCostoUltimo(Float costoUltimo) {
		this.costoUltimo = costoUltimo;
	}

	@Column
	public Calendar getCostoUltimoFecha() {
		return this.costoUltimoFecha;
	}

	public void setCostoUltimoFecha(Calendar costoUltimoFecha) {
		this.costoUltimoFecha = costoUltimoFecha;
	}

	@Column
	public Float getCostoPromedio() {
		return this.costoPromedio;
	}

	public void setCostoPromedio(Float costoPromedio) {
		this.costoPromedio = costoPromedio;
	}

	@Column
	public Float getPrecioPublico() {
		return this.precioPublico;
	}

	public void setPrecioPublico(Float precioPublico) {
		this.precioPublico = precioPublico;
	}

	@Transient
	public boolean isCambioNaturaleza() {
		return this.cambioNaturaleza;
	}

	public void setCambioNaturaleza(boolean cambioNaturaleza) {
		this.cambioNaturaleza = cambioNaturaleza;
	}

	@OneToOne
	@JoinColumn(name = "unidad")
	public Unidad getUnidad() {
		return this.unidad;
	}

	public void setUnidad(Unidad unidad) {
		this.unidad = unidad;
	}

	@OneToOne
	@JoinColumn(name = "productoNaturaleza")
	public ProductoNaturaleza getProductoNaturaleza() {
		return this.productoNaturaleza;
	}

	public void setProductoNaturaleza(ProductoNaturaleza productoNaturaleza) {
		this.productoNaturaleza = productoNaturaleza;
	}

	@OneToOne
	@JoinColumn(name = "moneda")
	public Moneda getMoneda() {
		return this.moneda;
	}

	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
	}

	@Column
	public Long getMinimo() {
		return this.minimo;
	}

	public void setMinimo(Long minimo) {
		this.minimo = minimo;
	}

	@Column
	public Long getMaximo() {
		return this.maximo;
	}

	public void setMaximo(Long maximo) {
		this.maximo = maximo;
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

	@OneToOne
	@JoinColumn(name = "claveArmonizada")
	public ClaveArmonizada getClaveArmonizada() {
		return this.claveArmonizada;
	}

	public void setClaveArmonizada(ClaveArmonizada claveArmonizada) {
		this.claveArmonizada = claveArmonizada;
	}

	@Transient
	public Integer getRestan() {
		return restan;
	}

	public void setRestan(Integer restan) {
		this.restan = restan;
	}
	
	
}
