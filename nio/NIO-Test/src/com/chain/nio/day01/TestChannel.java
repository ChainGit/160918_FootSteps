package com.chain.nio.day01;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.junit.Test;

/**
 * Channel用来传输Buffer
 * 
 * 传输方式由直接CPU控制的IO->DMA->Channel
 * 
 * Java 为 Channel 接口提供的最主要实现类如下：<br>
 * FileChannel：用于读取、写入、映射和操作文件的通道。<br>
 * DatagramChannel：通过 UDP 读写网络中的数据通道。<br>
 * SocketChannel：通过 TCP 读写网络中的数据。<br>
 * ServerSocketChannel：可以监听新进来的 TCP 连接，对每一个新进来的连接都会创建一个 SocketChannel。<br>
 * 
 * 支持通道的类如下：<br>
 * 本地IO： FileInputStream  FileOutputStream  RandomAccessFile <br>
 * 网络IO： DatagramSocket  Socket  ServerSocket <br>
 * 
 * 获取通道的其他方式(jdk1.7, nio2)：<br>
 * 使用 Files 类的静态方法 newByteChannel() 获取字节通道。 或者通过通道的静态方法 open()打开并返回指定通道。
 * 
 * 
 * @author chain
 *
 */
// 文件的复制
public class TestChannel {

	private static final String NAME = "test";
	private static final String EXT = ".jpg";
	private static final String FILE = "test0.jpg";

	public static void main(String[] args) throws IOException {
		test2();
	}

	// 使用通道和直接缓存区（transfer）
	@Test
	public void test5() throws IOException {
		// 无需创建流
		FileChannel ifc = FileChannel.open(Paths.get(FILE), StandardOpenOption.READ);
		FileChannel ofc = FileChannel.open(Paths.get(NAME + 5 + EXT), StandardOpenOption.CREATE,
				StandardOpenOption.WRITE, StandardOpenOption.READ);
		// 两种方式
		ifc.transferTo(0, ifc.size(), ofc);
		// ofc.transferFrom(ifc, 0, ifc.size());
		ifc.close();
		ofc.close();
	}

	// 使用通道和直接缓冲区（内存映射文件，内存映射文件是一个直接缓存）
	@Test
	public void test4() throws IOException {
		// 无需创建流
		FileChannel ifc = FileChannel.open(Paths.get(FILE), StandardOpenOption.READ);
		FileChannel ofc = FileChannel.open(Paths.get(NAME + 4 + EXT), StandardOpenOption.CREATE,
				StandardOpenOption.WRITE, StandardOpenOption.READ);
		// 内存映射文件（只有ByteBuffer）
		MappedByteBuffer ifcMapBuf = ifc.map(MapMode.READ_ONLY, 0, ifc.size());
		MappedByteBuffer ofcMapBuf = ofc.map(MapMode.READ_WRITE, 0, ifc.size());
		byte[] buf = new byte[ifcMapBuf.limit()];
		ifcMapBuf.get(buf);
		ofcMapBuf.put(buf);
		ifc.close();
		ofc.close();
	}

	// 使用通道和非直接缓冲区（通道是一个读立的处理器，比DMA更高效）
	@Test
	public void test3() throws IOException {
		// 先创建一个流
		FileInputStream fis = new FileInputStream(new File(FILE));
		FileOutputStream fos = new FileOutputStream(new File(NAME + 3 + EXT));
		// 获得FileChannel
		FileChannel ic = fis.getChannel();
		FileChannel oc = fos.getChannel();
		// 非直接缓冲区
		ByteBuffer bb = ByteBuffer.allocate(1024 * 8);
		// 直接缓冲区
		// ByteBuffer bb = ByteBuffer.allocateDirect(1024 * 8);
		while (ic.read(bb) != -1) {
			bb.flip();
			oc.write(bb);
			bb.clear();
		}
		fis.close();
		fos.close();
	}

	// 利用System
	private static void test2() throws IOException {
		System.setIn(new FileInputStream(FILE));
		System.setOut(new PrintStream(new FileOutputStream(NAME + 2 + EXT)));
		byte[] buf = new byte[1024 * 8];
		int len = -1;
		while ((len = System.in.read(buf)) != -1)
			System.out.write(buf, 0, len);
		System.out.flush();
	}

	// 传统的复制方法
	@Test
	public void test1() throws IOException {
		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(FILE)));
		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(NAME + 1 + EXT)));
		byte[] buf = new byte[1024 * 8];
		int len = -1;
		while ((len = bis.read(buf)) != -1)
			bos.write(buf, 0, len);
		bos.flush();
		bis.close();
		bos.close();
	}

}
