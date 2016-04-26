package com.cplsystems.stock.domain;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.cplsystems.stock.app.utils.RODTreeNodeData;

public class FileBean extends RODTreeNodeData {
	private static final long serialVersionUID = 8667276657476276079L;

	private List<FileBean> _children;

	private File _file;
	private String _name;
	private String _path;
	private String _icon;

	// constructor
	public FileBean(File file) {
		_file = file;
		_name = file.getName();
		_path = file.getAbsolutePath();
	}

	// getter, setter
	public String getName() {
		return _name;
	}

	public String getPath() {
		return _path;
	}
	
	public File get_file() {
		return _file;
	}

	public String get_icon() {
		if (_file != null) {
			String extension = "";
			if (!_file.isDirectory()) {
				List<String> parts = getExtensionArchivo(_file.getName(), ".");
				if (parts.size() > 0) {
					extension = parts.get(parts.size() - 1);
					if (extension.equalsIgnoreCase("xlsx") || extension.equalsIgnoreCase("xls")) {
						_icon = "/images/toolbar/excel16.png";
					} else {
						_icon = "/images/toolbar/list16.png";
					}
				}
			} else {
				if(_file.getPath().length() == 3){
					_name = "Unidad C";
					_icon = "/images/toolbar/computer.png";
				}else
					_icon = "/images/toolbar/folder32.png";
			}
		}
		return _icon;
	}

	/**
	 * implement {@link RODTreeNodeData#getChildren()}
	 */
	public List<FileBean> getChildren() {
		if (_children == null) {
			_children = new ArrayList<FileBean>();
			File f = new File(_path);
			File[] filelist = f.listFiles();
			if (filelist != null) {
				for (File file : filelist) {
					_children.add(new FileBean(file));
				}
			}
		}
		return _children;
	}

	/**
	 * implement {@link RODTreeNodeData#getChildCount()}
	 */
	public int getChildCount() {
		int childCount = 0;
		if (_children != null) {
			childCount = _children.size();
		} else if (_file.isDirectory()) {
			File[] filelist = new File(_path).listFiles();
			childCount = filelist == null ? 0 : filelist.length;
		}
		return childCount;
	}
}