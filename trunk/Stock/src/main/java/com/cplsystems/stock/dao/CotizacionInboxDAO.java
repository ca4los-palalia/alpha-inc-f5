/**
 * 
 */
package com.cplsystems.stock.dao;

import java.util.List;

import com.cplsystems.stock.domain.CotizacionInbox;
import com.cplsystems.stock.domain.Organizacion;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
public interface CotizacionInboxDAO {

	void save(CotizacionInbox cotizacionInbox);

	void delete(CotizacionInbox cotizacionInbox);

	List<CotizacionInbox> getAllNews(final Organizacion organizacion);

	List<CotizacionInbox> getAll(final Organizacion organizacion);

}
