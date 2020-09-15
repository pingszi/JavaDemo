package indi.pings.JavaDemo.jdk10;

import java.util.List;
import java.util.Map;

/**
 *********************************************************
 ** @desc  ： 集合copyOf静态方法
 ** @author  Pings
 ** @date    2020年09月02日
 ** @version v1.0
 * *******************************************************
 */
public class MyCollections {

    //**集合工厂方法，简化创建集合的代码
    public static void test() {
        var list = List.of(1, 2, 3);
        var newList = List.copyOf(list);
        System.out.println(newList);

        var map = Map.of("key1", "value1", "key2", "value2");
        System.out.println(Map.copyOf(map));

        Test test = new Test();
        var list1 = List.of(new Test(), new Test());
        var newList1 = List.copyOf(list1);
        System.out.println(newList1);

        list1.get(0).name = "pings";
        list1.get(1).name = "pings1";
        System.out.println(list1);
        System.out.println(newList1);
    }

    private static class Test {
        private String name;

        @Override
        public String toString() {
            return "Test{ name='" + name + '}';
        }
    }

    public static void main(String[] args) {
        test();
    }
}
