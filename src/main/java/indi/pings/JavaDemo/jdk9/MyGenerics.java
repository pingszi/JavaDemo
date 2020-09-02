package indi.pings.JavaDemo.jdk9;

/**
 *********************************************************
 ** @desc  ： 泛型改进
 ** @author  Pings
 ** @date    2020年08月28日
 ** @version v1.0
 * *******************************************************
 */
public class MyGenerics {

    interface MyClass<T> {
        T get();

        T get1();
    }

    public static void main(String[] args) {
        //**匿名类泛型推断
        MyClass<String> myClass = new MyClass<>() {
            @Override
            public String get() {
                return "pings";
            }

            @Override
            public String get1() {
                return "pings1";
            }
        };

        System.out.println(myClass.get());
    }
}
