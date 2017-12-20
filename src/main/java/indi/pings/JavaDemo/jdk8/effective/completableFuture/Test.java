package indi.pings.JavaDemo.jdk8.effective.completableFuture;

import java.util.function.Consumer;

public class Test {
	
	/**打印执行时间*/
	public static void printExecuteTime(String product, Consumer<String> consumer) {
		long start = System.nanoTime();
		
		consumer.accept(product);
		
		long invocationTime = ((System.nanoTime() - start) / 1_000_000);
		System.out.println(invocationTime);
	}
	
	public static void main(String[] args) {
		//**单个异步任务
		//Client.printPrice();
		//Client.printPrices("iphone");
		//Client.printPricesParallel("iphone");
		//Client.printPricesFuture("iphone");
		//Client.printPricesFuture2("iphone");
		
		//**多个异步任务
		Client.printPrices2("iphone");
		printExecuteTime("iphone", Client::printPricesFuture3);
	}
}
