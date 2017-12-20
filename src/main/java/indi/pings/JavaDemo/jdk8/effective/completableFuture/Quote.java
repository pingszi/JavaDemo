package indi.pings.JavaDemo.jdk8.effective.completableFuture;

import indi.pings.JavaDemo.jdk8.effective.completableFuture.Discount.Code;

/**
 *********************************************************
 ** @desc  ：  计算折扣                                           
 ** @author  Pings                                     
 ** @date    2017年12月8日  
 ** @version v1.0                                                                                  
 * *******************************************************
 */
public class Quote {

	private final String shopName;
	private final double price;
	private final Discount.Code discountCode;
	
	public String getShopName() {
		return shopName;
	}

	public double getPrice() {
		return price;
	}

	public Discount.Code getDiscountCode() {
		return discountCode;
	}

	public Quote(String shopName, double price, Code discountCode) {
		this.shopName = shopName;
		this.price = price;
		this.discountCode = discountCode;
	}
	
	public static Quote parse(String s) {
		String[] split = s.split(":");
		String shopName = split[0];
		double price = Double.parseDouble(split[1]);
		Discount.Code discountCode = Discount.Code.valueOf(split[2]);
		return new Quote(shopName, price, discountCode);
	}
}
