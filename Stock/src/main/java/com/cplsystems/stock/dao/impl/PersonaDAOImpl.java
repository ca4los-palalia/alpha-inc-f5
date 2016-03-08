package com.cplsystems.stock.dao.impl;

import com.cplsystems.stock.app.utils.HibernateDAOSuportUtil;
import com.cplsystems.stock.dao.PersonaDAO;
import com.cplsystems.stock.domain.Contacto;
import com.cplsystems.stock.domain.Direccion;
import com.cplsystems.stock.domain.Persona;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class PersonaDAOImpl
  extends HibernateDAOSuportUtil
  implements PersonaDAO
{
  @Transactional
  public void save(Persona persona)
  {
    getHibernateTemplate().saveOrUpdate(persona);
  }
  
  @Transactional
  public void update(Persona persona)
  {
    getHibernateTemplate().update(persona);
  }
  
  @Transactional
  public void delete(Persona persona)
  {
    getHibernateTemplate().delete(persona);
  }
  
  @Transactional(readOnly=true)
  public Persona getById(Long persona)
  {
    Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Persona.class);
    
    criteria.add(Restrictions.eq("idPersona", persona));
    List<Persona> lista = criteria.list();
    return (lista != null) && (!lista.isEmpty()) ? (Persona)lista.get(0) : null;
  }
  
  @Transactional(readOnly=true)
  public List<Persona> getAll()
  {
    Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Persona.class);
    
    List<Persona> lista = criteria.list();
    return (lista != null) && (!lista.isEmpty()) ? lista : null;
  }
  
  @Transactional(readOnly=true)
  public List<Persona> getBySexo(Long sexo)
  {
    Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Persona.class);
    
    criteria.add(Restrictions.eq("sexo", sexo));
    List<Persona> lista = criteria.list();
    return (lista != null) && (!lista.isEmpty()) ? lista : null;
  }
  
  @Transactional(readOnly=true)
  public List<Persona> getByDireccion(Direccion direccion)
  {
    Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Persona.class);
    
    criteria.add(Restrictions.eq("direccion", direccion));
    List<Persona> lista = criteria.list();
    return (lista != null) && (!lista.isEmpty()) ? lista : null;
  }
  
  @Transactional(readOnly=true)
  public List<Persona> getByContacto(Contacto contacto)
  {
    Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Persona.class);
    
    criteria.add(Restrictions.eq("contacto", contacto));
    List<Persona> lista = criteria.list();
    return (lista != null) && (!lista.isEmpty()) ? lista : null;
  }
  
  @Transactional(readOnly=true)
  public Persona getUltimoRegistroPersona()
  {
    Criteria criteria = getHibernateTemplate().getSessionFactory().openSession().createCriteria(Persona.class);
    
    criteria.addOrder(Order.desc("idPersona"));
    criteria.setMaxResults(1);
    List<Persona> lista = criteria.list();
    return (lista != null) && (!lista.isEmpty()) ? (Persona)lista.get(0) : null;
  }
}
