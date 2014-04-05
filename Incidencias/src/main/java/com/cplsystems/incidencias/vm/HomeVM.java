/**
 * 
 */
package com.cplsystems.incidencias.vm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.Init;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Include;
import org.zkoss.zul.Menubar;
import org.zkoss.zul.Panelchildren;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Toolbar;

/**
 * @author César Palalía López (csr.plz@aisa-automation.com)
 * 
 */
@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class HomeVM {

	private Logger log = LogManager.getLogger(this.getClass().getName());

	@Wire
	private Panelchildren contenedor;
	@Wire
	private Menubar menubar;
	@Wire
	private Toolbar toolbar;

	@Init
	public void init(@ContextParam(ContextType.DESKTOP) final Desktop desktop,
			@ContextParam(ContextType.SESSION) final Session session) {

		/*
		 * sessionUtils.getFromSession(SessionUtils.USUARIO);
		 * sessionUtils.getFromSession(SessionUtils.NEGOCIO);
		 */
	}

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		// Attach .zul components for fulfill navigation depending on
		// permissions
		attachComponents(view);


		// Add default permissions, the ones that everybody has
		//addDefaultModules(userModules);
		// Add module Comprobante only if the user has one of their children
		//searchForComprobante(userModules);

		// Fill the navigation components
		//fillNavigationComponents(userModules, variables);

		// Fill current user modules
		//fillModulesComponents(userModules, variables);

		// Show welcome screen
		//changeModule(NavInfrastructure.KEY_BIENVENIDO);
	}

	private void attachComponents(Component view) {
		Selectors.wireComponents(view, this, false);
	}

	private void addDefaultModules(final List<String> userModules) {/*
		userModules.add(NavInfrastructure.KEY_BIENVENIDO);
		userModules.add(NavInfrastructure.KEY_PERFIL);
		userModules.add(NavInfrastructure.KEY_NEGOCIO);*/
	}

	private void fillNavigationComponents(final List<String> userModules,
			final Map<String, Object> args) {
/*
		List<Integer> modulesPositions = new ArrayList<Integer>(
				NavInfrastructure.POSITION_KEY_MODULE.keySet());
		Collections.sort(modulesPositions);
		for (Integer currentPosition : modulesPositions) {
			String currentModuleKey = NavInfrastructure.POSITION_KEY_MODULE
					.get(currentPosition);
			if (NavInfrastructure.KEY_MODULE_NAVIGATION_COMPONENT
					.containsKey(currentModuleKey)
					&& userModules.contains(currentModuleKey)) {
				Include navComponent = new Include(
						NavInfrastructure.KEY_MODULE_NAVIGATION_COMPONENT
								.get(currentModuleKey));
				navigationPanel.appendChild(navComponent);
			}
		}*/
	}

	private void fillModulesComponents(final List<String> userModules,
			final Map<String, Object> args) {
		/*
		for (String currentKey : NavInfrastructure.KEY_MODULE_COMPONENT
				.keySet()) {
			if (userModules.contains(currentKey)) {
				Tab tab = new Tab();
				tab.setLabel("");
				tab.setStyle("visibility:hidden");
				tab.setZclass("tabs");
				modules.add(currentKey);
				tbxNavegacion.getTabs().appendChild(tab);
				Tabpanel tabPanel = new Tabpanel();
				tabPanel.setZclass("tabpanel");
				Include tabContent = new Include(
						NavInfrastructure.KEY_MODULE_COMPONENT.get(currentKey));
				tabPanel.appendChild(tabContent);
				tbxNavegacion.getTabpanels().appendChild(tabPanel);
			}
		}*/
	}

}
