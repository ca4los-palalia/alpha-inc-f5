package com.cplsystems.stock.app.vm.ordencompra;

import com.cplsystems.stock.app.utils.AplicacionExterna;
import com.cplsystems.stock.app.utils.SessionUtils;
import com.cplsystems.stock.app.utils.StockUtils;
import com.cplsystems.stock.app.utils.UserPrivileges;
import com.cplsystems.stock.app.vm.ordencompra.utils.OrdenCompraMetaclass;
import com.cplsystems.stock.domain.Area;
import com.cplsystems.stock.domain.CofiaPartidaGenerica;
import com.cplsystems.stock.domain.Contacto;
import com.cplsystems.stock.domain.Cotizacion;
import com.cplsystems.stock.domain.Email;
import com.cplsystems.stock.domain.EstatusRequisicion;
import com.cplsystems.stock.domain.OrdenCompra;
import com.cplsystems.stock.domain.OrdenCompraInbox;
import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.Persona;
import com.cplsystems.stock.domain.Privilegios;
import com.cplsystems.stock.domain.Producto;
import com.cplsystems.stock.domain.Proveedor;
import com.cplsystems.stock.domain.Requisicion;
import com.cplsystems.stock.domain.RequisicionProducto;
import com.cplsystems.stock.domain.Unidad;
import com.cplsystems.stock.domain.Usuarios;
import com.cplsystems.stock.services.CotizacionService;
import com.cplsystems.stock.services.EstatusRequisicionService;
import com.cplsystems.stock.services.MailService;
import com.cplsystems.stock.services.OrdenCompraInboxService;
import com.cplsystems.stock.services.OrdenCompraService;
import com.cplsystems.stock.services.PrivilegioService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;

