package indi.pings.JavaDemo.jdk8.effective.chain_of_responsibility;

/**
 *********************************************************
 ** @desc  ：  责任链                                           
 ** @author  Pings                                     
 ** @date    2017年12月6日  
 ** @version v1.0                                                                                  
 * *******************************************************
 */
public class HeaderTextProcessing extends ProcessingObject<String> {

	@Override
	protected String handleWork(String text) {
		return "From Raoul, Mario and Alan: " + text;
	}

}
