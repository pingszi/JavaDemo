package indi.pings.JavaDemo.javase.escape_analysis;

/**
 *********************************************************
 ** @desc  ：  方法逃逸的几种方式                                       
 ** @author  Pings                                     
 ** @date    2017年8月12日  
 ** @version v1.0                                                                                  
 * *******************************************************
 */
public class EscapeTest {

	public static Object obj;
	
	//**给全局变量赋值，发生逃逸
    public void globalVariableEscape() {  
        obj = new Object();
    }
    
    //**方法返回值，发生逃逸
    public Object methodEscape() {  
        return new Object();
    }
    
    //**实例引用发生逃逸
    public void instanceEscape() {  
        test(this); 
    }
    
    public void test(EscapeTest e) {}
}
