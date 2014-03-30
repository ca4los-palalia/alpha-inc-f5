package com.cplsystems.stock.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "provedor")
public class Proveedor {

	private Long idProveedor;
	private String comentario;
	private Long idContacto;
	private Long idContrato;
	private Long direccionDevolucion;
	private Long direccionFiscal;
	private Long director;
	private Long gerenteFinanzas;
	private Long gerenteVentas;
	private String giro;
	private String nombre;
	private String password;
	private String razonSocial;
	private Long representanteLegal;
	private Long representanteAnteCliente;
	private String rfc;
	private String status;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_proveedor", nullable = false)
	public Long getIdProveedor() {
		return idProveedor;
	}

	public void setIdProveedor(Long idProveedor) {
		this.idProveedor = idProveedor;
	}

	@Column(name = "comentario", length = 250)
	public String getComentario() {
		return comentario;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	@Column(name = "id_contacto", nullable = false)
	public Long getIdContacto() {
		return idContacto;
	}

	public void setIdContacto(Long idContacto) {
		this.idContacto = idContacto;
	}

	@Column(name = "id_contrato", nullable = false)
	public Long getIdContrato() {
		return idContrato;
	}

	public void setIdContrato(Long idContrato) {
		this.idContrato = idContrato;
	}

	@Column(name = "direccion_devolucion", nullable = false)
	public Long getDireccionDevolucion() {
		return direccionDevolucion;
	}

	public void setDireccionDevolucion(Long direccionDevolucion) {
		this.direccionDevolucion = direccionDevolucion;
	}

	@Column
	public Long getDireccionFiscal() {
		return direccionFiscal;
	}

	public void setDireccionFiscal(Long direccionFiscal) {
		this.direccionFiscal = direccionFiscal;
	}

	@Column
	public Long getDirector() {
		return director;
	}

	public void setDirector(Long director) {
		this.director = director;
	}

	@Column
	public Long getGerenteFinanzas() {
		return gerenteFinanzas;
	}

	public void setGerenteFinanzas(Long gerenteFinanzas) {
		this.gerenteFinanzas = gerenteFinanzas;
	}

	@Column
	public Long getGerenteVentas() {
		return gerenteVentas;
	}

	public void setGerenteVentas(Long gerenteVentas) {
		this.gerenteVentas = gerenteVentas;
	}

	@Column(name = "giro", length = 250)
	public String getGiro() {
		return giro;
	}

	public void setGiro(String giro) {
		this.giro = giro;
	}

	@Column(name = "nombre", length = 250)
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Column(name = "password", length = 250)
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Column(name = "razon_social", length = 250)
	public String getRazonSocial() {
		return razonSocial;
	}

	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}

	@Column
	public Long getRepresentanteLegal() {
		return representanteLegal;
	}

	public void setRepresentanteLegal(Long representanteLegal) {
		this.representanteLegal = representanteLegal;
	}

	@Column
	public Long getRepresentanteAnteCliente() {
		return representanteAnteCliente;
	}

	public void setRepresentanteAnteCliente(Long representanteAnteCliente) {
		this.representanteAnteCliente = representanteAnteCliente;
	}

	@Column(name = "rfc", length = 250)
	public String getRfc() {
		return rfc;
	}

	public void setRfc(String rfc) {
		this.rfc = rfc;
	}

	@Column(name = "status", length = 250)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
