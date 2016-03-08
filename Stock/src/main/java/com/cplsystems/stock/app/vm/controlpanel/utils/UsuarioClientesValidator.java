package com.cplsystems.stock.app.vm.controlpanel.utils;

import com.cplsystems.stock.app.utils.StockUtils;
import com.cplsystems.stock.app.vm.controlpanel.UsuariosClientesVM;
import com.cplsystems.stock.domain.Contacto;
import com.cplsystems.stock.domain.Email;
import com.cplsystems.stock.domain.Persona;
import com.cplsystems.stock.domain.Usuarios;
import com.cplsystems.stock.services.UsuarioService;
import java.util.Map;
import org.zkoss.bind.Property;
import org.zkoss.bind.ValidationContext;
import org.zkoss.bind.validator.AbstractValidator;

public class UsuarioClientesValidator
  extends AbstractValidator
{
  private Map<String, Property> beanProps;
  private boolean usuarioExistente;
  
  public void validate(ValidationContext ctx)
  {
    this.beanProps = ctx.getProperties(ctx.getProperty().getBase());
    UsuariosClientesVM usuariosClientesVM = (UsuariosClientesVM)((Property)this.beanProps.get(".")).getBase();
    if (usuariosClientesVM != null)
    {
      if ((usuariosClientesVM.getUsuarioSeleccionado().getBenutzer() == null) || (usuariosClientesVM.getUsuarioSeleccionado().getBenutzer().isEmpty()))
      {
        addInvalidMessage(ctx, "");
        StockUtils.showSuccessmessage("El nombre de usuario es un valor requerido", "warning", Integer.valueOf(3000), null);
        
        return;
      }
      this.usuarioExistente = usuariosClientesVM.getUsuarioService().verificarNombreUsuario(usuariosClientesVM.getUsuarioSeleccionado().getBenutzer(), usuariosClientesVM.getUsuarioSeleccionado().getIdUsuario());
      if (this.usuarioExistente)
      {
        StockUtils.showSuccessmessage("El nombre de usuario que ha especificado ya se encuentra en uso, por favor ingrese uno diferente", "warning", Integer.valueOf(0), null);
        
        addInvalidMessage(ctx, "");
        return;
      }
      if ((usuariosClientesVM.getUsuarioSeleccionado().getPersona().getApellidoPaterno() == null) || (usuariosClientesVM.getUsuarioSeleccionado().getPersona().getApellidoPaterno().isEmpty()) || (usuariosClientesVM.getUsuarioSeleccionado().getPersona().getApellidoMaterno() == null) || (usuariosClientesVM.getUsuarioSeleccionado().getPersona().getApellidoMaterno().isEmpty()) || (usuariosClientesVM.getUsuarioSeleccionado().getKennwort() == null) || (usuariosClientesVM.getUsuarioSeleccionado().getKennwort().isEmpty()) || (usuariosClientesVM.getUsuarioSeleccionado().getRetypeKennwort() == null) || (usuariosClientesVM.getUsuarioSeleccionado().getRetypeKennwort().isEmpty()) || (usuariosClientesVM.getUsuarioSeleccionado().getPersona().getContacto().getEmail().getEmail() == null) || (usuariosClientesVM.getUsuarioSeleccionado().getPersona().getContacto().getEmail().getEmail().isEmpty()))
      {
        StockUtils.showSuccessmessage("Los campos marcados con (*) son obligatorios.Verifique que la informaci�n es  correcta", "warning", Integer.valueOf(3500), null);
        
        addInvalidMessage(ctx, "");
        return;
      }
      if (!usuariosClientesVM.getUsuarioSeleccionado().getKennwort().equals(usuariosClientesVM.getUsuarioSeleccionado().getRetypeKennwort()))
      {
        StockUtils.showSuccessmessage("La clave de acceso que ha definido no coincide con la comprobaci�n. Verifique que ambas claves son correctas", "warning", Integer.valueOf(4500), null);
        
        addInvalidMessage(ctx, "");
        return;
      }
    }
  }
}
