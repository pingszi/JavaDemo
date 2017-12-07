package indi.pings.JavaDemo.jdk8.effective.completableFuture;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

/**
 *********************************************************
 ** @desc  ：  商店，提供产品价格查询接口                                           
 ** @author  Pings                                     
 ** @date    2017年12月7日  
 ** @version v1.0                                                                                  
 * *******************************************************
 */
public class Shop {
	
	Random random = new Random();

	/**模拟延迟*/
	public static void delay() {
		try {
			Thread.sleep(1000L);
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}

	private double calculatePrice(String product) {
		delay();
		return random.nextDouble() * product.charAt(0) + product.charAt(1);
	}
	
	/**查询产品价格，同步版本*/
	public double getPrice(String product) {
		return calculatePrice(product);
	}
	
	/**查询产品价格，异步版本*/
	public Future<Double> getPriceAsync(String produce) {
		//**创建CompletableFuture对象，它会包含计算的结果
		CompletableFuture<Double> futurePrice = new CompletableFuture<>();
		new Thread(() -> {
			//**需长时间计算的任务结束并得出结果时，设置Future的返回值
			try {
				double price = calculatePrice(produce);
				futurePrice.complete(price);
			} catch (Exception e) {
				//**失败就抛出导致失败的异常，完成这次Future操作
				futurePrice.completeExceptionally(e);
			}
		}).start();
		
		//**无需等待还没结束的计算，直接返回Future对象
		return futurePrice;
	}
}
