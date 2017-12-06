package indi.pings.JavaDemo.jdk8.effective.template_method;

/**
 *********************************************************
 ** @desc  ：  模版方法:中国在线银行                                           
 ** @author  Pings                                     
 ** @date    2017年12月6日  
 ** @version v1.0                                                                                  
 * *******************************************************
 */
public class ChinaOnlineBanking extends OnlineBanking {

	@Override
	void makeCustomerHappy(Customer c) {
		System.out.println(c + "：welcome");
	}

}
