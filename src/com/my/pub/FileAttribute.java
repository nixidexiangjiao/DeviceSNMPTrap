package com.my.pub;

import java.util.List;

/**
 * FTP文件属性
 * @author Administrator
 *
 */
public class FileAttribute {
	private String filename;	//文件名
	private long size;			//大小
	private boolean isdir;		//目录标志
	private List<FileAttribute> children;
	private boolean isForward = false;
	
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public long getSize() {
		return size;
	}
	public void setSize(long size) {
		this.size = size;
	}
	public boolean isDir() {
		return isdir;
	}
	public void setIsdir(boolean isdir) {
		this.isdir = isdir;
	}
	public List<FileAttribute> getChildren() {
		return children;
	}
	public void setChildren(List<FileAttribute> children) {
		this.children = children;
	}
	public boolean isForward() {
		return isForward;
	}
	public void setForward(boolean isForward) {
		this.isForward = isForward;
	}
}
