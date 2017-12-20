package indi.pings.JavaDemo.jdk8.effective.completableFuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadFactory;

import static java.util.stream.Collectors.*;

/**
 *********************************************************
 ** @desc  ：  查询商价格                                          
 ** @author  Pings                                     
 ** @date    2017年12月8日  
 ** @version v1.0                                                                                  
 * *******************************************************
 */
public class Client {
	
	public static List<Shop> shops = Arrays.asList(new Shop("BestPrice"),
			new Shop("LetsSaveBig"),
			new Shop("MyFavoriteShop"),
			new Shop("BuyItAll"));
	
	//**创建一个线程池，线程池中线程的数 目为 100和商店数目二者中较小的一个值
	private static final Executor executor = Executors.newFixedThreadPool(Math.min(shops.size(), 100), new ThreadFactory() {
		public Thread newThread(Runnable r) {
			Thread t = new Thread(r);
			//**使用守护线程——这种方式不会阻止程序的关停
			t.setDaemon(true);
			return t;
		}
	});

	/**获取商店产品的价格，getPriceAsync异步版本*/
	public static void printPrice() {
		Shop shop = new Shop();
		long start = System.nanoTime();
		//**查询商店，试图取得商品的价格
		Future<Double> futurePrice = shop.getPriceAsync("my favorite product");
		long invocationTime = ((System.nanoTime() - start) / 1_000_000);
		System.out.println("Invocation returned after " + invocationTime + " msecs");
		
		//**执行更多任务，比如查询其他商店
		System.out.println("...其它任务");
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
	
	/**打印产品价格，阻塞版本*/
	public static void printPrices(String product) {
		long start = System.nanoTime();
		List<String> prices = shops.stream().map(shop -> String.format("%s price is %.2f",
				shop.getName(), shop.getPrice(product))).collect(toList());
		long invocationTime = ((System.nanoTime() - start) / 1_000_000);
		System.out.println(invocationTime);
		System.out.println(prices);
	}
	
	/**打印产品价格，并行流版本*/
	public static void printPricesParallel(String product) {
		long start = System.nanoTime();
		List<String> prices = shops.parallelStream().map(shop -> String.format("%s price is %.2f",
				shop.getName(), shop.getPrice(product))).collect(toList());
		long invocationTime = ((System.nanoTime() - start) / 1_000_000);
		System.out.println(invocationTime);
		System.out.println(prices);
	}
	
	/**打印产品价格，CompletableFuture版本*/
	public static void printPricesFuture(String product) {
		long start = System.nanoTime();
		List<CompletableFuture<String>> futures = shops.parallelStream().map(
				shop -> CompletableFuture.supplyAsync(
						() -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product))
				)).collect(toList());
		List<String> prices = futures.stream().map(CompletableFuture::join).collect(toList());
		long invocationTime = ((System.nanoTime() - start) / 1_000_000);
		System.out.println(invocationTime);
		System.out.println(prices);
	}
	
	/**打印产品价格，CompletableFuture版，定制线程数*/
	public static void printPricesFuture2(String product) {
		long start = System.nanoTime();
		List<CompletableFuture<String>> futures = shops.parallelStream().map(
				shop -> CompletableFuture.supplyAsync(
						() -> String.format("%s price is %.2f", shop.getName(), shop.getPrice(product)), executor
				)).collect(toList());
		List<String> prices = futures.stream().map(CompletableFuture::join).collect(toList());
		long invocationTime = ((System.nanoTime() - start) / 1_000_000);
		System.out.println(invocationTime);
		System.out.println(prices);
	}
	
	/**打印产品价格，多个任务，阻塞版本*/
	public static void printPrices2(String product) {
		long start = System.nanoTime();
		List<String> prices = shops.stream().map(shop -> shop.getPrice2(product))
				.map(Quote::parse).map(Discount::applyDiscount).collect(toList());
		long invocationTime = ((System.nanoTime() - start) / 1_000_000);
		System.out.println(invocationTime);
		System.out.println(prices);
	}
	

	/**打印产品价格，多个任务，CompletableFuture版，定制线程数*/
	public static void printPricesFuture3(String product) {
		List<CompletableFuture<String>> priceFutures = 
				shops.stream()
					//**以异步方式取得每个shop中指定产品的原始价格
					.map(shop -> CompletableFuture.supplyAsync(() -> shop.getPrice2(product), executor))
					//**Quote对象存在时，对其返回的值进行转换
					.map(future -> future.thenApply(Quote::parse))
					//**使用另一个异步任务构造期望的Future，申请折扣
					.map(future -> future.thenCompose(quote ->
						CompletableFuture.supplyAsync(() -> Discount.applyDiscount(quote), executor)))
					.collect(toList());
		
		List<String> prices = priceFutures.stream().map(CompletableFuture::join).collect(toList());
		System.out.println(prices);
	}
}
