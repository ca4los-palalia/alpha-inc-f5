/**
 * 
 */
package com.cplsystems.stock.dao;

import java.util.List;

import com.cplsystems.stock.domain.CotizacionInbox;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
public interface CotizacionInboxDAO {

	void save(CotizacionInbox cotizacionInbox);

	void delete(CotizacionInbox cotizacionInbox);

	List<CotizacionInbox> getAllNews();

	List<CotizacionInbox> getAll();

}
