package com.cplsystems.stock.app.vm.controlpanel;

import com.cplsystems.stock.app.utils.StockUtils;
import com.cplsystems.stock.app.vm.controlpanel.utils.UsuarioVariables;
import com.cplsystems.stock.domain.Contacto;
import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.Persona;
import com.cplsystems.stock.domain.Privilegios;
import com.cplsystems.stock.domain.Usuarios;
import com.cplsystems.stock.services.ContactoService;
import com.cplsystems.stock.services.EmailService;
import com.cplsystems.stock.services.OrganizacionService;
import com.cplsystems.stock.services.PersonaService;
import com.cplsystems.stock.services.PrivilegioService;
import com.cplsystems.stock.services.UsuarioService;
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
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

@VariableResolver({ DelegatingVariableResolver.class })
public class UsuarioVM extends UsuarioVariables {
	private static final long serialVersionUID = -6605100514948939575L;

	@Init
	public void init() {
		super.init();
	}

	@NotifyChange({ "*" })
	@Command
	public void nuevaOrganizacion() {
		super.init();
	}

	@NotifyChange({ "*" })
	@Command
	public void saveChanges() {
		Persona persona = null;
		if (this.organizacion.getIdOrganizacion() == null) {
			persona = new Persona();
			persona.setNombre(this.organizacion.getNombre() + " Responsable");

			this.usuario.setPersona(persona);
			this.usuario.setOwner(Boolean.valueOf(false));
			this.usuario.setClient(Boolean.valueOf(true));
		}
		this.organizacionService.save(this.organizacion);
		this.personaService.save(this.usuario.getPersona());
		this.usuarioService.save(this.usuario);
		super.init();
		StockUtils.showSuccessmessage("La informaci�n se ha guardado correctamente", "info", Integer.valueOf(1000),
				null);
	}

	@Command
	public void delete() {
		if ((this.organizacion != null) && (this.organizacion.getIdOrganizacion() != null)) {
			Messagebox.show(
					"�Est� seguro de remover a " + this.organizacion.getNombre()
							+ "?. Esta acci�n es irreversible y remover� toda la informaci�n relacionada con "
							+ "usuarios, requisiciones, cotizaciones, etc. ",
					"Question", 3, "z-msgbox z-msgbox-question", new EventListener() {
						public void onEvent(Event event) throws Exception {
							if (((Integer) event.getData()).intValue() == 1) {
								Usuarios client = UsuarioVM.this.usuarioService
										.getClienteByOrganizacion(UsuarioVM.this.organizacion);
								if (client != null) {
									List<Usuarios> usuarios = UsuarioVM.this.usuarioService
											.getUsuariosByOrganizacion(UsuarioVM.this.organizacion);
									if (usuarios != null) {
										for (Usuarios toRemove : usuarios) {
											for (Privilegios privilegios : toRemove.getPrivilegios()) {
												UsuarioVM.this.privilegioService.delete(privilegios);
											}
											UsuarioVM.this.usuarioService.delete(toRemove);

											UsuarioVM.this.personaService.delete(toRemove.getPersona());

											UsuarioVM.this.contactoService.delete(toRemove.getPersona().getContacto());

											UsuarioVM.this.emailService
													.delete(toRemove.getPersona().getContacto().getEmail());

											usuarios.remove(toRemove);
											UsuarioVM.this.organizacionService.delete(UsuarioVM.this.organizacion);
										}
									} else {
										UsuarioVM.this.usuarioService.delete(client);
										UsuarioVM.this.organizacionService.delete(UsuarioVM.this.organizacion);

										UsuarioVM.this.init();
									}
									BindUtils.postGlobalCommand(null, null, "refreshOrganizaciones", null);
								} else {
									StockUtils.showSuccessmessage(
											"Esta cuenta no puede ser eliminada ya que posee todos los privilegios del sistema",
											"error", Integer.valueOf(3500), null);
								}
							}
						}
					});
		} else {
			StockUtils.showSuccessmessage("Esta cuenta no puede ser eliminada ya que a�n no ha sido registrada ",
					"error", Integer.valueOf(3500), null);
		}
	}

	@GlobalCommand
	@NotifyChange({ "*" })
	public void refreshOrganizaciones() {
	}

	@Command
	@NotifyChange({ "*" })
	public void uploadProfilePicture(@ContextParam(ContextType.BIND_CONTEXT) BindContext ctx) {
		UploadEvent event = (UploadEvent) ctx.getTriggerEvent();
		Media mediaObj = event.getMedia();
		if (!(mediaObj instanceof Image)) {
			return;
		}
		Image imageRetrieved = (Image) mediaObj;
		try {
			this.imageInBytes = imageRetrieved.getByteData();
			this.imageFormat = imageRetrieved.getFormat();
			File perfilPictureFile = new File("C://" + this.organizacion.getNombre() + ".png");

			FileOutputStream fos = new FileOutputStream(perfilPictureFile);
			fos.write(this.imageInBytes);
			fos.close();
			//organizacion.setLogotipo(perfilPictureFile.getPath());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Command
	public void buscarOrganizacion() {
		Window companiaDialog = this.stockUtils
				.createModelDialog("/modulos/controlPanel/utils/buscarOrganizaciones.zul");

		companiaDialog.doModal();
	}

	@GlobalCommand
	@NotifyChange({ "*" })
	public void loadDatosCompania(@BindingParam("organizacionSeleccionada") Organizacion organizacionSeleccionada,
			@BindingParam("usuariosOrganizacion") List<Usuarios> usuariosOrganizacion) {
		if (organizacionSeleccionada != null) {
			this.usuarios = usuariosOrganizacion;
			this.organizacion = organizacionSeleccionada;

			this.usuario = this.usuarioService.getClienteByOrganizacion(this.organizacion);
			if (this.usuario == null) {
				this.usuario = this.usuarioService.getOwner(this.organizacion);
			}
			if (this.usuario != null) {
				this.usuario.setRetypeKennwort(this.usuario.getKennwort());
			}
			if (isPropietario(this.usuarios)) {
				this.deshabiliraRadioButton = true;
			} else {
				this.deshabiliraRadioButton = false;
			}
			try {
				if (this.organizacion.getLogotipo() == null) {
					return;
				}
				//picture = new File(this.organizacion.getLogotipo());
			} catch (Exception e) {
				File picture;
				e.printStackTrace();
			}
		}
	}

	private boolean isPropietario(List<Usuarios> usuariosOrganizacion) {
		boolean owner = false;
		for (Usuarios usuarioLoop : usuariosOrganizacion) {
			if (usuarioLoop.getOwner().booleanValue()) {
				owner = true;
				break;
			}
		}
		return owner;
	}

	@Command
	public void cambiarAdministrador(@BindingParam("index") Integer index) {
		for (Usuarios loop : this.usuarios) {
			if (loop.getClient().booleanValue()) {
				loop.setClient(Boolean.valueOf(false));
				this.usuarioService.save(loop);
				break;
			}
		}
		Usuarios usuarioSalvar = (Usuarios) this.usuarios.get(index.intValue());
		this.usuarioService.save(usuarioSalvar);
		StockUtils.showSuccessmessage("El administrador ha sido cambiado", "info", Integer.valueOf(0), null);
	}
}
