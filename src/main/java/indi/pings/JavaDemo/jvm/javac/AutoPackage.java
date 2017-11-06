package indi.pings.JavaDemo.jvm.javac;

/**
 *********************************************************
 ** @desc  ：  自动装箱                                            
 ** @author  Pings                                     
 ** @date    2017年10月20日  
 ** @version v1.0                                                                                  
 * *******************************************************
 */
public class AutoPackage {

	public static void main(String[] args) {
		/*自动装箱，相当于调用了包装类的valueOf(i)方法。
    	      除了double和float包装类，其它的的包装类会使用缓存策略，缓存的这些对象都是经常使用到的（如字符、-128至127之间的数字），
                             防止每次自动装箱都创建一次对象的实例。*/
		Integer a = 1;
		Integer b = 2;
		Integer c = 3;
		Integer d = 3;
		Integer e = 254;
		Integer f = 254;
		Long g = 3L;
		//**在-128和127之间，为同一个Integer对象，true;
		System.out.println(c == d);
		//**大于127，为两个Integer对象，包装类的==运算在不遇到算术运算的情况下不会自动拆箱,即为两个对象的内存地址的对比，false;
		System.out.println(e == f);
		//**包装类的==运算在遇到算术运算的情况下会自动拆箱，即为两个基本类型int对比，true;
		System.out.println(c == (a + b));
		//**a + b自动拆箱计算结果，然后自动装箱为Integer对象，Integer的equals方法为拆箱后值的对比，true;
		System.out.println(c.equals(a + b));
		//**a + b自动拆箱计算结果，然后自动装箱为Integer对象，遇到==运算自动拆箱，隐式类型转换，进行值的对比，true;
		System.out.println(g == (a + b));
		//**a + b自动拆箱计算结果，然后自动装箱为Integer对象，Long的equals首先对比类型，false;
		System.out.println(g.equals(a + b));
	}
}
