package indi.pings.JavaDemo.jdk8.other;

import java.util.Spliterator;
import java.util.concurrent.BlockingQueue;
import java.util.function.Consumer;

/**
 *********************************************************
 ** @desc  ：  自定义Spliterator，用于遍历BlockingQueue对象                                         
 ** @author  Pings                                     
 ** @date    2018年1月8日  
 ** @version v1.0                                                                              
 * *******************************************************
 */
public class BlockingQueueSpliterator<T> implements Spliterator<T> {

	//**需要遍历的BlockingQueue对象
	private final BlockingQueue<T> q;

	BlockingQueueSpliterator(BlockingQueue<T> q) {
		this.q = q;
	}

	/**从BlockingQueue中取得原始流中的元素，元素会被作为进一步处理流的源头传递给Consumer对象（在流上要执行的函数会作为参数传递给某个fork方法调用）。 
	        返回true通知调用方还有其他的元素需要处理，直到它发现由ForkingSteamConsumer添加的特殊对象，表明队列中已经没有更多需要处理的元素了。
	 */
	@Override
	public boolean tryAdvance(Consumer<? super T> action) {
		T t;
		while (true) {
			try {
				t = q.take();
				break;
			} catch (InterruptedException e) {}
		}
		if (t != ForkingStreamConsumer.END_OF_STREAM) {
			action.accept(t);
			return true;
		}
		return false;
	}

	@Override
	public Spliterator<T> trySplit() {
		return null;
	}

	@Override
	public long estimateSize() {
		return 0;
	}

	@Override
	public int characteristics() {
		return 0;
	}
}
