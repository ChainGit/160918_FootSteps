package com.bxd.day23;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * TCP文件传输<br>
 * 一个服务套接字,一个TCP套接字,分别用于处理连接,接收发送文件<br>
 * <i>只适合小文件传输,不超过64KB</i>
 * 
 * @author Chain
 *
 */
public class TCPFileTrans2 {

	public static void main(String[] args) {
		System.out.println("0) 主动连接(发送) \t 1) 被动连接(接收)");
		System.out.print("请选择工作模式:");

		BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		int p = -1;
		try {
			while ((p = bufr.read()) != -1)
				if (p == '\r')
					continue;
				else if (p == '\n')
					break;
				else
					sb.append((char) p);
		} catch (IOException e) {
			e.printStackTrace();
		}

		String sel = sb.toString();
		Socket sok = null;
		if (sel.equals("0")) {
			try {
				System.out.print("请输入对方IP和端口: ");
				sb.delete(0, sb.length());
				while ((p = bufr.read()) != -1)
					if (p == '\r')
						continue;
					else if (p == '\n')
						break;
					else
						sb.append((char) p);
				String stmp = sb.toString();
				if (!stmp.contains(":"))
					return;

				String[] pas = stmp.split(":");
				if (pas.length < 2)
					return;

				// if
				// (!Inet4Address.getByAddress(pas[0].getBytes()).isAnyLocalAddress())
				// return;

				int portc = Integer.parseInt(pas[1]);
				if (portc < 0 || portc > 65535)
					return;

				sok = new Socket(pas[0], portc);
				System.out.println("本机IP和端口为: " + sok.getLocalAddress() + ":" + sok.getLocalPort());
				System.out.println("连接对方成功.");
				System.out.println("对方IP和端口为: " + sok.getInetAddress() + ":" + sok.getPort());

				new Thread(new SendThread(sok)).start();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else if (sel.equals("1"))
			try {
				InetAddress inetaddr = InetAddress.getLocalHost();
				int port = 10101;
				ServerSocket sersok = null;
				while (sersok == null)
					try {
						sersok = new ServerSocket(port++);
					} catch (Exception e) {
						sersok = null;
						continue;
					}
				System.out.println("本机IP和端口为: " + inetaddr.getHostAddress() + ":" + sersok.getLocalPort());

				System.out.println("等待对方连接..");
				sok = sersok.accept();
				sersok.close();

				System.out.println("对方连接成功.");
				System.out.println("对方IP和端口为: " + sok.getInetAddress() + ":" + sok.getPort());

				new Thread(new GetThread(sok)).start();
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
}

class SendThread implements Runnable {

	private Socket sok;
	private BufferedInputStream bufis;
	private BufferedOutputStream bufos;
	private BufferedReader bufr;

	public SendThread(Socket s) {
		if (s != null)
			this.sok = s;
	}

	@Override
	public void run() {
		try {
			if (sok == null)
				return;
			if (bufr == null)
				bufr = new BufferedReader(new InputStreamReader(System.in));
			String path = null;
			File file = null;
			while (true) {
				System.out.print("请输入要传输的文件路径:");
				path = bufr.readLine();
				if (path == null || path.length() < 1)
					continue;
				if (path.equals("over"))
					return;
				file = new File(path);
				if (!file.exists() || file.isDirectory()) {
					System.out.println("输入的路径为目录或文件不存在.");
					continue;
				} else
					break;
			}
			bufos = new BufferedOutputStream(sok.getOutputStream());
			if (bufos == null)
				return;
			bufos.write(new String("$#filename#$" + file.getName() + "$#filecontent#$").getBytes());
			bufos.flush();
			bufis = new BufferedInputStream(new FileInputStream(path));
			if (bufis == null)
				return;
			byte[] buf = new byte[1024 * 64];
			while ((bufis.read(buf)) != -1) {
				bufos.write(buf, 0, getActualLength(buf));
				bufos.flush();
			}
			bufis.close();
			bufos.flush();
			bufos.close();
			bufos = null;
			System.out.println("文件 " + file.getName() + " 传输完毕.");
			while (!sok.isClosed())
				Thread.sleep(1000);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeRes();
		}
	}

	private void closeRes() {
		try {
			if (bufis != null)
				bufis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			if (bufos != null)
				bufos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			if (sok != null)
				sok.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("连接断开.");
		System.exit(0);
	}

	private int getActualLength(byte[] buf) {
		int len = 0;
		for (; len < buf.length; len++)
			if (buf[len] == 0) {
				int i = len;
				for (; i < buf.length; i++)
					if (buf[i] != 0)
						break;
				if (i == buf.length)
					break;
			}
		return len;
	}

}

class GetThread implements Runnable {

	private Socket sok;
	private BufferedOutputStream bufos;
	private BufferedInputStream bufis;

	public GetThread(Socket s) {
		if (s != null)
			this.sok = s;
	}

	@Override
	public void run() {
		try {
			System.out.println("等待接收文件.");
			if (sok == null)
				return;
			if (bufis == null)
				bufis = new BufferedInputStream(sok.getInputStream());
			Date date = new Date();
			String tfname = new String("tmp_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(date));
			bufos = new BufferedOutputStream(new FileOutputStream(tfname));
			if (bufos == null)
				return;
			byte[] buf = new byte[1024 * 64];
			while ((bufis.read(buf)) != -1) {
				bufos.write(buf, 0, getActualLength(buf));
				bufos.flush();
			}
			bufis.close();
			bufos.flush();
			bufos.close();
			bufis = null;
			sok.close();
			sok = null;
			new Thread(new ProcGetTmpFile2(tfname)).start();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeRes();
		}
	}

	private void closeRes() {
		try {
			if (bufis != null)
				bufis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			if (bufos != null)
				bufos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			if (sok != null)
				sok.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("连接断开.");
	}

	private int getActualLength(byte[] buf) {
		int len = 0;
		for (; len < buf.length; len++)
			if (buf[len] == 0) {
				int i = len;
				for (; i < buf.length; i++)
					if (buf[i] != 0)
						break;
				if (i == buf.length)
					break;
			}
		return len;
	}

}

class ProcGetTmpFile2 implements Runnable {

	private BufferedInputStream bufis;
	private BufferedOutputStream bufos;
	private File f;

	public ProcGetTmpFile2(String fname) {
		if (fname != null)
			this.f = new File(fname);
	}

	@Override
	public void run() {
		try {
			if (f == null)
				return;
			if (bufis == null)
				bufis = new BufferedInputStream(new FileInputStream(f));
			byte[] buf = new byte[64 * 1024];
			String sbuf = null;
			if (bufis.read(buf) == -1)
				return;
			String fnflag = "$#filename#$";
			String fcflag = "$#filecontent#$";
			int fnflaglen = fnflag.length();
			int fcflaglen = fcflag.length();
			sbuf = new String(buf, 0, getActualLength(buf));
			if (!(sbuf.startsWith(fnflag) && sbuf.contains(fcflag)))
				return;
			int fcpoint = sbuf.indexOf(fcflag);
			if (fcpoint == -1 || fcpoint < fnflaglen + 1)
				return;
			String filename = sbuf.substring(fnflaglen, fcpoint);
			byte[] buf2 = Arrays.copyOfRange(buf, fnflaglen + filename.length() + fcflaglen, buf.length);
			if (new File(filename).exists())
				filename = "重命名_" + filename;
			if (bufos == null)
				bufos = new BufferedOutputStream(new FileOutputStream(filename));
			bufos.write(buf2, 0, getActualLength(buf2));
			bufos.flush();
			while ((bufis.read(buf)) != -1) {
				bufos.write(buf, 0, getActualLength(buf));
				bufos.flush();
			}
			bufos.flush();
			bufos.close();
			if (f.exists() && f.isFile())
				f.deleteOnExit();
			if (new File(filename).exists())
				System.out.println("文件 " + filename + " 接收成功.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeRes();
		}
	}

	private int getActualLength(byte[] buf) {
		int len = 0;
		for (; len < buf.length; len++)
			if (buf[len] == 0) {
				int i = len;
				for (; i < buf.length; i++)
					if (buf[i] != 0)
						break;
				if (i == buf.length)
					break;
			}
		return len;

	}

	private void closeRes() {
		try {
			if (bufis != null)
				bufis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			if (bufos != null)
				bufos.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.exit(0);
	}
}
