package indi.pings.JavaDemo.jdk8.effective.factory;

/**
 *********************************************************
 ** @desc  ：  产品，贷款                                           
 ** @author  Pings                                     
 ** @date    2017年12月6日  
 ** @version v1.0                                                                                  
 * *******************************************************
 */
public class Loan implements Product {

	@Override
	public void desc() {
		System.out.println("Loan");
	}

}
