package indi.pings.JavaDemo.jdk8.effective.Observer;

/**
 *********************************************************
 ** @desc  ：  主题                                           
 ** @author  Pings                                     
 ** @date    2017年12月6日  
 ** @version v1.0                                                                                  
 * *******************************************************
 */
public interface Subject {
	/**
	 *********************************************************
	 ** @desc ： 注册观察者                                            
	 ** @author Pings                                    
	 ** @date   2017年12月6日                                      
	 ** @param  o                                              
	 * *******************************************************
	 */
	void registerObserver(Observer o);
	/**
	 *********************************************************
	 ** @desc ： 通知观察者                                            
	 ** @author Pings                                    
	 ** @date   2017年12月6日                                      
	 ** @param  tweet                                             
	 * *******************************************************
	 */
	void notifyObservers(String tweet);
}
