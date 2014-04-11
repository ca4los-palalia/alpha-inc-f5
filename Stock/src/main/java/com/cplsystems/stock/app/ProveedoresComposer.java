/**
 * 
 */
package com.cplsystems.stock.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.zkoss.bind.annotation.Command;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zul.impl.MessageboxDlg.Button;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class ProveedoresComposer {

	@Autowired
	private Button resetButton;
	
	
	@Command
    public void changeSubmitStatus(){
        System.err.println("CLICK desde la vista");
    }

}
