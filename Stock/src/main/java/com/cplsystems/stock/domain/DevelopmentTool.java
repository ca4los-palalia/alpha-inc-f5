package com.cplsystems.stock.domain;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table
public class DevelopmentTool implements Serializable {
	private static final long serialVersionUID = 4373490847821834679L;
	private Long idDevelopmentTool;
	private String descripcion;
	private String value;
	
	@Id
	@Column
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getIdDevelopmentTool() {
		return this.idDevelopmentTool;
	}

	public void setIdDevelopmentTool(Long idDevelopmentTool) {
		this.idDevelopmentTool = idDevelopmentTool;
	}

	@Column
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Column
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	
	
	
	
	
	
}
