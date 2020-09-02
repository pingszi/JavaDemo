package indi.pings.JavaDemo.jdk9;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 *********************************************************
 ** @desc  ： CompletableFuture新增方法
 ** @author  Pings
 ** @date    2020年08月28日
 ** @version v1.0
 * *******************************************************
 */
public class MyCompletableFuture {

    public static void main(String[] args) throws Exception {
        CompletableFuture<String> future = CompletableFuture.completedFuture("pings");
        String rst = future.get();
        System.out.println(rst);

        CompletableFuture<String> future1 = CompletableFuture.failedFuture(new RuntimeException("错误"));
        //future1.get();

        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (Exception ignored) {}

            return "pings1";
        });
        //**在给定的时间内，如果future2能完成就返回future2的返回值，否则返回默认值
        //CompletableFuture<String> future3 = future2.completeOnTimeout("pings2", 4, TimeUnit.SECONDS);
        //System.out.println(future3.get());

        //**在给定的时间内，如果future2能完成就返回future2的返回值，否则以TimeoutException完成
        CompletableFuture<String> future4 = future2.orTimeout(4, TimeUnit.SECONDS);
        System.out.println(future4.get());
    }
}
