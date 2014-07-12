/**
 * 
 */
package com.cplsystems.stock.app.vm.controlpanel;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.GlobalCommand;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zul.Messagebox;

import com.cplsystems.stock.app.utils.UserPrivileges;
import com.cplsystems.stock.app.vm.controlpanel.utils.UsuariosClientesVariables;
import com.cplsystems.stock.domain.Privilegios;
import com.cplsystems.stock.domain.Usuarios;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class UsuariosClientesVM extends UsuariosClientesVariables {

	private static final long serialVersionUID = -6187792714156559485L;

	@Init
	public void init() {
		super.init();
	}

	@NotifyChange({ "usuarioSeleccionado", "privilegioRequision",
			"privilegioConcentrado", "privilegioCotizacionAutorizacion",
			"privilegioOrdenCompra" })
	@Command
	public void prepareUsuarioForEdition(@BindingParam("index") Integer index) {
		if (index != null) {
			usuarioSeleccionado = usuarios.get(index);
			privilegioRequision = false;
			privilegioConcentrado = false;
			privilegioCotizacionAutorizacion = false;
			privilegioOrdenCompra = false;
			for (Privilegios privilegio : usuarioSeleccionado.getPrivilegios()) {
				switch (privilegio.getUserPrivileges()) {
				case REQUISION:
					privilegioRequision = true;
					break;
				case CONCENTRAR:
					privilegioConcentrado = true;
					break;
				case COTIZAR_AUTORIZAR:
					privilegioCotizacionAutorizacion = true;
					break;
				case ORDEN_COMPRA:
					privilegioOrdenCompra = true;
					break;
				}
			}

		}
	}

	@NotifyChange({ "usuarios", "usuarioSeleccionado", "privilegioRequision",
			"privilegioConcentrado", "privilegioCotizacionAutorizacion",
			"privilegioOrdenCompra" })
	@Command
	public void saveChanges() {
		privilegioRequision = false;
		privilegioConcentrado = false;
		privilegioCotizacionAutorizacion = false;
		privilegioOrdenCompra = false;
		if (!usuarios.contains(usuarioSeleccionado)) {
			usuarios.add(usuarioSeleccionado);
		}
		for (Usuarios usuario : usuarios) {
			emailService.save(usuario.getPersona().getContacto().getEmail());
			contactoService.save(usuario.getPersona().getContacto());
			personaService.save(usuario.getPersona());
			usuarioService.save(usuario);
			for (Privilegios privilegios : usuario.getPrivilegios()) {
				privilegioService.save(privilegios);
			}
		}
		usuarioSeleccionado = new Usuarios();
	}

	@Command
	public void delete() {
		if (usuarioSeleccionado != null) {

		}
	}

	@NotifyChange({ "usuarioSeleccionado", "addConcentradosPrivilege" })
	@Command
	public void addRequisicionPrivilege() {

		if (privilegioRequision) {
			boolean agregarPrivilegio = true;
			for (Privilegios privilegio : usuarioSeleccionado.getPrivilegios()) {
				if (privilegio.getUserPrivileges().equals(
						UserPrivileges.REQUISION)) {
					agregarPrivilegio = false;
					break;
				}
			}
			if (agregarPrivilegio) {
				Privilegios privilegios = new Privilegios();
				privilegios.setUsuarios(usuarioSeleccionado);
				privilegios.setUserPrivileges(UserPrivileges.REQUISION);
				privilegios.setIcono(Privilegios.RQ_ICON);
				privilegios.setPathLocationModule(Privilegios.REQUISION);
				usuarioSeleccionado.getPrivilegios().add(privilegios);
			}
		} else {
			for (Privilegios privilegio : usuarioSeleccionado.getPrivilegios()) {
				if (privilegio.getUserPrivileges().equals(
						UserPrivileges.REQUISION)) {
					toRemove = privilegio;
					break;
				}
			}
			if (usuarioSeleccionado.getPrivilegios().contains(toRemove)) {
				if (toRemove.getIdPrivilegio() != null) {
					Messagebox.show(
							"¿Está seguro de remover este privilegio para "
									+ usuarioSeleccionado.getPersona()
											.getNombreCompleto() + "?",
							"Question", Messagebox.OK | Messagebox.CANCEL,
							Messagebox.QUESTION, new EventListener<Event>() {
								public void onEvent(Event event)
										throws Exception {
									if (((Integer) event.getData()).intValue() == Messagebox.OK) {
										privilegioService.delete(toRemove);
										usuarioSeleccionado.getPrivilegios()
												.remove(toRemove);
									} else {
										privilegioRequision = true;
									}
									BindUtils.postGlobalCommand(null, null,
											"refreshPrivilegios", null);
								}
							});
				} else {
					usuarioSeleccionado.getPrivilegios().remove(toRemove);
				}
			}
		}
	}

	@NotifyChange({ "usuarios", "usuarioSeleccionado", "privilegioRequision",
			"privilegioConcentrado", "privilegioCotizacionAutorizacion",
			"privilegioOrdenCompra" })
	@GlobalCommand
	public void refreshPrivilegios() {

	}

	@NotifyChange({ "usuarioSeleccionado", "addConcentradosPrivilege" })
	@Command
	public void addConcentradosPrivilege() {
		if (privilegioConcentrado) {
			boolean agregarPrivilegio = true;
			for (Privilegios privilegio : usuarioSeleccionado.getPrivilegios()) {
				if (privilegio.getUserPrivileges().equals(
						UserPrivileges.CONCENTRAR)) {
					agregarPrivilegio = false;
					break;
				}
			}
			if (agregarPrivilegio) {
				Privilegios privilegios = new Privilegios();
				privilegios.setUsuarios(usuarioSeleccionado);
				privilegios.setUserPrivileges(UserPrivileges.CONCENTRAR);
				privilegios.setIcono(Privilegios.CN_ICON);
				privilegios.setPathLocationModule(Privilegios.CONCENTRADO);
				usuarioSeleccionado.getPrivilegios().add(privilegios);
			}
		} else {
			for (Privilegios privilegio : usuarioSeleccionado.getPrivilegios()) {
				if (privilegio.getUserPrivileges().equals(
						UserPrivileges.CONCENTRAR)) {
					toRemove = privilegio;
					break;
				}
			}
			if (usuarioSeleccionado.getPrivilegios().contains(toRemove)) {
				if (toRemove.getIdPrivilegio() != null) {
					Messagebox.show(
							"¿Está seguro de remover este privilegio para "
									+ usuarioSeleccionado.getPersona()
											.getNombreCompleto() + "?",
							"Question", Messagebox.OK | Messagebox.CANCEL,
							Messagebox.QUESTION, new EventListener<Event>() {
								public void onEvent(Event event)
										throws Exception {
									if (((Integer) event.getData()).intValue() == Messagebox.OK) {
										privilegioService.delete(toRemove);
										usuarioSeleccionado.getPrivilegios()
												.remove(toRemove);
									} else {
										privilegioConcentrado = true;
									}
									BindUtils.postGlobalCommand(null, null,
											"refreshPrivilegios", null);
								}
							});
				} else {
					usuarioSeleccionado.getPrivilegios().remove(toRemove);
				}
			}
		}
	}

	@NotifyChange({ "usuarioSeleccionado", "addConcentradosPrivilege" })
	@Command
	public void addCotizaAutorizaPrivilege() {
		if (privilegioCotizacionAutorizacion) {
			boolean agregarPrivilegio = true;
			for (Privilegios privilegio : usuarioSeleccionado.getPrivilegios()) {
				if (privilegio.getUserPrivileges().equals(
						UserPrivileges.COTIZAR_AUTORIZAR)) {
					agregarPrivilegio = false;
					break;
				}
			}
			if (agregarPrivilegio) {
				Privilegios privilegios = new Privilegios();
				privilegios.setUsuarios(usuarioSeleccionado);
				privilegios.setUserPrivileges(UserPrivileges.COTIZAR_AUTORIZAR);
				privilegios.setIcono(Privilegios.CTAT_ICON);
				privilegios.setPathLocationModule(Privilegios.COTIZACION_AUTORIZACION);
				usuarioSeleccionado.getPrivilegios().add(privilegios);
			}
		} else {
			for (Privilegios privilegio : usuarioSeleccionado.getPrivilegios()) {
				if (privilegio.getUserPrivileges().equals(
						UserPrivileges.COTIZAR_AUTORIZAR)) {
					toRemove = privilegio;
					break;
				}
			}
			if (usuarioSeleccionado.getPrivilegios().contains(toRemove)) {
				if (toRemove.getIdPrivilegio() != null) {
					Messagebox.show(
							"¿Está seguro de remover este privilegio para "
									+ usuarioSeleccionado.getPersona()
											.getNombreCompleto() + "?",
							"Question", Messagebox.OK | Messagebox.CANCEL,
							Messagebox.QUESTION, new EventListener<Event>() {
								public void onEvent(Event event)
										throws Exception {
									if (((Integer) event.getData()).intValue() == Messagebox.OK) {
										privilegioService.delete(toRemove);
										usuarioSeleccionado.getPrivilegios()
												.remove(toRemove);
									} else {
										privilegioCotizacionAutorizacion = true;
									}
									BindUtils.postGlobalCommand(null, null,
											"refreshPrivilegios", null);
								}
							});
				} else {
					usuarioSeleccionado.getPrivilegios().remove(toRemove);
				}
			}
		}

	}

	@NotifyChange({ "usuarioSeleccionado", "addConcentradosPrivilege" })
	@Command
	public void addOrdenCompraPrivilege() {
		if (privilegioOrdenCompra) {
			boolean agregarPrivilegio = true;
			for (Privilegios privilegio : usuarioSeleccionado.getPrivilegios()) {
				if (privilegio.getUserPrivileges().equals(
						UserPrivileges.ORDEN_COMPRA)) {
					agregarPrivilegio = false;
					break;
				}
			}
			if (agregarPrivilegio) {
				Privilegios privilegios = new Privilegios();
				privilegios.setUsuarios(usuarioSeleccionado);
				privilegios.setUserPrivileges(UserPrivileges.ORDEN_COMPRA);
				privilegios.setIcono(Privilegios.OC_ICON);
				privilegios.setPathLocationModule(Privilegios.ORDEN_COMPRA);
				usuarioSeleccionado.getPrivilegios().add(privilegios);
			}
		} else {
			for (Privilegios privilegio : usuarioSeleccionado.getPrivilegios()) {
				if (privilegio.getUserPrivileges().equals(
						UserPrivileges.ORDEN_COMPRA)) {
					toRemove = privilegio;
					break;
				}
			}
			if (usuarioSeleccionado.getPrivilegios().contains(toRemove)) {
				if (toRemove.getIdPrivilegio() != null) {
					Messagebox.show(
							"¿Está seguro de remover este privilegio para "
									+ usuarioSeleccionado.getPersona()
											.getNombreCompleto() + "?",
							"Question", Messagebox.OK | Messagebox.CANCEL,
							Messagebox.QUESTION, new EventListener<Event>() {
								public void onEvent(Event event)
										throws Exception {
									if (((Integer) event.getData()).intValue() == Messagebox.OK) {
										privilegioService.delete(toRemove);
										usuarioSeleccionado.getPrivilegios()
												.remove(toRemove);
									} else {
										privilegioOrdenCompra = true;
									}
									BindUtils.postGlobalCommand(null, null,
											"refreshPrivilegios", null);
								}
							});
				} else {
					usuarioSeleccionado.getPrivilegios().remove(toRemove);
				}
			}
		}

	}
}
