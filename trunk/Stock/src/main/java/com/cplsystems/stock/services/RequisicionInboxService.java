/**
 * 
 */
package com.cplsystems.stock.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplsystems.stock.dao.RequisicionInboxDAO;
import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.RequisicionInbox;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
@Service
public class RequisicionInboxService {

	@Autowired
	private RequisicionInboxDAO requisicionInboxDAO;

	public void save(final RequisicionInbox requisicionInbox) {
		requisicionInboxDAO.save(requisicionInbox);
	}

	public void delete(final RequisicionInbox requisicionInbox) {
		requisicionInboxDAO.delete(requisicionInbox);
	}

	public List<RequisicionInbox> getAllNews(final Organizacion organizacion) {
		return requisicionInboxDAO.getAllNews(organizacion);
	}

	public List<RequisicionInbox> getAll(final Organizacion organizacion) {
		return requisicionInboxDAO.getAll(organizacion);
	}

}
