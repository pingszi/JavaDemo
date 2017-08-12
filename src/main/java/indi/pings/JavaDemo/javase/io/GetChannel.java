package indi.pings.JavaDemo.javase.io;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.*;

/**
 * @Description: nio使用示例-获取通道
 * @author ping 
 * @date 2014年9月3日
 * @version V1.0
 */
public class GetChannel {

	private static final int BSIZE = 1024;
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		FileChannel fc = new FileOutputStream("e:/test.txt").getChannel();
		fc.write(ByteBuffer.wrap("Some more".getBytes()));
		fc.close();
		
		fc = new RandomAccessFile("e:/test.txt", "rw").getChannel();
		fc.position(fc.size());
		fc.write(ByteBuffer.wrap("Some more".getBytes()));
		fc.close();
		
		fc = new FileInputStream("e:/test.txt").getChannel();
		ByteBuffer buff = ByteBuffer.allocate(BSIZE);
		fc.read(buff);
		buff.flip();
		while(buff.hasRemaining())
			System.out.print((char)buff.get());
	}
}
