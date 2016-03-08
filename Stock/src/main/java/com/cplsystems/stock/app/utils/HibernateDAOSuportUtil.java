package com.cplsystems.stock.app.utils;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public abstract class HibernateDAOSuportUtil
  extends HibernateDaoSupport
{
  @Autowired
  public void init(SessionFactory sessionFactory)
    throws DataAccessException
  {
    setSessionFactory(sessionFactory);
  }
}
