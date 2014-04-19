package com.cplsystems.stock.dao;

import java.util.List;

import com.cplsystems.stock.domain.Contrato;

/**
 * @author Carlos Palalía López
 */

public interface ContratoDAO {

	public void save(Contrato contrato);

	public void delete(Contrato contrato);

	public Contrato getById(Long idContrato);

	public List<Contrato> getAll();
}
