package indi.pings.JavaDemo.jdk8.effective.Observer;

/**
 *********************************************************
 ** @desc  ：  观察者：卫报                                         
 ** @author  Pings                                     
 ** @date    2017年12月6日  
 ** @version v1.0                                                                                  
 * *******************************************************
 */
public class Guardian implements Observer {

	/**卫报关与queen有关的字符*/
	@Override
	public void notify(String tweet) {
		if (tweet != null && tweet.contains("queen")) {
			System.out.println("Yet another news in London... " + tweet);
		}
	}
}
