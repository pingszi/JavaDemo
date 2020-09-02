package indi.pings.JavaDemo.jdk9;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 *********************************************************
 ** @desc  ： stream新增方法
 ** @author  Pings
 ** @date    2020年08月28日
 ** @version v1.0
 * *******************************************************
 */
public class MyStream {

    public static void main(String[] args) {
        List<Integer> list = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        //**takeWhile，返回符合表达式的元素，直到第一次不满足表达式
        List<Integer> list1 = list.stream().takeWhile(i -> i < 5).collect(toList());
        System.out.println(list1);

        //**dropWhile，删除表达式的元素，直到第一次不满足表达式
        List<Integer> list2 = list.stream().dropWhile(i -> i < 5 || i > 9).collect(toList());
        System.out.println(list2);

        //**for(int i = 1; i < 10; i +=2)
        IntStream.iterate(1, x -> x < 10, x -> x + 2).forEach(System.out::println);
        Stream.iterate("ping", s -> s.length() < 10, s -> s = s + "1").forEach(System.out::println);

        //**元素不支持是list，不知道实际有什么用
        Stream.ofNullable(null).forEach(System.out::println);
        //**通过这种方法可以避免list3为空时出现空指针，还是感觉没啥用
        List list3 = null;
        List<Integer> list4 = List.of(1, 2);
        Stream.ofNullable(list3).flatMap(List::stream).forEach(System.out::println);
        Stream.ofNullable(list4).flatMap(List::stream).forEach(System.out::println);
    }
}
