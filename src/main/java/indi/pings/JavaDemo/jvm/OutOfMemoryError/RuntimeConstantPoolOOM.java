package indi.pings.JavaDemo.jvm.OutOfMemoryError;

import java.util.ArrayList;
import java.util.List;

/**
 *********************************************************
 ** @desc  ：  方法区和运行时常量池溢出
 **          运行时常量池溢出
 **          在jdk1.6运行时常量池溢出， 在OutOfMemoryError后面跟随的提示信息是“PermGenspace” ， 说明运行时常量池属于方法区（HotSpot虚拟机中的永久代） 的一部分。
 **          在jdk1.7while循环将一直进行下去， jdk1.7用到了字符串常量池.
 **	@VM Args -XX:PermSize=10M -XX:MaxPermSize=10M                                           
 ** @author  Pings                                     
 ** @date    2017年8月22日  
 ** @version v1.0                                                                                  
 * *******************************************************
 */
public class RuntimeConstantPoolOOM {
	
	public static void main(String[] args) {
		//**使用List保持着常量池引用， 避免Full GC回收常量池行为
		List<String> list = new ArrayList<>();
		//**10MB的PermSize在integer范围内足够产生OOM了
		int i = 0;
		while (true) {
			list.add(String.valueOf(i++).intern());
		}
	}
}
