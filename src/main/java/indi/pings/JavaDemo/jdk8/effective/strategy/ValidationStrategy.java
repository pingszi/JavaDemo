package indi.pings.JavaDemo.jdk8.effective.strategy;

/**
 *********************************************************
 ** @desc  ：  验证策略                                          
 ** @author  Pings                                     
 ** @date    2017年12月6日  
 ** @version v1.0                                                                                  
 * *******************************************************
 */
public interface ValidationStrategy {

	/**
	 *********************************************************
	 ** @desc ：  验证字符串                                           
	 ** @author Pings                                    
	 ** @date   2017年12月6日                                      
	 ** @param  str
	 ** @return 验证结果                                             
	 * *******************************************************
	 */
	boolean execute(String str);
}
