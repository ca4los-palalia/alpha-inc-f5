/**
 * 
 */
package com.cplsystems.stock.dao;

import java.util.List;

import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.RequisicionInbox;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
public interface RequisicionInboxDAO {

	void save(RequisicionInbox requisicionInbox);

	void delete(RequisicionInbox requisicionInbox);

	List<RequisicionInbox> getAllNews(final Organizacion organizacion);

	List<RequisicionInbox> getAll(final Organizacion organizacion);

}
