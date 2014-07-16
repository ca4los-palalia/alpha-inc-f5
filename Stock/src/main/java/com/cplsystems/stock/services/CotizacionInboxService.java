/**
 * 
 */
package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplsystems.stock.dao.CotizacionInboxDAO;
import com.cplsystems.stock.domain.CotizacionInbox;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
@Service
public class CotizacionInboxService {

	@Autowired
	private CotizacionInboxDAO cotizacionInboxDAO;

	public void save(final CotizacionInbox cotizacionInbox) {
		cotizacionInboxDAO.save(cotizacionInbox);
	}

	public void delete(final CotizacionInbox cotizacionInbox) {
		cotizacionInboxDAO.delete(cotizacionInbox);
	}

	public List<CotizacionInbox> getAllNews() {
		return cotizacionInboxDAO.getAllNews();
	}

	public List<CotizacionInbox> getAll() {
		return cotizacionInboxDAO.getAll();
	}

}
