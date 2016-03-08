package com.cplsystems.stock.app.vm.controlpanel.utils;

import com.cplsystems.stock.app.vm.BasicStructure;
import com.cplsystems.stock.domain.Organizacion;
import java.util.ArrayList;
import java.util.List;

public class BuscarOrganizacionesVariables
  extends BasicStructure
{
  private static final long serialVersionUID = 4490601495632254008L;
  protected String compania;
  protected String rfc;
  protected List<Organizacion> organizaciones;
  protected Organizacion organizacionSeleccionada;
  
  public void init()
  {
    this.organizaciones = new ArrayList();
    this.organizacionSeleccionada = new Organizacion();
  }
  
  public String getCompania()
  {
    return this.compania;
  }
  
  public void setCompania(String compania)
  {
    this.compania = compania;
  }
  
  public String getRfc()
  {
    return this.rfc;
  }
  
  public void setRfc(String rfc)
  {
    this.rfc = rfc;
  }
  
  public List<Organizacion> getOrganizaciones()
  {
    return this.organizaciones;
  }
  
  public void setOrganizaciones(List<Organizacion> organizaciones)
  {
    this.organizaciones = organizaciones;
  }
  
  public Organizacion getOrganizacionSeleccionada()
  {
    return this.organizacionSeleccionada;
  }
  
  public void setOrganizacionSeleccionada(Organizacion organizacionSeleccionada)
  {
    this.organizacionSeleccionada = organizacionSeleccionada;
  }
}
