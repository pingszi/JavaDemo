package indi.pings.JavaDemo.jdk8.effective.completableFuture;

/**
 *********************************************************
 ** @desc  ：  折扣                                           
 ** @author  Pings                                     
 ** @date    2017年12月8日  
 ** @version v1.0                                                                                  
 * *******************************************************
 */
public class Discount {

	public enum Code {
		NONE(0), SILVER(5), GOLD(10), PLATINUM(15), DIAMOND(20);
		private final int percentage;

		public int getPercentage() {
			return percentage;
		}

		Code(int percentage) {
			this.percentage = percentage;
		}
	}
	
	public static String applyDiscount(Quote quote) {
		return quote.getShopName() + " price is " + Discount.apply(quote.getPrice(), quote.getDiscountCode());
	}

	private static double apply(double price, Code code) {
		Shop.delay();
		return price * (100 - code.percentage) / 100;
	}
}
