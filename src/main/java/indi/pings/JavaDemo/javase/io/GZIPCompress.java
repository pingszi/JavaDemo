package indi.pings.JavaDemo.javase.io;

import java.io.*;
import java.util.zip.GZIPOutputStream;

/**
 * @Description: GZIP压缩单个文件
 * @author ping 
 * @date 2014年9月3日
 * @version V1.0
 */
public class GZIPCompress {

	public static void compress(String source, String target) throws IOException{
		BufferedReader in = new BufferedReader(new FileReader(source));
		BufferedOutputStream out = new BufferedOutputStream(new GZIPOutputStream(new FileOutputStream(target)));
		System.out.println("Writing file");
		int c;
		while((c = in.read()) != -1)
			out.write(c);
		in.close();
		out.close();
	}
	
	public static void main(String[] args) throws IOException {
		compress("e:/test2.txt", "e:/test2.gz");
	}
}
