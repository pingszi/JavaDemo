package indi.pings.JavaDemo.jdk8.other;

import java.util.List;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Future;
import java.util.function.Consumer;

/**
 *********************************************************
 ** @desc  ：  自定义Consumer对象                              
 ** @author  Pings                                     
 ** @date    2018年1月8日  
 ** @version v1.0                                            
 * *******************************************************
 */
public class ForkingStreamConsumer<T> implements Consumer<T>, Results {
	
	//**截止符，表示队列结尾
	static final Object END_OF_STREAM = new Object();
	//**队列，按顺序存放StreamForker->stream中的每个元素
	private final List<BlockingQueue<T>> queues;
	//**异步计算结果，存放StreamForker->forks中每个Lambda表达式的异步计算结果
	private final Map<Object, Future<?>> actions;

	ForkingStreamConsumer(List<BlockingQueue<T>> queues, Map<Object, Future<?>> actions) {
		this.queues = queues;
		this.actions = actions;
	}

	@Override
	public void accept(T t) {
		//**将流中遍历的元素添加到所有的队列中
		queues.forEach(q -> q.add(t));
	}

	@SuppressWarnings("unchecked")
	void finish() {
		//**将截止符添加到队列中，表明该流已经结束
		accept((T) END_OF_STREAM);
	}

	@SuppressWarnings("unchecked")
	@Override
	public <R> R get(Object key) {
		try {
			//**等待Future完成相关的计算，返回由特定键标识的处理结果
			return ((Future<R>) actions.get(key)).get();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
