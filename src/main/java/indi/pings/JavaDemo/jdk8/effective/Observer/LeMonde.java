package indi.pings.JavaDemo.jdk8.effective.Observer;

/**
 *********************************************************
 ** @desc  ：  观察者：世界报                                        
 ** @author  Pings                                     
 ** @date    2017年12月6日  
 ** @version v1.0                                                                                  
 * *******************************************************
 */
public class LeMonde implements Observer {

	/**世界报关与wine有关的字符*/
	@Override
	public void notify(String tweet) {
		if (tweet != null && tweet.contains("wine")) {
			System.out.println("Today cheese, wine and news! " + tweet);
		}
	}
}
