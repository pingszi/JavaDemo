package indi.pings.JavaDemo.javase.lambda;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Consumer;

/**
 * @Description: Lambda表达式 
 * @author ping 
 * @date 2016年3月31日
 * @version V1.0
 */
public class LambdaDemo {
	String value;
	public LambdaDemo() {  
		value = "default";  
     } 

    public void t(){}
	public static void main(String[] args) {
		//**lambdaInterface
		lambdaInterface li1 = new lambdaInterface(){
									@Override
									public void me(String str) {
										System.out.println(str);
									}};
		//**2.lambda
		lambdaInterface li2 = (String s)->{System.out.println(s);};
		li1.me("test1");
		li2.me("test2");
		//**Runnable
		Runnable run = ()->{System.out.println(Thread.currentThread());};	
		new Thread(run).start();
		//**Comparator
		Comparator<String> co = (s1,s2) -> {return s2.length() - s1.length();};
		System.out.println(co.compare("ping", "lei"));
		
		List<String> list = new ArrayList<>();
		list.add("ping");
		list.add("lei");
		list.forEach(new Consumer<String>() { 
		    public void accept(String x) { 
		         System.out.println(x); 
		         System.out.println(x);
		    } 
		});
		list.forEach(x -> System.out.println(x));
		long i = list.stream().filter(x -> x == "lei").count();
		System.out.println(i);
		System.out.println(String.join("111", "222","333","444"));	
	}

}

interface lambdaInterface {   
    public void me(String str);  
    default int test(String s){
    	System.out.println(s);
    	return 1;
    }
}
@FunctionalInterface
interface Funcsss {   
	public int invoke();
}

