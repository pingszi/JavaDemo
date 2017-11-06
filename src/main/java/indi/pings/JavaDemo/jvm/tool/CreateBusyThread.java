package indi.pings.JavaDemo.jvm.tool;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *********************************************************
 ** @desc  ：  使用JConsole的线程监控                                    
 ** @author  Pings                                     
 ** @date    2017年9月7日  
 ** @version v1.0                                                                                  
 * *******************************************************
 */
public class CreateBusyThread {
	
	/**
	 * 线程死循环演示
	 */
	public static void createBusyThread() {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				while (true) // 第41行
					;
			}
		}, "testBusyThread");
		thread.start();
	}

	/**
	 * 线程锁等待演示
	 */
	public static void createLockThread(final Object lock) {
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				synchronized (lock) {
					try {
						lock.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}, "testLockThread");
		thread.start();
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		//1.
		new Thread(() -> {try {
			br.readLine();
		} catch (IOException e) {
		}}, "testWaitThread").start();
		//2.
		createBusyThread();
		//3.
		Object obj = new Object();
		createLockThread(obj);
	}
}
