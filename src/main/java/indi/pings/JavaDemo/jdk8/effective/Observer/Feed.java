package indi.pings.JavaDemo.jdk8.effective.Observer;

import java.util.ArrayList;
import java.util.List;

/**
 *********************************************************
 ** @desc  ：  主题                                            
 ** @author  Pings                                     
 ** @date    2017年12月6日  
 ** @version v1.0                                                                                  
 * *******************************************************
 */
public class Feed {

	private final List<Observer> observers = new ArrayList<>();

	public void registerObserver(Observer o) {
		this.observers.add(o);
	}

	public void notifyObservers(String tweet) {
		observers.forEach(o -> o.notify(tweet));
	}
}
