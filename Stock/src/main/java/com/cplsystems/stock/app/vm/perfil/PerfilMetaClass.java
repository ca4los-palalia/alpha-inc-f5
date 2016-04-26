package com.cplsystems.stock.app.vm.perfil;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;

import org.zkoss.bind.annotation.Init;

import com.cplsystems.stock.domain.Contacto;
import com.cplsystems.stock.domain.DevelopmentTool;
import com.cplsystems.stock.domain.Direccion;
import com.cplsystems.stock.domain.Email;
import com.cplsystems.stock.domain.Estado;
import com.cplsystems.stock.domain.Municipio;
import com.cplsystems.stock.domain.Persona;
import com.cplsystems.stock.domain.Usuarios;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.stream.JsonReader;

public abstract class PerfilMetaClass extends PerfilVariables {
	private static final long serialVersionUID = -2648381799748071319L;

	@Init
	public void init() {
		
	}

	@SuppressWarnings("static-access")
	public JsonElement inicializarConexionJsonUrl(String cp) {
		Calendar calendario = Calendar.getInstance();
		boolean continuar = false;
		int count = 0;	

		do {
			try {
				String uri = "https://api-codigos-postales.herokuapp.com/v2/codigo_postal/" + cp;
				URL url = new URL(uri);
				HttpURLConnection con = (HttpURLConnection) url.openConnection();
				System.err.println(calendario.getInstance().get(Calendar.HOUR_OF_DAY) + ":"
						+ calendario.getInstance().get(Calendar.MINUTE) + ":"
						+ calendario.getInstance().get(Calendar.SECOND) + " > Conectando a URL");
				JsonReader reader = new JsonReader(new InputStreamReader(con.getInputStream()));
				System.err.println(calendario.getInstance().get(Calendar.HOUR_OF_DAY) + ":"
						+ calendario.getInstance().get(Calendar.MINUTE) + ":"
						+ calendario.getInstance().get(Calendar.SECOND) + " > Haciendo Parseo");
				parser = new JsonParser();
				datosGlobalesJSON = parser.parse(reader);
				System.err.println(calendario.getInstance().get(Calendar.HOUR_OF_DAY) + ":"
						+ calendario.getInstance().get(Calendar.MINUTE) + ":"
						+ calendario.getInstance().get(Calendar.SECOND) + " > Extrayendo informacion");
				continuar = true;
			} catch (Exception e) {
				count++;
				System.err.println(calendario.getInstance().get(Calendar.HOUR_OF_DAY) + ":"
						+ calendario.getInstance().get(Calendar.MINUTE) + ":"
						+ calendario.getInstance().get(Calendar.SECOND) + " > " + e.getMessage()
						+ ": Retrying connection " + count);
			}
		} while (!continuar);

		
		return datosGlobalesJSON;
	}
	
	
	public void dumpProgramaJSONElement(JsonElement elemento) {
		if (elemento.isJsonObject()) {
			obj = elemento.getAsJsonObject();
			java.util.Set<java.util.Map.Entry<String, JsonElement>> entradas = obj.entrySet();
			java.util.Iterator<java.util.Map.Entry<String, JsonElement>> iter = entradas.iterator();
			while (iter.hasNext()) {
				java.util.Map.Entry<String, JsonElement> entrada = iter.next();

				keyJSon = entrada.getKey();
				valueJSon = entrada.getValue();
				direccionJSon = construirDireccionCodigoPostal(entrada.getKey(), entrada.getValue(), direccionJSon);
				dumpProgramaJSONElement(entrada.getValue());
			}

		} else if (elemento.isJsonArray()) {
			array = elemento.getAsJsonArray();
			java.util.Iterator<JsonElement> iter = array.iterator();
			while (iter.hasNext()) {
				JsonElement entrada = iter.next();
				dumpProgramaJSONElement(entrada);
			}
		} else if (elemento.isJsonPrimitive()) {
			valor = elemento.getAsJsonPrimitive();
			if (valor.isBoolean()) {
			} else if (valor.isNumber()) {
			} else if (valor.isString()) {
			}
		} else if (elemento.isJsonNull()) {
		} else {
		}
	}
	
	
	@SuppressWarnings("static-access")
	public Direccion construirDireccionCodigoPostal(String key, JsonElement value, Direccion direccionBuiding) {

		// String stringOut = new
		// DescuentosBanorteUtils().convertFromUTF8(String.valueOf(value));
		String stringOut = String.valueOf(value);
		stringOut = suprimirComillas(stringOut);
		stringOut = stockUtils.convertFromUTF8(stringOut);

		if (key.equals("codigo_postal")) {
			if (!stringOut.equals("\"\"")) {
				if(!stringOut.equals("")){
					direccionBuiding.setCp(stringOut);
					contadorCamposJson ++;
				}
				
			}
		}
		if (key.equals("municipio")) {
			if (!stringOut.equals("\"\"")) {
				if(!stringOut.equals("")){
					direccionBuiding.setMunicipio(getMunicipioFromListByName(stringOut));
					contadorCamposJson ++;
				}
			}
		}
		if (key.equals("estado")) {
			if (!stringOut.equals("\"\"")) {
				if(!stringOut.equals("")){
					if(stringOut.contains("Ciudad de M")){
						stringOut = "Distrito Federal";
					}
					direccionBuiding.setEstado(getEstadoFromListByName(stringOut));
					contadorCamposJson ++;	
				}
			}
		}
		if (key.equals("colonias")) {
			if (!stringOut.equals("\"\"")){
				
				String splitArray[] = stringOut.split(",");
				if(splitArray.length > 0){
					stringOut = suprimirComillas(splitArray[0]);
					direccionBuiding.setColonia(stringOut);
					contadorCamposJson ++;
				}
			}
		}
		
		return direccionBuiding;
	}
	
