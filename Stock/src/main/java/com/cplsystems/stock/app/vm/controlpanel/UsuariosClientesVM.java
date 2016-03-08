package com.cplsystems.stock.app.vm.controlpanel;

import com.cplsystems.stock.app.utils.StockUtils;
import com.cplsystems.stock.app.utils.UserPrivileges;
import com.cplsystems.stock.app.vm.controlpanel.utils.UsuariosClientesVariables;
import com.cplsystems.stock.domain.Contacto;
import com.cplsystems.stock.domain.Persona;
import com.cplsystems.stock.domain.Privilegios;
import com.cplsystems.stock.domain.Usuarios;
import com.cplsystems.stock.services.ContactoService;
import com.cplsystems.stock.services.EmailService;
import com.cplsystems.stock.services.PersonaService;
import com.cplsystems.stock.services.PrivilegioService;
import com.cplsystems.stock.services.UsuarioService;
import java.util.List;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Messagebox;

@VariableResolver({DelegatingVariableResolver.class})
public class UsuariosClientesVM
  extends UsuariosClientesVariables
{
  private static final long serialVersionUID = -6187792714156559485L;
  
  @Init
  public void init()
  {
    super.init();
  }
  
  @NotifyChange({"*"})
  @Command
  public void nevoUsuarioCliente()
  {
    super.init();
  }
  
  @NotifyChange({"usuarioSeleccionado", "privilegioRequision", "privilegioConcentrado", "privilegioCotizacionAutorizacion", "privilegioOrdenCompra"})
  @Command
  public void prepareUsuarioForEdition(@BindingParam("index") Integer index)
  {
    if (index != null)
    {
      this.usuarioSeleccionado = ((Usuarios)this.usuarios.get(index.intValue()));
      this.privilegioRequision = Boolean.valueOf(false);
      this.privilegioConcentrado = Boolean.valueOf(false);
      this.privilegioCotizacionAutorizacion = Boolean.valueOf(false);
      this.privilegioOrdenCompra = Boolean.valueOf(false);
      for (Privilegios privilegio : this.usuarioSeleccionado.getPrivilegios()) {
        switch (privilegio.getUserPrivileges())
        {
        case REQUISION: 
          this.privilegioRequision = Boolean.valueOf(true);
          break;
        case CONCENTRAR: 
          this.privilegioConcentrado = Boolean.valueOf(true);
          break;
        case COTIZAR_AUTORIZAR: 
          this.privilegioCotizacionAutorizacion = Boolean.valueOf(true);
          break;
        case ORDEN_COMPRA: 
          this.privilegioOrdenCompra = Boolean.valueOf(true);
        }
      }
    }
  }
  
  @NotifyChange({"usuarios", "usuarioSeleccionado", "privilegioRequision", "privilegioConcentrado", "privilegioCotizacionAutorizacion", "privilegioOrdenCompra"})
  @Command
  public void saveChanges()
  {
    this.privilegioRequision = Boolean.valueOf(false);
    this.privilegioConcentrado = Boolean.valueOf(false);
    this.privilegioCotizacionAutorizacion = Boolean.valueOf(false);
    this.privilegioOrdenCompra = Boolean.valueOf(false);
    if (!this.usuarios.contains(this.usuarioSeleccionado)) {
      this.usuarios.add(this.usuarioSeleccionado);
    }
    for (Usuarios usuario : this.usuarios)
    {
      this.emailService.save(usuario.getPersona().getContacto().getEmail());
      this.contactoService.save(usuario.getPersona().getContacto());
      this.personaService.save(usuario.getPersona());
      this.usuarioService.save(usuario);
      for (Privilegios privilegios : usuario.getPrivilegios()) {
        this.privilegioService.save(privilegios);
      }
    }
    super.init();
    StockUtils.showSuccessmessage("La informaci�n se ha guardado correctamente", "info", Integer.valueOf(1000), null);
  }
  
  @Command
  public void delete()
  {
    if ((this.usuarioSeleccionado != null) && 
      (this.usuarioSeleccionado.getIdUsuario() != null)) {
      Messagebox.show("�Est� seguro de remover a " + this.usuarioSeleccionado.getPersona().getNombreCompleto() + "?. Esta acci�n es irreversible ", "Question", 3, "z-msgbox z-msgbox-question", new EventListener()
      {
        public void onEvent(Event event)
          throws Exception
        {
          if (((Integer)event.getData()).intValue() == 1)
          {
            for (Privilegios privilegios : UsuariosClientesVM.this.usuarioSeleccionado.getPrivilegios()) {
              UsuariosClientesVM.this.privilegioService.delete(privilegios);
            }
            UsuariosClientesVM.this.usuarioService.delete(UsuariosClientesVM.this.usuarioSeleccionado);
            UsuariosClientesVM.this.personaService.delete(UsuariosClientesVM.this.usuarioSeleccionado.getPersona());
            
            UsuariosClientesVM.this.contactoService.delete(UsuariosClientesVM.this.usuarioSeleccionado.getPersona().getContacto());
            
            UsuariosClientesVM.this.emailService.delete(UsuariosClientesVM.this.usuarioSeleccionado.getPersona().getContacto().getEmail());
            
            UsuariosClientesVM.this.usuarios.remove(UsuariosClientesVM.this.usuarioSeleccionado);
            UsuariosClientesVM.this.privilegioRequision = Boolean.valueOf(false);
            UsuariosClientesVM.this.privilegioConcentrado = Boolean.valueOf(false);
            UsuariosClientesVM.this.privilegioCotizacionAutorizacion = Boolean.valueOf(false);
            UsuariosClientesVM.this.privilegioOrdenCompra = Boolean.valueOf(false);
            UsuariosClientesVM.this.init();
            BindUtils.postGlobalCommand(null, null, "refreshPrivilegios", null);
          }
        }
      });
    }
  }
  
  @NotifyChange({"usuarioSeleccionado", "addConcentradosPrivilege"})
  @Command
  public void addRequisicionPrivilege()
  {
    if (this.privilegioRequision.booleanValue())
    {
      boolean agregarPrivilegio = true;
      for (Privilegios privilegio : this.usuarioSeleccionado.getPrivilegios()) {
        if (privilegio.getUserPrivileges().equals(UserPrivileges.REQUISION))
        {
          agregarPrivilegio = false;
          break;
        }
      }
      if (agregarPrivilegio)
      {
        Privilegios privilegios = new Privilegios();
        privilegios.setUsuarios(this.usuarioSeleccionado);
        privilegios.setUserPrivileges(UserPrivileges.REQUISION);
        privilegios.setIcono("/images/toolbar/linedpaperpencil32.png");
        privilegios.setPathLocationModule("/modulos/requisicion/requisicion.zul");
        this.usuarioSeleccionado.getPrivilegios().add(privilegios);
      }
    }
    else
    {
      for (Privilegios privilegio : this.usuarioSeleccionado.getPrivilegios()) {
        if (privilegio.getUserPrivileges().equals(UserPrivileges.REQUISION))
        {
          this.toRemove = privilegio;
          break;
        }
      }
      if (this.usuarioSeleccionado.getPrivilegios().contains(this.toRemove)) {
        if (this.toRemove.getIdPrivilegio() != null) {
          Messagebox.show("�Est� seguro de remover este privilegio para " + this.usuarioSeleccionado.getPersona().getNombreCompleto() + "?", "Question", 3, "z-msgbox z-msgbox-question", new EventListener()
          {
            public void onEvent(Event event)
              throws Exception
            {
              if (((Integer)event.getData()).intValue() == 1)
              {
                UsuariosClientesVM.this.privilegioService.delete(UsuariosClientesVM.this.toRemove);
                UsuariosClientesVM.this.usuarioSeleccionado.getPrivilegios().remove(UsuariosClientesVM.this.toRemove);
              }
              else
              {
                UsuariosClientesVM.this.privilegioRequision = Boolean.valueOf(true);
              }
              BindUtils.postGlobalCommand(null, null, "refreshPrivilegios", null);
            }
          });
        } else {
          this.usuarioSeleccionado.getPrivilegios().remove(this.toRemove);
        }
      }
    }
  }
  
  @NotifyChange({"usuarios", "usuarioSeleccionado", "privilegioRequision", "privilegioConcentrado", "privilegioCotizacionAutorizacion", "privilegioOrdenCompra"})
  @GlobalCommand
  public void refreshPrivilegios() {}
  
  @NotifyChange({"usuarioSeleccionado", "addConcentradosPrivilege"})
  @Command
  public void addConcentradosPrivilege()
  {
    if (this.privilegioConcentrado.booleanValue())
    {
      boolean agregarPrivilegio = true;
      for (Privilegios privilegio : this.usuarioSeleccionado.getPrivilegios()) {
        if (privilegio.getUserPrivileges().equals(UserPrivileges.CONCENTRAR))
        {
          agregarPrivilegio = false;
          break;
        }
      }
      if (agregarPrivilegio)
      {
        Privilegios privilegios = new Privilegios();
        privilegios.setUsuarios(this.usuarioSeleccionado);
        privilegios.setUserPrivileges(UserPrivileges.CONCENTRAR);
        privilegios.setIcono("/images/toolbar/linedpaperplus32.png");
        privilegios.setPathLocationModule("/modulos/requisicion/concentrado.zul");
        this.usuarioSeleccionado.getPrivilegios().add(privilegios);
      }
    }
    else
    {
      for (Privilegios privilegio : this.usuarioSeleccionado.getPrivilegios()) {
        if (privilegio.getUserPrivileges().equals(UserPrivileges.CONCENTRAR))
        {
          this.toRemove = privilegio;
          break;
        }
      }
      if (this.usuarioSeleccionado.getPrivilegios().contains(this.toRemove)) {
        if (this.toRemove.getIdPrivilegio() != null) {
          Messagebox.show("�Est� seguro de remover este privilegio para " + this.usuarioSeleccionado.getPersona().getNombreCompleto() + "?", "Question", 3, "z-msgbox z-msgbox-question", new EventListener()
          {
            public void onEvent(Event event)
              throws Exception
            {
              if (((Integer)event.getData()).intValue() == 1)
              {
                UsuariosClientesVM.this.privilegioService.delete(UsuariosClientesVM.this.toRemove);
                UsuariosClientesVM.this.usuarioSeleccionado.getPrivilegios().remove(UsuariosClientesVM.this.toRemove);
              }
              else
              {
                UsuariosClientesVM.this.privilegioConcentrado = Boolean.valueOf(true);
              }
              BindUtils.postGlobalCommand(null, null, "refreshPrivilegios", null);
            }
          });
        } else {
          this.usuarioSeleccionado.getPrivilegios().remove(this.toRemove);
        }
      }
    }
  }
  
  @NotifyChange({"usuarioSeleccionado", "addConcentradosPrivilege"})
  @Command
  public void addCotizaAutorizaPrivilege()
  {
    if (this.privilegioCotizacionAutorizacion.booleanValue())
    {
      boolean agregarPrivilegio = true;
      for (Privilegios privilegio : this.usuarioSeleccionado.getPrivilegios()) {
        if (privilegio.getUserPrivileges().equals(UserPrivileges.COTIZAR_AUTORIZAR))
        {
          agregarPrivilegio = false;
          break;
        }
      }
      if (agregarPrivilegio)
      {
        Privilegios privilegios = new Privilegios();
        privilegios.setUsuarios(this.usuarioSeleccionado);
        privilegios.setUserPrivileges(UserPrivileges.COTIZAR_AUTORIZAR);
        privilegios.setIcono("/images/toolbar/notecheck32.png");
        privilegios.setPathLocationModule("/modulos/requisicion/cotizacion.zul");
        
        this.usuarioSeleccionado.getPrivilegios().add(privilegios);
      }
    }
    else
    {
      for (Privilegios privilegio : this.usuarioSeleccionado.getPrivilegios()) {
        if (privilegio.getUserPrivileges().equals(UserPrivileges.COTIZAR_AUTORIZAR))
        {
          this.toRemove = privilegio;
          break;
        }
      }
      if (this.usuarioSeleccionado.getPrivilegios().contains(this.toRemove)) {
        if (this.toRemove.getIdPrivilegio() != null) {
          Messagebox.show("�Est� seguro de remover este privilegio para " + this.usuarioSeleccionado.getPersona().getNombreCompleto() + "?", "Question", 3, "z-msgbox z-msgbox-question", new EventListener()
          {
            public void onEvent(Event event)
              throws Exception
            {
              if (((Integer)event.getData()).intValue() == 1)
              {
                UsuariosClientesVM.this.privilegioService.delete(UsuariosClientesVM.this.toRemove);
                UsuariosClientesVM.this.usuarioSeleccionado.getPrivilegios().remove(UsuariosClientesVM.this.toRemove);
              }
              else
              {
                UsuariosClientesVM.this.privilegioCotizacionAutorizacion = Boolean.valueOf(true);
              }
              BindUtils.postGlobalCommand(null, null, "refreshPrivilegios", null);
            }
          });
        } else {
          this.usuarioSeleccionado.getPrivilegios().remove(this.toRemove);
        }
      }
    }
  }
  
  @NotifyChange({"usuarioSeleccionado", "addConcentradosPrivilege"})
  @Command
  public void addOrdenCompraPrivilege()
  {
    if (this.privilegioOrdenCompra.booleanValue())
    {
      boolean agregarPrivilegio = true;
      for (Privilegios privilegio : this.usuarioSeleccionado.getPrivilegios()) {
        if (privilegio.getUserPrivileges().equals(UserPrivileges.ORDEN_COMPRA))
        {
          agregarPrivilegio = false;
          break;
        }
      }
      if (agregarPrivilegio)
      {
        Privilegios privilegios = new Privilegios();
        privilegios.setUsuarios(this.usuarioSeleccionado);
        privilegios.setUserPrivileges(UserPrivileges.ORDEN_COMPRA);
        privilegios.setIcono("/images/toolbar/shoppingcart32.png");
        privilegios.setPathLocationModule("/modulos/ordenCompra/ordenCompra.zul");
        this.usuarioSeleccionado.getPrivilegios().add(privilegios);
      }
    }
    else
    {
      for (Privilegios privilegio : this.usuarioSeleccionado.getPrivilegios()) {
        if (privilegio.getUserPrivileges().equals(UserPrivileges.ORDEN_COMPRA))
        {
          this.toRemove = privilegio;
          break;
        }
      }
      if (this.usuarioSeleccionado.getPrivilegios().contains(this.toRemove)) {
        if (this.toRemove.getIdPrivilegio() != null) {
          Messagebox.show("�Est� seguro de remover este privilegio para " + this.usuarioSeleccionado.getPersona().getNombreCompleto() + "?", "Question", 3, "z-msgbox z-msgbox-question", new EventListener()
          {
            public void onEvent(Event event)
              throws Exception
            {
              if (((Integer)event.getData()).intValue() == 1)
              {
                UsuariosClientesVM.this.privilegioService.delete(UsuariosClientesVM.this.toRemove);
                UsuariosClientesVM.this.usuarioSeleccionado.getPrivilegios().remove(UsuariosClientesVM.this.toRemove);
              }
              else
              {
                UsuariosClientesVM.this.privilegioOrdenCompra = Boolean.valueOf(true);
              }
              BindUtils.postGlobalCommand(null, null, "refreshPrivilegios", null);
            }
          });
        } else {
          this.usuarioSeleccionado.getPrivilegios().remove(this.toRemove);
        }
      }
    }
  }
}
