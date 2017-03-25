package com.tgjdbc.day04;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.tgjdbc.day00.DatabaseTool;

public class BLOBTest {

	public static void main(String[] args) {
		// upload();
		download();
	}

	private static void download() {
		Connection conn = null;
		PreparedStatement stat = null;
		ResultSet res = null;

		try {
			conn = DatabaseTool.getConnection();
			String sql = "select pics from blob_data where id = ?";
			stat = conn.prepareStatement(sql);

			stat.setInt(1, 1);

			res = stat.executeQuery();
			while (res.next()) {
				Blob pic = res.getBlob(1);
				InputStream is = pic.getBinaryStream();
				System.out.println("size:" + is.available());
				OutputStream os = new FileOutputStream("tmp/out.jpg");

				byte[] buf = new byte[1024];
				int len = -1;
				while ((len = is.read(buf)) != -1)
					os.write(buf, 0, len);

				is.close();
				os.close();
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseTool.close(res, stat, conn);
		}

	}

	private static void upload() {
		Connection conn = null;
		PreparedStatement stat = null;

		try {
			conn = DatabaseTool.getConnection();
			String sql = "insert into blob_data (pics) values (?)";
			stat = conn.prepareStatement(sql);

			InputStream is = new FileInputStream("tmp/test.jpg");
			stat.setBlob(1, is);

			stat.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DatabaseTool.close(null, stat, conn);
		}
	}
}
