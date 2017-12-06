package indi.pings.JavaDemo.jdk8.effective.strategy;

/**
 *********************************************************
 ** @desc  ：  验证策略:验证字符串是否小写                                          
 ** @author  Pings                                     
 ** @date    2017年12月6日  
 ** @version v1.0                                                                                  
 * *******************************************************
 */
public class IsAllLowerCase implements ValidationStrategy {

	@Override
	public boolean execute(String str) {
		return str.matches("[a-z]+");
	}

}
