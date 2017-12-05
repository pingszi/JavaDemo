package indi.pings.JavaDemo.jdk8.function.spliterator;

import java.util.Spliterator;
import java.util.function.Consumer;

/**
 *********************************************************
 ** @desc  ：  自定义Spliterator，并行流从空格处进行拆分                                       
 ** @author  Pings                                     
 ** @date    2017年12月5日  
 ** @version v1.0                                                                                  
 * *******************************************************
 */
public class WordCounterSpliterator implements Spliterator<Character> {

	private final String str;
	private int currentChar = 0;
	
	public WordCounterSpliterator(String str) {
		this.str = str;
	}

	@Override
	public boolean tryAdvance(Consumer<? super Character> action) {
		//**处理当前字符
		action.accept(str.charAt(currentChar++));
		//**如果还有字符返回true
		return currentChar < str.length();
	}

	@Override
	public Spliterator<Character> trySplit() {
		int currentSize = str.length() - currentChar;
		
		//**返回null表示要解析的字符串已经足够小，可以顺序处理
		if(currentChar < 10) return null;
		
		//**将试探拆分位置设定为要解析的字符串的中间
		for(int splitPos = currentSize / 2 + currentChar; splitPos < str.length(); splitPos++) {
			//**让拆分位置前进直到下一个空格
			if(Character.isWhitespace(str.charAt(splitPos))) {
				//**创建一个新WordCounterSpliterator来解析字符串从开始到拆分位置的部分
				Spliterator<Character> spliterator = new WordCounterSpliterator(str.substring(currentChar, splitPos));
				//**将这个WordCounterSpliterator的起始位置设为拆分位置
				currentChar = splitPos;
				return spliterator;
			}
		}
		return null;
	}

	@Override
	public long estimateSize() {
		return str.length() - currentChar;
	}

	@Override
	public int characteristics() {
		return ORDERED + SIZED + SUBSIZED + NONNULL + IMMUTABLE;
	}

}
