package indi.pings.JavaDemo.javase.io;

import java.io.*;
import java.util.Enumeration;
import java.util.zip.*;

/**
 * @Description: ZIP压缩文件
 * @author ping 
 * @date 2014年9月3日
 * @version V1.0
 */
public class ZIPCompress {

	@SuppressWarnings("rawtypes")
	public static void compress(String[] sources, String target) throws IOException{
		FileOutputStream f = new FileOutputStream(target);
		CheckedOutputStream csum = new CheckedOutputStream(f, new Adler32());
		ZipOutputStream zos =new ZipOutputStream(csum);
		BufferedOutputStream out = new BufferedOutputStream(zos);
		
		zos.setComment("A test of java Zipping");
		for(String source : sources){
			System.out.println("Writing file " + source);
			BufferedReader in = new BufferedReader(new FileReader(source));
			zos.putNextEntry(new ZipEntry(source));
			int c;
			while((c = in.read()) != -1)
				out.write(c);
			in.close();
			out.flush();
		}
		out.close();
		System.out.println("Checksum: " + csum.getChecksum().getValue());
		System.out.println("Reading file");
		FileInputStream fi = new FileInputStream(target);
		CheckedInputStream csumi = new CheckedInputStream(fi, new Adler32());
		ZipInputStream in2 = new ZipInputStream(csumi);
		BufferedInputStream bis = new BufferedInputStream(in2);
		ZipEntry ze;
		while((ze = in2.getNextEntry()) != null){
			System.out.println("Reading file " + ze);
			int x;
			while((x = bis.read()) != -1)
				System.out.println(x);
		}
		System.out.println("Checksun: " + csumi.getChecksum().getValue());
		ZipFile zf = new ZipFile(target);
		Enumeration e = zf.entries();
		while(e.hasMoreElements()){
			ZipEntry ze2 = (ZipEntry) e.nextElement();
			System.out.println("File:" + ze2);
		}
		
		bis.close();
		zf.close();
	}
	
	public static void main(String[] args) throws IOException {
		compress(new String[]{"e:/test.txt", "e:/test2.txt"}, "e:/test.zip");	
	}
}
