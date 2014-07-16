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
	private Float costoMaximo;// ELIMINAR
	private Calendar costoMaximoFecha;// ELIMINAR
	private Float costoReposicion;// ELIMINAR
	private Calendar costoReposicionFecha;// ELIMINAR
	private Float costoCapa;// ELIMINAR
	private Calendar costoCapaFecha;// ELIMINAR
	private Float costoUltimo;// ELIMINAR
	private Calendar costoUltimoFecha;// ELIMINAR
	private Float costoPromedio;// ELIMINAR
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

	@Column
	public boolean getActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	@Column
	public Integer getEnExistencia() {
		return enExistencia;
	}

	public void setEnExistencia(Integer enExistencia) {
		this.enExistencia = enExistencia;
	}

	@Transient
	public boolean isSeleccionar() {
		return seleccionar;
	}

	public void setSeleccionar(boolean seleccionar) {
		this.seleccionar = seleccionar;
	}

	@Column
	public Float getPrecio2() {
		return precio2;
	}

	public void setPrecio2(Float precio2) {
		this.precio2 = precio2;
	}

	@Column
	public Float getPrecio3() {
		return precio3;
	}

	public void setPrecio3(Float precio3) {
		this.precio3 = precio3;
	}

	@Column
	public Float getPrecio4() {
		return precio4;
	}

	public void setPrecio4(Float precio4) {
		this.precio4 = precio4;
	}

	@Column
	public Float getPrecio5() {
		return precio5;
	}

	public void setPrecio5(Float precio5) {
		this.precio5 = precio5;
	}

	@Column
	public Float getPrecio6() {
		return precio6;
	}

	public void setPrecio6(Float precio6) {
		this.precio6 = precio6;
	}

	@Column
	public Float getPrecio7() {
		return precio7;
	}

	public void setPrecio7(Float precio7) {
		this.precio7 = precio7;
	}

	@Column
	public Float getPrecio8() {
		return precio8;
	}

	public void setPrecio8(Float precio8) {
		this.precio8 = precio8;
	}

	@Column
	public Float getPrecio9() {
		return precio9;
	}

	public void setPrecio9(Float precio9) {
		this.precio9 = precio9;
	}

	@Column
	public Float getPrecio10() {
		return precio10;
	}

	public void setPrecio10(Float precio10) {
		this.precio10 = precio10;
	}

	@Column
	public Integer getFactor1() {
		return factor1;
	}

	public void setFactor1(Integer factor1) {
		this.factor1 = factor1;
	}

	@Column
	public Integer getFactor2() {
		return factor2;
	}

	public void setFactor2(Integer factor2) {
		this.factor2 = factor2;
	}

	@Column
	public Integer getFactor3() {
		return factor3;
	}

	public void setFactor3(Integer factor3) {
		this.factor3 = factor3;
	}

	@Column
	public Integer getFactor4() {
		return factor4;
	}

	public void setFactor4(Integer factor4) {
		this.factor4 = factor4;
	}

	@Column
	public Integer getFactor5() {
		return factor5;
	}

	public void setFactor5(Integer factor5) {
		this.factor5 = factor5;
	}

	@Column
	public Integer getFactor6() {
		return factor6;
	}

	public void setFactor6(Integer factor6) {
		this.factor6 = factor6;
	}

	@Column
	public Integer getFactor7() {
		return factor7;
	}

	public void setFactor7(Integer factor7) {
		this.factor7 = factor7;
	}

	@Column
	public Integer getFactor8() {
		return factor8;
	}

	public void setFactor8(Integer factor8) {
		this.factor8 = factor8;
	}

	@Column
	public Integer getFactor9() {
		return factor9;
	}

	public void setFactor9(Integer factor9) {
		this.factor9 = factor9;
	}

	@Column
	public Integer getFactor10() {
		return factor10;
	}

	public void setFactor10(Integer factor10) {
		this.factor10 = factor10;
	}

	@Column
	public Float getMargen1() {
		return margen1;
	}

	public void setMargen1(Float margen1) {
		this.margen1 = margen1;
	}

	@Column
	public Float getMargen2() {
		return margen2;
	}

	public void setMargen2(Float margen2) {
		this.margen2 = margen2;
	}

	@Column
	public Float getMargen3() {
		return margen3;
	}

	public void setMargen3(Float margen3) {
		this.margen3 = margen3;
	}

	@Column
	public Float getMargen4() {
		return margen4;
	}

	public void setMargen4(Float margen4) {
		this.margen4 = margen4;
	}

	@Column
	public Float getMargen5() {
		return margen5;
	}

	public void setMargen5(Float margen5) {
		this.margen5 = margen5;
	}

	@Column
	public Float getMargen6() {
		return margen6;
	}

	public void setMargen6(Float margen6) {
		this.margen6 = margen6;
	}

	@Column
	public Float getMargen7() {
		return margen7;
	}

	public void setMargen7(Float margen7) {
		this.margen7 = margen7;
	}

	@Column
	public Float getMargen8() {
		return margen8;
	}

	public void setMargen8(Float margen8) {
		this.margen8 = margen8;
	}

	@Column
	public Float getMargen9() {
		return margen9;
	}

	public void setMargen9(Float margen9) {
		this.margen9 = margen9;
	}

	@Column
	public Float getMargen10() {
		return margen10;
	}

	public void setMargen10(Float margen10) {
		this.margen10 = margen10;
	}

	@Column
	public Float getCostoMaximo() {
		return costoMaximo;
	}

	public void setCostoMaximo(Float costoMaximo) {
		this.costoMaximo = costoMaximo;
	}

	@Column
	public Calendar getCostoMaximoFecha() {
		return costoMaximoFecha;
	}

	public void setCostoMaximoFecha(Calendar costoMaximoFecha) {
		this.costoMaximoFecha = costoMaximoFecha;
	}

	@Column
	public Float getCostoReposicion() {
		return costoReposicion;
	}

	public void setCostoReposicion(Float costoReposicion) {
		this.costoReposicion = costoReposicion;
	}

	@Column
	public Calendar getCostoReposicionFecha() {
		return costoReposicionFecha;
	}

	public void setCostoReposicionFecha(Calendar costoReposicionFecha) {
		this.costoReposicionFecha = costoReposicionFecha;
	}

	@Column
	public Float getCostoCapa() {
		return costoCapa;
	}

	public void setCostoCapa(Float costoCapa) {
		this.costoCapa = costoCapa;
	}

	@Column
	public Calendar getCostoCapaFecha() {
		return costoCapaFecha;
	}

	public void setCostoCapaFecha(Calendar costoCapaFecha) {
		this.costoCapaFecha = costoCapaFecha;
	}

	@Column
	public Float getCostoUltimo() {
		return costoUltimo;
	}

	public void setCostoUltimo(Float costoUltimo) {
		this.costoUltimo = costoUltimo;
	}

	@Column
	public Calendar getCostoUltimoFecha() {
		return costoUltimoFecha;
	}

	public void setCostoUltimoFecha(Calendar costoUltimoFecha) {
		this.costoUltimoFecha = costoUltimoFecha;
	}

	@Column
	public Float getCostoPromedio() {
		return costoPromedio;
	}

	public void setCostoPromedio(Float costoPromedio) {
		this.costoPromedio = costoPromedio;
	}

	@Column
	public Float getPrecioPublico() {
		return precioPublico;
	}

	public void setPrecioPublico(Float precioPublico) {
		this.precioPublico = precioPublico;
	}

	@Transient
	public boolean isCambioNaturaleza() {
		return cambioNaturaleza;
	}

	public void setCambioNaturaleza(boolean cambioNaturaleza) {
		this.cambioNaturaleza = cambioNaturaleza;
	}

	@OneToOne
	@JoinColumn(name = "unidad")
	public Unidad getUnidad() {
		return unidad;
	}

	public void setUnidad(Unidad unidad) {
		this.unidad = unidad;
	}

	@OneToOne
	@JoinColumn(name = "productoNaturaleza")
	public ProductoNaturaleza getProductoNaturaleza() {
		return productoNaturaleza;
	}

	public void setProductoNaturaleza(ProductoNaturaleza productoNaturaleza) {
		this.productoNaturaleza = productoNaturaleza;
	}

	@OneToOne
	@JoinColumn(name = "moneda")
	public Moneda getMoneda() {
		return moneda;
	}

	public void setMoneda(Moneda moneda) {
		this.moneda = moneda;
	}

	@Column
	public Long getMinimo() {
		return minimo;
	}

	public void setMinimo(Long minimo) {
		this.minimo = minimo;
	}

	@Column
	public Long getMaximo() {
		return maximo;
	}

	public void setMaximo(Long maximo) {
		this.maximo = maximo;
	}

	@OneToOne
	@JoinColumn(name = "organizacion")
	public Organizacion getOrganizacion() {
		return organizacion;
	}
	public void setOrganizacion(Organizacion organizacion) {
		this.organizacion = organizacion;
	}
	
	@OneToOne
	@JoinColumn(name = "usuario")
	public Usuarios getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}
	
	@Column
	public String getFechaActualizacion() {
		return fechaActualizacion;
	}
	public void setFechaActualizacion(String fechaActualizacion) {
		this.fechaActualizacion = fechaActualizacion;
	}
}
