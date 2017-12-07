package indi.pings.JavaDemo.jdk8.effective.optional;

import java.util.Optional;

/**
 *********************************************************
 ** @desc  ：  Optional示例                                           
 ** @author  Pings                                     
 ** @date    2017年12月7日  
 ** @version v1.0                                                                                  
 * *******************************************************
 */
public class Person {
	//**人可能有车，也可能没有车，因此将这个字段声明为Optional
	private Optional<Car> car;
	public Optional<Car> getCar() { return car; }
	
	static class Car {
		//**车可能进行了保险，也可能没有保险，所以将这个字段声明为Optional
		private Optional<Insurance> insurance;
		public Optional<Insurance> getInsurance() { return insurance; }
	}
	
	static class Insurance {
		//**保险公司必须有名字
		private String name;
		public String getName() { return name; }
	}
	
	public static String getCarInsuranceName(Optional<Person> person) {
		return person.flatMap(Person::getCar).flatMap(Car::getInsurance).map(Insurance::getName).orElse("Unknown");
	}
	
	public Insurance findCheapestInsurance(Person person, Car car) {
		// 不同的保险公司提供的查询服务
		// 对比所有数据
		Insurance insurance = new Insurance();
		insurance.name = "test";
		return insurance;
	}
	
	public Optional<Insurance> nullSafeFindCheapestInsurance(Optional<Person> person, Optional<Car> car) {
		return person.flatMap(p -> car.map(c -> findCheapestInsurance(p, c)));
	}
	
	public static void main(String[] args) {
		Optional<Person> person = Optional.ofNullable(null);
		System.out.println(getCarInsuranceName(person));
	}
}
