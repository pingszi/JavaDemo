package indi.pings.JavaDemo.javase.io;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

/**
 * @Description: 使用nio使一个通道与另一个通道直接相连
 * @author ping 
 * @date 2014年9月3日
 * @version V1.0
 */
public class TransferTo {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		FileChannel 
		  in = new FileInputStream("e:/test.txt").getChannel(),
		  out = new FileOutputStream("e:/test2.txt").getChannel();
		//**复制test.txt的内容到test2.txt
		in.transferTo(0, in.size(), out);
		//out.transferFrom(in, 0, in.size());
	}
}
