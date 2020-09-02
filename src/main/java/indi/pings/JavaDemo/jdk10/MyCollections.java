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
    }

    public static void main(String[] args) {
        test();
    }
}
