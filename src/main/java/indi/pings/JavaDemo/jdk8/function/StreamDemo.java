package indi.pings.JavaDemo.jdk8.function;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

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
	
	/**["Hello","World"]，返回列表["H","e","l", "o","W","r","d"]*/
	public static void test4() {
		String[] words = {"Goodbye", "World"};
		Object obj = Arrays.stream(words)
						//**将每个单词转换为由其字母构成的数组
						.map(word -> word.split(""))
						//**将各个生成流扁平化为单个流
						.flatMap(Arrays::stream)
						.distinct()
						.collect(toList());
		System.out.println(obj);
	}
	
	/**列表[1, 2, 3]和列表[3, 4]，应该返回[(1, 3), (1, 4), (2, 3), (2, 4), (3, 3), (3, 4)]*/
	public static void test5() {
		List<Integer> i1 = Arrays.asList(1, 2, 3);
		List<Integer> i2 = Arrays.asList(3, 4);
		List<int[]> pairs = i1.stream()
							.flatMap(i -> 
								i2.stream().map(j -> new int[]{i, j})
							)
							.collect(toList());
		System.out.println(pairs);
	}
	
	/**查找元素*/
	public static void test6() {
		Dish.menu.stream()
			.filter(Dish::isVegetarian)
			//**返回一个Optional<Dish>
			.findAny()
			//**如果包含 一 个值就打印它，否则什么都不做
			.ifPresent(d -> System.out.println(d.getName()));
	}
	
	/**生成100以内的勾股数*/
	public static void test7() {
		IntStream.rangeClosed(1, 100).boxed()
			.flatMap(
					a -> IntStream.rangeClosed(a, 100)
								.filter(b -> Math.sqrt(a*a + b*b) % 1 == 0)
								.mapToObj(b -> new int[] {a, b , (int)Math.sqrt(a*a + b*b)})
					)
			.forEach(array -> System.out.println(Arrays.toString(array)));
	}
	
	/**由指定文件中的各行构成的字符串流*/
	public static void test8() {
		//**由指定文件中的各行构成的字符串流
		String path = "D:/java/source/Pings/JavaDemo/src/main/java/indi/pings/JavaDemo/jdk8/function/readme.txt";
		try(Stream<String> lines = Files.lines(Paths.get(path))) {
			lines.forEach(line -> System.out.println(line));
		} catch (IOException e) {
			e.printStackTrace();
		} 
	}
	
	/**生成斐波纳契元组序列
	 * @throws IOException */
	public static void test9() {
		Stream.iterate(new int[]{0, 1},  t -> new int[] {t[1], t[0] + t[1]})
			.limit(20)
			.forEach(array -> System.out.println(Arrays.toString(array)));
	}

	public static void main(String[] args) {
		System.out.println(test1());
		System.out.println(test2());
		System.out.println(test3());
		
		test4();
		test5();
		test6();
		test7();
		test8();
		test9();
	}
}
