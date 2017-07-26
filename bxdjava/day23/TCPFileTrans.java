package com.bxd.day23;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

/**
 * TCP文件传输<br>
 * 两个TCP套接字,一个服务套接字,各自不同的线程,分别负责接收文件,发送文件,处理连接<br>
 * <i>只适合小文件的传输,不大于64KB</i>
 * 
 * @author Chain
 *
 */
public class TCPFileTrans {

	private static final String ERR_LOG = "err.log";

	public static void main(String[] args) throws Exception {
		System.setErr(new PrintStream(ERR_LOG));

		ServerSocket sersok = new ServerSocket();
		String serip = InetAddress.getLocalHost().getHostAddress();
		int port = 10101;
		while (!sersok.isBound() && port < 65536)
			try {
				sersok.bind(new InetSocketAddress(serip, port++));
			} catch (Exception e) {
				continue;
			}
		System.out.println("本机接收数据IP和端口为: " + sersok.getInetAddress() + ":" + sersok.getLocalPort());

		new Thread(new FileGet(sersok)).start();

		Thread.sleep(1000);

		while (true) {
			BufferedReader bufr = new BufferedReader(new InputStreamReader(System.in));
			System.out.println();
			System.out.print("请输入对方信息:");
			String info = bufr.readLine();
			if (info.contains("over"))
				System.exit(0);
			if (!info.contains(":"))
				continue;
			String[] pas = info.split(":");
			if (pas.length > 2)
				continue;
			try {
				if (!Inet4Address.getByAddress(pas[0].getBytes()).isAnyLocalAddress())
					continue;
			} catch (Exception e) {
				continue;
			}
			int portc = Integer.parseInt(pas[1]);
			if (portc < 0 || portc > 65535)
				continue;
			try {
				Socket soksend = new Socket(pas[0], portc);
				Thread ts = new Thread(new FileSend(soksend));
				ts.start();

				while (ts.isAlive())
					Thread.sleep(1000);
			} catch (Exception e) {
				continue;
			}
		}
	}
}

class FileGet implements Runnable {

	private ServerSocket sersok;
	private Socket clisok;
	private BufferedOutputStream bufos;
	private BufferedInputStream bufis;

	public FileGet(ServerSocket s) {
		if (s != null)
			this.sersok = s;
	}

	@Override
	public void run() {
		try {
			System.out.println("接收文件功能已启用.");
			while (true) {
				if (sersok == null)
					break;
				clisok = sersok.accept();
				if (clisok == null)
					break;
				System.out.println();
				System.out.println("接收到来自 " + clisok.getInetAddress() + ":" + clisok.getPort() + " 的连接,可以接收文件.");
				if (bufis == null)
					bufis = new BufferedInputStream(clisok.getInputStream());
				Date date = new Date();
				String tfname = new String("tmp_" + new SimpleDateFormat("yyyyMMdd_HHmmss").format(date));
				bufos = new BufferedOutputStream(new FileOutputStream(tfname));
				if (bufos == null)
					break;
				byte[] buf = new byte[1024 * 64];
				while ((bufis.read(buf)) != -1) {
					bufos.write(buf, 0, getActualLength(buf));
					bufos.flush();
				}
				bufis.close();
				bufos.flush();
				bufos.close();
				bufis = null;
				clisok.close();
				clisok = null;
				new Thread(new ProcGetTmpFile(tfname)).start();
			}
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
			if (clisok != null)
				clisok.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			if (sersok != null)
				sersok.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("接收文件功能意外终止.");
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

class FileSend implements Runnable {

	private Socket sok;
	private BufferedInputStream bufis;
	private BufferedOutputStream bufos;
	private BufferedReader bufr;

	public FileSend(Socket s) {
		if (s != null)
			this.sok = s;
	}

	@Override
	public void run() {
		try {
			System.out.println("本机发送数据IP和端口为: " + sok.getLocalAddress() + ":" + sok.getLocalPort());
			System.out.println("连接 " + sok.getInetAddress() + ":" + sok.getPort() + " 成功,可以发送文件.");
			while (true) {
				if (sok == null)
					break;
				if (bufr == null)
					bufr = new BufferedReader(new InputStreamReader(System.in));
				System.out.print("请输入要传输的文件路径:");
				String path = bufr.readLine();
				if (path == null || path.length() < 1)
					continue;
				if (path.equals("over"))
					break;
				File file = new File(path);
				if (!file.exists() || file.isDirectory()) {
					System.out.println("输入的路径为目录或文件不存在.");
					continue;
				}
				bufos = new BufferedOutputStream(sok.getOutputStream());
				if (bufos == null)
					break;
				bufos.write(new String("$#filename#$" + file.getName() + "$#filecontent#$").getBytes());
				bufos.flush();
				bufis = new BufferedInputStream(new FileInputStream(path));
				if (bufis == null)
					break;
				byte[] buf = new byte[1024 * 64];
				while ((bufis.read(buf)) != -1) {
					bufos.write(buf, 0, getActualLength(buf));
					bufos.flush();
				}
				bufis.close();
				bufos.flush();
				bufos.close();
				bufos = null;
				sok.close();
				sok = null;
				System.out.println("文件 " + file.getName() + " 传输完毕.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			closeRes();
		}
	}

	private void closeRes() {
		// try {
		// if (bufr != null)
		// bufr.close();
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

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

		System.out.println("本次文件传输连接已关闭.");
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

class ProcGetTmpFile implements Runnable {

	private BufferedInputStream bufis;
	private BufferedOutputStream bufos;
	private File f;

	public ProcGetTmpFile(String fname) {
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
	}
}