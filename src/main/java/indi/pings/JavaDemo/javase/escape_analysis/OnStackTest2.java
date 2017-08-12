package indi.pings.JavaDemo.javase.escape_analysis;

import java.io.IOException;

/**
 *********************************************************
 ** @desc  ： 锁消除深入分析         
 **          -XX:+EliminateLocks开启锁消除（jdk1.8默认开启，其它版本未测试）
 **          -XX:-EliminateLocks 关闭锁消除                    
 ** @author  Pings                                     
 ** @date    2017年8月12日  
 ** @version v1.0                                                                                  
 * *******************************************************
 */
public class OnStackTest2 {
	
	public static void alloc(){
		//**默认数组长度大于64不会在栈上分配，以堆上分配为例来测试锁消除带来的影响
        byte[] b=new byte[65];
        //**同步代码块
        synchronized (b) {  
            b[0]=1;
        }
    }
	
    public static void main(String[] args) throws IOException {
        long b=System.currentTimeMillis();
        for(int i=0;i<100000000;i++){
            alloc();
        }
        long e=System.currentTimeMillis();
        System.out.println(e-b);
    }
}

/**
 * 锁消除结论：
 * 1.开启：-Xmx1G -XX:+DoEscapeAnalysis -XX:+EliminateLocks -XX:+PrintGC，运行时间5116毫秒
 * 2.关闭：-Xmx1G -XX:-DoEscapeAnalysis -XX:-EliminateLocks -XX:+PrintGC，运行时间6740毫秒
 * 3.jdk1.8默认开启，锁消除基于分析逃逸基础之上，开启锁消除必须开启逃逸分析
 **/