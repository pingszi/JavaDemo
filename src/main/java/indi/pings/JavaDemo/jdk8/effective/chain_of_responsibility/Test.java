package indi.pings.JavaDemo.jdk8.effective.chain_of_responsibility;

import java.util.function.Function;
import java.util.function.UnaryOperator;

public class Test {

	public static void main(String[] args) {
		//**使用对比
		//**1.使用原始的版本
		//**1.1.定义模版ProcessingObject，持有自己的引用
		//**1.2.分别定义实现类HeaderTextProcessing和SpellCheckerProcessing，实现各自的handleWork方法
		//**1.3.把下一个任务链接到当前任务
		ProcessingObject<String> p1 = new HeaderTextProcessing();
		ProcessingObject<String> p2 = new SpellCheckerProcessing();
		//**将两个处理对象链接起来
		p1.setSuccessor(p2);
		String result1 = p1.handle("Aren't labdas really sexy?!!");
		System.out.println(result1);
		
		//**2.使用Lambda的版本
		//**2.1.定义模版，使用默认函数接口UnaryOperator<String>，不需要定义接口
		//**2.2.分别定义实现类，直接传递Lambda代码，不需要定义类
		//**2.3.把下一个任务链接到当前任务，返回Function
		UnaryOperator<String> headerProcessing = (String text) -> "From Raoul, Mario and Alan: " + text;
		UnaryOperator<String> spellCheckerProcessing = (String text) -> text.replaceAll("labda", "lambda");
		Function<String, String> pipeline = headerProcessing.andThen(spellCheckerProcessing);
		String result2 = pipeline.apply("Aren't labdas really sexy?!!");
		System.out.println(result2);
	}
}
