package com.cplsystems.stock.dao;

import com.cplsystems.stock.domain.OrdenCompraInbox;
import com.cplsystems.stock.domain.Organizacion;
import java.util.List;

public abstract interface OrdenCompraInboxDAO {
	public abstract void save(OrdenCompraInbox paramOrdenCompraInbox);

	public abstract void delete(OrdenCompraInbox paramOrdenCompraInbox);

	public abstract List<OrdenCompraInbox> getAllNews(Organizacion paramOrganizacion);

	public abstract List<OrdenCompraInbox> getAll(Organizacion paramOrganizacion);
}
