package com.cplsystems.stock.app;

import com.cplsystems.stock.app.utils.StockUtils;
import com.cplsystems.stock.app.vm.BasicStructure;
import com.cplsystems.stock.domain.ClaveArmonizada;
import com.cplsystems.stock.domain.CofiaFuenteFinanciamiento;
import com.cplsystems.stock.domain.CofiaPartidaGenerica;
import com.cplsystems.stock.domain.CofiaProg;
import com.cplsystems.stock.domain.CofiaPy;
import com.cplsystems.stock.domain.Estado;
import com.cplsystems.stock.domain.EstatusRequisicion;
import com.cplsystems.stock.domain.Municipio;
import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.Pais;
import com.cplsystems.stock.domain.Persona;
import com.cplsystems.stock.domain.Usuarios;
import com.cplsystems.stock.services.ClaveArmonizadaService;
import com.cplsystems.stock.services.CofiaFuenteFinanciamientoService;
import com.cplsystems.stock.services.CofiaPartidaGenericaService;
import com.cplsystems.stock.services.CofiaProgService;
import com.cplsystems.stock.services.CofiaPyService;
import com.cplsystems.stock.services.EstadoService;
import com.cplsystems.stock.services.EstatusRequisicionService;
import com.cplsystems.stock.services.MunicipioService;
import com.cplsystems.stock.services.OrganizacionService;
import com.cplsystems.stock.services.PaisService;
import com.cplsystems.stock.services.PersonaService;
import com.cplsystems.stock.services.UsuarioService;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import javax.annotation.PostConstruct;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DatosIniciales extends BasicStructure {
	
	private static final long serialVersionUID = -2306337289920279395L;
	@Autowired
	private PersonaService personaService;
	@Autowired
	private OrganizacionService organizacionService;
	@Autowired
	private UsuarioService usuarioService;
	@Autowired
	private CofiaFuenteFinanciamientoService cofiaFuenteFinanciamientoService;
	@Autowired
	private CofiaPartidaGenericaService cofiaPartidaGenericaService;
	@Autowired
	private CofiaProgService cofiaProgService;
	@Autowired
	private CofiaPyService cofiaPyService;
	@Autowired
	private EstadoService estadoService;
	@Autowired
	private EstatusRequisicionService estatusRequisicionService;
	@Autowired
	private PaisService paisService;
	@Autowired
	private MunicipioService municipioService;
	@Autowired
	private ClaveArmonizadaService claveArmonizadaService;
	
	FileInputStream fileInputStream;
	XSSFWorkbook workBook;

	@PostConstruct
	public void init() {
		crearUsuarioInicial();
		crearCatalogosPorDefecto();
		catalogoClaveArmonizada();
	}

	private void crearUsuarioInicial() {
		Persona persona = null;
		Organizacion organizacion = null;
		Usuarios usuario = null;

		List<Persona> personas = this.personaService.getAll();
		if (personas == null) {
			persona = new Persona();
			persona.setApellidoPaterno("Paterno admin");
			persona.setApellidoMaterno("Materno admin");
			persona.setNombre("Nombre admin");
			this.personaService.save(persona);
		}
		List<Organizacion> org = this.organizacionService.getAll();
		if ((org == null) || (org.size() == 0)) {
			organizacion = new Organizacion();
			organizacion.setNombre("Nombre Organizacion");
			organizacion.setRfc("CAME880957");
			this.organizacionService.save(organizacion);
		}
		List<Usuarios> usuarios = this.usuarioService.getAll();
		if (usuarios == null) {
			usuario = new Usuarios();
			usuario.setBenutzer("came");
			usuario.setKennwort("came");
			usuario.setPersona(persona);
			usuario.setOrganizacion(organizacion);
			usuario.setOwner(Boolean.valueOf(true));
			usuario.setClient(Boolean.valueOf(false));
			this.usuarioService.save(usuario);
		}
	}

	
	
	
	@SuppressWarnings("rawtypes")
	public void leerDatosDesdeExcel(int indiceSheet) {
		XSSFSheet hssfSheet = workBook.getSheetAt(indiceSheet);
		Iterator rowIterator = hssfSheet.rowIterator();
		
		switch (indiceSheet) {
		case 0:
			extraerPaisesDeExcel(rowIterator);
			break;
		case 1:
			extraerEstadosDeExcel(rowIterator);
			break;
		case 2:
			extraerEsMunicipiosDeExcel(rowIterator);
			break;
		}
	}
	
	@SuppressWarnings("rawtypes")
	private void extraerPaisesDeExcel(Iterator rowIterator){
		List<Pais> paisesTemp = new ArrayList<>();
		Integer i = 0;
		int j;
		XSSFCell hssfCell;
		while (rowIterator.hasNext()) {
			Pais nuevoPais= new Pais();
			XSSFRow hssfRow = (XSSFRow) rowIterator.next();
			Iterator iterator = hssfRow.cellIterator();
			
			if (i > 0) {
				j = 0;
				while ((iterator.hasNext()) && (j < 2)) {
					hssfCell = (XSSFCell) iterator.next();
					nuevoPais = crearPais(nuevoPais, hssfCell, j);
					j++;
				}
				paisesTemp.add(nuevoPais);
			}
			i++;
		}
		if(paisesTemp.size() > 0){
			for (Pais item : paisesTemp) {
				paisService.save(item);
			}
		}
	}
	
	@SuppressWarnings("rawtypes")
	private void extraerEstadosDeExcel(Iterator rowIterator){
		List<Estado> estadosTemp = new ArrayList<>();
		Integer i = 0;
		int j;
		XSSFCell hssfCell;
		while (rowIterator.hasNext()) {
			Estado nuevoEstado= new Estado();
			XSSFRow hssfRow = (XSSFRow) rowIterator.next();
			Iterator iterator = hssfRow.cellIterator();
			
			if (i > 0) {
				j = 0;
				while ((iterator.hasNext()) && (j < 4)) {
					hssfCell = (XSSFCell) iterator.next();
					nuevoEstado = crearEstado(nuevoEstado, hssfCell, j);
					j++;
				}
				estadosTemp.add(nuevoEstado);
			}
			i++;
		}
		if(estadosTemp.size() > 0){
			for (Estado item : estadosTemp) {
				estadoService.save(item);
			}
			estados = estadosTemp;
		}
	}
	
	@SuppressWarnings("rawtypes")
	private void extraerEsMunicipiosDeExcel(Iterator rowIterator){
		List<Municipio> municipiosTemp = new ArrayList<>();
		Integer i = 0;
		int j;
		XSSFCell hssfCell;
		while (rowIterator.hasNext()) {
			Municipio nuevoMunicipio= new Municipio();
			XSSFRow hssfRow = (XSSFRow) rowIterator.next();
			Iterator iterator = hssfRow.cellIterator();
			
			if (i > 0) {
				j = 0;
				while ((iterator.hasNext()) && (j < 3)) {
					hssfCell = (XSSFCell) iterator.next();
					nuevoMunicipio = crearMunicipio(nuevoMunicipio, hssfCell, j);
					j++;
				}
				municipiosTemp.add(nuevoMunicipio);
			}
			i++;
		}
		if(municipiosTemp.size() > 0){
			for (Municipio item : municipiosTemp) {
				municipioService.save(item);
			}
		}
	}
	
	
	private Pais crearPais(Pais pais, XSSFCell valorDePropiedad, int indice) {
		String valor = String.valueOf(valorDePropiedad);
		switch (indice) {
		case 0:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) 
				pais.setNombre(valor);
			break;
		case 1:
			if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) 
				pais.setAbreviatura(valor);
			break;
		}
		return pais;
	}
	
	private Estado crearEstado(Estado estado, XSSFCell valorDePropiedad, int indice) {
		String valor = String.valueOf(valorDePropiedad);
		switch (indice) {
			case 0:
				if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) 
					estado.setNombre(valor);
				break;
			case 1:
				if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) 
					estado.setCapital(valor);
				break;
			case 2:
				if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) 
					estado.setAbreviatura(valor);
				break;
			case 3:
				if ((valor != null) && (!valor.isEmpty()) && (!valor.equalsIgnoreCase("NULL"))) 
					estado.setSimbolo(valor);
				break;
		}
		return estado;
	}

	private Municipio crearMunicipio(Municipio municipio, XSSFCell valorDePropiedad, int indice) {
		String valor = String.valueOf(valorDePropiedad);
		switch (indice) {
		case 0:
			municipio.setNombre(valor);
			
			break;
		case 1:
			if (valor.contains(".0")) {
				valor = removerPuntoCero(valor);
			}
			municipio.setEstado(getEstadoFromList(Long.valueOf(Long.parseLong(valor))));
			break;
		case 2:
			if (valor.contains(".0")) {
				valor = removerPuntoCero(valor);
			}
			municipio.setNumeroMunicipio(String.valueOf(valor));
		}
		return municipio;
	}

	

	private void crearCatalogosPorDefecto() {
		if (cofiaFuenteFinanciamientoService.getAll() == null) {
			System.err.println("Creando catalogos");
			CofiaFuenteFinanciamiento cofiaFuenteFinanciamiento = null;
			for (int i = 0; i < 10; i++) {
				cofiaFuenteFinanciamiento = new CofiaFuenteFinanciamiento();
				cofiaFuenteFinanciamiento.setNombre("Financiamiento " + (i + 1));

				new StockUtils();
				cofiaFuenteFinanciamiento.setFechaActualizacion(StockUtils.getFechaActualConHora());

				cofiaFuenteFinanciamiento.setUltimaActualizacion(Calendar.getInstance());

				cofiaFuenteFinanciamientoService.save(cofiaFuenteFinanciamiento);
			}
		}
		if (cofiaPartidaGenericaService.getAll() == null) {
			CofiaPartidaGenerica cofiaPartidaGenerica = null;
			for (int i = 0; i < 10; i++) {
				cofiaPartidaGenerica = new CofiaPartidaGenerica();
				cofiaPartidaGenerica.setNombre("Partida generica " + (i + 1));
				new StockUtils();
				cofiaPartidaGenerica.setFechaActualizacion(StockUtils.getFechaActualConHora());

				cofiaPartidaGenerica.setUltimaActualizacion(Calendar.getInstance());

				cofiaPartidaGenericaService.save(cofiaPartidaGenerica);
			}
		}
		if (cofiaProgService.getAll() == null) {
			CofiaProg cofiaProg = null;
			for (int i = 0; i < 10; i++) {
				cofiaProg = new CofiaProg();
				cofiaProg.setNombre("Programa " + (i + 1));
				new StockUtils();
				cofiaProg.setFechaActualizacion(StockUtils.getFechaActualConHora());

				cofiaProg.setUltimaActualizacion(Calendar.getInstance());
				cofiaProgService.save(cofiaProg);
			}
		}
		if (cofiaPyService.getAll() == null) {
			CofiaPy cofiaPy = null;
			for (int i = 0; i < 10; i++) {
				cofiaPy = new CofiaPy();
				cofiaPy.setNombre("Pay " + (i + 1));
				new StockUtils();
				cofiaPy.setFechaActualizacion(StockUtils.getFechaActualConHora());

				cofiaPy.setUltimaActualizacion(Calendar.getInstance());
				cofiaPyService.save(cofiaPy);
			}
		}
		
		
		
		try {
			fileInputStream = new FileInputStream(generarUrlString("layout/Script.xlsx"));
			workBook = new XSSFWorkbook(fileInputStream);
			
			if (this.paisService.getAll() == null) {
				leerDatosDesdeExcel(0);
				System.err.print("... Precarga de paises Terminada!\n");
			}
			if (this.estadoService.getAll() == null) {
				leerDatosDesdeExcel(1);
				System.err.print("... Precarga de Estados Terminada!\n");
			}
			if (municipioService.getAll() == null) {
				leerDatosDesdeExcel(2);
				System.err.print("... Precarga de Municipios Terminada!\n");
			}
			fileInputStream.close();
			
		} catch (Exception e) {
			System.err.println("Error en la precarga inicial");
			e.printStackTrace();
		}
		
		if (this.estatusRequisicionService.getAll() == null) {
			EstatusRequisicion estatusRequisicion1 = new EstatusRequisicion();
			estatusRequisicion1.setClave("RQN");
			estatusRequisicion1.setNombre("nueva");
			estatusRequisicion1.setColor(
					"padding:5px;background:#007DC1;background:-moz-linear-gradient(top, #007DC1 0%, #0061A7 100%);background:-webkit-gradient(linear, left top, left bottom, color-stop(0%,#007DC1), color-stop(100%,#0061A7));background:-webkit-linear-gradient(top, #007DC1 0%,#0061A7 100%);background:-o-linear-gradient(top, #007DC1 0%,#0061A7 100%);background:-ms-linear-gradient(top, #007DC1 0%,#0061A7 100%);background:linear-gradient(to bottom, #007DC1 0%,#0061A7 100%);filter:progid:DXImageTransform.Microsoft.gradient( startColorstr='#007DC1',  endColorstr='#0061A7', GradientType=0 );border-width:2px;border-style:solid;border-color:#124D77;border-radius:100px;box-shadow:0px 1px 0px 0px #54A3F7;text-shadow:0px 1px 0px #154682;");

			EstatusRequisicion estatusRequisicion2 = new EstatusRequisicion();
			estatusRequisicion2.setClave("CON");
			estatusRequisicion2.setNombre("nueva");
			estatusRequisicion2.setColor(
					"padding:5px;background:#007DC1;background:-moz-linear-gradient(top, #007DC1 0%, #0061A7 100%);background:-webkit-gradient(linear, left top, left bottom, color-stop(0%,#007DC1), color-stop(100%,#0061A7));background:-webkit-linear-gradient(top, #007DC1 0%,#0061A7 100%);background:-o-linear-gradient(top, #007DC1 0%,#0061A7 100%);background:-ms-linear-gradient(top, #007DC1 0%,#0061A7 100%);background:linear-gradient(to bottom, #007DC1 0%,#0061A7 100%);filter:progid:DXImageTransform.Microsoft.gradient( startColorstr='#007DC1',  endColorstr='#0061A7', GradientType=0 );border-width:2px;border-style:solid;border-color:#124D77;border-radius:100px;box-shadow:0px 1px 0px 0px #54A3F7;text-shadow:0px 1px 0px #154682;");

			EstatusRequisicion estatusRequisicion3 = new EstatusRequisicion();
			estatusRequisicion3.setClave("OCN");
			estatusRequisicion3.setNombre("nueva");
			estatusRequisicion3.setColor(
					"padding:5px;background:#007DC1;background:-moz-linear-gradient(top, #007DC1 0%, #0061A7 100%);background:-webkit-gradient(linear, left top, left bottom, color-stop(0%,#007DC1), color-stop(100%,#0061A7));background:-webkit-linear-gradient(top, #007DC1 0%,#0061A7 100%);background:-o-linear-gradient(top, #007DC1 0%,#0061A7 100%);background:-ms-linear-gradient(top, #007DC1 0%,#0061A7 100%);background:linear-gradient(to bottom, #007DC1 0%,#0061A7 100%);filter:progid:DXImageTransform.Microsoft.gradient( startColorstr='#007DC1',  endColorstr='#0061A7', GradientType=0 );border-width:2px;border-style:solid;border-color:#124D77;border-radius:100px;box-shadow:0px 1px 0px 0px #54A3F7;text-shadow:0px 1px 0px #154682;");

			EstatusRequisicion estatusRequisicion4 = new EstatusRequisicion();
			estatusRequisicion4.setClave("RQC");
			estatusRequisicion4.setNombre("cancelada");
			estatusRequisicion4.setColor(
					"padding:5px;background:#D0451B;background:-moz-linear-gradient(top, #D0451B 0%, #BC3315 100%);background:-webkit-gradient(linear, left top, left bottom, color-stop(0%,#D0451B), color-stop(100%,#BC3315));background:-webkit-linear-gradient(top, #D0451B 0%,#BC3315 100%);background:-o-linear-gradient(top, #D0451B 0%,#BC3315 100%);background:-ms-linear-gradient(top, #D0451B 0%,#BC3315 100%);background:linear-gradient(to bottom, #D0451B 0%,#BC3315 100%);filter:progid:DXImageTransform.Microsoft.gradient( startColorstr='#D0451B',  endColorstr='#BC3315', GradientType=0 );border-width:2px;border-style:solid;border-color:#942911;border-radius:100px;box-shadow:0px 1px 0px 0px #CF866C;text-shadow:0px 1px 0px #FFFFFF;");

			EstatusRequisicion estatusRequisicion5 = new EstatusRequisicion();
			estatusRequisicion5.setClave("COC");
			estatusRequisicion5.setNombre("cancelada");
			estatusRequisicion5.setColor(
					"padding:5px;background:#D0451B;background:-moz-linear-gradient(top, #D0451B 0%, #BC3315 100%);background:-webkit-gradient(linear, left top, left bottom, color-stop(0%,#D0451B), color-stop(100%,#BC3315));background:-webkit-linear-gradient(top, #D0451B 0%,#BC3315 100%);background:-o-linear-gradient(top, #D0451B 0%,#BC3315 100%);background:-ms-linear-gradient(top, #D0451B 0%,#BC3315 100%);background:linear-gradient(to bottom, #D0451B 0%,#BC3315 100%);filter:progid:DXImageTransform.Microsoft.gradient( startColorstr='#D0451B',  endColorstr='#BC3315', GradientType=0 );border-width:2px;border-style:solid;border-color:#942911;border-radius:100px;box-shadow:0px 1px 0px 0px #CF866C;text-shadow:0px 1px 0px #FFFFFF;");

			EstatusRequisicion estatusRequisicion6 = new EstatusRequisicion();
			estatusRequisicion6.setClave("OCC");
			estatusRequisicion6.setNombre("cancelada");
			estatusRequisicion6.setColor(
					"padding:5px;background:#D0451B;background:-moz-linear-gradient(top, #D0451B 0%, #BC3315 100%);background:-webkit-gradient(linear, left top, left bottom, color-stop(0%,#D0451B), color-stop(100%,#BC3315));background:-webkit-linear-gradient(top, #D0451B 0%,#BC3315 100%);background:-o-linear-gradient(top, #D0451B 0%,#BC3315 100%);background:-ms-linear-gradient(top, #D0451B 0%,#BC3315 100%);background:linear-gradient(to bottom, #D0451B 0%,#BC3315 100%);filter:progid:DXImageTransform.Microsoft.gradient( startColorstr='#D0451B',  endColorstr='#BC3315', GradientType=0 );border-width:2px;border-style:solid;border-color:#942911;border-radius:100px;box-shadow:0px 1px 0px 0px #CF866C;text-shadow:0px 1px 0px #FFFFFF;");

			EstatusRequisicion estatusRequisicion7 = new EstatusRequisicion();
			estatusRequisicion7.setClave("RQP");
			estatusRequisicion7.setNombre("pendiente");
			estatusRequisicion7.setColor(
					"padding:5px;background:#FFEC64;background:-moz-linear-gradient(top, #FFEC64 0%, #FFAB23 100%);background:-webkit-gradient(linear, left top, left bottom, color-stop(0%,#FFEC64), color-stop(100%,#FFAB23));background:-webkit-linear-gradient(top, #FFEC64 0%,#FFAB23 100%);background:-o-linear-gradient(top, #FFEC64 0%,#FFAB23 100%);background:-ms-linear-gradient(top, #FFEC64 0%,#FFAB23 100%);background:linear-gradient(to bottom, #FFEC64 0%,#FFAB23 100%);filter:progid:DXImageTransform.Microsoft.gradient( startColorstr='#FFEC64',  endColorstr='#FFAB23', GradientType=0 );border-width:2px;border-style:solid;border-color:#FFAA22;border-radius:100px;box-shadow:0px 1px 0px 0px #FFF6AF;text-shadow:0px 1px 0px #FFEE66;");

			EstatusRequisicion estatusRequisicion8 = new EstatusRequisicion();
			estatusRequisicion8.setClave("RQT");
			estatusRequisicion8.setNombre("terminada");
			estatusRequisicion8.setColor(
					"padding:5px;background:#77B55A;background:-moz-linear-gradient(top, #77B55A 0%, #72B352 100%);background:-webkit-gradient(linear, left top, left bottom, color-stop(0%,#77B55A), color-stop(100%,#72B352));background:-webkit-linear-gradient(top, #77B55A 0%,#72B352 100%);background:-o-linear-gradient(top, #77B55A 0%,#72B352 100%);background:-ms-linear-gradient(top, #77B55A 0%,#72B352 100%);background:linear-gradient(to bottom, #77B55A 0%,#72B352 100%);filter:progid:DXImageTransform.Microsoft.gradient( startColorstr='#77B55A',  endColorstr='#72B352', GradientType=0 );border-width:2px;border-style:solid;border-color:#4B8F29;border-radius:100px;box-shadow:0px 10px 14px -7px #3E7327;text-shadow:0px 1px 0px #5B8A3C;");

			EstatusRequisicion estatusRequisicion9 = new EstatusRequisicion();
			estatusRequisicion9.setClave("COE");
			estatusRequisicion9.setNombre("enviada");
			estatusRequisicion9.setColor(
					"padding:5px;background:#FFEC64;background:-moz-linear-gradient(top, #FFEC64 0%, #FFAB23 100%);background:-webkit-gradient(linear, left top, left bottom, color-stop(0%,#FFEC64), color-stop(100%,#FFAB23));background:-webkit-linear-gradient(top, #FFEC64 0%,#FFAB23 100%);background:-o-linear-gradient(top, #FFEC64 0%,#FFAB23 100%);background:-ms-linear-gradient(top, #FFEC64 0%,#FFAB23 100%);background:linear-gradient(to bottom, #FFEC64 0%,#FFAB23 100%);filter:progid:DXImageTransform.Microsoft.gradient( startColorstr='#FFEC64',  endColorstr='#FFAB23', GradientType=0 );border-width:2px;border-style:solid;border-color:#FFAA22;border-radius:100px;box-shadow:0px 1px 0px 0px #FFF6AF;text-shadow:0px 1px 0px #FFEE66;");

			EstatusRequisicion estatusRequisicion10 = new EstatusRequisicion();
			estatusRequisicion10.setClave("COA");
			estatusRequisicion10.setNombre("aceptada");
			estatusRequisicion10.setColor(
					"padding:5px;background:#77B55A;background:-moz-linear-gradient(top, #77B55A 0%, #72B352 100%);background:-webkit-gradient(linear, left top, left bottom, color-stop(0%,#77B55A), color-stop(100%,#72B352));background:-webkit-linear-gradient(top, #77B55A 0%,#72B352 100%);background:-o-linear-gradient(top, #77B55A 0%,#72B352 100%);background:-ms-linear-gradient(top, #77B55A 0%,#72B352 100%);background:linear-gradient(to bottom, #77B55A 0%,#72B352 100%);filter:progid:DXImageTransform.Microsoft.gradient( startColorstr='#77B55A',  endColorstr='#72B352', GradientType=0 );border-width:2px;border-style:solid;border-color:#4B8F29;border-radius:100px;box-shadow:0px 10px 14px -7px #3E7327;text-shadow:0px 1px 0px #5B8A3C;");

			EstatusRequisicion estatusRequisicion11 = new EstatusRequisicion();
			estatusRequisicion11.setClave("OCT");
			estatusRequisicion11.setNombre("terminada");
			estatusRequisicion11.setColor(
					"padding:5px;background:#77B55A;background:-moz-linear-gradient(top, #77B55A 0%, #72B352 100%);background:-webkit-gradient(linear, left top, left bottom, color-stop(0%,#77B55A), color-stop(100%,#72B352));background:-webkit-linear-gradient(top, #77B55A 0%,#72B352 100%);background:-o-linear-gradient(top, #77B55A 0%,#72B352 100%);background:-ms-linear-gradient(top, #77B55A 0%,#72B352 100%);background:linear-gradient(to bottom, #77B55A 0%,#72B352 100%);filter:progid:DXImageTransform.Microsoft.gradient( startColorstr='#77B55A',  endColorstr='#72B352', GradientType=0 );border-width:2px;border-style:solid;border-color:#4B8F29;border-radius:100px;box-shadow:0px 10px 14px -7px #3E7327;text-shadow:0px 1px 0px #5B8A3C;");

			this.estatusRequisicionService.save(estatusRequisicion1);
			this.estatusRequisicionService.save(estatusRequisicion2);
			this.estatusRequisicionService.save(estatusRequisicion3);
			this.estatusRequisicionService.save(estatusRequisicion4);
			this.estatusRequisicionService.save(estatusRequisicion5);
			this.estatusRequisicionService.save(estatusRequisicion6);
			this.estatusRequisicionService.save(estatusRequisicion7);
			this.estatusRequisicionService.save(estatusRequisicion8);
			this.estatusRequisicionService.save(estatusRequisicion9);
			this.estatusRequisicionService.save(estatusRequisicion10);
			this.estatusRequisicionService.save(estatusRequisicion11);
		}
		
	}

	private void catalogoClaveArmonizada() {
		if (this.claveArmonizadaService.getAll() == null) {
			ClaveArmonizada clave1 = new ClaveArmonizada();
			clave1.setClasificacionId("1");
			clave1.setClasificacionNombre("Muebles");
			clave1.setGrupo(Integer.valueOf(2));
			clave1.setSubGrupo(Integer.valueOf(1));
			clave1.setClase(Integer.valueOf(1));
			clave1.setClave("211");
			clave1.setDescripcion("Materiales, �tiles y equipos menores de oficina");
			clave1.setFechaActualizacion(Calendar.getInstance());
			
			ClaveArmonizada clave2 = new ClaveArmonizada();
			clave2.setClasificacionId("1");
			clave2.setClasificacionNombre("Muebles");
			clave2.setGrupo(Integer.valueOf(2));
			clave2.setSubGrupo(Integer.valueOf(1));
			clave2.setClase(Integer.valueOf(2));
			clave2.setClave("212");
			clave2.setDescripcion("Materiales y �tiles de impresi�n y reproducci�n");
			clave2.setFechaActualizacion(Calendar.getInstance());
			ClaveArmonizada clave3 = new ClaveArmonizada();
			clave3.setClasificacionId("1");
			clave3.setClasificacionNombre("Muebles");
			clave3.setGrupo(Integer.valueOf(2));
			clave3.setSubGrupo(Integer.valueOf(1));
			clave3.setClase(Integer.valueOf(3));
			clave3.setClave("213");
			clave3.setDescripcion("Material estad�stico y geogr�fico");
			clave3.setFechaActualizacion(Calendar.getInstance());
			ClaveArmonizada clave4 = new ClaveArmonizada();
			clave4.setClasificacionId("1");
			clave4.setClasificacionNombre("Muebles");
			clave4.setGrupo(Integer.valueOf(2));
			clave4.setSubGrupo(Integer.valueOf(1));
			clave4.setClase(Integer.valueOf(4));
			clave4.setClave("214");
			clave4.setDescripcion(
					"Materiales, �tiles y equipos menores de tecnolog�as de la informaci�n y comunicaciones");
			clave4.setFechaActualizacion(Calendar.getInstance());
			ClaveArmonizada clave5 = new ClaveArmonizada();
			clave5.setClasificacionId("1");
			clave5.setClasificacionNombre("Muebles");
			clave5.setGrupo(Integer.valueOf(2));
			clave5.setSubGrupo(Integer.valueOf(1));
			clave5.setClase(Integer.valueOf(5));
			clave5.setClave("215");
			clave5.setDescripcion("Material impreso e informaci�n digital");
			clave5.setFechaActualizacion(Calendar.getInstance());
			ClaveArmonizada clave6 = new ClaveArmonizada();
			clave6.setClasificacionId("1");
			clave6.setClasificacionNombre("Muebles");
			clave6.setGrupo(Integer.valueOf(2));
			clave6.setSubGrupo(Integer.valueOf(1));
			clave6.setClase(Integer.valueOf(6));
			clave6.setClave("216");
			clave6.setDescripcion("Material de limpieza");
			clave6.setFechaActualizacion(Calendar.getInstance());
			ClaveArmonizada clave7 = new ClaveArmonizada();
			clave7.setClasificacionId("1");
			clave7.setClasificacionNombre("Muebles");
			clave7.setGrupo(Integer.valueOf(2));
			clave7.setSubGrupo(Integer.valueOf(1));
			clave7.setClase(Integer.valueOf(7));
			clave7.setClave("217");
			clave7.setDescripcion("Materiales y �tiles de ense�anza");
			clave7.setFechaActualizacion(Calendar.getInstance());
			ClaveArmonizada clave8 = new ClaveArmonizada();
			clave8.setClasificacionId("1");
			clave8.setClasificacionNombre("Muebles");
			clave8.setGrupo(Integer.valueOf(2));
			clave8.setSubGrupo(Integer.valueOf(1));
			clave8.setClase(Integer.valueOf(8));
			clave8.setClave("218");
			clave8.setDescripcion("Materiales para el registro e identificaci�n de bienes y personas");
			clave8.setFechaActualizacion(Calendar.getInstance());
			
			
			
			ClaveArmonizada clave9 = new ClaveArmonizada();
			clave9.setClasificacionId("1");
			clave9.setClasificacionNombre("Muebles");
			clave9.setGrupo(Integer.valueOf(2));
			clave9.setSubGrupo(Integer.valueOf(2));
			clave9.setClase(Integer.valueOf(1));
			clave9.setClave("221");
			clave9.setDescripcion("Productos alimenticios para personas");
			clave9.setFechaActualizacion(Calendar.getInstance());
			ClaveArmonizada clave10 = new ClaveArmonizada();
			clave10.setClasificacionId("1");
			clave10.setClasificacionNombre("Muebles");
			clave10.setGrupo(Integer.valueOf(2));
			clave10.setSubGrupo(Integer.valueOf(2));
			clave10.setClase(Integer.valueOf(2));
			clave10.setClave("222");
			clave10.setDescripcion("Productos alimenticios para animales");
			clave10.setFechaActualizacion(Calendar.getInstance());
			ClaveArmonizada clave11 = new ClaveArmonizada();
			clave11.setClasificacionId("1");
			clave11.setClasificacionNombre("Muebles");
			clave11.setGrupo(Integer.valueOf(2));
			clave11.setSubGrupo(Integer.valueOf(2));
			clave11.setClase(Integer.valueOf(3));
			clave11.setClave("223");
			clave11.setDescripcion("Utensilios para el servicio de alimentaci�n");
			clave11.setFechaActualizacion(Calendar.getInstance());
			
			
			ClaveArmonizada clave12 = new ClaveArmonizada();
			clave12.setClasificacionId("1");
			clave12.setClasificacionNombre("Muebles");
			clave12.setGrupo(Integer.valueOf(2));
			clave12.setSubGrupo(Integer.valueOf(3));
			clave12.setClase(Integer.valueOf(1));
			clave12.setClave("231");
			clave12.setDescripcion("Productos alimenticios, agropecuarios y forestales adquiridos como materia prima");
			clave12.setFechaActualizacion(Calendar.getInstance());
			ClaveArmonizada clave13 = new ClaveArmonizada();
			clave13.setClasificacionId("1");
			clave13.setClasificacionNombre("Muebles");
			clave13.setGrupo(Integer.valueOf(2));
			clave13.setSubGrupo(Integer.valueOf(3));
			clave13.setClase(Integer.valueOf(2));
			clave13.setClave("232");
			clave13.setDescripcion("Insumos textiles adquiridos como materia prima");
			clave13.setFechaActualizacion(Calendar.getInstance());
			ClaveArmonizada clave14 = new ClaveArmonizada();
			clave14.setClasificacionId("1");
			clave14.setClasificacionNombre("Muebles");
			clave14.setGrupo(Integer.valueOf(2));
			clave14.setSubGrupo(Integer.valueOf(3));
			clave14.setClase(Integer.valueOf(3));
			clave14.setClave("233");
			clave14.setDescripcion("Productos de papel, cart�n e impresos adquiridos como materia prima");
			clave14.setFechaActualizacion(Calendar.getInstance());
			
			
			
			ClaveArmonizada clave15 = new ClaveArmonizada();
			clave15.setClasificacionId("1");
			clave15.setClasificacionNombre("Muebles");
			clave15.setGrupo(Integer.valueOf(2));
			clave15.setSubGrupo(Integer.valueOf(34));
			clave15.setClase(Integer.valueOf(34));
			clave15.setClave("234");
			clave15.setDescripcion(
					"Combustibles, lubricantes, aditivos, carb�n y sus derivados adquiridos como materia prima");
			clave15.setFechaActualizacion(Calendar.getInstance());
			ClaveArmonizada clave16 = new ClaveArmonizada();
			clave16.setClasificacionId("1");
			clave16.setClasificacionNombre("Muebles");
			clave16.setGrupo(Integer.valueOf(2));
			clave16.setSubGrupo(Integer.valueOf(3));
			clave16.setClase(Integer.valueOf(5));
			clave16.setClave("235");
			clave16.setDescripcion("Productos qu�micos, farmac�uticos y de laboratorio adquiridos como materia prima");
			clave16.setFechaActualizacion(Calendar.getInstance());
			ClaveArmonizada clave17 = new ClaveArmonizada();
			clave17.setClasificacionId("1");
			clave17.setClasificacionNombre("Muebles");
			clave17.setGrupo(Integer.valueOf(2));
			clave17.setSubGrupo(Integer.valueOf(3));
			clave17.setClase(Integer.valueOf(6));
			clave17.setClave("236");
			clave17.setDescripcion(
					"Productos met�licos y a base de minerales no met�licos adquiridos como materia prima");
			clave17.setFechaActualizacion(Calendar.getInstance());
			ClaveArmonizada clave19 = new ClaveArmonizada();
			clave19.setClasificacionId("1");
			clave19.setClasificacionNombre("Muebles");
			clave19.setGrupo(Integer.valueOf(2));
			clave19.setSubGrupo(Integer.valueOf(3));
			clave19.setClase(Integer.valueOf(7));
			clave19.setClave("237");
			clave19.setDescripcion("Productos de cuero, piel, pl�stico y hule adquiridos como materia prima");
			clave19.setFechaActualizacion(Calendar.getInstance());
			ClaveArmonizada clave20 = new ClaveArmonizada();
			clave20.setClasificacionId("1");
			clave20.setClasificacionNombre("Muebles");
			clave20.setGrupo(Integer.valueOf(2));
			clave20.setSubGrupo(Integer.valueOf(3));
			clave20.setClase(Integer.valueOf(8));
			clave20.setClave("238");
			clave20.setDescripcion("Mercanc�as adquiridas para su comercializaci�n");
			clave20.setFechaActualizacion(Calendar.getInstance());
			ClaveArmonizada clave21 = new ClaveArmonizada();
			clave21.setClasificacionId("1");
			clave21.setClasificacionNombre("Muebles");
			clave21.setGrupo(Integer.valueOf(2));
			clave21.setSubGrupo(Integer.valueOf(3));
			clave21.setClase(Integer.valueOf(9));
			clave21.setClave("239");
			clave21.setDescripcion("Otros productos adquiridos como materia prima");
			clave21.setFechaActualizacion(Calendar.getInstance());
			
			
			ClaveArmonizada clave22 = new ClaveArmonizada();
			clave22.setClasificacionId("1");
			clave22.setClasificacionNombre("Muebles");
			clave22.setGrupo(Integer.valueOf(2));
			clave22.setSubGrupo(Integer.valueOf(4));
			clave22.setClase(Integer.valueOf(1));
			clave22.setClave("241");
			clave22.setDescripcion("Productos minerales no met�licos");
			clave22.setFechaActualizacion(Calendar.getInstance());
			ClaveArmonizada clave23 = new ClaveArmonizada();
			clave23.setClasificacionId("1");
			clave23.setClasificacionNombre("Muebles");
			clave23.setGrupo(Integer.valueOf(2));
			clave23.setSubGrupo(Integer.valueOf(4));
			clave23.setClase(Integer.valueOf(2));
			clave23.setClave("242");
			clave23.setDescripcion("Cemento y productos de concreto");
			clave23.setFechaActualizacion(Calendar.getInstance());
			ClaveArmonizada clave24 = new ClaveArmonizada();
			clave24.setClasificacionId("1");
			clave24.setClasificacionNombre("Muebles");
			clave24.setGrupo(Integer.valueOf(2));
			clave24.setSubGrupo(Integer.valueOf(4));
			clave24.setClase(Integer.valueOf(3));
			clave24.setClave("243");
			clave24.setDescripcion("Cal, yeso y productos de yeso");
			clave24.setFechaActualizacion(Calendar.getInstance());
			ClaveArmonizada clave25 = new ClaveArmonizada();
			clave25.setClasificacionId("1");
			clave25.setClasificacionNombre("Muebles");
			clave25.setGrupo(Integer.valueOf(2));
			clave25.setSubGrupo(Integer.valueOf(4));
			clave25.setClase(Integer.valueOf(4));
			clave25.setClave("244");
			clave25.setDescripcion("Madera y productos de madera");
			clave25.setFechaActualizacion(Calendar.getInstance());
			ClaveArmonizada clave26 = new ClaveArmonizada();
			clave26.setClasificacionId("1");
			clave26.setClasificacionNombre("Muebles");
			clave26.setGrupo(Integer.valueOf(2));
			clave26.setSubGrupo(Integer.valueOf(4));
			clave26.setClase(Integer.valueOf(5));
			clave26.setClave("245");
			clave26.setDescripcion("Vidrio y productos de vidrio");
			clave26.setFechaActualizacion(Calendar.getInstance());
			ClaveArmonizada clave27 = new ClaveArmonizada();
			clave27.setClasificacionId("1");
			clave27.setClasificacionNombre("Muebles");
			clave27.setGrupo(Integer.valueOf(2));
			clave27.setSubGrupo(Integer.valueOf(4));
			clave27.setClase(Integer.valueOf(6));
			clave27.setClave("246");
			clave27.setDescripcion("Material el�ctrico y electr�nico");
			clave27.setFechaActualizacion(Calendar.getInstance());
			ClaveArmonizada clave28 = new ClaveArmonizada();
			clave28.setClasificacionId("1");
			clave28.setClasificacionNombre("Muebles");
			clave28.setGrupo(Integer.valueOf(2));
			clave28.setSubGrupo(Integer.valueOf(4));
			clave28.setClase(Integer.valueOf(7));
			clave28.setClave("247");
			clave28.setDescripcion("Art�culos met�licos para la construcci�n");
			clave28.setFechaActualizacion(Calendar.getInstance());
			ClaveArmonizada clave29 = new ClaveArmonizada();
			clave29.setClasificacionId("1");
			clave29.setClasificacionNombre("Muebles");
			clave29.setGrupo(Integer.valueOf(2));
			clave29.setSubGrupo(Integer.valueOf(4));
			clave29.setClase(Integer.valueOf(8));
			clave29.setClave("248");
			clave29.setDescripcion("Materiales complementarios");
			clave29.setFechaActualizacion(Calendar.getInstance());
			ClaveArmonizada clave30 = new ClaveArmonizada();
			clave30.setClasificacionId("1");
			clave30.setClasificacionNombre("Muebles");
			clave30.setGrupo(Integer.valueOf(2));
			clave30.setSubGrupo(Integer.valueOf(4));
			clave30.setClase(Integer.valueOf(9));
			clave30.setClave("249");
			clave30.setDescripcion("Otros materiales y art�culos de construcci�n y reparaci�n");
			clave30.setFechaActualizacion(Calendar.getInstance());
			
			
			ClaveArmonizada clave31 = new ClaveArmonizada();
			clave31.setClasificacionId("1");
			clave31.setClasificacionNombre("Muebles");
			clave31.setGrupo(Integer.valueOf(2));
			clave31.setSubGrupo(Integer.valueOf(5));
			clave31.setClase(Integer.valueOf(1));
			clave31.setClave("251");
			clave31.setDescripcion("Productos qu�micos b�sicos");
			clave31.setFechaActualizacion(Calendar.getInstance());
			ClaveArmonizada clave32 = new ClaveArmonizada();
			clave32.setClasificacionId("1");
			clave32.setClasificacionNombre("Muebles");
			clave32.setGrupo(Integer.valueOf(2));
			clave32.setSubGrupo(Integer.valueOf(5));
			clave32.setClase(Integer.valueOf(2));
			clave32.setClave("252");
			clave32.setDescripcion("Fertilizantes, pesticidas y otros agroqu�micos");
			clave32.setFechaActualizacion(Calendar.getInstance());
			ClaveArmonizada clave33 = new ClaveArmonizada();
			clave33.setClasificacionId("1");
			clave33.setClasificacionNombre("Muebles");
			clave33.setGrupo(Integer.valueOf(2));
			clave33.setSubGrupo(Integer.valueOf(5));
			clave33.setClase(Integer.valueOf(3));
			clave33.setClave("253");
			clave33.setDescripcion("Medicinas y productos farmac�uticos");
			clave33.setFechaActualizacion(Calendar.getInstance());
			ClaveArmonizada clave34 = new ClaveArmonizada();
			clave34.setClasificacionId("1");
			clave34.setClasificacionNombre("Muebles");
			clave34.setGrupo(Integer.valueOf(2));
			clave34.setSubGrupo(Integer.valueOf(5));
			clave34.setClase(Integer.valueOf(4));
			clave34.setClave("254");
			clave34.setDescripcion("Materiales, accesorios y suministros m�dicos");
			clave34.setFechaActualizacion(Calendar.getInstance());
			ClaveArmonizada clave35 = new ClaveArmonizada();
			clave35.setClasificacionId("1");
			clave35.setClasificacionNombre("Muebles");
			clave35.setGrupo(Integer.valueOf(2));
			clave35.setSubGrupo(Integer.valueOf(5));
			clave35.setClase(Integer.valueOf(5));
			clave35.setClave("255");
			clave35.setDescripcion("Materiales, accesorios y suministros de laboratorio");
			clave35.setFechaActualizacion(Calendar.getInstance());
			ClaveArmonizada clave36 = new ClaveArmonizada();
			clave36.setClasificacionId("1");
			clave36.setClasificacionNombre("Muebles");
			clave36.setGrupo(Integer.valueOf(2));
			clave36.setSubGrupo(Integer.valueOf(5));
			clave36.setClase(Integer.valueOf(6));
			clave36.setClave("256");
			clave36.setDescripcion("Fibras sint�ticas, hules, pl�sticos y derivados");
			clave36.setFechaActualizacion(Calendar.getInstance());
			ClaveArmonizada clave37 = new ClaveArmonizada();
			clave37.setClasificacionId("1");
			clave37.setClasificacionNombre("Muebles");
			clave37.setGrupo(Integer.valueOf(2));
			clave37.setSubGrupo(Integer.valueOf(5));
			clave37.setClase(Integer.valueOf(9));
			clave37.setClave("259");
			clave37.setDescripcion("Otros productos qu�micos");
			clave37.setFechaActualizacion(Calendar.getInstance());
			
			
			ClaveArmonizada clave38 = new ClaveArmonizada();
			clave38.setClasificacionId("1");
			clave38.setClasificacionNombre("Muebles");
			clave38.setGrupo(Integer.valueOf(2));
			clave38.setSubGrupo(Integer.valueOf(6));
			clave38.setClase(Integer.valueOf(1));
			clave38.setClave("261");
			clave38.setDescripcion("Combustibles, lubricantes y aditivos");
			clave38.setFechaActualizacion(Calendar.getInstance());
			ClaveArmonizada clave39 = new ClaveArmonizada();
			clave39.setClasificacionId("1");
			clave39.setClasificacionNombre("Muebles");
			clave39.setGrupo(Integer.valueOf(2));
			clave39.setSubGrupo(Integer.valueOf(6));
			clave39.setClase(Integer.valueOf(2));
			clave39.setClave("262");
			clave39.setDescripcion("Carb�n y sus derivados");
			clave39.setFechaActualizacion(Calendar.getInstance());
			
			
			ClaveArmonizada clave40 = new ClaveArmonizada();
			clave40.setClasificacionId("1");
			clave40.setClasificacionNombre("Muebles");
			clave40.setGrupo(Integer.valueOf(2));
			clave40.setSubGrupo(Integer.valueOf(7));
			clave40.setClase(Integer.valueOf(1));
			clave40.setClave("271");
			clave40.setDescripcion("Vestuario y uniformes");
			clave40.setFechaActualizacion(Calendar.getInstance());
			ClaveArmonizada clave41 = new ClaveArmonizada();
			clave41.setClasificacionId("1");
			clave41.setClasificacionNombre("Muebles");
			clave41.setGrupo(Integer.valueOf(2));
			clave41.setSubGrupo(Integer.valueOf(7));
			clave41.setClase(Integer.valueOf(2));
			clave41.setClave("272");
			clave41.setDescripcion("Prendas de seguridad y protecci�n personal");
			clave41.setFechaActualizacion(Calendar.getInstance());
			ClaveArmonizada clave42 = new ClaveArmonizada();
			clave42.setClasificacionId("1");
			clave42.setClasificacionNombre("Muebles");
			clave42.setGrupo(Integer.valueOf(2));
			clave42.setSubGrupo(Integer.valueOf(7));
			clave42.setClase(Integer.valueOf(3));
			clave42.setClave("273");
			clave42.setDescripcion("Art�culos deportivos");
			clave42.setFechaActualizacion(Calendar.getInstance());
			ClaveArmonizada clave43 = new ClaveArmonizada();
			clave43.setClasificacionId("1");
			clave43.setClasificacionNombre("Muebles");
			clave43.setGrupo(Integer.valueOf(2));
			clave43.setSubGrupo(Integer.valueOf(7));
			clave43.setClase(Integer.valueOf(4));
			clave43.setClave("274");
			clave43.setDescripcion("Productos textiles");
			clave43.setFechaActualizacion(Calendar.getInstance());
			ClaveArmonizada clave44 = new ClaveArmonizada();
			clave44.setClasificacionId("1");
			clave44.setClasificacionNombre("Muebles");
			clave44.setGrupo(Integer.valueOf(2));
			clave44.setSubGrupo(Integer.valueOf(7));
			clave44.setClase(Integer.valueOf(5));
			clave44.setClave("275");
			clave44.setDescripcion("Blancos y otros productos textiles, excepto prendas de vestir");
			clave44.setFechaActualizacion(Calendar.getInstance());
			
			
			ClaveArmonizada clave45 = new ClaveArmonizada();
			clave45.setClasificacionId("1");
			clave45.setClasificacionNombre("Muebles");
			clave45.setGrupo(Integer.valueOf(2));
			clave45.setSubGrupo(Integer.valueOf(8));
			clave45.setClase(Integer.valueOf(1));
			clave45.setClave("281");
			clave45.setDescripcion("Sustancias y materiales explosivos");
			clave45.setFechaActualizacion(Calendar.getInstance());
			ClaveArmonizada clave46 = new ClaveArmonizada();
			clave46.setClasificacionId("1");
			clave46.setClasificacionNombre("Muebles");
			clave46.setGrupo(Integer.valueOf(2));
			clave46.setSubGrupo(Integer.valueOf(8));
			clave46.setClase(Integer.valueOf(2));
			clave46.setClave("282");
			clave46.setDescripcion("Materiales de seguridad publica");
			clave46.setFechaActualizacion(Calendar.getInstance());
			ClaveArmonizada clave47 = new ClaveArmonizada();
			clave47.setClasificacionId("1");
			clave47.setClasificacionNombre("Muebles");
			clave47.setGrupo(Integer.valueOf(2));
			clave47.setSubGrupo(Integer.valueOf(8));
			clave47.setClase(Integer.valueOf(3));
			clave47.setClave("283");
			clave47.setDescripcion("Prendas de protecci�n para seguridad p�blica y nacional");
			clave47.setFechaActualizacion(Calendar.getInstance());
			
			
			ClaveArmonizada clave48 = new ClaveArmonizada();
			clave48.setClasificacionId("1");
			clave48.setClasificacionNombre("Muebles");
			clave48.setGrupo(Integer.valueOf(2));
			clave48.setSubGrupo(Integer.valueOf(9));
			clave48.setClase(Integer.valueOf(1));
			clave48.setClave("291");
			clave48.setDescripcion("Herramientas menores");
			clave48.setFechaActualizacion(Calendar.getInstance());
			ClaveArmonizada clave50 = new ClaveArmonizada();
			clave50.setClasificacionId("1");
			clave50.setClasificacionNombre("Muebles");
			clave50.setGrupo(Integer.valueOf(2));
			clave50.setSubGrupo(Integer.valueOf(9));
			clave50.setClase(Integer.valueOf(2));
			clave50.setClave("292");
			clave50.setDescripcion("Refacciones y accesorios menores de edificios");
			clave50.setFechaActualizacion(Calendar.getInstance());
			ClaveArmonizada clave51 = new ClaveArmonizada();
			clave51.setClasificacionId("1");
			clave51.setClasificacionNombre("Muebles");
			clave51.setGrupo(Integer.valueOf(2));
			clave51.setSubGrupo(Integer.valueOf(9));
			clave51.setClase(Integer.valueOf(3));
			clave51.setClave("293");
			clave51.setDescripcion(
					"Refacciones y accesorios menores de mobiliario y equipo de administraci�n, educacional y recreativo");
			clave51.setFechaActualizacion(Calendar.getInstance());
			ClaveArmonizada clave52 = new ClaveArmonizada();
			clave52.setClasificacionId("1");
			clave52.setClasificacionNombre("Muebles");
			clave52.setGrupo(Integer.valueOf(2));
			clave52.setSubGrupo(Integer.valueOf(9));
			clave52.setClase(Integer.valueOf(4));
			clave52.setClave("294");
			clave52.setDescripcion(
					"Refacciones y accesorios menores de equipo de c�mputo y tecnolog�as de la informaci�n");
			clave52.setFechaActualizacion(Calendar.getInstance());
			ClaveArmonizada clave53 = new ClaveArmonizada();
			clave53.setClasificacionId("1");
			clave53.setClasificacionNombre("Muebles");
			clave53.setGrupo(Integer.valueOf(2));
			clave53.setSubGrupo(Integer.valueOf(9));
			clave53.setClase(Integer.valueOf(5));
			clave53.setClave("295");
			clave53.setDescripcion("Refacciones y accesorios menores de equipo e instrumental m�dico y de laboratorio");
			clave53.setFechaActualizacion(Calendar.getInstance());
			ClaveArmonizada clave54 = new ClaveArmonizada();
			clave54.setClasificacionId("1");
			clave54.setClasificacionNombre("Muebles");
			clave54.setGrupo(Integer.valueOf(2));
			clave54.setSubGrupo(Integer.valueOf(9));
			clave54.setClase(Integer.valueOf(6));
			clave54.setClave("296");
			clave54.setDescripcion("Refacciones y accesorios menores de equipo de transporte");
			clave54.setFechaActualizacion(Calendar.getInstance());
			ClaveArmonizada clave55 = new ClaveArmonizada();
			clave55.setClasificacionId("1");
			clave55.setClasificacionNombre("Muebles");
			clave55.setGrupo(Integer.valueOf(2));
			clave55.setSubGrupo(Integer.valueOf(9));
			clave55.setClase(Integer.valueOf(7));
			clave55.setClave("297");
			clave55.setDescripcion("Refacciones y accesorios menores de equipo de defensa y seguridad");
			clave55.setFechaActualizacion(Calendar.getInstance());
			ClaveArmonizada clave56 = new ClaveArmonizada();
			clave56.setClasificacionId("1");
			clave56.setClasificacionNombre("Muebles");
			clave56.setGrupo(Integer.valueOf(2));
			clave56.setSubGrupo(Integer.valueOf(9));
			clave56.setClase(Integer.valueOf(8));
			clave56.setClave("298");
			clave56.setDescripcion("Refacciones y accesorios menores de maquinaria y otros equipos");
			clave56.setFechaActualizacion(Calendar.getInstance());
			ClaveArmonizada clave57 = new ClaveArmonizada();
			clave57.setClasificacionId("1");
			clave57.setClasificacionNombre("Muebles");
			clave57.setGrupo(Integer.valueOf(2));
			clave57.setSubGrupo(Integer.valueOf(9));
			clave57.setClase(Integer.valueOf(9));
			clave57.setClave("299");
			clave57.setDescripcion("Refacciones y accesorios menores otros bienes muebles");
			clave57.setFechaActualizacion(Calendar.getInstance());

			this.claveArmonizadaService.save(clave1);
			this.claveArmonizadaService.save(clave2);
			this.claveArmonizadaService.save(clave3);
			this.claveArmonizadaService.save(clave4);
			this.claveArmonizadaService.save(clave5);
			this.claveArmonizadaService.save(clave6);
			this.claveArmonizadaService.save(clave7);
			this.claveArmonizadaService.save(clave8);
			this.claveArmonizadaService.save(clave9);
			this.claveArmonizadaService.save(clave10);
			this.claveArmonizadaService.save(clave11);
			this.claveArmonizadaService.save(clave12);
			this.claveArmonizadaService.save(clave13);
			this.claveArmonizadaService.save(clave14);
			this.claveArmonizadaService.save(clave15);
			this.claveArmonizadaService.save(clave16);
			this.claveArmonizadaService.save(clave17);
			this.claveArmonizadaService.save(clave19);
			this.claveArmonizadaService.save(clave20);
			this.claveArmonizadaService.save(clave21);
			this.claveArmonizadaService.save(clave22);
			this.claveArmonizadaService.save(clave23);
			this.claveArmonizadaService.save(clave24);
			this.claveArmonizadaService.save(clave25);
			this.claveArmonizadaService.save(clave26);
			this.claveArmonizadaService.save(clave27);
			this.claveArmonizadaService.save(clave28);
			this.claveArmonizadaService.save(clave29);
			this.claveArmonizadaService.save(clave30);
			this.claveArmonizadaService.save(clave31);
			this.claveArmonizadaService.save(clave32);
			this.claveArmonizadaService.save(clave33);
			this.claveArmonizadaService.save(clave34);
			this.claveArmonizadaService.save(clave35);
			this.claveArmonizadaService.save(clave36);
			this.claveArmonizadaService.save(clave37);
			this.claveArmonizadaService.save(clave38);
			this.claveArmonizadaService.save(clave39);
			this.claveArmonizadaService.save(clave40);
			this.claveArmonizadaService.save(clave41);
			this.claveArmonizadaService.save(clave42);
			this.claveArmonizadaService.save(clave43);
			this.claveArmonizadaService.save(clave44);
			this.claveArmonizadaService.save(clave45);
			this.claveArmonizadaService.save(clave46);
			this.claveArmonizadaService.save(clave47);
			this.claveArmonizadaService.save(clave48);
			this.claveArmonizadaService.save(clave50);
			this.claveArmonizadaService.save(clave51);
			this.claveArmonizadaService.save(clave52);
			this.claveArmonizadaService.save(clave53);
			this.claveArmonizadaService.save(clave54);
			this.claveArmonizadaService.save(clave55);
			this.claveArmonizadaService.save(clave56);
			this.claveArmonizadaService.save(clave57);
		}
	}
}
