package com.chain.nio.day01;

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
public class TestChannel {

}
