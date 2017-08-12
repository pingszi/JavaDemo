package indi.pings.JavaDemo.javase.escape_analysis;

/**
 *********************************************************
 ** @desc  ：  逃逸分析深入分析   
 **          -XX:+DoEscapeAnalysis 开启逃逸分析（jdk1.8默认开启，其它版本未测试）
 **	         -XX:-DoEscapeAnalysis 关闭逃逸分析                                 
 ** @author  Pings                                     
 ** @date    2017年8月12日  
 ** @version v1.0                                                                                  
 * *******************************************************
 */
public class OnStackTest {
	
	public static void alloc(){
        byte[] b=new byte[2];
        b[0]=1;
    }
	
    public static void main(String[] args) {
        long b=System.currentTimeMillis();
        for(int i=0;i<100000000;i++){
            alloc();
        }
        long e=System.currentTimeMillis();
        System.out.println(e-b);
    }
}

/**
 * 分析逃逸结论：
 * 1.开启：-Xmx1G -XX:+DoEscapeAnalysis -XX:+PrintGC，运行时间6毫秒
 * 2.关闭：-Xmx1G -XX:-DoEscapeAnalysis -XX:+PrintGC，运行时间1500毫秒
 * 3.jdk1.8默认开启
 * 
 * 标量替换结论：
 * 1.开启栈上分配，开启标量替换：-Xmx1G -XX:+DoEscapeAnalysis -XX:+EliminateAllocations -XX:+PrintGC，运行时间6毫秒
 * 2.开启栈上分配，关闭标量替换：-Xmx1G -XX:+DoEscapeAnalysis -XX:-EliminateAllocations -XX:+PrintGC，运行时间1500毫秒
 * 3.jdk1.8默认开启，标量替换基于分析逃逸基础之上，开启标量替换必须开启逃逸分析
 **/
