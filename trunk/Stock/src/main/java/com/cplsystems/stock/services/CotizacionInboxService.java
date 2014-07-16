/**
 * 
 */
package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplsystems.stock.dao.CotizacionInboxDAO;
import com.cplsystems.stock.domain.CotizacionInbox;
import com.cplsystems.stock.domain.Organizacion;

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

	public List<CotizacionInbox> getAllNews(final Organizacion organizacion) {
		return cotizacionInboxDAO.getAllNews(organizacion);
	}

	public List<CotizacionInbox> getAll(final Organizacion organizacion) {
		return cotizacionInboxDAO.getAll(organizacion);
	}

}
