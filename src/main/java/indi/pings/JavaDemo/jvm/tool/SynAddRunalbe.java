package indi.pings.JavaDemo.jvm.tool;

/**
 *********************************************************
 ** @desc  ：  使用JConsole的线程死锁                                
 ** @author  Pings                                     
 ** @date    2017年9月7日  
 ** @version v1.0                                                                                  
 * *******************************************************
 */
public class SynAddRunalbe implements Runnable {
	int a, b;

	public SynAddRunalbe(int a, int b) {
		this.a = a;
		this.b = b;
	}

	@Override
	public void run() {
		synchronized(Integer.valueOf(a)) {
			synchronized(Integer.valueOf(b) ) {
				System.out.println(a+b) ;
			}
		}
	}

	public static void main(String[] args) {
		for (int i = 0; i < 100; i++) {
			new Thread(new SynAddRunalbe(1, 2)).start();
			new Thread(new SynAddRunalbe(2, 1)).start();
		}
	}
}
