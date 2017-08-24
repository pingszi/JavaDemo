package indi.pings.JavaDemo.jvm.OutOfMemoryError;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

/**
 *********************************************************
 ** @desc  ：  方法区和运行时常量池溢出
 **          借助CGLib使方法区出现内存溢出异常
 **          方法区溢出也是一种常见的内存溢出异常， 一个类要被垃圾收集器回收掉， 判定条件是比较苛刻的。 
 **          在经常动态生成大量Class的应用中， 需要特别注意类的回收状况。 这类场景除了上面提到的程序使用了CGLib字节码增强和动态语言之外，
 **          常见的还有： 大量JSP或动态产生JSP文件的应用（JSP第一次运行时需要编译为Java类） 、 基于OSGi的应用（即使是同一个类文件， 被不同的加载器加载也会视为不同的类）。
 **	@VM Args -XX:PermSize=10M -XX:MaxPermSize=10M                                          
 ** @author  Pings                                     
 ** @date    2017年8月23日  
 ** @version v1.0                                                                                  
 * *******************************************************
 */
public class JavaMethodAreaOOM {
	
	public static void main(String[] args) {
		while (true) {
			Enhancer enhancer = new Enhancer();
			enhancer.setSuperclass(OOMObject.class);
			enhancer.setUseCache(false);
			enhancer.setCallback(new MethodInterceptor() {
				
				@Override
				public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
					return proxy.invokeSuper(obj, args);
				}
				
			});
			enhancer.create();
		}
	}

	static class OOMObject {}
}

/*
测试结果内存占用率一直在增加，但是没有出现java.lang.OutOfMemoryError异常，
jdk1.8，取消了方法区，改为元空间，元空间使用参数-XX:MaxMetaspaceSzie设定大小，是一块堆外的直接内存，如果不指定大小，虚拟机会耗尽可用系统内存。
*/

