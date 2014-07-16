/**
 * 
 */
package com.cplsystems.stock.app.vm;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.cplsystems.stock.domain.Area;
import com.cplsystems.stock.domain.Cotizacion;
import com.cplsystems.stock.domain.Posicion;
import com.cplsystems.stock.domain.Requisicion;


/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
public abstract class BasicStructure extends ServiceLayer {

	private static final long serialVersionUID = 3686010678115196973L;

	public void init() {
		areas = new ArrayList<Area>();
		posiciones = new ArrayList<Posicion>();
		requisicion = new Requisicion();
		libro = new HSSFWorkbook();
		cotizacionesList = new ArrayList<Cotizacion>();
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
			if ((new File(url)).exists()) {

				Process p = Runtime.getRuntime().exec(
						"rundll32 url.dll,FileProtocolHandler " + url);
				p.waitFor();

			} else {
			}
		} catch (Exception ex) {
			closePdf(url);
		}
	}

	public boolean closePdf(String nombreAplicacion) {
		boolean eliminado = false;
		String osName = System.getProperty("os.name");
		String cmd = "";

		if (osName.toUpperCase().contains("WIN")) {// S.O. Windows
			cmd += "tskill " + nombreAplicacion;
		} else {// Solo ha sido probado en win y linux
			cmd += "killall " + nombreAplicacion;
		}
		Process hijo;
		try {
			hijo = Runtime.getRuntime().exec(cmd);
			hijo.waitFor();
			if (hijo.exitValue() == 0) {
				eliminado = true;
			}
		} catch (IOException e) {
			System.out.println("Incapaz de matar: Excepcion IOE");
		} catch (InterruptedException e) {
			System.out
					.println("Incapaz de matar: Excepcion InterruptedException");
		}
		return eliminado;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
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
	
	
	public void generarArbolDirectorios(){
		
	}
}
