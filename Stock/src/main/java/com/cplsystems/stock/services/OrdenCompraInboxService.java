package com.cplsystems.stock.services;

import com.cplsystems.stock.dao.OrdenCompraInboxDAO;
import com.cplsystems.stock.domain.OrdenCompraInbox;
import com.cplsystems.stock.domain.Organizacion;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrdenCompraInboxService {
	@Autowired
	private OrdenCompraInboxDAO ordenCompraInboxDAO;

	public void save(OrdenCompraInbox ordenCompraInbox) {
		this.ordenCompraInboxDAO.save(ordenCompraInbox);
	}

	public void delete(OrdenCompraInbox ordenCompraInbox) {
		this.ordenCompraInboxDAO.delete(ordenCompraInbox);
	}

	public List<OrdenCompraInbox> getAllNews(Organizacion organizacion) {
		return this.ordenCompraInboxDAO.getAllNews(organizacion);
	}

	public List<OrdenCompraInbox> getAll(Organizacion organizacion) {
		return this.ordenCompraInboxDAO.getAll(organizacion);
	}
}
