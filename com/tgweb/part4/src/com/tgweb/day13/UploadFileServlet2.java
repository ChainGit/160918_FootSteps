package com.tgweb.day13;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 * Servlet implementation class UploadFileServlet2
 */
public class UploadFileServlet2 extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private static final String BASE_DIR = "E:\\More\\Temps\\upload";
	private static final String TEMP_DIR = BASE_DIR + "\\temp";
	private static final String DISK_DIR = BASE_DIR + "\\disk";

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(1024 * 500);

		File tempDir = new File(TEMP_DIR);
		factory.setRepository(tempDir);

		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(1024 * 1024 * 5);

		PrintWriter out = response.getWriter();

		try {
			List<FileItem> items = upload.parseRequest(request);

			for (FileItem i : items) {
				String contentType = i.getContentType();
				out.println("<br />");
				out.println(contentType);
				if (!i.isFormField()) {
					out.println("data:");

					String fileName = i.getName();
					long fileSize = i.getSize();

					out.println(fileName + " " + fileSize);

					String savePath = DISK_DIR + File.separator + fileName;
					OutputStream os = new FileOutputStream(savePath);
					InputStream is = i.getInputStream();
					byte[] buf = new byte[1024];
					while ((is.read(buf)) != -1)
						os.write(buf); 
					os.flush();
					is.close();
					os.close();
				} else {
					out.println("field:");

					String fieldName = i.getFieldName();
					String fieldValue = i.getString("UTF-8");

					out.println(fieldName + " " + fieldValue);
				}
			}

			out.close();

		} catch (FileUploadException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
