package com.cplsystems.stock.app.vm.ordencompra.utils;

import com.cplsystems.stock.app.utils.AplicacionExterna;
import com.cplsystems.stock.app.utils.SessionUtils;
import com.cplsystems.stock.app.utils.StockConstants;
import com.cplsystems.stock.app.utils.StockUtils;
import com.cplsystems.stock.domain.Cotizacion;
import com.cplsystems.stock.domain.OrdenCompraInbox;
import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.services.OrdenCompraInboxService;
import java.util.HashMap;
import java.util.List;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;

public class OrdenCompraMetaclass
  extends OrdenCompraVariables
{
  private static final long serialVersionUID = 5093877120990395398L;
  
  @Init
  public void init()
  {
    initObjects();
    initProperties();
  }
  
  public void initObjects()
  {
    loadOrdenesCompraInbox();
  }
  
  public void initProperties()
  {
    this.readJasper = generarUrlString("jasperTemplates/ordenCompraFormato.jasper");
  }
  
  private void loadOrdenesCompraInbox()
  {
    this.ordenesCompraInbox = this.ordenCompraInboxService.getAll((Organizacion)this.sessionUtils.getFromSession("FIRMA"));
    
    this.ordenCompraInboxSeleccionada = new OrdenCompraInbox();
    for (OrdenCompraInbox compraInbox : this.ordenesCompraInbox) {
      if ((compraInbox.getLeido() != null) && (!compraInbox.getLeido().booleanValue())) {
        compraInbox.setIcono("/images/toolbar/newEmail.png");
      }
    }
  }
  
  @Command
  public void checkNueva()
  {
    if (!this.checkBuscarNueva) {
      this.checkBuscarNueva = true;
    } else {
      this.checkBuscarNueva = false;
    }
  }
  
  @Command
  public void checkCancelada()
  {
    if (!this.checkBuscarCancelada) {
      this.checkBuscarCancelada = true;
    } else {
      this.checkBuscarCancelada = false;
    }
  }
  
  @Command
  public void checkAceptada()
  {
    if (!this.checkBuscarAceptada) {
      this.checkBuscarAceptada = true;
    } else {
      this.checkBuscarAceptada = false;
    }
  }
  
  @Command
  public String generarOrdenCompraJasper(List<HashMap> listaHashsParametros, List<AplicacionExterna> aplicaciones, List<Cotizacion> lista)
  {
    String mensaje = "";
    HashMap hashParametros = construirHashMapParametros(listaHashsParametros);
    try
    {
      this.print = JasperFillManager.fillReport(this.readJasper, hashParametros, new JRBeanCollectionDataSource(lista));
      
      JasperExportManager.exportReportToPdfFile(this.print, StockConstants.CARPETA_ARCHIVOS_ORDEN_COMPRA + hashParametros.get("ordenCompra") + StockUtils.getFechaActualConHora() + ".pdf");
      
      openPdf(StockConstants.CARPETA_ARCHIVOS_ORDEN_COMPRA);
      mensaje = "Orden de Compra Exportada a PDF " + StockConstants.CARPETA_ARCHIVOS_ORDEN_COMPRA;
    }
    catch (JRException e)
    {
      e.printStackTrace();
      for (AplicacionExterna aplicacion : aplicaciones) {
        closePdf(aplicacion.getNombre());
      }
      try
      {
        JasperExportManager.exportReportToPdfFile(this.print, StockConstants.CARPETA_ARCHIVOS_ORDEN_COMPRA);
        
        openPdf(StockConstants.CARPETA_ARCHIVOS_ORDEN_COMPRA);
        mensaje = "Se ha generado un PDF: " + StockConstants.CARPETA_ARCHIVOS_ORDEN_COMPRA;
      }
      catch (JRException e1)
      {
        e1.printStackTrace();
      }
    }
    return mensaje;
  }
}
