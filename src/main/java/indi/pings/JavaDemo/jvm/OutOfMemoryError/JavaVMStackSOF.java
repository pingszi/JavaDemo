package indi.pings.JavaDemo.jvm.OutOfMemoryError;

/**
 *********************************************************
 ** @desc  ：  虚拟机栈和本地方法栈溢出
 **          在HotSpot虚拟机中并不区分虚拟机栈和本地方法栈，  对于HotSpot来说， 虽然-Xoss参数 存在， 但实际上是无效的， 栈容量只由-Xss参数设定。 
 **          关于虚拟机栈和本地方法栈， 在Java虚拟机规范中描述了两种异常：
 **			   如果线程请求的栈深度大于虚拟机所允许的最大深度， 将抛出StackOverflowError异常。
 **			   如果虚拟机在扩展栈时无法申请到足够的内存空间， 则抛出OutOfMemoryError异常。
 **	@VM Args -Xss128k                                              
 ** @author  Pings                                     
 ** @date    2017年8月22日  
 ** @version v1.0                                                                                  
 * *******************************************************
 */
public class JavaVMStackSOF {

	private int stackLength = 1;
	
	public void stackLeak() {
		stackLength++;
		stackLeak();
	}

	public static void main(String[] args) throws Throwable {
		JavaVMStackSOF oom = new JavaVMStackSOF();
		try {
			oom.stackLeak();
		} catch (Throwable e) {
			System.out.println("stack length： " + oom.stackLength);
			throw e;
		}
	}
}
/*
stack length： 11425
Exception in thread "main" java.lang.StackOverflowError
*/