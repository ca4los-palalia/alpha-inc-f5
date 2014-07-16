/**
 * 
 */
package com.cplsystems.stock.dao;

import java.util.List;

import com.cplsystems.stock.domain.OrdenCompraInbox;
import com.cplsystems.stock.domain.Organizacion;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
public interface OrdenCompraInboxDAO {

	void save(OrdenCompraInbox ordenCompraInbox);

	void delete(OrdenCompraInbox ordenCompraInbox);

	List<OrdenCompraInbox> getAllNews(final Organizacion organizacion);

	List<OrdenCompraInbox> getAll(final Organizacion organizacion);

}
