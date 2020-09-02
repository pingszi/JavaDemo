package indi.pings.JavaDemo.jdk9;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 *********************************************************
 ** @desc  ： 集合工厂方法(创建不可变的集合)，简化创建集合的代码，特别是Map
 ** @author  Pings
 ** @date    2020年08月28日
 ** @version v1.0
 * *******************************************************
 */
public class MyCollections {

    //**集合工厂方法，简化创建集合的代码
    public static void test() {
        List list = List.of(1, 2, 3);
        System.out.println(list);

        Set set = Set.of(1, 2, 3);
        System.out.println(set);

        Map map = Map.of("key1", "value1", "key2", "value2");
        System.out.println(map);
    }

    public static void main(String[] args) {
        test();
    }
}
