package com.bxd.day19;

import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class DecorationTest2 {

	public static void main(String[] args) {
		try {
			IOTest2.test1();

			MyLineNumberBufferedReader lbufr = new MyLineNumberBufferedReader(new FileReader("demo4.txt"));
			BufferedWriter bufw = new BufferedWriter(new FileWriter("demo4_3.txt"));

			// lbufr.setCurrentLineNumber(1);

			String line = null;
			while ((line = lbufr.myReadLine()) != null) {
				bufw.write(line);
				bufw.newLine();
			}
			bufw.flush();
			bufw.close();
			lbufr.myClose();

			IOTest.deleteAll();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

class MyLineNumberBufferedReader extends MyBufferedReader {

	private int ln = 0;

	public MyLineNumberBufferedReader(FileReader fr) {
		super(fr);
	}

	@Override
	public String myReadLine() throws IOException {
		String sprl = super.myReadLine();
		if (sprl == null)
			return null;
		StringBuilder sb = new StringBuilder();
		sb.append(ln++);
		while (sb.length() < 3)
			sb.append(new char[] { '0' }, 0, 1);
		sb.append(' ');
		return sb.append(sprl).toString();
	}

	public void setCurrentLineNumber(int n) {
		this.ln = n;
	}

}
