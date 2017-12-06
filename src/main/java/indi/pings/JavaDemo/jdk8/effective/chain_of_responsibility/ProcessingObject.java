package indi.pings.JavaDemo.jdk8.effective.chain_of_responsibility;

/**
 *********************************************************
 ** @desc  ：  责任链                                           
 ** @author  Pings                                     
 ** @date    2017年12月6日  
 ** @version v1.0                                                                                  
 * *******************************************************
 */
public abstract class ProcessingObject<T> {
	//**持有自己的引用
	protected ProcessingObject<T> successor;
	
	public void setSuccessor(ProcessingObject<T> successor){
		this.successor = successor;
	}
	
	/**
	 *********************************************************
	 ** @desc ： 类似模块方法，handle方法提供了如何进行工作处理的框架                                            
	 ** @author Pings                                    
	 ** @date   2017年12月6日                                      
	 ** @param input
	 ** @return                                              
	 * *******************************************************
	 */
	public T handle(T input) {
		T r = handleWork(input);
		if (successor != null) {
			return successor.handle(r);
		}
		return r;
	}

	abstract protected T handleWork(T input);
}
