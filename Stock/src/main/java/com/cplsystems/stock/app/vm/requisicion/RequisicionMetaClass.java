package com.cplsystems.stock.app.vm.requisicion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zul.SimpleListModel;

import com.cplsystems.stock.app.utils.StockConstants;
import com.cplsystems.stock.app.utils.StockUtils;
import com.cplsystems.stock.app.vm.requisicion.utils.RequisicionVariables;
import com.cplsystems.stock.domain.AplicacionExterna;
import com.cplsystems.stock.domain.Area;
import com.cplsystems.stock.domain.CofiaFuenteFinanciamiento;
import com.cplsystems.stock.domain.CofiaPartidaGenerica;
import com.cplsystems.stock.domain.CofiaProg;
import com.cplsystems.stock.domain.CofiaPy;
import com.cplsystems.stock.domain.EstatusRequisicion;
import com.cplsystems.stock.domain.Persona;
import com.cplsystems.stock.domain.Posicion;
import com.cplsystems.stock.domain.Requisicion;
import com.cplsystems.stock.domain.RequisicionProducto;
import com.cplsystems.stock.domain.Usuarios;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public abstract class RequisicionMetaClass extends RequisicionVariables {
	private static final long serialVersionUID = -4078164796340868446L;

	@Init
	public void init() {
		this.readJasper = generarUrlString("jasperTemplates/requisicionFormato.jasper");
	}

	@Command
	@NotifyChange({ "*" })
	public void checkNueva() {
		checkBuscarNueva = true;
		List<Requisicion> tempList = buscadorRequisicion();
		if(tempList == null || tempList.size() == 0)
			StockUtils.showSuccessmessage("Ningun resultado encontrado bajo el criterio NUEVAS", "warning", Integer.valueOf(0), null);
		checkBuscarNueva = false;
	}

	@Command
	@NotifyChange({ "*" })
	public void checkCancelada() {
		checkBuscarCancelada = true;
		try {
			List<Requisicion> tempList = buscadorRequisicion();
			if(tempList == null || tempList.size() == 0)
				StockUtils.showSuccessmessage("Ningun resultado encontrado bajo el criterio CANCELADAS", "warning", Integer.valueOf(0), null);
			checkBuscarCancelada = false;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Command
	@NotifyChange({ "*" })
	public void checkAceptada() {
		this.checkBuscarAceptada = true;
		List<Requisicion> tempList = buscadorRequisicion();
		if(tempList == null || tempList.size() == 0)
			StockUtils.showSuccessmessage("Ningun resultado encontrado bajo el criterio ACEPTADAS", "warning", Integer.valueOf(0), null);
		checkBuscarAceptada = false;
	}
	
	@Command
	@NotifyChange({ "*" })
	public void buscarPorArea() {
		List<Requisicion> tempList = buscadorRequisicion();
		if(tempList == null || tempList.size() == 0)
			StockUtils.showSuccessmessage("Ningun resultado encontrado bajo el criterio de " + areaBuscar.getNombre(), "warning", Integer.valueOf(0), null);
		areaBuscar = null;
	}
	
	@Command
	@NotifyChange({ "*" })
	public void buscarPorCadenaTexto() {
		List<Requisicion> tempList = buscadorRequisicion();
		if(tempList == null || tempList.size() == 0){
			String cadenaBuscada = "VACIO";
			if(requisicion.getBuscarRequisicion() != null)
				cadenaBuscada = requisicion.getBuscarRequisicion();
			StockUtils.showSuccessmessage("Ningun resultado encontrado bajo el criterio ''" + cadenaBuscada + "''", "warning", Integer.valueOf(0), null);
		}
			
	}
	
	//@Command
	//@NotifyChange({ "*" })
	public List<Requisicion> buscadorRequisicion() {
		
		if ((this.checkBuscarNueva) || (this.checkBuscarCancelada) || (this.checkBuscarEnviada)
				|| (this.checkBuscarAceptada)
				|| ((this.requisicion != null) && (((this.requisicion.getBuscarRequisicion() != null)
						&& (!this.requisicion.getBuscarRequisicion().isEmpty()))
						|| ((this.areaBuscar != null) && (this.areaBuscar.getNombre() != null)
								&& (!this.areaBuscar.getNombre().isEmpty()))))) {
			List<EstatusRequisicion> listaEstatus = generarListaEstatusIN();
			this.requisiciones = this.requisicionService.getRequisicionesConListaDeEstatusFolioArea(listaEstatus,
					this.requisicion.getBuscarRequisicion(), this.areaBuscar);
			if (this.requisiciones == null) {
				limpiarFormulario();
			}
		} else {
			/*
			StockUtils.showSuccessmessage(
					"Debe elegir algun criterio para realizar la busqueda de requisiciones (nueva, cancelada o aceptada) o (ingresar palabra en el buscador)",
					"warning", Integer.valueOf(0), null);
			*/
		}
		return requisiciones;
	}
	
	@Command
	@NotifyChange({ "*" })
	public void limpiarFormulario() {
		this.requisicionProductos = new ArrayList();
		this.requisicion = new Requisicion();
		loadItemsKeys();
		initDefaultValues();
		this.readOnly = false;
		this.requisiciones = new ArrayList();
		this.requisicion.setFolio("FRQ" + this.requisicionService.getUltimoFolio());
	}
	
	public void loadItemsKeys() {
		this.productosTemporalModel = this.productoService.getAllKeys();
		if (this.productosTemporalModel != null) {
			this.productosModel = new SimpleListModel(this.productosTemporalModel);
		}
	}
	
	@NotifyChange({ "*" })
	public void initDefaultValues() {
		this.cofiaPartidaGenericas = this.cofiaPartidaGenericaService.getAll();
		this.cofiaProgs = this.cofiaProgService.getAll();
		this.cofiaPys = this.cofiaPyService.getAll();
		this.cofiaFuenteFinanciamientos = this.cofiaFuenteFinanciamientoService.getAll();
		this.areas = this.areaService.getAll();

		Usuarios usuarioSesion = (Usuarios) this.sessionUtils.getFromSession("usuario");
		Persona personaCaptura = usuarioSesion.getPersona();
		this.requisicion.setPersona(personaCaptura);
		if ((this.areas != null) && (this.areas.size() > 0)) {
			this.requisicion.setArea((Area) this.areas.get(0));
		}
		if ((this.cofiaProgs != null) && (this.cofiaProgs.size() > 0)) {
			this.requisicion.setCofiaProg((CofiaProg) this.cofiaProgs.get(0));
		}
		if ((this.cofiaPys != null) && (this.cofiaPys.size() > 0)) {
			this.requisicion.setCofiaPy((CofiaPy) this.cofiaPys.get(0));
		}
		if ((this.cofiaFuenteFinanciamientos != null) && (this.cofiaFuenteFinanciamientos.size() > 0)) {
			this.requisicion
					.setCofiaFuenteFinanciamiento((CofiaFuenteFinanciamiento) this.cofiaFuenteFinanciamientos.get(0));
		}
		if ((this.posiciones != null) && (this.posiciones.size() > 0)) {
			this.requisicion.setPosicion((Posicion) this.posiciones.get(0));
		}
		addNewItemToBill();
		this.requisicion.setFolio("FRQ" + this.requisicionService.getUltimoFolio());
	}
	
	@NotifyChange({ "requisicionProductos", "itemsOnList" })
	@Command
	public void addNewItemToBill() {
		RequisicionProducto objeto = new RequisicionProducto();
		objeto.setCofiaPartidaGenerica(new CofiaPartidaGenerica());
		if (this.requisicionProductos == null) {
			this.requisicionProductos = new ArrayList();
		}
		this.requisicionProductos.add(objeto);
		this.itemsOnList = Integer.valueOf(this.requisicionProductos.size());
	}

	public List<EstatusRequisicion> generarListaEstatusIN() {
		List<EstatusRequisicion> lista = null;
		if ((this.checkBuscarNueva) || (this.checkBuscarCancelada) || (this.checkBuscarEnviada)
				|| (this.checkBuscarAceptada)) {
			lista = new ArrayList();
			if (this.checkBuscarNueva) {
				lista.add(this.estatusRequisicionService.getByClave("RQN"));
			}
			if (this.checkBuscarCancelada) {
				lista.add(this.estatusRequisicionService.getByClave("RQC"));
			}
			if (this.checkBuscarAceptada) {
				lista.add(this.estatusRequisicionService.getByClave("RQT"));
			}
		}
		return lista;
	}

	@Command
	public String generarRequisicionJasper(List<HashMap> listaHashsParametros, List<AplicacionExterna> aplicaciones,
			List<RequisicionProducto> lista) {
		String mensaje = "";
		HashMap hashParametros = construirHashMapParametros(listaHashsParametros);
		try {
			this.print = JasperFillManager.fillReport(this.readJasper, hashParametros,
					new JRBeanCollectionDataSource(lista));

			JasperExportManager.exportReportToPdfFile(this.print, StockConstants.CARPETA_ARCHIVOS_REQUISICIONES
					+ hashParametros.get("folio") + StockUtils.getFechaActualConHora() + ".pdf");

			openPdf(StockConstants.CARPETA_ARCHIVOS_REQUISICIONES);
			mensaje = "REQUISICI�N GENERADA EN PDF" + StockConstants.CARPETA_ARCHIVOS_REQUISICIONES;
		} catch (JRException e) {
			e.printStackTrace();
			for (AplicacionExterna aplicacion : aplicaciones) {
				closePdf(aplicacion.getNombre());
			}
			try {
				JasperExportManager.exportReportToPdfFile(this.print, StockConstants.CARPETA_ARCHIVOS_REQUISICIONES);

				openPdf(StockConstants.CARPETA_ARCHIVOS_REQUISICIONES);
				mensaje = "Se ha generado un PDF: " + StockConstants.CARPETA_ARCHIVOS_REQUISICIONES;
			} catch (JRException e1) {
				e1.printStackTrace();
			}
		}
		return mensaje;
	}
}
