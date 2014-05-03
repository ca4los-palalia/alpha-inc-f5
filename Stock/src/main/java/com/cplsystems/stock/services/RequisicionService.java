/**
 * 
 */
package com.cplsystems.stock.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplsystems.stock.dao.RequisicionDAO;
import com.cplsystems.stock.domain.Requisicion;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
@Service
public class RequisicionService {

	@Autowired
	private RequisicionDAO requisicionDAO;

	public void save(final Requisicion requisicion) {
		requisicionDAO.save(requisicion);
	}

}
