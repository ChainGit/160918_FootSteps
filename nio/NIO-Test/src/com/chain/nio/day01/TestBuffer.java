package com.chain.nio.day01;

import java.nio.ByteBuffer;

import org.junit.Test;

/**
 * Buffer用来存储，Channel用来传输
 * 
 * 除了boolean，有ByteBuffer,ShortBuffer,IntBuffer,LongBuffer,FloatBuffer,DoubleBuffer
 * 
 * 容量 (capacity) ：表示 Buffer 最大数据容量，缓冲区容量不能为负，并且创 建后不能更改。 <br>
 * 限制 (limit)：第一个不应该读取或写入的数据的索引，即位于 limit 后的数据 不可读写。缓冲区的限制不能为负，并且不能大于其容量。 <br>
 * 位置 (position)：下一个要读取或写入的数据的索引。缓冲区的位置不能为 负，并且不能大于其限制  <br>
 * 标记 (mark)与重置 (reset)：标记是一个索引，通过 Buffer 中的 mark() 方法 指定 Buffer 中一个特定的
 * position，之后可以通过调用 reset() 方法恢复到这 个 position.<br>
 * 
 * 标记、位置、限制、容量遵守以下不变式： 0 <= mark <= position <= limit <= capacity<br>
 * 
 * @author chain
 *
 */
public class TestBuffer {

	/**
	 * 直接缓冲区
	 */
	@Test
	public void test3() {
		// 在物理内存中开辟空间
		ByteBuffer bb = ByteBuffer.allocateDirect(1024);

		System.out.println(bb.isDirect());
	}

	/**
	 * 非直接缓冲区
	 */
	@Test
	public void test2() {
		String str = "abcde";

		// 在JVM的堆上开辟空间
		ByteBuffer bb = ByteBuffer.allocate(1024);

		bb.put(str.getBytes());

		bb.flip();

		byte[] dst = new byte[bb.limit()];
		bb.get(dst, 0, 2);
		System.out.println(new String(dst, 0, 2));
		System.out.println(bb.position());

		bb.mark();

		bb.get(dst, 2, 2);
		System.out.println(new String(dst, 0, 2));
		System.out.println(bb.position());

		bb.reset();

		System.out.println(bb.position());
		System.out.println((char) bb.get());

		if (bb.hasRemaining())
			System.out.println(bb.remaining());

		// 遗忘状态
		bb.clear();
	}

	@Test
	public void test() {
		// ByteBuffer常用
		ByteBuffer bb = ByteBuffer.allocate(1024);

		System.out.println(bb.position());
		System.out.println(bb.limit());
		System.out.println(bb.capacity());
		System.out.println();

		// 写入数据
		bb.put("abcde".getBytes());

		System.out.println(bb.position());
		System.out.println(bb.limit());
		System.out.println(bb.capacity());
		System.out.println();

		// 切换状态，进入读模式
		bb.flip();

		System.out.println(bb.position());
		System.out.println(bb.limit());
		System.out.println(bb.capacity());
		System.out.println();

		System.out.println(bb.get());

		System.out.println(bb.position());
		System.out.println(bb.limit());
		System.out.println(bb.capacity());
		System.out.println();

		// 重新从头开始读
		bb.rewind();

		System.out.println(bb.position());
		System.out.println(bb.limit());
		System.out.println(bb.capacity());
		System.out.println();

		System.out.println(bb.get(2));
		// 做个标记（在读模式下）
		bb.mark();
		bb.get();

		System.out.println(bb.position());
		System.out.println(bb.limit());
		System.out.println(bb.capacity());
		System.out.println();

		// 恢复到mark的位置
		bb.reset();
		System.out.println(bb.get());

		System.out.println(bb.position());
		System.out.println(bb.limit());
		System.out.println(bb.capacity());
		System.out.println();

		// 从头开始重新读
		bb.rewind();

		System.out.println(bb.position());
		System.out.println(bb.limit());
		System.out.println(bb.capacity());
		System.out.println();

		// 重置为写模式，从头开始重新写，并处于遗忘状态
		bb.clear();

		// 遗忘状态仍然可以读取到数据
		System.out.println(bb.get());

	}

}
