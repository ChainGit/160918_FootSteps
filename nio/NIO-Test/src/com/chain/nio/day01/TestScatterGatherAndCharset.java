package com.chain.nio.day01;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import org.junit.Test;

/**
 * 分散(Scatter)与聚集(Gather)<br>
 * 分散读取（Scattering Reads）：将通道中的数据分散到多个缓冲区中<br>
 * 聚集写入（Gathering Writes）：将多个缓冲区中的数据聚集到通道中<br>
 * 
 * 字符集：Charset<br>
 * 编码：字符串 -> 字节数组<br>
 * 解码：字节数组 -> 字符串<br>
 * 
 * @author chain
 *
 */
public class TestScatterGatherAndCharset {

	// CharBuffer和字符集
	@Test
	public void test3() throws IOException {

		// 创建指定的字符集
		Charset cs = Charset.forName("BIG5");
		// 获取编码器
		CharsetEncoder en = cs.newEncoder();
		// 获取解码器
		CharsetDecoder de = cs.newDecoder();

		// ------------------------------------------------

		CharBuffer cbuf = CharBuffer.allocate(1024);
		// 存入字符数据，以系统默认的字符集
		cbuf.put("你好啊，hello123!");
		// 在我的系统里，这里等价于
		cbuf.put(new String("你好啊，hello123!".getBytes("UTF-8"), "UTF-8"));
		// 切换成读模式
		cbuf.flip();

		// ------------------------------------------------

		// 将字符缓冲区编码转换成Byte缓冲区（默认返回的就是读模式）
		ByteBuffer ebbuf = en.encode(cbuf);

		// -----------------------------------------------

		System.out.println("limit: " + ebbuf.limit());
		for (int i = 0; i < ebbuf.limit(); i++)
			// 这里的读会改变position，再次读要使用rewind
			System.out.println(ebbuf.get());

		// ----------------------------------------------

		// 可以正确读取
		// 将byte缓冲区解码转换成Char缓冲区（默认返回就是读模式）
		ebbuf.rewind();
		CharBuffer dcbuf = de.decode(ebbuf);
		System.out.println(dcbuf.toString());

		// 不能正确读取
		Charset cs2 = Charset.forName("GBK");
		ebbuf.rewind();
		CharBuffer dcbuf2 = cs2.decode(ebbuf);
		System.out.println(dcbuf2.toString());

		// 不能正确读取
		Charset cs3 = Charset.forName("UTF-8");
		ebbuf.rewind();
		CharBuffer dcbuf3 = cs3.decode(ebbuf);
		System.out.println(dcbuf3.toString());

		// 可以正确读取
		Charset cs4 = Charset.forName("BIG5");
		ebbuf.rewind();
		CharBuffer dcbuf4 = cs4.decode(ebbuf);
		System.out.println(dcbuf4.toString());
	}

	@Test
	public void test2() throws IOException {
		// 获得可随机访问的文件
		RandomAccessFile raf = new RandomAccessFile(new File("test0.jpg"), "r");
		// 获得通道
		FileChannel ifc = raf.getChannel();
		long size = ifc.size();
		// 如果设置太小会有丢失
		int blen = 1024 * 16 * 8;
		int alen = (int) (size / blen) + 1;
		// 创建分散的缓冲区
		ByteBuffer[] bbufs = new ByteBuffer[alen];
		for (int i = 0; i < alen; i++)
			bbufs[i] = ByteBuffer.allocate(blen);
		// 将数据加载到分散的缓冲区(缓存区有多余没有关系)
		long rlen = ifc.read(bbufs, 0, bbufs.length);
		// 切换为读模式
		for (ByteBuffer b : bbufs)
			b.flip();
		FileChannel ofc = FileChannel.open(Paths.get("test7.jpg"), StandardOpenOption.WRITE, StandardOpenOption.READ,
				StandardOpenOption.CREATE);
		// 将各个缓冲区聚集按顺序写入
		long wlen = ofc.write(bbufs);
		System.out.println(ifc.size() + " " + alen * blen + " " + rlen + " " + wlen);
		ifc.close();
		ofc.close();
		raf.close();
	}

	// 分散和聚集方式复制文件
	@Test
	public void test1() throws IOException {
		// 获得可随机访问的文件
		RandomAccessFile raf = new RandomAccessFile(new File("test0.jpg"), "r");
		// 获得通道
		FileChannel ifc = raf.getChannel();
		long size = ifc.size();
		// 如果设置太小会有丢失
		int blen = 1024 * 16 * 8;
		int alen = (int) (size / blen), olen = alen;
		if (size % blen != 0)
			alen++;
		// 创建分散的缓冲区
		ByteBuffer[] bbufs = new ByteBuffer[alen];
		for (int i = 0; i < olen; i++)
			bbufs[i] = ByteBuffer.allocate(blen);
		if (alen == olen + 1)
			bbufs[olen] = ByteBuffer.allocate((int) (size - blen * olen));
		// 将数据加载到分散的缓冲区
		long rlen = ifc.read(bbufs, 0, bbufs.length);
		// 切换为读模式
		for (ByteBuffer b : bbufs)
			b.flip();
		FileChannel ofc = FileChannel.open(Paths.get("test6.jpg"), StandardOpenOption.WRITE, StandardOpenOption.READ,
				StandardOpenOption.CREATE);
		// 将各个缓冲区聚集按顺序写入
		long wlen = ofc.write(bbufs);
		System.out.println(ifc.size() + " " + olen * blen + " " + rlen + " " + wlen);
		ifc.close();
		ofc.close();
		raf.close();
	}

}
