package indi.pings.JavaDemo.jdk8.other;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.function.Function;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import static java.util.stream.Collectors.*;

import indi.pings.JavaDemo.jdk8.function.Dish;

/**
 *********************************************************
 ** @desc  ：  为一个流绑定多个Lambda表达式                                       
 ** @author  Pings                                     
 ** @date    2018年1月8日  
 ** @version v1.0                                            
 * *******************************************************
 */
public class StreamForker<T> {

	//**流
	private final Stream<T> stream;
	//**需要绑定的多个Lambda表达式
	private final Map<Object, Function<Stream<T>, ?>> forks = new HashMap<>();

	public StreamForker(Stream<T> stream) {
		this.stream = stream;
	}

	/**
	 *********************************************************
	 ** @desc ：  绑定多个Lambda表达式                                         
	 ** @author Pings                                    
	 ** @date   2018年1月4日                                      
	 ** @param  key   Lambda表达式对应的key，用于获取返回值
	 ** @param  func  绑定的Lambda表达式
	 ** @return 自身，保证多次调用fork方法                                          
	 * *******************************************************
	 */
	public StreamForker<T> fork(Object key, Function<Stream<T>, ?> func) {
		//**使用一个键对流上的函数进行索引
		forks.put(key, func);
		//**返回this从而保证多次流畅地调用fork方法
		return this;
	}

	/**
	 *********************************************************
	 ** @desc ：  获取返回结果（异步）                                         
	 ** @author Pings                                    
	 ** @date   2018年1月4日                                      
	 ** @return 结果对象                                             
	 * *******************************************************
	 */
	public Results getResults() {
		//**创建ForkingStreamConsumer对象
		ForkingStreamConsumer<T> consumer = build();
		try {
			//**把stream中的每个对象分发到ForkingStreamConsumer->queues的每个BlockingQueue中
			stream.sequential().forEach(consumer);
		} finally {
			//**在ForkingStreamConsumer->queues的每个BlockingQueue的末尾存在特殊对象（表示队列结尾）
			consumer.finish();
		}
		
		return consumer;
	}
	
	/**
	 *********************************************************
	 ** @desc ：  创建ForkingStreamConsumer对象                                           
	 ** @author Pings                                    
	 ** @date   2018年1月8日                                      
	 ** @return ForkingStreamConsumer                                             
	 * *******************************************************
	 */
	private ForkingStreamConsumer<T> build() {
		//**创建由队列组成的列表，每一个队列对应一个操作
		List<BlockingQueue<T>> queues = new ArrayList<>();
		//**建立用于标识操作的键与包含操作结果的Future之间的映射关系
		Map<Object, Future<?>> actions = forks.entrySet().stream().reduce(new HashMap<Object, Future<?>>(),
				(map, e) -> {
					map.put(e.getKey(), getOperationResult(queues, e.getValue()));
					return map;
				}, (m1, m2) -> {
					m1.putAll(m2);
					return m1;
				}
		);
		return new ForkingStreamConsumer<>(queues, actions);
	}
	
	/**
	 *********************************************************
	 ** @desc ：  异步执行指定的Lambda表达式                                     
	 ** @author Pings                                    
	 ** @date   2018年1月8日                                      
	 ** @return 异步计算结果                                             
	 * *******************************************************
	 */
	private Future<?> getOperationResult(List<BlockingQueue<T>> queues, Function<Stream<T>, ?> f) {
		//**创建一个队列，并将其添加到队列的列表中
		BlockingQueue<T> queue = new LinkedBlockingQueue<>();
		queues.add(queue);
		
		//**创建Spliterator，用于遍历BlockingQueue对象
		Spliterator<T> spliterator = new BlockingQueueSpliterator<>(queue);
		//**创建流
		Stream<T> source = StreamSupport.stream(spliterator, false);
		//**创建Future对象， 在流上执行以异步方式计算Lambda表达式
		return CompletableFuture.supplyAsync(() -> f.apply(source));
	}
	
	public static void main(String[] args) {
		Stream<Dish> menuStream = Dish.menu.stream();
		Results results = new StreamForker<Dish>(menuStream)
			.fork("shortMenu", s -> s.map(Dish::getName).collect(joining(", ")))
			.fork("totalCalories", s -> s.mapToInt(Dish::getCalories).sum())
			.fork("mostCaloricDish", s -> s.collect(reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2)).get())
			.fork("dishesByType", s -> s.collect(groupingBy(Dish::getType)))
			.getResults();
		
		String shortMenu = results.get("shortMenu");
		int totalCalories = results.get("totalCalories");
		Dish mostCaloricDish = results.get("mostCaloricDish");
		Map<Dish.Type, List<Dish>> dishesByType = results.get("dishesByType");
		
		System.out.println("Short menu: " + shortMenu);
		System.out.println("Total calories: " + totalCalories);
		System.out.println("Most caloric dish: " + mostCaloricDish);
		System.out.println("Dishes by type: " + dishesByType);
	}
}
