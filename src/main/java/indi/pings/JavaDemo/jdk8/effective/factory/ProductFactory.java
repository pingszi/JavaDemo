package indi.pings.JavaDemo.jdk8.effective.factory;

/**
 *********************************************************
 ** @desc  ：   产品工厂                                          
 ** @author  Pings                                     
 ** @date    2017年12月6日  
 ** @version v1.0                                                                                  
 * *******************************************************
 */
public class ProductFactory {

	public static Product createProduct(String name) {
		switch (name) {
		case "loan": return new Loan();
		case "stock": return new Stock();
		default: throw new RuntimeException("No such product " + name);
		}
	}
}
