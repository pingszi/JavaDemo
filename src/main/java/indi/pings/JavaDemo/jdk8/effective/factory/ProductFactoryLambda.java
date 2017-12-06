package indi.pings.JavaDemo.jdk8.effective.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 *********************************************************
 ** @desc  ：   产品工厂，lambda版本                                        
 ** @author  Pings                                     
 ** @date    2017年12月6日  
 ** @version v1.0                                                                                  
 * *******************************************************
 */
public class ProductFactoryLambda {
	
	final static Map<String, Supplier<Product>> map = new HashMap<>();
	static {
		map.put("loan", Loan::new);
		map.put("stock", Stock::new);
	}

	public static Product createProduct(String name) {
		Supplier<Product> p = map.get(name);
		if(p != null) return p.get();
		throw new IllegalArgumentException("No such product " + name);
	}
}
