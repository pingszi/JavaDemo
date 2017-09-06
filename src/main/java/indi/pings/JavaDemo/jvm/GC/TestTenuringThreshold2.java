package indi.pings.JavaDemo.jvm.GC;

/**
 *********************************************************
 ** @desc  ：  动态对象年龄判定
 **	@VM Args -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseSerialGC -XX:MaxTenuringThreshold=15 -XX:+PrintTenuringDistribution                                       
 ** @author  Pings                                     
 ** @date    2017年8月24日  
 ** @version v1.0                                                                                  
 * *******************************************************
 */
public class TestTenuringThreshold2 {
	
	private static final int _1MB = 1024 * 1024;

	@SuppressWarnings("unused")
	public static void testTenuringThreshold2() {
		byte[] allocation1, allocation2, allocation3, allocation4;
		allocation1 = new byte[_1MB / 4];
		// allocation1+allocation2大于survivo空间一半
		allocation2 = new byte[_1MB / 4];
		allocation3 = new byte[4 * _1MB];
		allocation4 = new byte[4 * _1MB];
		allocation4 = null;
		allocation4 = new byte[4 * _1MB];
	}
	
	public static void main(String[] args) {
		testTenuringThreshold2();
	}
}
/*
[GC (Allocation Failure) [DefNew
Desired survivor size 524288 bytes, new threshold 1 (max 15)
- age   1:    1048568 bytes,    1048568 total
: 5591K->1023K(9216K), 0.0029626 secs] 5591K->5136K(19456K), 0.0030249 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
[GC (Allocation Failure) [DefNew
Desired survivor size 524288 bytes, new threshold 15 (max 15)
: 5120K->0K(9216K), 0.0010205 secs] 9232K->5135K(19456K), 0.0010420 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
Heap
 def new generation   total 9216K, used 4178K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
  eden space 8192K,  51% used [0x00000000fec00000, 0x00000000ff014930, 0x00000000ff400000)
  from space 1024K,   0% used [0x00000000ff400000, 0x00000000ff400000, 0x00000000ff500000)
  to   space 1024K,   0% used [0x00000000ff500000, 0x00000000ff500000, 0x00000000ff600000)
 tenured generation   total 10240K, used 5135K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
   the space 10240K,  50% used [0x00000000ff600000, 0x00000000ffb03f90, 0x00000000ffb04000, 0x0000000100000000)
 Metaspace       used 2660K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 286K, capacity 386K, committed 512K, reserved 1048576K
*/
