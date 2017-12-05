package indi.pings.JavaDemo.jdk8.function.collectorDemo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.EnumSet;
import java.util.List;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;

import indi.pings.JavaDemo.jdk8.function.Dish;

import static java.util.stream.Collector.Characteristics.*;
import static java.util.stream.Collectors.*;

/**
 *********************************************************
 ** @desc  ：  自定义收集器，模拟toList()                                           
 ** @author  Pings                                     
 ** @date    2017年12月1日  
 ** @version v1.0                                                                                  
 * *******************************************************
 */
public class ToListCollector<T> implements Collector<T, List<T>, List<T>> {

	/**建立新的结果容器： supplier方法
	   supplier方法必须返回一个结果为空的Supplier，也就是一个无参数函数，在调用时它会创建一个空的累加器实例，供数据收集过程使用。
	*/
	@Override
	public Supplier<List<T>> supplier() {
		//return () -> new ArrayList<>();
		return ArrayList::new;
	}

	/**将元素添加到结果容器： accumulator方法 
	   accumulator方法会返回执行归约操作的函数。当遍历到流中第n个元素时，这个函数执行时会有两个参数：保存归约结果的累加器（已收集了流中的前 n-1 个项目），
	         还有第n个元素本身。该函数将返回void，因为累加器是原位更新，即函数的执行改变了它的内部状态以体现遍历的元素的效果。
	 */
	@Override
	public BiConsumer<List<T>, T> accumulator() {
		//return (list, item) -> list.add(item);
		return List::add;
	}

	/**合并两个结果容器： combiner方法
       combiner方法会返回一个供归约操作使用的函数，它定义了对流的各个子部分进行并行处理时，各个子部分归约所得的累加器要如何合并。
                     对流进行并行归约会用到Java 7中引入的分支/合并框架和Spliterator抽象。
     */
	@Override
	public BinaryOperator<List<T>> combiner() {
		return (list1, list2) -> {
			list1.addAll(list2);
			return list1;
		};
	}

	/**对结果容器应用最终转换： finisher方法
                    在遍历完流后， finisher方法必须返回在累积过程的最后要调用的一个函数，以便将累加器对象转换为整个集合操作的最终结果，
                    累加器对象恰好符合预期的最终结果，因此无需进行转换。所以finisher方法只需返回identity函数。
	 */
	@Override
	public Function<List<T>, List<T>> finisher() {
		return Function.identity();
	}

	/** 为收集器添加IDENTITY_FINISH和CONCURRENT标志*/
	@Override
	public Set<Characteristics> characteristics() {
		return Collections.unmodifiableSet(EnumSet.of(IDENTITY_FINISH, CONCURRENT));
	}

	public static void main(String[] args) {
		//**使用对比
		//**1.实现Collector接口
		System.out.println(Dish.menu.stream().map(Dish::getName).collect(new ToListCollector<String>()));
		//**2.自带toList
		System.out.println(Dish.menu.stream().map(Dish::getName).collect(toList()));
		//**3.自定义收集
		System.out.println(Dish.menu.stream().collect(ArrayList::new, List::add, List::addAll));
	}
}
