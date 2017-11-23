package indi.pings.JavaDemo.jdk8.base;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

/**
 *********************************************************
 ** @desc  ：  苹果，测试传递代码功能                                           
 ** @author  Pings                                     
 ** @date    2017年11月23日  
 ** @version v1.0                                                                                  
 * *******************************************************
 */
public class Apple {
	
	String color;
	int weight;
	
	public Apple(String color, int weight) {
		this.color = color;
		this.weight = weight;
	}

	public String getColor() {
		return this.color;
	}
	
	public int getWeight() {
		return this.weight;
	}

	public static boolean isGreenApple(Apple apple) {
		return "green".equals(apple.getColor());
	}

	public static boolean isHeavyApple(Apple apple) {
		return apple.getWeight() > 150;
	}

	//**方法作为Predicate参数p传递进去
	static List<Apple> filterApples(List<Apple> inventory, Predicate<Apple> p) {
		List<Apple> result = new ArrayList<>();
		for (Apple apple : inventory) {
			//**苹果符合p所代表的条件吗?
			if (p.test(apple)) {
				result.add(apple);
			}
		}
		return result;
	}
	
	@Override
	public String toString() {
		return "Apple [color=" + color + ", weight=" + weight + "]";
	}

	public static void main(String[] args) {
		List<Apple> apples = new ArrayList<>();
		apples.add(new Apple("green", 100));
		apples.add(new Apple("red", 180));
		
		//**筛选苹果
		//**1.传递代码
		System.out.println(filterApples(apples, Apple::isGreenApple));
		System.out.println(filterApples(apples, Apple::isHeavyApple));
		
		//**2.stream，顺序执行
		System.out.println(apples.stream().filter(apple -> apple.weight > 150).collect(toList()));
		
		//**3.stream，并行执行
		System.out.println(apples.parallelStream().filter(apple -> apple.weight > 150).collect(toList()));
	}
}
