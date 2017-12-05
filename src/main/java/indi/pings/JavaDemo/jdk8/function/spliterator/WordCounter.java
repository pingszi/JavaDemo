package indi.pings.JavaDemo.jdk8.function.spliterator;

/**
 *********************************************************
 ** @desc  ：   单词计数器                                          
 ** @author  Pings                                     
 ** @date    2017年12月5日  
 ** @version v1.0                                                                                  
 * *******************************************************
 */
public class WordCounter {

	private final int counter;
	private final boolean lastSpace;
	
	public int getCounter() {
		return counter;
	}

	public WordCounter(int counter, boolean lastSpace) {
		this.counter = counter;
		this.lastSpace = lastSpace;
	}
	
	public WordCounter accumulate(Character c) {
		if (Character.isWhitespace(c)) {
			return lastSpace ? this : new WordCounter(counter, true);
		} else {//**上一个字符是空格，而当前遍历的字符不是空格时，将单词计数器加一
			return lastSpace ? new WordCounter(counter + 1, false) : this;
		}
	}
	
	public WordCounter combine(WordCounter wordCounter) {
		return new WordCounter(counter + wordCounter.counter, wordCounter.lastSpace);
	}
}
