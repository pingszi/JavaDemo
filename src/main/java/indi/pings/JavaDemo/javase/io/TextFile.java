package indi.pings.JavaDemo.javase.io;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * @Description: 文件读写操作工具
 * @author ping 
 * @date 2014年9月2日
 * @version V1.0
 */
public class TextFile extends ArrayList<String> {
	
	private static final long serialVersionUID = 1L;
	
	public TextFile(String fileName, String splitter){
		super(Arrays.asList(read(fileName).split(splitter)));
		if(get(0).equals(""))
			remove(0);
	}
	
	public TextFile(String fileName){
		this(fileName, "\n");
	}

	/**
	 * @Description: 读取指定文件的内容
	 * @param fileName
	 * @return String
	 * @throws
	 */
	public static String read(String fileName){
		StringBuilder sb = new StringBuilder();
		try{
			BufferedReader in = new BufferedReader(new FileReader(new File(fileName).getAbsoluteFile()));
			try{
				String s;
				while((s = in.readLine()) != null){
					sb.append(s);
					sb.append("\n");
				}
			}finally{
				in.close();
			}
		}catch(IOException e){
			throw new RuntimeException(e);
		}
		return sb.toString();
	}
	
	/**
	 * @Description: 在指定文件中写入指定内容
	 * @param fileName
	 * @param text
	 * @return void
	 * @throws
	 */
	public static void write(String fileName, String text){
		try{
			PrintWriter out = new PrintWriter(new File(fileName).getAbsoluteFile());
			try{
				out.print(text);
			}finally{
				out.close();
			}
		}catch(IOException e){
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * @Description: 在指定文件中写入构造器传入的文件内容
	 * @param  fileName
	 * @return void
	 * @throws
	 */
	public void write(String fileName){
		try{
			PrintWriter out = new PrintWriter(new File(fileName).getAbsoluteFile());
			try{
				for(String item : this)
					out.println(item);
			}finally{
				out.close();
			}
		}catch(IOException e){
			throw new RuntimeException(e);
		}
	}
}
