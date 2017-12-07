package indi.pings.JavaDemo.jdk8.effective.completableFuture;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class Test {
	
	/**获取商店产品的价格，getPriceAsync异步版本*/
	public static void printPrice() {
		Shop shop = new Shop();
		long start = System.nanoTime();
		//**查询商店，试图取得商品的价格
		Future<Double> futurePrice = shop.getPriceAsync("my favorite product");
		long invocationTime = ((System.nanoTime() - start) / 1_000_000);
		System.out.println("Invocation returned after " + invocationTime + " msecs");
		
		//**执行更多任务，比如查询其他商店
		Shop.delay();
		try {
			//**从Future对象中读取价格，如果价格未知，会发生阻塞
			double price = futurePrice.get();
			System.out.printf("Price is %.2f%n", price);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
		long retrievalTime = ((System.nanoTime() - start) / 1_000_000);
		System.out.println("Price returned after " + retrievalTime + " msecs");
	}

	public static void main(String[] args) {
		printPrice();
		
	}
}
