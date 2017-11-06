package indi.pings.JavaDemo.jvm.tool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *********************************************************
 ** @desc  ：  使用VisualVM BTrace动态日志跟踪                             
 ** @author  Pings                                     
 ** @date    2017年9月8日  
 ** @version v1.0                                                                                  
 * *******************************************************
 */
public class BTraceTest {
	
	public int add(int a, int b) {
		return a + b;
	}

	public static void main(String[] args) throws IOException {
		BTraceTest test = new BTraceTest();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		for (int i = 0; i < 10; i++) {
			reader.readLine();
			int a = (int) Math.round(Math.random() * 1000);
			int b = (int) Math.round(Math.random() * 1000);
			System.out.println(test.add(a, b));
		}
	}
}

/*@BTrace
public class TracingScript {
	
    @OnMethod(clazz="indi.pings.JavaDemo.jvm.tool.BTraceTest", method="add", location=@Location(Kind.RETURN))
    public static void func(@Self indi.pings.JavaDemo.jvm.tool.BTraceTest instance, int a, int b, @Return int result){
        println("调用堆栈：");
        jstack();
        println(strcat("方法参数A:", str(a)));
        println(strcat("方法参数B:", str(b)));
        println(strcat("方法结果:", str(result)));
    }
}*/
