package indi.pings.JavaDemo.jdk8.effective.strategy;

/**
 *********************************************************
 ** @desc  ：  验证策略:验证字符串是否是数字                                      
 ** @author  Pings                                     
 ** @date    2017年12月6日  
 ** @version v1.0                                                                                  
 * *******************************************************
 */
public class IsNumeric implements ValidationStrategy {

	@Override
	public boolean execute(String str) {
		return str.matches("\\d+");
	}

}
