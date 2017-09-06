package indi.pings.JavaDemo.jvm.GC;

/**
 *********************************************************
 ** @desc  ：  长期存活的对象将进入老年代
 **	@VM Args -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseSerialGC -XX:MaxTenuringThreshold=1 -XX:+PrintTenuringDistribution                                       
 ** @author  Pings                                     
 ** @date    2017年9月5日  
 ** @version v1.0                                                                                  
 * *******************************************************
 */
public class TestTenuringThreshold {
	private static final int _1MB = 1024 * 1024;

	@SuppressWarnings("unused")
	public static void testTenuringThreshold() {
		byte[] allocation1, allocation2, allocation3;
		allocation1 = new byte[_1MB / 4];
		// 什么时候进入老年代取决于XX： MaxTenuringThreshold设置
		allocation2 = new byte[4 * _1MB];
		allocation3 = new byte[4 * _1MB];
		allocation3 = null;
		allocation3 = new byte[4 * _1MB];
	}
	
	public static void main(String[] args) {
		testTenuringThreshold();
	}
}

/*
MaxTenuringThreshold = 1
[GC (Allocation Failure) [DefNew
Desired survivor size 524288 bytes, new threshold 1 (max 1)
- age   1:     803784 bytes,     803784 total
: 5335K->784K(9216K), 0.0026205 secs] 5335K->4880K(19456K), 0.0026581 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
[GC (Allocation Failure) [DefNew
Desired survivor size 524288 bytes, new threshold 1 (max 1)
: 4880K->0K(9216K), 0.0008987 secs] 8976K->4880K(19456K), 0.0009189 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
Heap
 def new generation   total 9216K, used 4178K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
  eden space 8192K,  51% used [0x00000000fec00000, 0x00000000ff014930, 0x00000000ff400000)
  from space 1024K,   0% used [0x00000000ff400000, 0x00000000ff400000, 0x00000000ff500000)
  to   space 1024K,   0% used [0x00000000ff500000, 0x00000000ff500000, 0x00000000ff600000)
 tenured generation   total 10240K, used 4880K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
   the space 10240K,  47% used [0x00000000ff600000, 0x00000000ffac4028, 0x00000000ffac4200, 0x0000000100000000)
 Metaspace       used 2660K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 286K, capacity 386K, committed 512K, reserved 1048576K
  
MaxTenuringThreshold = 15 (未生效，结果和MaxTenuringThreshold = 1相同，allocation1晋升到了老年代)
[GC (Allocation Failure) [DefNew
Desired survivor size 524288 bytes, new threshold 1 (max 15)
- age   1:     802776 bytes,     802776 total
: 5335K->783K(9216K), 0.0028189 secs] 5335K->4879K(19456K), 0.0028620 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
[GC (Allocation Failure) [DefNew
Desired survivor size 524288 bytes, new threshold 15 (max 15)
: 4879K->0K(9216K), 0.0008943 secs] 8975K->4879K(19456K), 0.0009134 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
Heap
 def new generation   total 9216K, used 4178K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
  eden space 8192K,  51% used [0x00000000fec00000, 0x00000000ff014930, 0x00000000ff400000)
  from space 1024K,   0% used [0x00000000ff400000, 0x00000000ff400000, 0x00000000ff500000)
  to   space 1024K,   0% used [0x00000000ff500000, 0x00000000ff500000, 0x00000000ff600000)
 tenured generation   total 10240K, used 4879K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
   the space 10240K,  47% used [0x00000000ff600000, 0x00000000ffac3c38, 0x00000000ffac3e00, 0x0000000100000000)
 Metaspace       used 2660K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 286K, capacity 386K, committed 512K, reserved 1048576K
 */
