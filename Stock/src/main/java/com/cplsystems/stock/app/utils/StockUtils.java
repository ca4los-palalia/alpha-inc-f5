package com.cplsystems.stock.app.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Repository;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Window;

@Repository
public class StockUtils {
	private static final String ALGORITHM = "md5";
	private static final String DIGEST_STRING = "HG58YZ3CR9";
	private static final String CHARSET_UTF_8 = "utf-8";
	private static final String SECRET_KEY_ALGORITHM = "DESede";
	private static final String TRANSFORMATION_PADDING = "DESede/CBC/PKCS5Padding";
	private DecimalFormat format = new DecimalFormat("###,###,###.00");

	public Window createModelDialog(String locationView) {
		Window window = (Window) Executions.createComponents(locationView, null, null);

		return window;
	}

	public Window createModelDialogWithParams(String locationView, Map<String, Object> params) {
		Window window = (Window) Executions.createComponents(locationView, null, params);

		return window;
	}

	public void redirect(String page) {
		Executions.getCurrent().sendRedirect(page);
	}

	public static void showSuccessmessage(String mensaje, String tipo, Integer duracionEnVista, Component componente) {
		Clients.showNotification(mensaje, tipo, componente, null, duracionEnVista.intValue());
	}

	public String formatCurrency(Double quantity) {
		if (quantity != null) {
			return this.format.format(quantity);
		}
		return null;
	}

	public Calendar convertirDateToCalendar(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return calendar;
	}

	public Date convertirCalendarToDate(Calendar calendar) {
		Date date = calendar.getTime();
		return date;
	}

	public String convertirCalendarToString(Calendar calendar) {
		Date date = convertirCalendarToDate(calendar);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		return dateFormat.format(date);
	}

	public Calendar convertirStringToCalendar(Integer dia, Integer mes, Integer anyo) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(1, anyo.intValue());
		calendar.set(2, mes.intValue() - 1);
		calendar.set(5, dia.intValue());
		return calendar;
	}

	public static String encrypt(String message) {

		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			byte[] digestOfPassword = md.digest("HG58YZ3CR9".getBytes("utf-8"));

			byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
			int j = 0;
			for (int k = 16; j < 8;) {
				keyBytes[(k++)] = keyBytes[(j++)];
			}
			SecretKey key = new SecretKeySpec(keyBytes, "DESede");

			IvParameterSpec iv = new IvParameterSpec(new byte[8]);
			Cipher cipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
			cipher.init(1, key, iv);

			byte[] plainTextBytes = message.getBytes("utf-8");
			byte[] cipherText = cipher.doFinal(plainTextBytes);

			return new String(cipherText);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String decrypt(String message) {
		try {
			MessageDigest md = MessageDigest.getInstance("md5");
			byte[] digestOfPassword = md.digest("HG58YZ3CR9".getBytes("utf-8"));

			byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
			int j = 0;
			for (int k = 16; j < 8;) {
				keyBytes[(k++)] = keyBytes[(j++)];
			}
			SecretKey key = new SecretKeySpec(keyBytes, "DESede");

			IvParameterSpec iv = new IvParameterSpec(new byte[8]);
			Cipher decipher = Cipher.getInstance("DESede/CBC/PKCS5Padding");
			decipher.init(2, key, iv);

			byte[] plainText = decipher.doFinal(message.getBytes());

			return new String(plainText, "utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String getFechaActualConHora() {
		Date date = new Date();
		DateFormat hourdateFormat = new SimpleDateFormat("yyyy-MM-dd HH+mm+ss");
		return hourdateFormat.format(date);
	}

	public FileInputStream getLogotipoDeOrganizacionParaJasper(String nombreAtchivo) {
		File archivoLogotipo = new File(StockConstants.CARPETA_ARCHIVOS_LOGOTIPOS + nombreAtchivo);

		FileInputStream streamLogotipo = null;
		if (archivoLogotipo.exists()) {
			try {
				streamLogotipo = new FileInputStream(archivoLogotipo);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
		return streamLogotipo;
	}

	// Convierte texto de ISO-8859-1 a UTF-8
	public static String convertFromUTF8(String s) {
		String out = null;
		try {
			out = new String(s.getBytes("ISO-8859-1"), "UTF-8");
		} catch (java.io.UnsupportedEncodingException e) {
			return null;
		}
		return out;
	}

	// Convierte texto de UTF-8 a ISO-8859-1
	public static String convertToUTF8(String s) {
		String out = null;
		try {
			out = new String(s.getBytes("UTF-8"), "ISO-8859-1");
		} catch (java.io.UnsupportedEncodingException e) {
			return null;
		}
		return out;
	}

	// ----------------------------------------------------

	public String Encriptar(String texto) {

		String secretKey = "qualityinfosolutions"; // llave para encriptar datos
		String base64EncryptedString = "";

		try {

			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
			byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);

			SecretKey key = new SecretKeySpec(keyBytes, "DESede");
			Cipher cipher = Cipher.getInstance("DESede");
			cipher.init(Cipher.ENCRYPT_MODE, key);

			byte[] plainTextBytes = texto.getBytes("utf-8");
			byte[] buf = cipher.doFinal(plainTextBytes);
			byte[] base64Bytes = Base64.encodeBase64(buf);
			base64EncryptedString = new String(base64Bytes);

		} catch (Exception ex) {
		}
		return base64EncryptedString;
	}

	public String Desencriptar(String textoEncriptado) throws Exception {

		String secretKey = "qualityinfosolutions"; // llave para desenciptar
													// datos
		String base64EncryptedString = "";

		try {
			byte[] message = Base64.decodeBase64(textoEncriptado.getBytes("utf-8"));
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] digestOfPassword = md.digest(secretKey.getBytes("utf-8"));
			byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
			SecretKey key = new SecretKeySpec(keyBytes, "DESede");

			Cipher decipher = Cipher.getInstance("DESede");
			decipher.init(Cipher.DECRYPT_MODE, key);

			byte[] plainText = decipher.doFinal(message);

			base64EncryptedString = new String(plainText, "UTF-8");

		} catch (Exception ex) {
		}
		return base64EncryptedString;
	}

}
