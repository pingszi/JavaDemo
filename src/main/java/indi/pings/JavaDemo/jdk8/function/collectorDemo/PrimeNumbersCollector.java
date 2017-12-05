package indi.pings.JavaDemo.jdk8.function.collectorDemo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.IntStream;

import static java.util.stream.Collector.Characteristics.*;

/**
 *********************************************************
 ** @desc  ：  自定义质数收集器                                           
 ** @author  Pings                                     
 ** @date    2017年12月1日  
 ** @version v1.0                                                                                  
 * *******************************************************
 */
public class PrimeNumbersCollector implements Collector<Integer, Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> {

	/**建立新的结果容器*/
	@SuppressWarnings("serial")
	@Override
	public Supplier<Map<Boolean, List<Integer>>> supplier() {
		return () -> new HashMap<Boolean, List<Integer>>() {{
			put(true, new ArrayList<Integer>());
			put(false, new ArrayList<Integer>());
		}};
	}

	/**将元素添加到结果容器*/
	@Override
	public BiConsumer<Map<Boolean, List<Integer>>, Integer> accumulator() {
		return (Map<Boolean, List<Integer>> acc, Integer candidate) -> acc.get(isPrime(acc.get(true), candidate)).add(candidate);
	}

	/**合并两个结果容器*/
	@Override
	public BinaryOperator<Map<Boolean, List<Integer>>> combiner() {
		return null;
	}

	/**对结果容器应用最终转换*/
	@Override
	public Function<Map<Boolean, List<Integer>>, Map<Boolean, List<Integer>>> finisher() {
		return Function.identity();
	}

	@Override
	public Set<Characteristics> characteristics() {
		return Collections.unmodifiableSet(EnumSet.of(IDENTITY_FINISH));
	}

	/**判断质数*/
	private static boolean isPrime(List<Integer> primes, int candidate) {
		int candidateRoot = (int) Math.sqrt((double) candidate);
		return takeWhile(primes, i -> i <= candidateRoot).stream().noneMatch(p -> candidate % p == 0);
	}

	/**截取集合*/
	private static <A> List<A> takeWhile(List<A> list, Predicate<A> p) {
		int i = 0;
		for (A item : list) {
			if (!p.test(item)) {
				return list.subList(0, i);
			}
			i++;
		}
		return list;
	}
	
	public static void main(String[] args) {
		//**使用对比
		//**1.实现Collector接口
		System.out.println(IntStream.rangeClosed(2, 100).boxed().collect(new PrimeNumbersCollector()));
	}
}
