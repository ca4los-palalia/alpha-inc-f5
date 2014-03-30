/**
 * 
 */
package com.cplsystems.incidencias.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 
 * 
 */
@Entity
@Table
public class ActaMatrimonio implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 6599403705011451514L;
    private Integer idActaMatrimonio;

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Integer getIdActaMatrimonio() {
	return idActaMatrimonio;
    }

    public void setIdActaMatrimonio(Integer idActaMatrimonio) {
	this.idActaMatrimonio = idActaMatrimonio;
    }

}
