package indi.pings.JavaDemo.jdk11;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.concurrent.CompletableFuture;

/**
 *********************************************************
 ** @desc  ： HttpClient
 ** @author  Pings
 ** @date    2020年09月02日
 ** @version v1.0
 * *******************************************************
 */
public class MyHttpClient {

    public static void test() throws Exception {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create("http://www.baidu.com")).build();
        HttpResponse.BodyHandler<String> handler = HttpResponse.BodyHandlers.ofString();

        //**同步调用
        //HttpResponse<String> response = client.send(request, handler);
        //String body = response.body();

        //**异步调用
        CompletableFuture<HttpResponse<String>> response = client.sendAsync(request, handler);
        HttpResponse<String> result = response.get();
        String body = result.body();

        System.out.println(body);
    }

    public static void main(String[] args) throws Exception {
        test();
    }
}
