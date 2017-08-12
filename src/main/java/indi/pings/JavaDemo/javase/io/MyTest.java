package indi.pings.JavaDemo.javase.io;

import java.io.File;

public class MyTest {

	public static void main(String[] args) {
		File oldFile = new File("e:/新建文本文档.txt");
		File newFile = new File("f:/新建文本文档.txt");
		//**移动文件
		oldFile.renameTo(newFile);
	}
}
