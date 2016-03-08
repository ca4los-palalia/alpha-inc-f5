package com.cplsystems.stock.dao;

import com.cplsystems.stock.domain.CotizacionInbox;
import com.cplsystems.stock.domain.Organizacion;
import java.util.List;

public abstract interface CotizacionInboxDAO {
	public abstract void save(CotizacionInbox paramCotizacionInbox);

	public abstract void delete(CotizacionInbox paramCotizacionInbox);

	public abstract List<CotizacionInbox> getAllNews(Organizacion paramOrganizacion);

	public abstract List<CotizacionInbox> getAll(Organizacion paramOrganizacion);
}
