package com.cplsystems.stock.dao;

import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.RequisicionInbox;
import java.util.List;

public abstract interface RequisicionInboxDAO {
	public abstract void save(RequisicionInbox paramRequisicionInbox);

	public abstract void delete(RequisicionInbox paramRequisicionInbox);

	public abstract List<RequisicionInbox> getAllNews(Organizacion paramOrganizacion);

	public abstract List<RequisicionInbox> getAll(Organizacion paramOrganizacion);
}
