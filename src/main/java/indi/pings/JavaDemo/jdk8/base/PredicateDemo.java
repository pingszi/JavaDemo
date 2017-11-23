package indi.pings.JavaDemo.jdk8.base;

import java.util.ArrayList;
import java.util.List;

/**
 *********************************************************
 ** @desc  ：  Predicate接口示例                                          
 ** @author  Pings                                     
 ** @date    2017年11月23日  
 ** @version v1.0                                                                                  
 * *******************************************************
 */
public class PredicateDemo {
	@FunctionalInterface
	public interface Predicate<T> {
		boolean test(T t);
	}

	public static <T> List<T> filter(List<T> list, Predicate<T> p) {
		List<T> results = new ArrayList<>();
		for (T s : list) {
			if (p.test(s)) {
				results.add(s);
			}
		}
		return results;
	}
	
	public static void main(String[] args) {
		List<Apple> apples = new ArrayList<>();
		apples.add(new Apple("green", 100));
		apples.add(new Apple("red", 180));
		
		System.out.println(filter(apples, apple -> apple.getColor().equals("red")));
	}
}
