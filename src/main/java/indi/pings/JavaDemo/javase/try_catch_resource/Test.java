package indi.pings.JavaDemo.javase.try_catch_resource;

import java.io.IOException;

public class Test {

	public void test(){
		try(A a = new A()){  //**try里面的a会在try块结束时自动关闭
			A a2 = new A();  //**a2不会自动关闭
			System.out.println(a2);
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("end");
	}
	
	public static void main(String[] args) {
		Test test = new Test();
		test.test();
	}
}
