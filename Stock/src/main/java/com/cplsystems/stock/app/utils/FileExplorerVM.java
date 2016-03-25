package com.cplsystems.stock.app.utils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.zkoss.bind.BindUtils;
import org.zkoss.bind.annotation.AfterCompose;
import org.zkoss.bind.annotation.Command;
import org.zkoss.bind.annotation.ContextParam;
import org.zkoss.bind.annotation.ContextType;
import org.zkoss.bind.annotation.ExecutionArgParam;
import org.zkoss.bind.annotation.Init;
import org.zkoss.bind.annotation.NotifyChange;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.SelectEvent;
import org.zkoss.zk.ui.select.Selectors;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zkplus.spring.DelegatingVariableResolver;
import org.zkoss.zul.TreeModel;
import org.zkoss.zul.Window;

import com.cplsystems.stock.app.vm.BasicStructure;
import com.cplsystems.stock.domain.FileBean;

@VariableResolver({ DelegatingVariableResolver.class })
public class FileExplorerVM extends BasicStructure {
	private static final long serialVersionUID = 3098239433101641553L;
	@Wire("#fileModalDialog")
	private Window filesModalDialog;
	private String globalCommandName;
	
	
	
	RODTreeModel<FileBean> _directoryTreeModel;
    FileBean _selectedDirectory;
    File file = new File("C:" + File.separator);
	
	
	
	@Init
	public void init(@ContextParam(ContextType.VIEW) Component view,
			@ExecutionArgParam("itemFinder") String itemFinder) {
		Selectors.wireComponents(view, this, false);
		globalCommandName = itemFinder;
		
	}
	
	//-----------------------------------------------------------

	@AfterCompose
	public void afterCompose(@ContextParam(ContextType.VIEW) Component view) {
		Selectors.wireComponents(view, this, false);
	}

	@NotifyChange({ "productos" })
	@Command
	public void searchItemByKeyOrName() {
		
	}

	@Command
	public void transferProduct() {
		
		if (_selectedDirectory != null) {
			filesModalDialog.detach();
			Map<String, Object> args = new HashMap();
			args.put("fileSelected", _selectedDirectory);
			if ((globalCommandName != null) && (!globalCommandName.isEmpty())) {
				BindUtils.postGlobalCommand(null, null, globalCommandName, args);
			} else {
				BindUtils.postGlobalCommand(null, null, "updateFromSelectedItem", args);
			}
		}
		
	}

	@Command
	public void closeDialog() {
		if (filesModalDialog != null) {
			filesModalDialog.detach();
		}
	}

	
	
	

	
	//--------------------------------------------------------------------------------
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
    public TreeModel<RODTreeNode<FileBean>> getDirectoryModel () {
        RODTreeNode root = new RODTreeNode(null,
                new RODTreeNode[] {new RODTreeNode(new FileBean(file), (List)null)
        });
        if (_directoryTreeModel == null) {
            _directoryTreeModel = new RODTreeModel<FileBean>(root);
        }
        return _directoryTreeModel;
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Command
    @NotifyChange({ "_selectedDirectory" })
    public void updateSelectedDirectory (@ContextParam(ContextType.TRIGGER_EVENT) SelectEvent event) {
        Set s = event.getSelectedObjects();
        if (s != null && s.size() > 0) {
            _selectedDirectory = ((RODTreeNode<FileBean>)s.iterator().next()).getData();
            System.out.println("selected: " + _selectedDirectory.getName() + ", path = " + _selectedDirectory.getPath());
        }
    }

	public FileBean get_selectedDirectory() {
		return _selectedDirectory;
	}
    
    
}
