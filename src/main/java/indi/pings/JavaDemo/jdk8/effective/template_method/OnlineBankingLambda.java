package indi.pings.JavaDemo.jdk8.effective.template_method;

import java.util.function.Consumer;

import indi.pings.JavaDemo.jdk8.effective.template_method.OnlineBanking.Customer;
import indi.pings.JavaDemo.jdk8.effective.template_method.OnlineBanking.Database;

/**
 *********************************************************
 ** @desc  ：  模版方法lambda版本，在线银行                                           
 ** @author  Pings                                     
 ** @date    2017年12月6日  
 ** @version v1.0                                                                                  
 * *******************************************************
 */
public class OnlineBankingLambda {

	public void processCustomer(int id, Consumer<OnlineBanking.Customer> makeCustomerHappy) {
		Customer c = Database.getCustomerWithId(id);
		makeCustomerHappy.accept(c);
	}
}
