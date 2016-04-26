package com.cplsystems.stock.app.vm.controlpanel;

import java.util.ArrayList;

import org.zkoss.bind.annotation.BindingParam;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;

import com.cplsystems.stock.app.utils.StockUtils;
import com.cplsystems.stock.app.vm.controlpanel.utils.ControlPanelMetaclass;
import com.cplsystems.stock.domain.Contrato;
import com.cplsystems.stock.domain.Organizacion;
import com.cplsystems.stock.domain.Usuarios;

@VariableResolver({ DelegatingVariableResolver.class })
public class CatalogoContratoVM extends ControlPanelMetaclass {
	private static final long serialVersionUID = -8141487067470696501L;
	
	
	@Init
	public void init() {
		contratos = contratoService.getAll();
		usuario = (Usuarios) sessionUtils.getFromSession("usuario");
		organizacion = (Organizacion) sessionUtils.getFromSession("FIRMA");
	}

	@Command
	@NotifyChange({ "*" })
	public void save() {
		guardarContratos();
	}


	@Command
	@NotifyChange({ "*" })
	public void eliminarContrato(@BindingParam("index") Integer index) {
		contrato = ((Contrato) contratos.get(index.intValue()));
		boolean continuarEliminacion = true;
		if (contratos != null) {
			try {
				contratoService.delete(contrato);
			} catch (Exception e) {
				continuarEliminacion = false;
			}
			if (continuarEliminacion) {
				contratos.clear();
				contratos = contratoService.getAll();

				StockUtils.showSuccessmessage("El contrato -" + contrato.getNombre() + "- ha sido eliminado", "info",
						Integer.valueOf(0), null);
			} else {
				StockUtils.showSuccessmessage(
						"El Contrato -" + contrato.getNombre() + "- esta siendo utilizado. No puede ser eliminado",
						"error", Integer.valueOf(0), null);
			}
		} else {
			StockUtils.showSuccessmessage("Debe seleccionar un contrato para proceder con la eliminaci�n", "warning",
					Integer.valueOf(0), null);
		}
	}

	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Command
	@NotifyChange({ "*" })
	public void agregarNuevoContrato() {
		if (contratos == null) {
			contratos = new ArrayList();
		}
		contratos.add(crearColumnaVaciaContrato());
		mensajeDeCambios = "No olvide salvar sus cambios";
	}
}
