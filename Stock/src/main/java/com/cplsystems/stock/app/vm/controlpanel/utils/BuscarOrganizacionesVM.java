package com.cplsystems.stock.app.vm.controlpanel.utils;

import com.cplsystems.stock.app.utils.StockUtils;
import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.Usuarios;
import com.cplsystems.stock.services.OrganizacionService;
import com.cplsystems.stock.services.UsuarioService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Window;

@VariableResolver({DelegatingVariableResolver.class})
public class BuscarOrganizacionesVM
  extends BuscarOrganizacionesVariables
{
  private static final long serialVersionUID = 7041693171502316281L;
  @Wire("#companiasModalDialog")
  private Window companiasModalDialog;
  private List<Organizacion> companias;
  private Organizacion organizacionSeleccionada;
  private List<Usuarios> usuariosOrganizacion;
  
  @Init
  public void init(@ContextParam(ContextType.VIEW) Component view)
  {
    Selectors.wireComponents(view, this, false);
    this.usuariosOrganizacion = new ArrayList();
  }
  
  @Command
  @NotifyChange({"companias"})
  public void buscarCompania()
  {
    if ((this.compania != null) && (!this.compania.isEmpty()))
    {
      if ((this.rfc != null) && (!this.rfc.isEmpty())) {
        this.companias = this.organizacionService.getCompaniasByNombreRFC(this.compania, this.rfc);
      } else {
        this.companias = this.organizacionService.getCompaniasByNombre(this.compania);
      }
    }
    else if ((this.rfc != null) && (!this.rfc.isEmpty())) {
      this.companias = this.organizacionService.getCompaniasByRFC(this.rfc);
    } else {
      this.companias = this.organizacionService.getAll();
    }
  }
  
  @Command
  public void transferirCompania()
  {
    if (this.organizacionSeleccionada == null)
    {
      StockUtils.showSuccessmessage("Por favor seleccione un elemento antes de continuar", "warning", Integer.valueOf(4500), null);
      
      return;
    }
    this.usuariosOrganizacion = new ArrayList();
    this.usuariosOrganizacion = this.usuarioService.getUsuariosByOrganizacionAll(this.organizacionSeleccionada);
    
    this.companiasModalDialog.detach();
    Map<String, Object> args = new HashMap();
    args.put("organizacionSeleccionada", this.organizacionSeleccionada);
    args.put("usuariosOrganizacion", this.usuariosOrganizacion);
    BindUtils.postGlobalCommand(null, null, "loadDatosCompania", args);
  }
  
  @Command
  public void closeDialog()
  {
    if (this.companiasModalDialog != null) {
      this.companiasModalDialog.detach();
    }
  }
  
  public List<Organizacion> getCompanias()
  {
    return this.companias;
  }
  
  public void setCompanias(List<Organizacion> companias)
  {
    this.companias = companias;
  }
  
  public Organizacion getOrganizacionSeleccionada()
  {
    return this.organizacionSeleccionada;
  }
  
  public void setOrganizacionSeleccionada(Organizacion organizacionSeleccionada)
  {
    this.organizacionSeleccionada = organizacionSeleccionada;
  }
  
  public List<Usuarios> getUsuariosOrganizacion()
  {
    return this.usuariosOrganizacion;
  }
  
  public void setUsuariosOrganizacion(List<Usuarios> usuariosOrganizacion)
  {
    this.usuariosOrganizacion = usuariosOrganizacion;
  }
}
