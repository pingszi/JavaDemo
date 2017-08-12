package indi.pings.JavaDemo.javase.io;

import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @Description: 内存映射文件
 * @author ping 
 * @date 2014年9月3日
 * @version V1.0
 */
public class LargeMappedFiles {
    
	static int length = 0x8FFFFFF; //**128M
	
	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		FileChannel channel = new RandomAccessFile("e:/bigFile.txt", "rw").getChannel();
		MappedByteBuffer out = channel.map(FileChannel.MapMode.READ_WRITE, 0, length);
		
		for(int i = 0; i < length; i++){
			out.put((byte)'x');
		}
		System.out.println("Finished writing");
		for(int i = length/2; i < length/2 + 6; i++){
			System.out.println((char)out.get(i));
		}
	}
}
