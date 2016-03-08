package com.cplsystems.stock.services;

import com.cplsystems.stock.dao.RequisicionInboxDAO;
import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.RequisicionInbox;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RequisicionInboxService {
	@Autowired
	private RequisicionInboxDAO requisicionInboxDAO;

	public void save(RequisicionInbox requisicionInbox) {
		this.requisicionInboxDAO.save(requisicionInbox);
	}

	public void delete(RequisicionInbox requisicionInbox) {
		this.requisicionInboxDAO.delete(requisicionInbox);
	}

	public List<RequisicionInbox> getAllNews(Organizacion organizacion) {
		return this.requisicionInboxDAO.getAllNews(organizacion);
	}

	public List<RequisicionInbox> getAll(Organizacion organizacion) {
		return this.requisicionInboxDAO.getAll(organizacion);
	}
}
