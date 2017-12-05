package indi.pings.JavaDemo.jdk8.function;

import java.util.Arrays;
import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 *********************************************************
 ** @desc  ：  菜肴                                          
 ** @author  Pings                                     
 ** @date    2017年11月24日  
 ** @version v1.0                                                                                  
 * *******************************************************
 */
public class Dish {
	private final String name;
	private final boolean vegetarian;
	private final int calories;
	private final Type type;
	
	public static List<Dish> menu = Arrays.asList(
			new Dish("pork", false, 800, Dish.Type.MEAT),
			new Dish("beef", false, 700, Dish.Type.MEAT),
			new Dish("chicken", false, 400, Dish.Type.MEAT),
			new Dish("french fries", true, 530, Dish.Type.OTHER),
			new Dish("rice", true, 350, Dish.Type.OTHER),
			new Dish("season fruit", true, 120, Dish.Type.OTHER),
			new Dish("pizza", true, 550, Dish.Type.OTHER),
			new Dish("prawns", false, 300, Dish.Type.FISH),
			new Dish("salmon", false, 450, Dish.Type.FISH) );

	public Dish(String name, boolean vegetarian, int calories, Type type) {
		this.name = name;
		this.vegetarian = vegetarian;
		this.calories = calories;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public boolean isVegetarian() {
		return vegetarian;
	}

	public int getCalories() {
		return calories;
	}

	public Type getType() {
		return type;
	}

	@Override
	public String toString() {
		return name;
	}

	public enum Type {
		MEAT, FISH, OTHER
	}
	
	public static void main(String[] args) {
		//**尽管filter和map是两个独立的操作，但它们合并到同一次遍历中了（我们把这种技术叫作循环合并）。
		List<String> names = menu.stream().filter(d -> {
			System.out.println("filtering" + d.getName());
			return d.getCalories() > 300;
		}).map(d -> {
			System.out.println("mapping" + d.getName());
			return d.getName();
		}).limit(3)  //**limit操作是一种短路技巧
		  .collect(toList());
		System.out.println(names);
	}
}