	public String validarFormularioUsuario(){
		String mensaje = "";
		
		if(!personaUsuario.getNombre().equals("")){
				
		}else
			mensaje = "El nombre de usuario es requerido";
		
		if(mensaje.equals("") && !personaUsuario.getApellidoPaterno().equals("")){
				
		}else
			mensaje = "El apellido paterno es requerido";
		
		if(mensaje.equals("") && !personaUsuario.getApellidoMaterno().equals("")){
				
		}else
			mensaje = "El apellido materno es requerido";
		
		if(mensaje.equals("") && !usuario.getBenutzer().equals("")){
			
		}else
			mensaje = "El nombre de usuario requerido";
		if(mensaje.equals("") && !usuario.getKennwort().equals("")){
			
		}else
			mensaje = "El contraseña requerida";
		
		
		
		
		
		
		return mensaje;
	}
	
	public String validarDireccionUsuario(){
		String mensaje = "";
		
		if(mensaje.equals("") &&  (direccionUsuario.getCalle() != null && !direccionUsuario.getCalle().equals(""))){
			
		}else if(mensaje.equals(""))
			mensaje = "Calle de usuario requerida";
		
		if(mensaje.equals("") &&  (direccionUsuario.getCp() != null && !direccionUsuario.getCp().equals(""))){
			
		}else if(mensaje.equals(""))
			mensaje = "Direccion postal del usuario requerida";
		
		if(mensaje.equals("") &&  (direccionUsuario.getNumExt() != null && !direccionUsuario.getNumExt().equals(""))){
			
		}else if(mensaje.equals(""))
			mensaje = "Numero exterior del usuario requerido";
		
		if(mensaje.equals("")){
			if(paisUsuario != null && (paisUsuario.getIdPais() != null && paisUsuario.getIdPais() > 0)){
				if(estadoUsuario != null && (estadoUsuario.getIdEstado() != null && estadoUsuario.getIdEstado() > 0)){
					if(municipioUsuario != null && (municipioUsuario.getIdMunicipio() != null && municipioUsuario.getIdMunicipio() > 0)){
					}else
						mensaje = "Municipio del usuario requerido";
				}else
					mensaje = "Estado del usuario requerido";
			}else
				mensaje = "Pais del usuario requerido";
		}
		return mensaje;
	}
	
	public String validarEnvioDeCorreo(DevelopmentTool dev, Email emailDevelopment){
		//---------------- testing funcionamiento del correo ----------------
		String mensajeEnvio = "";
		Contacto c = new Contacto();
		c.setEmail(emailDevelopment);
		Persona p = new Persona();
		p.setApellidoPaterno("Envio");
		p.setApellidoMaterno("Testing");
		p.setNombre("Prueba");
		p.setContacto(c);
		Usuarios u = new Usuarios();
		u.setPersona(p);
		
		Persona per = new Persona();
		per.setApellidoPaterno("HOSTING");
		per.setApellidoMaterno("SENDING");
		per.setNombre("TEST");
		per.setContacto(c);
		
		
		mensajeEnvio = sendEmail(u,
				per,
				"Testing de correo en funcionamiento",
				"Testeo de funcionamiento de correo",
				dev, null);
		//---------------- END testing funcionamiento del correo ----------------
		//https://www.google.com/settings/security/lesssecureapps
		return mensajeEnvio;
	}
	
}

