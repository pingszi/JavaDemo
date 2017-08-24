package indi.pings.JavaDemo.jvm.OutOfMemoryError;

/**
 *********************************************************
 ** @desc  ：  虚拟机栈和本地方法栈溢出
 **          创建线程导致内存溢出异常
 **          操作系统分配给每个进程的内存是有限制的， 32位的Windows限制为2GB，2GB（操作系统限制） 减去Xmx（最大堆容量） ， 再减去MaxPermSize（最大方法区容量），
 **          程序计数器消耗内存很小， 可以忽略掉。 如果虚拟机进程本身耗费的内存不计算在内， 剩下的内存就由虚拟机栈和本地方法栈“瓜分” 了。
 **          每个线程分配到的栈容量越大， 可以建立的线程数量自然就越少， 建立线程时就越容易把剩下的内存耗尽。
 **	@VM Args -Xss2M                                             
 ** @author  Pings                                     
 ** @date    2017年8月22日  
 ** @version v1.0                                                                                  
 * *******************************************************
 */
public class JavaVMStackOOM {
	private void dontStop() {
		while (true) {
		}
	}

	public void stackLeakByThread() {
		while (true) {
			Thread thread = new Thread(new Runnable() {
				@Override
				public void run() {
					dontStop();
				}
			});
			thread.start();
		}
	}

	public static void main(String[] args) throws Throwable {
		JavaVMStackOOM oom = new JavaVMStackOOM();
		oom.stackLeakByThread();
	}
}
