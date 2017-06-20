package com.tgweb.struts2.day16;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

public class TestFileDownloadAction extends ActionSupport {

	private static final long serialVersionUID = 1L;

	private String contentType;
	private long contentLength;
	private String contentDisposition;
	private InputStream inputStream;

	public String download() {
		return "download-success";
	}

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public long getContentLength() {
		return contentLength;
	}

	public void setContentLength(long contentLength) {
		this.contentLength = contentLength;
	}

	public String getContentDisposition() {
		return contentDisposition;
	}

	public void setContentDisposition(String contentDisposition) {
		this.contentDisposition = contentDisposition;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String doDownload() throws Exception {
		String fileName = "hello.txt";
		setContentType("text/plain");
		setContentDisposition("attachment;filename=" + fileName);
		ServletContext sc = ServletActionContext.getServletContext();
		String fileRealPath = sc.getRealPath(File.separator + fileName);
		setInputStream(new FileInputStream(fileRealPath));
		setContentLength(inputStream.available());
		return "doDownload-success";
	}

}
