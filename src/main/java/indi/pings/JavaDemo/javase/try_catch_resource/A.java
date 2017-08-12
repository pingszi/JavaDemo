package indi.pings.JavaDemo.javase.try_catch_resource;

import java.io.Closeable;
import java.io.IOException;

public class A implements Closeable{
	
	public double id = Math.random();

	@Override
	public void close() throws IOException {
		System.out.println(id + "：关闭");
	}

}
