package indi.pings.JavaDemo.jdk8.base;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static java.util.Comparator.comparing;

/**
 *********************************************************
 ** @desc  ：  复合Lambda                                           
 ** @author  Pings                                     
 ** @date    2017年11月23日  
 ** @version v1.0                                                                                  
 * *******************************************************
 */
public class LambdaDemo {
	
	public static class Letter {
		
		public static String addHeader(String text) {
			return "From Raoul, Mario and Alan: " + text;
		}

		public static String addFooter(String text) {
			return text + " Kind regards";
		}

		public static String checkSpelling(String text) {
			return text.replaceAll("labda", "lambda");
		}
	}

	public static void main(String[] args) {
		List<Apple> apples = new ArrayList<>();
		apples.add(new Apple("green", 100));
		apples.add(new Apple("red", 180));
		
		//**1.比较器复合
		Comparator<Apple> c = Comparator.comparing(Apple::getWeight);
		System.out.println(c);
		//**按重量排序
		apples.sort(comparing(Apple::getWeight));
		//**按重量递减排序
		apples.sort(comparing(Apple::getWeight).reversed());
		apples.sort(comparing(Apple::getWeight)   //**按重量排序
				.reversed()                       //**倒序
				.thenComparing(Apple::getColor)); //**重复排序
		
		System.out.println(apples);
		
		//**2.谓词复合
		//**红苹果
		Predicate<Apple> redApple = (apple) -> apple.getColor().equals("red");
		System.out.println(Apple.filterApples(apples, redApple));
		//**不是红苹果
		Predicate<Apple> notRedApple = redApple.negate();
		System.out.println(Apple.filterApples(apples, notRedApple));
		//*红苹果 and 重量 > 150
		Predicate<Apple> redAndHeavyApple = redApple.and(a -> a.getWeight() > 150);
		System.out.println(Apple.filterApples(apples, redAndHeavyApple));
		//*(红苹果 and 重量 > 150) or 绿苹果
		Predicate<Apple> redAndHeavyAppleOrGreen = 
				redApple.and(a -> a.getWeight() > 150).or(a -> "green".equals(a.getColor()));
		System.out.println(Apple.filterApples(apples, redAndHeavyAppleOrGreen));
		
		//**3.函数复合
		//**andThen先对输入应用一个给定函数，再对输出应用另一个函数（先计算f，然后把计算的结果做为g的输入进行计算，结果为4）
		Function<Integer, Integer> f = x -> x + 1;
		Function<Integer, Integer> g = x -> x * 2;
		Function<Integer, Integer> h = f.andThen(g);
		System.out.println(h.apply(1));
		//**先把给定的函数用作compose的参数里面给的那个函数，然后再把函数本身用于结果（先计算g1，然后把计算的结果做为f1的输入进行计算，结果为3）
		Function<Integer, Integer> f1 = x -> x + 1;
		Function<Integer, Integer> g1 = x -> x * 2;
		Function<Integer, Integer> h1 = f1.compose(g1);
		System.out.println(h1.apply(1));
		//**应用
		Function<String, String> addHeader = Letter::addHeader;
		Function<String, String> transformationPipeline = addHeader.andThen(Letter::checkSpelling).andThen(Letter::addFooter);
		System.out.println(transformationPipeline.apply("test"));
	}
}
