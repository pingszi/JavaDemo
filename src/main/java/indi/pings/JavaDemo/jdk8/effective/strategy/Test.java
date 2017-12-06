package indi.pings.JavaDemo.jdk8.effective.strategy;

/**
 *********************************************************
 ** @desc  ：  测试                                           
 ** @author  Pings                                     
 ** @date    2017年12月6日  
 ** @version v1.0                                                                                  
 * *******************************************************
 */
public class Test {

	public static void main(String[] args) {
		//**使用对比
		//**1.使用原始的版本
		//**1.1.定义接口ValidationStrategy
		//**1.2.分别定义实现类IsAllLowerCase和IsNumeric
		//**1.3.定义消费类Validator
		String str = "test";
		Validator validator1 = new Validator(new IsAllLowerCase());
		System.out.println(validator1.validate(str));
		Validator validator2 = new Validator(new IsNumeric());
		System.out.println(validator2.validate(str));
		
		//**2.使用Lambda的版本
		//**1.1.定义接口，使用默认函数接口Predicate<String>，不需要定义接口
		//**1.2.分别定义实现类，直接传递Lambda代码，不需要定义类
		//**1.3.定义消费类Validator
		Validator validator3 = new Validator(s -> str.matches("[a-z]+"));
		System.out.println(validator3.validate(str));
		Validator validator4 = new Validator(s -> str.matches("\\d+"));
		System.out.println(validator4.validate(str));
	}
}
