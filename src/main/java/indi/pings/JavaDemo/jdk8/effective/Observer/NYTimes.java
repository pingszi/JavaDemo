package indi.pings.JavaDemo.jdk8.effective.Observer;

/**
 *********************************************************
 ** @desc  ：  观察者：纽约时报                                           
 ** @author  Pings                                     
 ** @date    2017年12月6日  
 ** @version v1.0                                                                                  
 * *******************************************************
 */
public class NYTimes implements Observer {

	/**纽约时报关与money有关的字符*/
	@Override
	public void notify(String tweet) {
		if (tweet != null && tweet.contains("money")) {
			System.out.println("Breaking news in NY! " + tweet);
		}
	}

}
