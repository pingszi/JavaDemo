package indi.pings.JavaDemo.jdk8.effective.factory;

public class Test {

	public static void main(String[] args) {
		//**使用对比
		//**1.使用原始的版本
		//**1.1.定义接口Product
		//**1.2.分别定义实现类Loan和Product
		//**1.3.定义工厂ProductFactory
		Product product1 = ProductFactory.createProduct("loan");
		Product product2 = ProductFactory.createProduct("stock");
		product1.desc();
		product2.desc();
		
		//**1.使用原始的版本
		//**2.1.定义接口Product
		//**2.2.分别定义实现类Loan和Product
		//**2.3.定义工厂，ProductFactoryLambda
		Product product3 = ProductFactoryLambda.createProduct("loan");
		Product product4 = ProductFactoryLambda.createProduct("stock");
		product3.desc();
		product4.desc();
	}
}
