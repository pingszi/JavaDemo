package indi.pings.JavaDemo.jdk8.effective.strategy;

/**
 *********************************************************
 ** @desc  ：  验证器                                           
 ** @author  Pings                                     
 ** @date    2017年12月6日  
 ** @version v1.0                                                                                  
 * *******************************************************
 */
public class Validator {

	private final ValidationStrategy strategy;

	public Validator(ValidationStrategy v) {
		this.strategy = v;
	}
	
	public boolean validate(String str) {
		return this.strategy.execute(str);
	}
}
