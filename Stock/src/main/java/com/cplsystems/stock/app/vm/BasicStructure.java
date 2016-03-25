package com.cplsystems.stock.app.vm;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Part;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.zkoss.bind.BindContext;
import org.zkoss.json.JSONArray;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.util.Clients;

import com.cplsystems.stock.app.utils.SistemaOperativo;
import com.cplsystems.stock.app.utils.StockUtils;
import com.cplsystems.stock.app.utils.UserPrivileges;
import com.cplsystems.stock.domain.ClaveArmonizada;
import com.cplsystems.stock.domain.DevelopmentTool;
import com.cplsystems.stock.domain.Direccion;
import com.cplsystems.stock.domain.Estado;
import com.cplsystems.stock.domain.Moneda;
import com.cplsystems.stock.domain.Municipio;
import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.Pais;
import com.cplsystems.stock.domain.Persona;
import com.cplsystems.stock.domain.Privilegios;
import com.cplsystems.stock.domain.ProductoNaturaleza;
import com.cplsystems.stock.domain.Requisicion;
import com.cplsystems.stock.domain.Unidad;
import com.cplsystems.stock.domain.Usuarios;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

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

	public InputStream getStreamMediaExcel(BindContext contexto) {
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

	public ClaveArmonizada getClaveArminizadaFromList(String clave) {
		ClaveArmonizada retornar = null;
		if (claveArmonizadaList != null) {
			for (ClaveArmonizada item : claveArmonizadaList) {
				if (clave.equals(item.getClave())) {
					retornar = item;
					break;
				}
			}
		}
		return retornar;
	}

	public Unidad getUnidadFromList(Long idUnidad) {
		Unidad retornar = null;
		if (unidadesDB != null) {
			for (Unidad item : unidadesDB) {
				if (idUnidad.equals(item.getIdUnidad())) {
					retornar = item;
					break;
				}
			}
		}

		return retornar;
	}

	public ProductoNaturaleza getProductoNaturalezaFromList(Long idProductoNaturaleza) {
		ProductoNaturaleza retornar = null;
		if (productosNaturalezas != null) {
			for (ProductoNaturaleza item : productosNaturalezas) {
				if (idProductoNaturaleza.equals(item.getIdProductoNaturaleza())) {
					retornar = item;
					break;
				}
			}
		}

		return retornar;
	}

	public Moneda getMonedaFromList(Long idMoneda) {
		Moneda retornar = null;
		if (monedasDB != null) {
			for (Moneda item : monedasDB) {
				if (idMoneda.equals(item.getIdMoneda())) {
					retornar = item;
					break;
				}
			}
		}
		return retornar;
	}

	public Estado getEstadoFromList(Long idEstado) {
		Estado retornar = null;
		if (estados != null) {
			for (Estado item : estados) {
				if (idEstado.equals(item.getIdEstado())) {
					retornar = item;
					break;
				}
			}
		}
		return retornar;
	}

	public Estado getEstadoFromListByName(String name) {
		Estado retornar = null;
		if (estados != null) {
			for (Estado item : estados) {
				if (name.equalsIgnoreCase(item.getNombre())) {
					retornar = item;
					break;
				}
			}
		}
		return retornar;
	}

	public Municipio getMunicipioFromList(Long idMunicipio) {
		Municipio retornar = null;
		if (municipios != null) {
			for (Municipio item : municipios) {
				if (idMunicipio.equals(item.getIdMunicipio())) {
					retornar = item;
					break;
				}
			}
		}
		return retornar;
	}

	public Municipio getMunicipioFromListByName(String name) {
		Municipio retornar = null;
		if (municipios != null) {
			for (Municipio item : municipios) {
				if (name.equalsIgnoreCase(item.getNombre())) {
					retornar = item;
					break;
				}
			}
		}
		return retornar;
	}

	public Pais getPaisFromList(Long idPais) {
		Pais retornar = null;
		if (paises != null) {
			for (Pais item : paises) {
				if (idPais.equals(item.getIdPais())) {
					retornar = item;
					break;
				}
			}
		}
		return retornar;
	}

	public Pais getPaisFromListByName(String name) {
		Pais retornar = null;
		if (paises != null) {
			for (Pais item : paises) {
				if (name.equalsIgnoreCase(item.getNombre())) {
					retornar = item;
					break;
				}
			}
		}
		return retornar;
	}

	public Direccion getDireccionFromList(Long idDireccion) {
		Direccion retornar = null;
		if (direccionesList != null) {
			for (Direccion item : direccionesList) {
				if (idDireccion.equals(item.getIdDireccion())) {
					retornar = item;
					break;
				}
			}
		}
		return retornar;
	}

	public String suprimirComillas(String input) {
		String salida = input;
		if (input.contains("\""))
			salida = input.substring(1, (input.length() - 1));
		return salida;
	}

	public void generarArbolDirectorios() {
	}

	public String sendEmail(Usuarios usuario, Persona persona, String mensaje, String subject,
			DevelopmentTool development, File file) {
		String debug = "";
		try {
			JsonParser parser = new JsonParser();
			JsonObject jobject = parser.parse(stockUtils.Desencriptar(development.getValue())).getAsJsonObject();

			final String us = suprimirComillas(String.valueOf(jobject.get("user")));
			final String pa = suprimirComillas(String.valueOf(jobject.get("pass")));

			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");
			Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(us, pa);
				}
			});

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(usuario.getPersona().getContacto().getEmail().getEmail()));
			message.setRecipients(Message.RecipientType.TO,
					InternetAddress.parse(persona.getContacto().getEmail().getEmail()));
			message.setSubject(subject);

			// ---------------------------
			Multipart multipart = new MimeMultipart("alternative");
			MimeBodyPart textPart = new MimeBodyPart();
			String textContent = "Hi, Nice to meet you! ";

			textPart.setText(textContent);

			MimeBodyPart htmlPart = new MimeBodyPart();
			String htmlContent = "<html><h1>Hola " + persona.getNombreCompleto() + "</h1><p> <b>"
					+ usuario.getPersona().getNombreCompleto() + " ("
					+ usuario.getPersona().getContacto().getEmail().getEmail() + ")</b><br/><b style='color:red;'> "
					+ mensaje + "</b></p></html>";
			htmlPart.setContent(htmlContent, "text/html");

			multipart.addBodyPart(textPart);
			multipart.addBodyPart(htmlPart);
			message.setContent(multipart);
			
			if(file != null){// Adjuntar archivo al correo
				DataSource ds = new FileDataSource(file);
				htmlPart.setDataHandler(new DataHandler(ds));
				
				htmlPart.setFileName(file.getName());
				htmlPart.setDisposition(Part.ATTACHMENT);
		        multipart.addBodyPart(htmlPart);
			}
			
			
			// -------------------------
			Transport.send(message);

		} catch (Exception e) {
			debug = e.getLocalizedMessage();
			throw new RuntimeException(e);
		}
		return debug;
	}

	public DevelopmentTool crearCorreoDeServicioEmail(String correo, String pass, DevelopmentTool dev) {
		// DevelopmentTool tool = new DevelopmentTool();
		if (correo.contains("@") && correo.contains("gmail")) {
			JsonObject object = crearJsonObjectEmailDevelopment(correo, pass);
			dev.setValue(stockUtils.Encriptar(object.toString()));
		} else
			dev.setDescripcion("El correo debe tener un dominio google para el funcionamiento del servicio");
		return dev;
	}

	public JsonObject crearJsonObjectEmailDevelopment(String correo, String pass) {
		JsonObject object = new JsonObject();
		object.addProperty("user", correo);
		object.addProperty("pass", pass);
		object.addProperty("activado", true);

		return object;
	}

	public void enviarCorreos(Usuarios usuario, Organizacion org, List<Privilegios> privilegios, String mensaje, String asunto, File file) {
		// --- enviar correo
		for (Privilegios item : privilegios) {
			if (item.getUsuarios().getPersona().getContacto() != null) {
				String mensajeEnvio = sendEmail(usuario, item.getUsuarios().getPersona(),
						mensaje,
						asunto,
						org.getDevelopmentTool(), file);

				if (mensajeEnvio.equals("")) {
					StockUtils.showSuccessmessage("Correo enviado", Clients.NOTIFICATION_TYPE_INFO, 0, null);
				} else
					StockUtils.showSuccessmessage("Correo enviado", Clients.NOTIFICATION_TYPE_ERROR, 0, null);
			}
		}
	}
	
	public List<String> getExtensionArchivo(String palabra, String caracter) {
		List<String> parts = new ArrayList<String>();
		String nuevaPalabra = "";
		for (int i = 0; i < palabra.length(); i++) {
			String item = palabra.substring(i, (i + 1));
			if(!item.equals("."))
				nuevaPalabra += item;
			if (item.equals(caracter)) {
				if (!nuevaPalabra.equals(".") && !nuevaPalabra.equals("")) {
					parts.add(nuevaPalabra);
					nuevaPalabra = "";
				}
			}
		}
		if(!nuevaPalabra.equals(""))
			parts.add(nuevaPalabra);
		return parts;
	}

}
