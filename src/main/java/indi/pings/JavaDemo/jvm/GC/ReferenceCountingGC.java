package indi.pings.JavaDemo.jvm.GC;

/**
 *********************************************************
 ** @desc  ：  GC
 **          如果使用引用计数算法，对象objA和objB都有字段instance， 赋值令objA.instance=objB及objB.instance=objA， 这两个对象再无任何引用， 
 **          实际上这两个对象已经不可能再被访问， 但是它们因为互相引用着对方， 导致它们的引用计数都不为0， 于是引用计数算法无法通知GC收集器回收它们。  
 **          实际上GC回收了他们，说明虚拟机并不是通过引用计数算法来判断对象是否存活的                                      
 ** @author  Pings                                     
 ** @date    2017年8月25日  
 ** @version v1.0                                                                                  
 * *******************************************************
 */
public class ReferenceCountingGC {

	public Object instance = null;
	private static final int _1MB = 1024 * 1024;
	
	//**这个成员属性的唯一意义就是占点内存， 以便能在GC日志中看清楚是否被回收过
	@SuppressWarnings("unused")
	private byte[] bigSize = new byte[2 * _1MB];

	public static void testGC() {
		ReferenceCountingGC objA = new ReferenceCountingGC();
		ReferenceCountingGC objB = new ReferenceCountingGC();
		objA.instance = objB;
		objB.instance = objA;
		objA = null;
		objB = null;
		//**假设在这行发生GC,objA和objB是否能被回收？
		System.gc();
	}
	
	public static void main(String[] args) {
		testGC();
	}
}

/*
[GC (System.gc()) [PSYoungGen: 6092K->600K(38400K)] 6092K->608K(125952K), 0.0208503 secs] [Times: user=0.00 sys=0.00, real=0.03 secs] 
[Full GC (System.gc()) [PSYoungGen: 600K->0K(38400K)] [ParOldGen: 8K->527K(87552K)] 608K->527K(125952K), [Metaspace: 2654K->2654K(1056768K)], 0.0049007 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
Heap
 PSYoungGen      total 38400K, used 333K [0x00000000d5e00000, 0x00000000d8880000, 0x0000000100000000)
  eden space 33280K, 1% used [0x00000000d5e00000,0x00000000d5e534a8,0x00000000d7e80000)
  from space 5120K, 0% used [0x00000000d7e80000,0x00000000d7e80000,0x00000000d8380000)
  to   space 5120K, 0% used [0x00000000d8380000,0x00000000d8380000,0x00000000d8880000)
 ParOldGen       total 87552K, used 527K [0x0000000081a00000, 0x0000000086f80000, 0x00000000d5e00000)
  object space 87552K, 0% used [0x0000000081a00000,0x0000000081a83c40,0x0000000086f80000)
 Metaspace       used 2660K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 286K, capacity 386K, committed 512K, reserved 1048576K
  */
