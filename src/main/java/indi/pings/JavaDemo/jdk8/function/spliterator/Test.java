package indi.pings.JavaDemo.jdk8.function.spliterator;

import java.util.Spliterator;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Test {
	final static String SENTENCE =
			" Nel mezzo del cammin di nostra vita " +
			"mi ritrovai in una selva oscura" +
			" ché la dritta via era smarrita ";
	
	/**计算单词数量*/
	private static int countWords(Stream<Character> stream) {
		WordCounter wordCounter = stream.reduce(new WordCounter(0, true), WordCounter::accumulate, WordCounter::combine);
		return wordCounter.getCounter();
	}

	public static void main(String[] args) {
		//**直接使用并行流会出现错误（字符串不能在任意位置进行拆分）
		Stream<Character> stream1 = IntStream.range(0, SENTENCE.length()).mapToObj(SENTENCE::charAt);
		System.out.println(countWords(stream1.parallel()));
		
		//**使用自定义spliterator（字符串从空格处进行拆分）
		Spliterator<Character> spliterator = new WordCounterSpliterator(SENTENCE);
		Stream<Character> stream2 = StreamSupport.stream(spliterator, true);
		System.out.println(countWords(stream2));
	}
}
