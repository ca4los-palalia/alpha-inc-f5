package com.cplsystems.stock.dao;

import com.cplsystems.stock.domain.Email;
import java.util.List;

public abstract interface EmailDAO {
	public abstract void save(Email paramEmail);

	public abstract void delete(Email paramEmail);

	public abstract Email getById(Long paramLong);

	public abstract List<Email> getAll();

	public abstract Email getUltimoRegistroEmail();
}
