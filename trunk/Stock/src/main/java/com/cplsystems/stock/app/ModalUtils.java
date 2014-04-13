/**
 * 
 */
package com.cplsystems.stock.app;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zul.Window;

/**
 * @author Carlos Palalia López
 *
 */
public class ModalUtils   extends SelectorComposer<Component> {

	
	private static final long serialVersionUID = 7430264817059488060L;

	@Listen("onClick = #dirButton")
    public void showModal(Event e) {
        //create a window programmatically and use it as a modal dialog.
        Window window = (Window)Executions.createComponents(
                "/modulos/ShowModalDetails.zul", null, null);
        window.doModal();
    }
}
