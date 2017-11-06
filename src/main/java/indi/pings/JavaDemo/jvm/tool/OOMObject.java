package indi.pings.JavaDemo.jvm.tool;

import java.util.ArrayList;
import java.util.List;

/**
 *********************************************************
 ** @desc  ：  使用JConsole的“内存” 页签进行监视， 观察曲线和柱状指示图的变化
 **	@VM Args -Xms100m -Xmx100m -XX:+UseSerialGC                                     
 ** @author  Pings                                     
 ** @date    2017年9月7日  
 ** @version v1.0                                                                                  
 * *******************************************************
 */
public class OOMObject {

	public byte[] placeholder = new byte[64 * 1024];

	public static void fillHeap(int num) throws InterruptedException {
		List<OOMObject> list = new ArrayList<OOMObject>();
		for (int i = 0; i < num; i++) {
			//**稍作延时， 令监视曲线的变化更加明显
			Thread.sleep(50);
			list.add(new OOMObject());
		}
		System.gc();
	}

	public static void main(String[] args) throws Exception {
		fillHeap(1000);
	}
}
