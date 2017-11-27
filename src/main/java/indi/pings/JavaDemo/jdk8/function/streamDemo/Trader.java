package indi.pings.JavaDemo.jdk8.function.streamDemo;

/**
 *********************************************************
 ** @desc  ：  交易员                                           
 ** @author  Pings                                     
 ** @date    2017年11月27日  
 ** @version v1.0                                                                                  
 * *******************************************************
 */
public class Trader {
	private final String name;
	private final String city;

	public Trader(String n, String c) {
		this.name = n;
		this.city = c;
	}

	public String getName() {
		return this.name;
	}

	public String getCity() {
		return this.city;
	}

	public String toString() {
		return "Trader:" + this.name + " in " + this.city;
	}
}
