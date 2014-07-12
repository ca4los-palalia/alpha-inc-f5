/**
 * 
 */
package com.cplsystems.stock.app.vm.controlpanel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.zkoss.bind.BindContext;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.image.Image;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Window;

import com.cplsystems.stock.app.utils.StockConstants;
import com.cplsystems.stock.app.utils.StockUtils;
import com.cplsystems.stock.app.vm.controlpanel.utils.UsuarioVariables;
import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.Persona;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class UsuarioVM extends UsuarioVariables {

	private static final long serialVersionUID = -6605100514948939575L;

	@Init
	public void init() {
		super.init();
	}

	@NotifyChange("*")
	@Command
	public void saveChanges() {
		if (organizacion.getIdOrganizacion() == null) {
			Persona persona = new Persona();
			persona.setNombre(organizacion.getNombre() + " Responsable");
			personaService.save(persona);			
		}
		organizacionService.save(organizacion);
		super.init();
		StockUtils.showSuccessmessage(
				"La información se ha guardado correctamente",
				Clients.NOTIFICATION_TYPE_INFO, 1000, null);
	}

	@Command
	@NotifyChange("*")
	public void uploadProfilePicture(
			@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) {
		UploadEvent event = (UploadEvent) ctx.getTriggerEvent();
		Media mediaObj = event.getMedia();
		if (!(mediaObj instanceof Image)) {
			return;
		}
		Image imageRetrieved = (Image) mediaObj;

		try {
			imageInBytes = imageRetrieved.getByteData();
			imageFormat = imageRetrieved.getFormat();
			File perfilPictureFile = new File("C://" + organizacion.getNombre()
					+ ".png");
			FileOutputStream fos = new FileOutputStream(perfilPictureFile);
			fos.write(imageInBytes);
			fos.close();
			organizacion.setLogotipo(perfilPictureFile.getPath());/*
																 * businessImage
																 * = ImageUtils.
																 * scaleToSize
																 * (imageInBytes
																 * , 250, 400,
																 * imageFormat
																 * ).getContent
																 * ();
																 */
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Command
	public void buscarOrganizacion() {
		Window companiaDialog = stockUtils
				.createModelDialog(StockConstants.GLOBAL_PAGES.MODAL_VIEW_COMPANIA);
		companiaDialog.doModal();
	}

	@GlobalCommand
	@NotifyChange({ "*" })
	public void loadDatosCompania(
			@BindingParam("organizacionSeleccionada") Organizacion organizacionSeleccionada) {
		if (organizacionSeleccionada != null) {
			organizacion = organizacionSeleccionada;
			try {
				if (organizacion.getLogotipo() == null) {
					return;
				}
				File picture = new File(organizacion.getLogotipo());/*
																	 * imageInBytes
																	 * =
																	 * incidenciasUtils
																	 * .
																	 * readFileToByteArray
																	 * (
																	 * picture);
																	 * businessImage
																	 * =
																	 * ImageUtils
																	 * .
																	 * scaleToSize
																	 * (
																	 * imageInBytes
																	 * , 250,
																	 * 400,
																	 * "png"
																	 * ).getContent
																	 * ();
																	 */
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

}
