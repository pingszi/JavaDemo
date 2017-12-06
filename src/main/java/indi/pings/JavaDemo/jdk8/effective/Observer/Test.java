package indi.pings.JavaDemo.jdk8.effective.Observer;

public class Test {

	public static void main(String[] args) {
		//**使用对比
		//**1.使用原始的版本
		//**1.1.定义观察者接口Observer
		//**1.2.分别观察者实现类NYTimes、Guardian和LeMonde
		//**1.3.定义主题接口Subject
		//**1.4.定义主题实现类Feed
		Feed feed1 = new Feed();
		feed1.registerObserver(new NYTimes());
		feed1.registerObserver(new Guardian());
		feed1.registerObserver(new LeMonde());
		feed1.notifyObservers("The queen said her favourite book is Java 8 in Action!");
		
		//**2.使用Lambda的版本
		//**2.1.定义观察者接口，使用默认函数接口Predicate<String>，不需要定义接口
		//**2.2.分别观察者实现类，直接传递Lambda代码，不需要定义类
		//**2.3.定义主题接口Subject
		//**2.4.定义主题实现类Feed
		Feed feed2 = new Feed();
		feed2.registerObserver((String tweet) -> {
			if (tweet != null && tweet.contains("money")) {
				System.out.println("Breaking news in NY! " + tweet);
			}
		});
		feed2.registerObserver((String tweet) -> {
			if (tweet != null && tweet.contains("queen")) {
				System.out.println("Yet another news in London... " + tweet);
			}
		});
		feed2.notifyObservers("The queen said her favourite book is Java 8 in Action!");
	}
}
