package indi.pings.JavaDemo.jvm.OutOfMemoryError;

/**
 *********************************************************
 ** @desc  ：  直接内存溢出
 **          DirectMemory容量可通过-XX： MaxDirectMemorySize指定， 如果不指定， 则默认与Java堆最大值（-Xmx指定）一样， 
 **          通过反射获取Unsafe实例进行内存分配Unsafe类的getUnsafe()方法限制了只有引导类加载器才会返回实例。
 **          使用DirectByteBuffer分配内存也会抛出内存溢出， 但抛出异常时并没有真正向操作系统申请分配内存， 而是通过计算得知内存无法分配， 然后手动抛出异常， 
 **          真正申请分配内存的方法是unsafe.allocateMemory()
 **          由DirectMemory导致的内存溢出， 明显的特征是在Heap Dump文件中没有明显的异常， 如果OOM之后Dump文件很小， 而程序中又直接或间接使用了NIO， 有可能是直接内存溢出。
 **	@VM Args -Xmx20M -XX:MaxDirectMemorySize=10M                                         
 ** @author  Pings                                     
 ** @date    2017年8月24日  
 ** @version v1.0                                                                                  
 * *******************************************************
 */
public class DirectMemoryOOM {
	/*
	private static final int _1MB = 1024 * 1024;

	public static void main(String[] args) throws Exception {
		Field unsafeField = Unsafe.class.getDeclaredFields()[0];
		unsafeField.setAccessible(true);
		Unsafe unsafe = (Unsafe) unsafeField.get(null);
		while (true) {
			unsafe.allocateMemory(_1MB);
		}
	}*/
}

/*
jdk1.8，Unsafe类不在允许使用
*/