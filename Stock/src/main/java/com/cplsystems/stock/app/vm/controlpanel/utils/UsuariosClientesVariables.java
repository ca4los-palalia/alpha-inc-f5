package com.cplsystems.stock.app.vm.controlpanel.utils;

import com.cplsystems.stock.app.utils.SessionUtils;
import com.cplsystems.stock.app.vm.BasicStructure;
import com.cplsystems.stock.domain.Contacto;
import com.cplsystems.stock.domain.Email;
import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.Persona;
import com.cplsystems.stock.domain.Privilegios;
import com.cplsystems.stock.domain.Usuarios;
import com.cplsystems.stock.services.ContactoService;
import com.cplsystems.stock.services.PrivilegioService;
import com.cplsystems.stock.services.UsuarioService;
import java.util.ArrayList;
import java.util.List;

public class UsuariosClientesVariables
  extends BasicStructure
{
  private static final long serialVersionUID = 8459358422760322689L;
  protected List<Usuarios> usuarios;
  protected Usuarios usuarioSeleccionado;
  protected Organizacion organizacion;
  protected Boolean privilegioRequision = Boolean.valueOf(false);
  protected Boolean privilegioConcentrado = Boolean.valueOf(false);
  protected Boolean privilegioCotizacionAutorizacion = Boolean.valueOf(false);
  protected Boolean privilegioOrdenCompra = Boolean.valueOf(false);
  protected Privilegios toRemove = null;
  
  public void init()
  {
    this.organizacion = ((Organizacion)this.sessionUtils.getFromSession("FIRMA"));
    
    this.usuarios = this.usuarioService.getUsuariosByOrganizacion(this.organizacion);
    
    this.usuarioSeleccionado = new Usuarios();
    Persona persona = new Persona();
    Contacto contacto = new Contacto();
    contacto.setEmail(new Email());
    persona.setContacto(contacto);
    this.usuarioSeleccionado.setPersona(persona);
    this.usuarioSeleccionado.setOrganizacion(this.organizacion);
    if (this.usuarios == null) {
      this.usuarios = new ArrayList();
    } else {
      for (Usuarios usuario : this.usuarios)
      {
        if (usuario.getPersona().getContacto() != null)
        {
          usuario.getPersona().setContacto(this.contactoService.getContactoByEmail(usuario.getPersona().getContacto().getEmail()));
        }
        else
        {
          Email email = new Email();
          contacto = new Contacto();
          contacto.setEmail(email);
          usuario.getPersona().setContacto(contacto);
        }
        usuario.setRetypeKennwort(usuario.getKennwort());
        usuario.setPrivilegios(this.privilegioService.getPrivilegiosByUsuario(usuario));
      }
    }
  }
  
  public List<Usuarios> getUsuarios()
  {
    return this.usuarios;
  }
  
  public void setUsuarios(List<Usuarios> usuarios)
  {
    this.usuarios = usuarios;
  }
  
  public Usuarios getUsuarioSeleccionado()
  {
    return this.usuarioSeleccionado;
  }
  
  public void setUsuarioSeleccionado(Usuarios usuarioSeleccionado)
  {
    this.usuarioSeleccionado = usuarioSeleccionado;
  }
  
  public Boolean getPrivilegioRequision()
  {
    return this.privilegioRequision;
  }
  
  public void setPrivilegioRequision(Boolean privilegioRequision)
  {
    this.privilegioRequision = privilegioRequision;
  }
  
  public Boolean getPrivilegioConcentrado()
  {
    return this.privilegioConcentrado;
  }
  
  public void setPrivilegioConcentrado(Boolean privilegioConcentrado)
  {
    this.privilegioConcentrado = privilegioConcentrado;
  }
  
  public Boolean getPrivilegioCotizacionAutorizacion()
  {
    return this.privilegioCotizacionAutorizacion;
  }
  
  public void setPrivilegioCotizacionAutorizacion(Boolean privilegioCotizacionAutorizacion)
  {
    this.privilegioCotizacionAutorizacion = privilegioCotizacionAutorizacion;
  }
  
  public Boolean getPrivilegioOrdenCompra()
  {
    return this.privilegioOrdenCompra;
  }
  
  public void setPrivilegioOrdenCompra(Boolean privilegioOrdenCompra)
  {
    this.privilegioOrdenCompra = privilegioOrdenCompra;
  }
}
