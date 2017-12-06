package indi.pings.JavaDemo.jdk8.effective.template_method;

public class Test {

	public static void main(String[] args) {
		//**使用对比
		//**1.使用原始的版本
		//**1.1.定义模版方法OnlineBanking
		//**1.2.定义实现类ChinaOnlineBanking，实现makeCustomerHappy方法
		OnlineBanking bank1 = new ChinaOnlineBanking();
		bank1.processCustomer(123);
		
		//**2.使用Lambda的版本
		//**2.1.定义模版方法OnlineBankingLambda
		//**2.2.定义实现类,直接传递Lambda代码，不需要定义类
		OnlineBankingLambda bank2 = new OnlineBankingLambda();
		bank2.processCustomer(123, c -> System.out.println(c + "：welcome"));
	}
}