@VariableResolver({DelegatingVariableResolver.class})
public class OrdenCompraVM
  extends OrdenCompraMetaclass
{
  private static final long serialVersionUID = 999672890629004080L;
  
  @Init
  public void init()
  {
    super.init();
    this.requisicion = new Requisicion();
  }
  
  @Command
  public void transferirOrdenCompraToFormulario(@BindingParam("index") Integer index)
  {
    if (index != null)
    {
      OrdenCompraInbox toLoad = (OrdenCompraInbox)this.ordenesCompraInbox.get(index.intValue());
      if ((toLoad.getLeido() != null) && (!toLoad.getLeido().booleanValue()))
      {
        toLoad.setLeido(Boolean.valueOf(true));
        this.ordenCompraInboxService.save(toLoad);
        toLoad.setIcono("/images/toolbar/openedEmail.png");
      }
    }
  }
  
  private List<EstatusRequisicion> generarListaEstatusIN()
  {
    List<EstatusRequisicion> lista = null;
    if ((this.checkBuscarNueva) || (this.checkBuscarCancelada) || (this.checkBuscarEnviada) || (this.checkBuscarAceptada))
    {
      lista = new ArrayList();
      if (this.checkBuscarNueva) {
        lista.add(this.estatusRequisicionService.getByClave("OCN"));
      }
      if (this.checkBuscarCancelada) {
        lista.add(this.estatusRequisicionService.getByClave("OCC"));
      }
      if (this.checkBuscarEnviada) {
        lista.add(this.estatusRequisicionService.getByClave("OCT"));
      }
    }
    return lista;
  }
  
  @Command
  @NotifyChange({"*"})
  public void buscarOrdenCompra()
  {
    if ((this.checkBuscarNueva) || (this.checkBuscarCancelada) || (this.checkBuscarEnviada) || (this.checkBuscarAceptada) || ((this.requisicion != null) && (this.requisicion.getBuscarRequisicion() != null) && (!this.requisicion.getBuscarRequisicion().isEmpty())))
    {
      List<EstatusRequisicion> listaEstatus = generarListaEstatusIN();
      this.ordenesCompras = this.ordenCompraService.getOrdenesByEstatusAndFolioOr(this.requisicion.getBuscarRequisicion(), listaEstatus);
      if (this.cotizacionesList == null) {}
    }
    else
    {
      StockUtils.showSuccessmessage("Debe elegir algun criterio para realizar la busqueda de ordenes de compra (nueva, cancelada o aceptada) o (ingresar palabra en el buscador)", "warning", Integer.valueOf(0), null);
    }
  }
  
  @Command
  @NotifyChange({"*"})
  public void mostrarProductosOrdenCompra()
  {
    this.cotizacionSelected = this.ordenCompra.getCotizacion();
    if (this.cotizacionSelected != null)
    {
      this.cotizacionesConProductos = this.cotizacionService.getByProveedorFolioCotizacionNueva(this.cotizacionSelected.getProveedor(), this.cotizacionSelected.getFolioCotizacion(), this.cotizacionSelected.getEstatusRequisicion());
      
      this.numeroProductos = Integer.valueOf(this.cotizacionesConProductos.size());
      this.precioTotal = Float.valueOf(0.0F);
      for (Cotizacion item : this.cotizacionesConProductos)
      {
        item.getRequisicionProducto().setTotalProductoPorUnidad(Float.valueOf(item.getRequisicionProducto().getCantidad().floatValue() * item.getProducto().getPrecio().floatValue()));
        
        this.precioTotal = Float.valueOf(this.precioTotal.floatValue() + item.getRequisicionProducto().getTotalProductoPorUnidad().floatValue());
      }
    }
  }
  
  @Command
  @NotifyChange({"*"})
  public void abrirVentanaCancelacion()
  {
    if (this.ordenCompra != null)
    {
      if (this.ordenCompra.getEstatusRequisicion().getClave().equals("OCN"))
      {
        HashMap<String, Object> map = new HashMap();
        map.put("orden", this.ordenCompra);
        Executions.createComponents("/modulos/ordenCompra/cancelacionOrdenCompra.zul", null, map);
      }
      else
      {
        StockUtils.showSuccessmessage("La cotizacion con folio -" + this.ordenCompra.getCodigo() + "- nu puede ser cancelada bajo este estatus (" + this.ordenCompra.getEstatusRequisicion().getNombre() + ")", "warning", Integer.valueOf(0), null);
      }
    }
    else {
      StockUtils.showSuccessmessage("Es necesario seleccionar primero una orden de compra", "warning", Integer.valueOf(0), null);
    }
  }
  
  @Command
  @NotifyChange({"*"})
  public void aceptarOrdenCompra()
  {
    if (this.ordenCompra != null)
    {
      if (this.ordenCompra.getEstatusRequisicion().getClave().equals("OCN"))
      {
        EstatusRequisicion estado = this.estatusRequisicionService.getByClave("OCT");
        
        this.ordenCompra.setEstatusRequisicion(estado);
        this.ordenCompraService.save(this.ordenCompra);
        
        List<Privilegios> privilegios = getEmailsUsuariosCotizacion();
        for (Privilegios privilegio : privilegios) {
          if (privilegio.getUsuarios().getPersona().getContacto() != null) {
            this.mailService.sendMail(privilegio.getUsuarios().getPersona().getContacto().getEmail().getEmail(), "1nn3rgy@gmail.com", "Orden de compra aceptada", "La cotizacion " + this.ordenCompra.getCodigo() + " ha sido aceptada");
          }
        }
        StockUtils.showSuccessmessage("La orden de compra [" + this.ordenCompra.getCodigo() + "] ha sido Aceptada", "info", Integer.valueOf(0), null);
      }
      else
      {
        StockUtils.showSuccessmessage("La orden de compra [" + this.ordenCompra.getCodigo() + "] no puede ser aceptada bajo este estatus (" + this.ordenCompra.getEstatusRequisicion().getNombre() + ")", "warning", Integer.valueOf(0), null);
      }
    }
    else {
      StockUtils.showSuccessmessage("Es necesario seleccionar primero una orden de compra", "warning", Integer.valueOf(0), null);
    }
  }
  
  @Command
  public void imprimirOrdenCompra()
  {
    if (this.ordenCompra != null)
    {
      this.cotizacionSelected = this.ordenCompra.getCotizacion();
      if ((this.cotizacionSelected != null) && 
        (this.cotizacionesConProductos != null) && (this.cotizacionesConProductos.size() > 0))
      {
        Organizacion org = (Organizacion)this.sessionUtils.getFromSession("FIRMA");
        
        HashMap mapa = new HashMap();
        mapa.put("logotipo", this.stockUtils.getLogotipoDeOrganizacionParaJasper(org.getLogotipo()));
        
        mapa.put("nombreEmpresa", org.getNombre());
        mapa.put("proveedor", this.cotizacionSelected.getProveedor().getNombre());
        
        mapa.put("ur", this.cotizacionSelected.getRequisicion().getArea().getNombre());
        
        mapa.put("comentarios", this.cotizacionSelected.getDetallesExtras());
        
        mapa.put("ordenCompra", this.ordenCompra.getCodigo());
        mapa.put("fechaOC", this.stockUtils.convertirCalendarToString(this.ordenCompra.getFechaOrden()));
        
        mapa.put("claveCotizacion", this.cotizacionSelected.getFolioCotizacion());
        
        Float total = Float.valueOf(0.0F);
        for (Cotizacion item : this.cotizacionesConProductos) {
          total = Float.valueOf(total.floatValue() + item.getRequisicionProducto().getTotalProductoPorUnidad().floatValue());
        }
        mapa.put("total", String.valueOf(total));
        
        mapa.put("entregarEn", "");
        mapa.put("dependencia", "");
        mapa.put("tiempoEntrega", "");
        
        List<HashMap> listaHashsParametros = new ArrayList();
        listaHashsParametros.add(mapa);
        
        List<AplicacionExterna> aplicaciones = new ArrayList();
        AplicacionExterna aplicacion = new AplicacionExterna();
        aplicacion.setNombre("PDFXCview");
        aplicaciones.add(aplicacion);
        StockUtils.showSuccessmessage(generarOrdenCompraJasper(listaHashsParametros, aplicaciones, this.cotizacionesConProductos), "info", Integer.valueOf(0), null);
        
        ((Cotizacion)this.cotizacionesConProductos.get(0)).getProducto().getClave();
        ((Cotizacion)this.cotizacionesConProductos.get(0)).getProducto().getNombre();
        ((Cotizacion)this.cotizacionesConProductos.get(0)).getProducto().getUnidad().getNombre();
        
        ((Cotizacion)this.cotizacionesConProductos.get(0)).getProducto().getPrecio();
        ((Cotizacion)this.cotizacionesConProductos.get(0)).getRequisicionProducto().getCofiaPartidaGenerica().getNombre();
        
        ((Cotizacion)this.cotizacionesConProductos.get(0)).getRequisicionProducto().getCantidad();
        
        ((Cotizacion)this.cotizacionesConProductos.get(0)).getRequisicionProducto().getTotalProductoPorUnidad();
      }
    }
    else
    {
      StockUtils.showSuccessmessage("Es necesario seleccionar primero una orden de compra", "warning", Integer.valueOf(0), null);
    }
  }
  
  private List<Privilegios> getEmailsUsuariosCotizacion()
  {
    List<Privilegios> usuarios = this.privilegioService.getUsuariosByPrivilegio(UserPrivileges.COTIZAR_AUTORIZAR);
    
    return usuarios;
  }
}
