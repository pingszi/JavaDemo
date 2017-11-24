package indi.pings.JavaDemo.jdk8.function;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import indi.pings.JavaDemo.jdk8.base.Apple;

/**
 *********************************************************
 ** @desc  ：  stream示例                                           
 ** @author  Pings                                     
 ** @date    2017年11月24日  
 ** @version v1.0                                                                                  
 * *******************************************************
 */
public class StreamDemo {
	
	static List<Apple> apples = new ArrayList<>();
	static {
		apples.add(new Apple("green", 100));
		apples.add(new Apple("red", 180));
	}
	
	/**Java 7*/
	public static List<String> test1() {
		//**用累加器筛选元素
		List<Apple> lowCaloricDishes = new ArrayList<>();
		for (Apple d : apples) {
			if (d.getWeight() < 150) {
				lowCaloricDishes.add(d);
			}
		}
		//**用匿名类对苹果排序
		Collections.sort(lowCaloricDishes, new Comparator<Apple>() {
			public int compare(Apple d1, Apple d2) {
				return Integer.compare(d1.getWeight(), d2.getWeight());
			}
		});
		//**处理排序后的苹果颜色
		List<String> lowCaloricDishesName = new ArrayList<>();
		for (Apple d : lowCaloricDishes) {
			lowCaloricDishesName.add(d.getColor());
		}
		
		return lowCaloricDishesName;
	}
	
	/**Java 8 单线程版本*/
	public static List<String> test2() {
		return apples.stream()
		.filter(d -> d.getWeight() < 150)    //**筛选元素
		.sorted(comparing(Apple::getWeight)) //**对苹果排序
		.map(Apple::getColor)                //**处理排序后的苹果颜色
		.collect(toList());
	}
	
	/**Java 8多线程版本*/
	public static List<String> test3() {
		return apples.parallelStream()
		.filter(d -> d.getWeight() < 150)    //**筛选元素
		.sorted(comparing(Apple::getWeight)) //**对苹果排序
		.map(Apple::getColor)                //**处理排序后的苹果颜色
		.collect(toList());
	}

	public static void main(String[] args) {
		System.out.println(test1());
		System.out.println(test2());
		System.out.println(test3());
	}
}
