package indi.pings.JavaDemo.jdk8.effective.Observer;

/**
 *********************************************************
 ** @desc  ：  观察者                                           
 ** @author  Pings                                     
 ** @date    2017年12月6日  
 ** @version v1.0                                                                                  
 * *******************************************************
 */
public interface Observer {

	/**
	 *********************************************************
	 ** @desc ： 通知                                            
	 ** @author Pings                                    
	 ** @date   2017年12月6日                                      
	 ** @param tweet                                              
	 * *******************************************************
	 */
	void notify(String tweet);
}
