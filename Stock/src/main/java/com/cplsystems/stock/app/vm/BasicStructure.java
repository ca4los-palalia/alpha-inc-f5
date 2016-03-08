package com.cplsystems.stock.app.vm;

import com.cplsystems.stock.app.utils.SistemaOperativo;
import com.cplsystems.stock.domain.ClaveArmonizada;
import com.cplsystems.stock.domain.Direccion;
import com.cplsystems.stock.domain.Estado;
import com.cplsystems.stock.domain.Moneda;
import com.cplsystems.stock.domain.Municipio;
import com.cplsystems.stock.domain.Pais;
import com.cplsystems.stock.domain.ProductoNaturaleza;
import com.cplsystems.stock.domain.Requisicion;
import com.cplsystems.stock.domain.Unidad;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.zkoss.bind.BindContext;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.event.UploadEvent;

public abstract class BasicStructure extends ServiceLayer {
	private static final long serialVersionUID = 3686010678115196973L;

	public void init() {
		this.areas = new ArrayList();
		this.posiciones = new ArrayList();
		this.requisicion = new Requisicion();
		this.libro = new HSSFWorkbook();
		this.cotizacionesList = new ArrayList();
		this.sistemaOperativo = new SistemaOperativo();
	}

	public void newRecord() {
	}

	public void deleteRecord() {
	}

	public void saveChanges() {
	}

	public void performSerch() {
	}

	public String generarUrlString(String phat) {
		URL ruta = getClass().getClassLoader().getResource(phat);
		phat = ruta.getPath();
		return phat;
	}

	public void openPdf(String url) {
		try {
			if (new File(url).exists()) {
				Process p = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + url);

				p.waitFor();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			closePdf(url);
		}
	}

	public void fileCopy(String sourceFile, String destinationFile) {
		try {
			File inFile = new File(sourceFile);
			File outFile = new File(destinationFile);

			FileInputStream in = new FileInputStream(inFile);
			FileOutputStream out = new FileOutputStream(outFile);
			int c;
			while ((c = in.read()) != -1) {
				out.write(c);
			}
			in.close();
			out.close();
		} catch (IOException e) {
			System.err.println("Hubo un error de entrada/salida!!!");
		}
	}

	public boolean closePdf(String nombreAplicacion) {
		boolean eliminado = false;
		String osName = System.getProperty("os.name");
		String cmd = "";
		if (osName.toUpperCase().contains("WIN")) {
			cmd = cmd + "tskill " + nombreAplicacion;
		} else {
			cmd = cmd + "killall " + nombreAplicacion;
		}
		try {
			Process hijo = Runtime.getRuntime().exec(cmd);
			hijo.waitFor();
			if (hijo.exitValue() == 0) {
				eliminado = true;
			}
		} catch (IOException e) {
			System.out.println("Incapaz de matar: Excepcion IOE");
		} catch (InterruptedException e) {
			System.out.println("Incapaz de matar: Excepcion InterruptedException");
		}
		return eliminado;
	}

	public HashMap construirHashMapParametros(List<HashMap> parametros) {
		HashMap parametrosGenerados = new HashMap();
		for (HashMap hashMap : parametros) {
			Iterator iterador = hashMap.keySet().iterator();
			while (iterador.hasNext()) {
				Object parametro = iterador.next();
				parametrosGenerados.put(parametro, hashMap.get(parametro));
			}
		}
		return parametrosGenerados;
	}

	public String obtenerVAlorDeCeldaDeExcel(Cell cell) {
		String valor = "";
		switch (cell.getCellType()) {
		case 0:
			Integer entero = Integer.valueOf((int) cell.getNumericCellValue());
			valor = String.valueOf(entero);
			break;
		case 1:
			valor = cell.getStringCellValue();
			break;
		case 2:
			valor = String.valueOf(cell.getCachedFormulaResultType());
		}
		return valor;
	}

	public String removerPuntoCero(String valor) {
		String salida = "";
		for (int i = 0; i < valor.length(); i++) {
			String caracter = valor.substring(i, i + 1);
			if (caracter.equals(".")) {
				break;
			}
			salida = salida + caracter;
		}
		return salida;
	}
	
	public InputStream getStreamMediaExcel(BindContext contexto){
		InputStream stream = null;
		
		UploadEvent upEvent = null;
		Object objUploadEvent = contexto.getTriggerEvent();
		if ((objUploadEvent != null) && ((objUploadEvent instanceof UploadEvent)))
			upEvent = (UploadEvent) objUploadEvent;
		
		if (upEvent != null) {
			Media media = upEvent.getMedia();
			stream = media.getStreamData();
		}
		return stream;
	}
	
	public ClaveArmonizada getClaveArminizadaFromList(String clave){
		ClaveArmonizada retornar = null;
		if(claveArmonizadaList != null){
			for (ClaveArmonizada item : claveArmonizadaList) {
				if(clave.equals(item.getClave())){
					retornar = item;
					break;
				}
			}
		}
		return retornar;
	}
	
	public Unidad getUnidadFromList(Long idUnidad){
		Unidad retornar = null;
		if(unidadesDB != null){
			for (Unidad item : unidadesDB) {
				if(idUnidad.equals(item.getIdUnidad())){
					retornar = item;
					break;
				}
			}
		}
		
		return retornar;
	}
	
	public ProductoNaturaleza getProductoNaturalezaFromList(Long idProductoNaturaleza){
		ProductoNaturaleza retornar = null;
		if(productosNaturalezas != null){
			for (ProductoNaturaleza item : productosNaturalezas) {
				if(idProductoNaturaleza.equals(item.getIdProductoNaturaleza())){
					retornar = item;
					break;
				}
			}
		}
		
		return retornar;
	}
	
	public Moneda getMonedaFromList(Long idMoneda){
		Moneda retornar = null;
		if(monedasDB != null){
			for (Moneda item : monedasDB) {
				if(idMoneda.equals(item.getIdMoneda())){
					retornar = item;
					break;
				}
			}
		}
		return retornar;
	}
	
	public Estado getEstadoFromList(Long idEstado){
		Estado retornar = null;
		if(estados != null){
			for (Estado item : estados) {
				if(idEstado.equals(item.getIdEstado())){
					retornar = item;
					break;
				}
			}
		}
		return retornar;
	}
	
	public Municipio getMunicipioFromList(Long idMunicipio){
		Municipio retornar = null;
		if(municipios != null){
			for (Municipio item : municipios) {
				if(idMunicipio.equals(item.getIdMunicipio())){
					retornar = item;
					break;
				}
			}
		}
		return retornar;
	}
	
	public Pais getPaisFromList(Long idPais){
		Pais retornar = null;
		if(paises != null){
			for (Pais item : paises) {
				if(idPais.equals(item.getIdPais())){
					retornar = item;
					break;
				}
			}
		}
		return retornar;
	}
	

	public Direccion getDireccionFromList(Long idDireccion){
		Direccion retornar = null;
		if(direccionesList != null){
			for (Direccion item : direccionesList) {
				if(idDireccion.equals(item.getIdDireccion())){
					retornar = item;
					break;
				}
			}
		}
		return retornar;
	}
	
	
	
	
	
	public void generarArbolDirectorios() {
	}
}
