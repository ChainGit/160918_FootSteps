package com.tgweb.struts2.day16;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class TestFileUploadAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private File file;
	private String fileContentType;
	private String fileFileName;
	private String desc;

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String upload() {
		return "upload-success";
	}

	public String upload2() {
		return "upload2-success";
	}

	public String doUpload() throws Exception {
		System.out.println(file + "," + fileFileName + "," + fileContentType + "," + desc);
		Long currentTime = System.currentTimeMillis();
		ServletContext servletContext = ServletActionContext.getServletContext();
		String storePath = servletContext
				.getRealPath(File.separator + "stores" + File.separator + fileFileName + "_" + currentTime);
		FileInputStream fis = new FileInputStream(file);
		FileOutputStream fos = new FileOutputStream(storePath);
		byte[] buf = new byte[1024 * 8];
		while (fis.read(buf) != -1)
			fos.write(buf);
		fos.flush();
		fis.close();
		fos.close();
		System.out.println(storePath);
		return "doUpload-success";
	}

}
