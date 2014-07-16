/**
 * 
 */
package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplsystems.stock.dao.OrdenCompraInboxDAO;
import com.cplsystems.stock.domain.OrdenCompraInbox;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 *
 */
@Service
public class OrdenCompraInboxService {

	@Autowired
	private OrdenCompraInboxDAO ordenCompraInboxDAO;
	
	public void save(final OrdenCompraInbox ordenCompraInbox) {
		ordenCompraInboxDAO.save(ordenCompraInbox);
	}
	
	public void delete(final OrdenCompraInbox ordenCompraInbox) {
		ordenCompraInboxDAO.delete(ordenCompraInbox);
	}
	
	public List<OrdenCompraInbox> getAllNews() {
		return ordenCompraInboxDAO.getAllNews();
	}
	
	public List<OrdenCompraInbox> getAll() {
		return ordenCompraInboxDAO.getAll();
	}
}
