package com.cplsystems.stock.services;

import com.cplsystems.stock.dao.CotizacionInboxDAO;
import com.cplsystems.stock.domain.CotizacionInbox;
import com.cplsystems.stock.domain.Organizacion;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CotizacionInboxService {
	@Autowired
	private CotizacionInboxDAO cotizacionInboxDAO;

	public void save(CotizacionInbox cotizacionInbox) {
		this.cotizacionInboxDAO.save(cotizacionInbox);
	}

	public void delete(CotizacionInbox cotizacionInbox) {
		this.cotizacionInboxDAO.delete(cotizacionInbox);
	}

	public List<CotizacionInbox> getAllNews(Organizacion organizacion) {
		return this.cotizacionInboxDAO.getAllNews(organizacion);
	}

	public List<CotizacionInbox> getAll(Organizacion organizacion) {
		return this.cotizacionInboxDAO.getAll(organizacion);
	}
}
