package indi.pings.JavaDemo.jdk8.effective.template_method;

/**
 *********************************************************
 ** @desc  ：  模版方法 ，在线银行                                           
 ** @author  Pings                                     
 ** @date    2017年12月6日  
 ** @version v1.0                                                                                  
 * *******************************************************
 */
public abstract class OnlineBanking {

	public void processCustomer(int id) {
		Customer c = Database.getCustomerWithId(id);
		makeCustomerHappy(c);
	}
	
	abstract void makeCustomerHappy(Customer c);
	
	static class Customer {
		private int id;

		public Customer(int id) {
			this.id = id;
		}

		@Override
		public String toString() {
			return "Customer [id=" + id + "]";
		}
		
	}
	
	static class Database {
		public static Customer getCustomerWithId(int id) {
			return new Customer(id);
		}
	}
}
