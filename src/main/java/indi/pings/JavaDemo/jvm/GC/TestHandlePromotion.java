package indi.pings.JavaDemo.jvm.GC;

/**
 *********************************************************
 ** @desc  ：  空间分配担保,jdk1.8 HandlePromotionFailure参数报错
 **	@VM Args -verbose:gc -Xms20M -Xmx20M -Xmn10M -XX:+PrintGCDetails -XX:SurvivorRatio=8 -XX:+UseSerialGC                                     
 ** @author  Pings                                     
 ** @date    2017年9月5日  
 ** @version v1.0                                                                                  
 * *******************************************************
 */
public class TestHandlePromotion {
	
	private static final int _1MB = 1024 * 1024;

	@SuppressWarnings("unused")
	public static void testHandlePromotion() {
		byte[] allocation1, allocation2, allocation3, allocation4, allocation5, allocation6, allocation7;
		allocation1 = new byte[2 * _1MB];
		allocation2 = new byte[2 * _1MB];
		allocation3 = new byte[2 * _1MB];
		allocation1 = null;
		allocation4 = new byte[2 * _1MB];
		allocation5 = new byte[2 * _1MB];
		allocation6 = new byte[2 * _1MB];
		allocation4 = null;
		allocation5 = null;
		allocation6 = null;
		allocation7 = new byte[2 * _1MB];
	}
	
	public static void main(String[] args) {
		testHandlePromotion();
	}
}

/*
[GC (Allocation Failure) [DefNew: 7127K->527K(9216K), 0.0030632 secs] 7127K->4623K(19456K), 0.0031042 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
[GC (Allocation Failure) [DefNew: 6832K->0K(9216K), 0.0010133 secs] 10928K->4623K(19456K), 0.0010472 secs] [Times: user=0.00 sys=0.00, real=0.00 secs] 
Heap
 def new generation   total 9216K, used 2212K [0x00000000fec00000, 0x00000000ff600000, 0x00000000ff600000)
  eden space 8192K,  27% used [0x00000000fec00000, 0x00000000fee290e0, 0x00000000ff400000)
  from space 1024K,   0% used [0x00000000ff400000, 0x00000000ff400000, 0x00000000ff500000)
  to   space 1024K,   0% used [0x00000000ff500000, 0x00000000ff500000, 0x00000000ff600000)
 tenured generation   total 10240K, used 4623K [0x00000000ff600000, 0x0000000100000000, 0x0000000100000000)
   the space 10240K,  45% used [0x00000000ff600000, 0x00000000ffa83fe0, 0x00000000ffa84000, 0x0000000100000000)
 Metaspace       used 2660K, capacity 4486K, committed 4864K, reserved 1056768K
  class space    used 286K, capacity 386K, committed 512K, reserved 1048576K
*/
