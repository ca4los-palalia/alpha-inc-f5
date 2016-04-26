package com.cplsystems.stock.app;

import java.io.File;
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
import org.zkoss.image.AImage;

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

	private FileInputStream fileInputStream;
	private XSSFWorkbook workBook;

	@PostConstruct
	public void init() {
		crearUsuarioInicial();
		crearCatalogosPorDefecto();
		organizacion = null;
		usuario = null;
	}

	private void crearUsuarioInicial() {
		Persona personaTemp = null;
		List<Persona> personas = personaService.getAll();
		if (personas == null) {
			personaTemp = new Persona();
			personaTemp.setApellidoPaterno("Paterno admin");
			personaTemp.setApellidoMaterno("Materno admin");
			personaTemp.setNombre("Nombre admin");
			personaService.save(personaTemp);
		}
		List<Organizacion> org = organizacionService.getAll();
		if ((org == null) || (org.size() == 0)) {
			organizacion = new Organizacion();
			organizacion.setNombre("Nombre Organizacion");
			organizacion.setRfc("CAME880957");

			AImage aimagen = getAImage();
			organizacion.setLogotipo(aimagen.getByteData());

			organizacionService.save(organizacion);

		}
		List<Usuarios> usuarios = usuarioService.getAll();
		if (usuarios == null) {
			usuario = new Usuarios();
			usuario.setBenutzer("came");
			usuario.setKennwort("came");
			usuario.setPersona(personaTemp);
			usuario.setOrganizacion(organizacion);
			usuario.setOwner(Boolean.valueOf(true));
			usuario.setClient(Boolean.valueOf(false));
			usuarioService.save(usuario);
		}
	}

	private AImage getAImage() {
		AImage aimagen = null;
		File file = null;

		try {
			file = new File(getClass().getClassLoader().getResource("layout/companyProfile.png").toURI());
			
			aimagen = new AImage(file);
			
			
		} catch (Exception e) {
			System.out.println("El fichero " + file.getAbsolutePath() + " no se encuentra en el sistema");
		}
		
		return aimagen;
	}

	private void crearCatalogosPorDefecto() {
		try {
			fileInputStream = new FileInputStream(generarUrlString("layout/Script.xlsx"));
			workBook = new XSSFWorkbook(fileInputStream);

			if (paisService.getAll() == null) {
				leerDatosDesdeExcel(0);
				System.err.print("... Precarga de paises Terminada!\n");
			}
			if (estadoService.getAll() == null) {
				leerDatosDesdeExcel(1);
				System.err.print("... Precarga de Estados Terminada!\n");
			}
			if (municipioService.getAll() == null) {
				leerDatosDesdeExcel(2);
				System.err.print("... Precarga de Municipios Terminada!\n");
			}
			if (cofiaFuenteFinanciamientoService.getAll() == null) {
				leerDatosDesdeExcel(3);
				System.err.print("... Precarga de Fuente de financiamiento Terminada!\n");
			}
			if (cofiaPartidaGenericaService.getAll() == null) {
				leerDatosDesdeExcel(4);
				System.err.print("... Precarga de Partida generica Terminada!\n");
			}
			if (cofiaProgService.getAll() == null) {
				leerDatosDesdeExcel(5);
				System.err.print("... Precarga de Programa Terminada!\n");
			}
			if (cofiaPyService.getAll() == null) {
				leerDatosDesdeExcel(6);
				System.err.print("... Precarga de Py Terminada!\n");
			}
			if (estatusRequisicionService.getAll() == null) {
				leerDatosDesdeExcel(7);
				System.err.print("... Precarga de Estatus requisiciones Terminada!\n");
			}
			if (claveArmonizadaService.getAll() == null) {
				leerDatosDesdeExcel(8);
				System.err.print("... Precarga de Clave armonizada Terminada!\n");
			}
			fileInputStream.close();

		} catch (Exception e) {
			System.err.println("Error en la precarga inicial");
			e.printStackTrace();
		}
	}

	@SuppressWarnings("rawtypes")
	private void extraerPaisesDeExcel(Iterator rowIterator) {
		List<Pais> paisesTemp = new ArrayList<>();
		Integer i = 0;
		int j;
		XSSFCell hssfCell;
		while (rowIterator.hasNext()) {
			Pais nuevoPais = new Pais();
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
		if (paisesTemp.size() > 0) {
			for (Pais item : paisesTemp) {
				paisService.save(item);
			}
		}
	}

	@SuppressWarnings("rawtypes")
	private void extraerEstadosDeExcel(Iterator rowIterator) {
		List<Estado> estadosTemp = new ArrayList<>();
		Integer i = 0;
		int j;
		XSSFCell hssfCell;
		while (rowIterator.hasNext()) {
			Estado nuevoEstado = new Estado();
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
		if (estadosTemp.size() > 0) {
			for (Estado item : estadosTemp) {
				estadoService.save(item);
			}
			estados = estadosTemp;
		}
	}

	@SuppressWarnings("rawtypes")
	private void extraerMunicipiosDeExcel(Iterator rowIterator) {
		List<Municipio> municipiosTemp = new ArrayList<>();
		Integer i = 0;
		int j;
		XSSFCell hssfCell;
		while (rowIterator.hasNext()) {
			Municipio nuevoMunicipio = new Municipio();
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
		if (municipiosTemp.size() > 0) {
			for (Municipio item : municipiosTemp) {
				municipioService.save(item);
			}
		}
	}

	@SuppressWarnings("rawtypes")
	private void extraerEstatusRequisicionDeExcel(Iterator rowIterator) {
		List<EstatusRequisicion> estatusRequisicionTemp = new ArrayList<>();
		Integer i = 0;
		int j;
		XSSFCell hssfCell;
		while (rowIterator.hasNext()) {
			EstatusRequisicion nuevoestatusRequisicion = new EstatusRequisicion();
			XSSFRow hssfRow = (XSSFRow) rowIterator.next();
			Iterator iterator = hssfRow.cellIterator();
			if (i > 0) {
				j = 0;
				while ((iterator.hasNext()) && (j < 5)) {
					hssfCell = (XSSFCell) iterator.next();
					nuevoestatusRequisicion = crearEstatusRequisicion(nuevoestatusRequisicion, hssfCell, j);
					j++;
				}
				estatusRequisicionTemp.add(nuevoestatusRequisicion);
			}
			i++;
		}
		if (estatusRequisicionTemp.size() > 0) {
			for (EstatusRequisicion item : estatusRequisicionTemp) {
				estatusRequisicionService.save(item);
			}
		}
	}

	@SuppressWarnings("rawtypes")
	private void extraerCofiaPyDeExcel(Iterator rowIterator) {
		List<CofiaPy> cofiaPyTemp = new ArrayList<>();
		Integer i = 0;
		int j;
		XSSFCell hssfCell;
		while (rowIterator.hasNext()) {
			CofiaPy nuevoCofiaPy = new CofiaPy();
			XSSFRow hssfRow = (XSSFRow) rowIterator.next();
			Iterator iterator = hssfRow.cellIterator();
			if (i > 0) {
				j = 0;
				while ((iterator.hasNext()) && (j < 1)) {
					hssfCell = (XSSFCell) iterator.next();
					nuevoCofiaPy = crearCofiaPy(nuevoCofiaPy, hssfCell, j);
					j++;
				}
				cofiaPyTemp.add(nuevoCofiaPy);
			}
			i++;
		}
		if (cofiaPyTemp.size() > 0) {
			for (CofiaPy item : cofiaPyTemp) {
				item.setUltimaActualizacion(Calendar.getInstance());
				item.setFechaActualizacion(StockUtils.getFechaActualConHora());
				item.setOrganizacion(organizacion);
				item.setUsuario(usuario);
				cofiaPyService.save(item);
			}
		}
	}

	@SuppressWarnings("rawtypes")
	private void extraerCofiaProgramaDeExcel(Iterator rowIterator) {
		List<CofiaProg> cofiaProgTemp = new ArrayList<>();
		Integer i = 0;
		int j;
		XSSFCell hssfCell;
		while (rowIterator.hasNext()) {
			CofiaProg nuevoCofiaProg = new CofiaProg();
			XSSFRow hssfRow = (XSSFRow) rowIterator.next();
			Iterator iterator = hssfRow.cellIterator();
			if (i > 0) {
				j = 0;
				while ((iterator.hasNext()) && (j < 1)) {
					hssfCell = (XSSFCell) iterator.next();
					nuevoCofiaProg = crearCofiaProg(nuevoCofiaProg, hssfCell, j);
					j++;
				}
				cofiaProgTemp.add(nuevoCofiaProg);
			}
			i++;
		}
		if (cofiaProgTemp.size() > 0) {
			for (CofiaProg item : cofiaProgTemp) {
				item.setUltimaActualizacion(Calendar.getInstance());
				item.setFechaActualizacion(StockUtils.getFechaActualConHora());
				item.setOrganizacion(organizacion);
				item.setUsuario(usuario);
				cofiaProgService.save(item);
			}
		}
	}

	@SuppressWarnings("rawtypes")
	private void extraerCofiaPartidaGenericaDeExcel(Iterator rowIterator) {
		List<CofiaPartidaGenerica> cofiaPartidaGenericaTemp = new ArrayList<>();
		Integer i = 0;
		int j;
		XSSFCell hssfCell;
		while (rowIterator.hasNext()) {
			CofiaPartidaGenerica nuevoCofiaPartidaGenerica = new CofiaPartidaGenerica();
			XSSFRow hssfRow = (XSSFRow) rowIterator.next();
			Iterator iterator = hssfRow.cellIterator();
			if (i > 0) {
				j = 0;
				while ((iterator.hasNext()) && (j < 1)) {
					hssfCell = (XSSFCell) iterator.next();
					nuevoCofiaPartidaGenerica = crearCofiaPartidaGenerica(nuevoCofiaPartidaGenerica, hssfCell, j);
					j++;
				}
				cofiaPartidaGenericaTemp.add(nuevoCofiaPartidaGenerica);
			}
			i++;
		}
		if (cofiaPartidaGenericaTemp.size() > 0) {
			for (CofiaPartidaGenerica item : cofiaPartidaGenericaTemp) {
				item.setUltimaActualizacion(Calendar.getInstance());
				item.setFechaActualizacion(StockUtils.getFechaActualConHora());
				item.setOrganizacion(organizacion);
				item.setUsuario(usuario);
				cofiaPartidaGenericaService.save(item);
			}
		}
	}

	@SuppressWarnings("rawtypes")
	private void extraerCofiaFuenteFinanciamientoDeExcel(Iterator rowIterator) {
		List<CofiaFuenteFinanciamiento> cofiaFuenteFinanciamientoTemp = new ArrayList<>();
		Integer i = 0;
		int j;
		XSSFCell hssfCell;
		while (rowIterator.hasNext()) {
			CofiaFuenteFinanciamiento nuevoCofiaFuenteFinanciamiento = new CofiaFuenteFinanciamiento();
			XSSFRow hssfRow = (XSSFRow) rowIterator.next();
			Iterator iterator = hssfRow.cellIterator();
			if (i > 0) {
				j = 0;
				while ((iterator.hasNext()) && (j < 1)) {
					hssfCell = (XSSFCell) iterator.next();
					nuevoCofiaFuenteFinanciamiento = crearCofiaFuenteFinanciamiento(nuevoCofiaFuenteFinanciamiento,
							hssfCell, j);
					j++;
				}
				cofiaFuenteFinanciamientoTemp.add(nuevoCofiaFuenteFinanciamiento);
			}
			i++;
		}
		if (cofiaFuenteFinanciamientoTemp.size() > 0) {
			for (CofiaFuenteFinanciamiento item : cofiaFuenteFinanciamientoTemp) {
				item.setUltimaActualizacion(Calendar.getInstance());
				item.setFechaActualizacion(StockUtils.getFechaActualConHora());
				item.setOrganizacion(organizacion);
				item.setUsuario(usuario);
				cofiaFuenteFinanciamientoService.save(item);
			}
		}
	}

	@SuppressWarnings("rawtypes")
	private void extraerClaveArmonizadaDeExcel(Iterator rowIterator) {
		List<ClaveArmonizada> claveArmonizadaTemp = new ArrayList<>();
		Integer i = 0;
		int j;
		XSSFCell hssfCell;
		while (rowIterator.hasNext()) {
			ClaveArmonizada nuevoClaveArmonizada = new ClaveArmonizada();
			XSSFRow hssfRow = (XSSFRow) rowIterator.next();
			Iterator iterator = hssfRow.cellIterator();
			if (i > 0) {
				j = 0;
				while ((iterator.hasNext()) && (j < 7)) {
					hssfCell = (XSSFCell) iterator.next();
					nuevoClaveArmonizada = crearClaveArmonizada(nuevoClaveArmonizada, hssfCell, j);
					j++;
				}
				nuevoClaveArmonizada.setFechaActualizacion(Calendar.getInstance());
				claveArmonizadaTemp.add(nuevoClaveArmonizada);
			}
			i++;
		}
		if (claveArmonizadaTemp.size() > 0) {
			for (ClaveArmonizada item : claveArmonizadaTemp) {
				claveArmonizadaService.save(item);
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

	private CofiaFuenteFinanciamiento crearCofiaFuenteFinanciamiento(
			CofiaFuenteFinanciamiento nuevoCofiaFuenteFinanciamiento, XSSFCell valorDePropiedad, int indice) {
		String valor = String.valueOf(valorDePropiedad);
		switch (indice) {
		case 0:
			nuevoCofiaFuenteFinanciamiento.setNombre(valor);
			break;
		}
		return nuevoCofiaFuenteFinanciamiento;
	}

	private CofiaPartidaGenerica crearCofiaPartidaGenerica(CofiaPartidaGenerica nuevoCofiaPartidaGenerica,
			XSSFCell valorDePropiedad, int indice) {
		String valor = String.valueOf(valorDePropiedad);
		switch (indice) {
		case 0:
			nuevoCofiaPartidaGenerica.setNombre(valor);
			break;
		}
		return nuevoCofiaPartidaGenerica;
	}

	private CofiaProg crearCofiaProg(CofiaProg nuevoCofiaProg, XSSFCell valorDePropiedad, int indice) {
		String valor = String.valueOf(valorDePropiedad);
		switch (indice) {
		case 0:
			nuevoCofiaProg.setNombre(valor);
			break;
		}
		return nuevoCofiaProg;
	}

	private CofiaPy crearCofiaPy(CofiaPy nuevoCofiaPy, XSSFCell valorDePropiedad, int indice) {
		String valor = String.valueOf(valorDePropiedad);
		switch (indice) {
		case 0:
			nuevoCofiaPy.setNombre(valor);
			break;
		}
		return nuevoCofiaPy;
	}

	private EstatusRequisicion crearEstatusRequisicion(EstatusRequisicion nuevoestatusRequisicion,
			XSSFCell valorDePropiedad, int indice) {

		String valor = String.valueOf(valorDePropiedad);
		switch (indice) {
		case 0:
			nuevoestatusRequisicion.setClave(valor);
			break;
		case 1:
			nuevoestatusRequisicion.setNombre(valor);
			break;
		case 2:
			nuevoestatusRequisicion.setDescripcion(valor);
			break;
		case 3:
			nuevoestatusRequisicion.setColor(valor);
			break;
		case 4:
			nuevoestatusRequisicion.setColorFont(valor);
			break;
		}
		return nuevoestatusRequisicion;
	}

	private ClaveArmonizada crearClaveArmonizada(ClaveArmonizada nuevoClaveArmonizada, XSSFCell valorDePropiedad,
			int indice) {
		String valor = String.valueOf(valorDePropiedad);
		switch (indice) {
		case 0:
			nuevoClaveArmonizada.setClasificacionId(valor);
			break;
		case 1:
			nuevoClaveArmonizada.setClasificacionNombre(valor);
			break;
		case 2:
			nuevoClaveArmonizada.setGrupo(Integer.parseInt(removerPuntoCero(valor)));
			break;
		case 3:
			nuevoClaveArmonizada.setSubGrupo(Integer.parseInt(removerPuntoCero(valor)));
			break;
		case 4:
			nuevoClaveArmonizada.setClase(Integer.parseInt(removerPuntoCero(valor)));
			break;
		case 5:
			nuevoClaveArmonizada.setClave(valor);
			break;
		case 6:
			nuevoClaveArmonizada.setDescripcion(valor);
			break;
		}
		return nuevoClaveArmonizada;
	}

	@SuppressWarnings("rawtypes")
	public void leerDatosDesdeExcel(int indiceSheet) {
		XSSFSheet hssfSheet = workBook.getSheetAt(indiceSheet);
		Iterator rowIterator = hssfSheet.rowIterator();

		switch (indiceSheet) {
		case 0:// Pais
			extraerPaisesDeExcel(rowIterator);
			break;
		case 1:// Estados
			extraerEstadosDeExcel(rowIterator);
			break;
		case 2:// Municipios
			extraerMunicipiosDeExcel(rowIterator);
			break;
		case 3://
			extraerCofiaFuenteFinanciamientoDeExcel(rowIterator);
			break;
		case 4://
			extraerCofiaPartidaGenericaDeExcel(rowIterator);
			break;
		case 5://
			extraerCofiaProgramaDeExcel(rowIterator);
			break;
		case 6://
			extraerCofiaPyDeExcel(rowIterator);
			break;
		case 7://
			extraerEstatusRequisicionDeExcel(rowIterator);
			break;
		case 8://
			extraerClaveArmonizadaDeExcel(rowIterator);
			break;
		}
	}

}
