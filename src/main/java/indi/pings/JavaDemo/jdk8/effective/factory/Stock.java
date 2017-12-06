package indi.pings.JavaDemo.jdk8.effective.factory;

/**
 *********************************************************
 ** @desc  ：  产品，股票                                           
 ** @author  Pings                                     
 ** @date    2017年12月6日  
 ** @version v1.0                                                                                  
 * *******************************************************
 */
public class Stock implements Product {
	
	@Override
	public void desc() {
		System.out.println("Stock");
	}
}
