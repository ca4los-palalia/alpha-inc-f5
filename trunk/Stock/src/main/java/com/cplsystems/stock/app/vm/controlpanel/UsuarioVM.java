/**
 * 
 */
package com.cplsystems.stock.app.vm.controlpanel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.zkoss.bind.BindContext;
import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.image.Image;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

import com.cplsystems.stock.app.utils.StockConstants;
import com.cplsystems.stock.app.utils.StockUtils;
import com.cplsystems.stock.app.vm.controlpanel.utils.UsuarioVariables;
import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.Persona;
import com.cplsystems.stock.domain.Privilegios;
import com.cplsystems.stock.domain.Usuarios;

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
	public void nuevaOrganizacion() {
		super.init();
	}

	@NotifyChange("*")
	@Command
	public void saveChanges() {
		Persona persona = null;
		if (organizacion.getIdOrganizacion() == null) {
			persona = new Persona();
			persona.setNombre(organizacion.getNombre() + " Responsable");

			usuario.setPersona(persona);
			usuario.setOwner(false);
			usuario.setClient(true);

		}
		organizacionService.save(organizacion);
		personaService.save(usuario.getPersona());
		usuarioService.save(usuario);
		super.init();
		StockUtils.showSuccessmessage(
				"La información se ha guardado correctamente",
				Clients.NOTIFICATION_TYPE_INFO, 1000, null);
	}

	@Command
	public void delete() {
		if (organizacion != null && organizacion.getIdOrganizacion() != null) {
			Messagebox
					.show("¿Está seguro de remover a "
							+ organizacion.getNombre()
							+ "?. Esta acción es irreversible y removerá toda la información relacionada con "
							+ "usuarios, requisiciones, cotizaciones, etc. ",
							"Question", Messagebox.OK | Messagebox.CANCEL,
							Messagebox.QUESTION, new EventListener<Event>() {
								public void onEvent(Event event)
										throws Exception {
									if (((Integer) event.getData()).intValue() == Messagebox.OK) {
										Usuarios client = usuarioService
												.getClienteByOrganizacion(organizacion);
										if (client != null) {
											List<Usuarios> usuarios = usuarioService
													.getUsuariosByOrganizacion(organizacion);
											if (usuarios != null) {
												for (Usuarios toRemove : usuarios) {
													for (Privilegios privilegios : toRemove
															.getPrivilegios()) {
														privilegioService
																.delete(privilegios);
													}
													usuarioService
															.delete(toRemove);
													personaService.delete(toRemove
															.getPersona());
													contactoService
															.delete(toRemove
																	.getPersona()
																	.getContacto());
													emailService
															.delete(toRemove
																	.getPersona()
																	.getContacto()
																	.getEmail());
													usuarios.remove(toRemove);
													organizacionService
															.delete(organizacion);
												}
											} else {
												usuarioService.delete(client);
												organizacionService
														.delete(organizacion);
												UsuarioVM.this.init();
											}

											BindUtils.postGlobalCommand(null,
													null,
													"refreshOrganizaciones",
													null);
										} else {
											StockUtils
													.showSuccessmessage(
															"Esta cuenta no puede ser eliminada "
																	+ "ya que posee todos los privilegios del sistema",
															Clients.NOTIFICATION_TYPE_ERROR,
															3500, null);
										}
									}
								}
							});

		} else {
			StockUtils
					.showSuccessmessage(
							"Esta cuenta no puede ser eliminada ya que aún no ha sido registrada ",
							Clients.NOTIFICATION_TYPE_ERROR, 3500, null);
		}
	}

	@GlobalCommand
	@NotifyChange("*")
	public void refreshOrganizaciones() {

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
			usuario = usuarioService.getClienteByOrganizacion(organizacion);
			if (usuario == null) {
				usuario = usuarioService.getOwner(organizacion);
			}
			if (usuario != null) {
				usuario.setRetypeKennwort(usuario.getKennwort());
			}

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
