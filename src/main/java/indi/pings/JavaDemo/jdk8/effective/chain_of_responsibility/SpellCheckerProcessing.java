package indi.pings.JavaDemo.jdk8.effective.chain_of_responsibility;

/**
 *********************************************************
 ** @desc  ：  责任链                                           
 ** @author  Pings                                     
 ** @date    2017年12月6日  
 ** @version v1.0                                                                                  
 * *******************************************************
 */
public class SpellCheckerProcessing extends ProcessingObject<String> {

	@Override
	protected String handleWork(String text) {
		return text.replaceAll("labda", "lambda");
	}

}
