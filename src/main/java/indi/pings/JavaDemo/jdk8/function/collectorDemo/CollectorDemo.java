package indi.pings.JavaDemo.jdk8.function.collectorDemo;

import indi.pings.JavaDemo.jdk8.function.Dish;

import static java.util.stream.Collectors.*;

import java.util.Comparator;
import java.util.HashSet;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.IntStream;

/**
 *********************************************************
 ** @desc  ：  Collector示例                                           
 ** @author  Pings                                     
 ** @date    2017年11月29日  
 ** @version v1.0                                                                                  
 * *******************************************************
 */
public class CollectorDemo {
	
	/**判断是否是质数*/
	public static boolean isPrime(int candidate) {
		int candidateRoot = (int) Math.sqrt((double) candidate);
		return IntStream.rangeClosed(2, candidateRoot).noneMatch(i -> candidate % i == 0);
	}

	public static void main(String[] args) {
		//**获取集合数量
		Dish.menu.stream().collect(counting());
		Dish.menu.stream().count();
		//**查找流中的最大值和最小值
		Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);
		Optional<Dish> mostCalorieDish = Dish.menu.stream().collect(maxBy(dishCaloriesComparator));
		System.out.println(mostCalorieDish.get());
		
		//**总和
		int totalCalories = Dish.menu.stream().collect(summingInt(Dish::getCalories));
		//**平均值
		double avgCalories = Dish.menu.stream().collect(averagingInt(Dish::getCalories));
		//**获取元素的个数，总和、平均值、最大值和最小值
		IntSummaryStatistics menuStatistics = Dish.menu.stream().collect(summarizingInt(Dish::getCalories));
		System.out.println(totalCalories);
		System.out.println(avgCalories);
		System.out.println(menuStatistics);
		
		//**连接字符串
		String shortMenu = Dish.menu.stream().map(Dish::getName).collect(joining());
		System.out.println(shortMenu);
		
		//**reducing实现的求和
		int totalCalories1 = Dish.menu.stream().collect(reducing(0, Dish::getCalories, (i, j) -> i + j));
		System.out.println(totalCalories1);
		
		//**分组
		Map<Dish.Type, List<Dish>> dishesByType = Dish.menu.stream().collect(groupingBy(Dish::getType));
		System.out.println(dishesByType);
		//**多级分组
		Map<Dish.Type, Map<String, List<Dish>>> dishesByTypeCaloricLevel = Dish.menu.stream()
				.collect(groupingBy(Dish::getType, groupingBy(dish -> {
					if (dish.getCalories() <= 400)
						return "test1";
					else if (dish.getCalories() <= 700)
						return "test2";
					else
						return "test3";
				})));
		System.out.println(dishesByTypeCaloricLevel);
		//**每种类型的数量
		Map<Dish.Type, Long> typesCount = Dish.menu.stream().collect(groupingBy(Dish::getType, counting()));
		//**每种类型的最大值
		Map<Dish.Type, Optional<Dish>> mostCaloricByType = Dish.menu.stream()
				.collect(groupingBy(Dish::getType, maxBy(Comparator.comparingInt(Dish::getCalories))));
		System.out.println(typesCount);
		System.out.println(mostCaloricByType);
		//**每种类型的最大值,并转换成另一种类型
		Map<Dish.Type, Dish> mostCaloricByType1 = Dish.menu.stream().collect(
				groupingBy(Dish::getType, 
						collectingAndThen(maxBy(Comparator.comparingInt(Dish::getCalories)), Optional::get)));
		System.out.println(mostCaloricByType1);
		//**常常和groupingBy联合使用的另一个收集器是mapping方法生成的
		//**分组，返回HashSet
		Map<Dish.Type, Set<String>> caloricLevelsByType = Dish.menu.stream()
				.collect(groupingBy(Dish::getType, mapping(dish -> {
					if (dish.getCalories() <= 400)
						return "test1";
					else if (dish.getCalories() <= 700)
						return "test2";
					else
						return "test3";
				}, toCollection(HashSet::new))));
		System.out.println(caloricLevelsByType);
		
		//**将数字按质数和非质数分区
		Map<Boolean, List<Integer>> test1 = IntStream.rangeClosed(2, 100).boxed()
				.collect(partitioningBy(candidate -> isPrime(candidate)));
		System.out.println(test1);
	}
}
