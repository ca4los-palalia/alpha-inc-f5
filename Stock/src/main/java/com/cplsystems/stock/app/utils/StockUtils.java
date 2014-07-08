/**
 * 
 */
package com.cplsystems.stock.app.utils;

import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Repository;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Window;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
@Repository
public class StockUtils {

	private static final String ALGORITHM = "md5";
	private static final String DIGEST_STRING = "HG58YZ3CR9";
	private static final String CHARSET_UTF_8 = "utf-8";
	private static final String SECRET_KEY_ALGORITHM = "DESede";
	private static final String TRANSFORMATION_PADDING = "DESede/CBC/PKCS5Padding";

	private DecimalFormat format = new DecimalFormat(
			StockConstants.CURRENCY_FORMAT);

	/**
	 * Create a window programmatically and use it as a modal dialog. eg
	 * /widgets/window/modal_dialog/employee_dialog.zul
	 */

	public Window createModelDialog(final String locationView) {
		Window window = (Window) Executions.createComponents(locationView,
				null, null);
		return window;
	}

	/**
	 * Create a window programmatically and use it as a modal dialog. eg
	 * /widgets/window/modal_dialog/employee_dialog.zul
	 */

	public Window createModelDialogWithParams(final String locationView,
			Map<String, Object> params) {
		Window window = (Window) Executions.createComponents(locationView,
				null, params);
		return window;
	}

	/** Redirect to a new web page eg /login.zul */
	public void redirect(final String page) {
		Executions.getCurrent().sendRedirect(page);
	}

	/**
	 * Notificador de mensajes en vista
	 * 
	 * @param Mensaje
	 * @param Clients
	 *            .NOTIFICATION_TYPE_INFO
	 */
	public static void showSuccessmessage(String mensaje, String tipo,
			Integer duracionEnVista, Component componente) {
		Clients.showNotification(mensaje, tipo, componente, null,
				duracionEnVista);
	}

	public String formatCurrency(Double quantity) {
		if (quantity != null) {
			return format.format(quantity);
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

	/* Encryption Method */
	public static String encrypt(String message) {
		try {
			final MessageDigest md = MessageDigest.getInstance(ALGORITHM);
			final byte[] digestOfPassword = md.digest(DIGEST_STRING
					.getBytes(CHARSET_UTF_8));
			final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
			for (int j = 0, k = 16; j < 8;) {
				keyBytes[k++] = keyBytes[j++];
			}

			final SecretKey key = new SecretKeySpec(keyBytes,
					SECRET_KEY_ALGORITHM);
			final IvParameterSpec iv = new IvParameterSpec(new byte[8]);
			final Cipher cipher = Cipher.getInstance(TRANSFORMATION_PADDING);
			cipher.init(Cipher.ENCRYPT_MODE, key, iv);

			final byte[] plainTextBytes = message.getBytes(CHARSET_UTF_8);
			final byte[] cipherText = cipher.doFinal(plainTextBytes);

			return new String(cipherText);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/* Decryption Method */
	public static String decrypt(String message) {
		try {
			final MessageDigest md = MessageDigest.getInstance(ALGORITHM);
			final byte[] digestOfPassword = md.digest(DIGEST_STRING
					.getBytes(CHARSET_UTF_8));
			final byte[] keyBytes = Arrays.copyOf(digestOfPassword, 24);
			for (int j = 0, k = 16; j < 8;) {
				keyBytes[k++] = keyBytes[j++];
			}

			final SecretKey key = new SecretKeySpec(keyBytes,
					SECRET_KEY_ALGORITHM);
			final IvParameterSpec iv = new IvParameterSpec(new byte[8]);
			final Cipher decipher = Cipher.getInstance(TRANSFORMATION_PADDING);
			decipher.init(Cipher.DECRYPT_MODE, key, iv);

			final byte[] plainText = decipher.doFinal(message.getBytes());

			return new String(plainText, CHARSET_UTF_8);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
