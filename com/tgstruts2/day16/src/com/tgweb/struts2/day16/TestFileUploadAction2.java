package com.tgweb.struts2.day16;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class TestFileUploadAction2 extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private List<File> file;
	private List<String> fileContentType;
	private List<String> fileFileName;
	private List<String> desc;

	public List<File> getFile() {
		return file;
	}

	public void setFile(List<File> file) {
		this.file = file;
	}

	public List<String> getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(List<String> fileContentType) {
		this.fileContentType = fileContentType;
	}

	public List<String> getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(List<String> fileFileName) {
		this.fileFileName = fileFileName;
	}

	public List<String> getDesc() {
		return desc;
	}

	public void setDesc(List<String> desc) {
		this.desc = desc;
	}

	public String doUpload() throws Exception {
		ServletContext servletContext = ServletActionContext.getServletContext();
		int len = file.size();
		for (int i = 0; i < len; i++) {
			File pFile = file.get(i);
			String pFileFileName = fileFileName.get(i);
			String pFileContentType = fileContentType.get(i);
			String pDesc = desc.get(i);
			System.out.println(pFile + "," + pFileFileName + "," + pFileContentType + "," + pDesc);
			Long currentTime = System.currentTimeMillis();
			String storePath = servletContext
					.getRealPath(File.separator + "stores" + File.separator + pFileFileName + "_" + currentTime);
			FileInputStream fis = new FileInputStream(pFile);
			FileOutputStream fos = new FileOutputStream(storePath);
			byte[] buf = new byte[1024 * 8];
			while (fis.read(buf) != -1)
				fos.write(buf);
			fos.flush();
			fis.close();
			fos.close();
			System.out.println(storePath);
		}
		return "doUpload-success";
	}

}
