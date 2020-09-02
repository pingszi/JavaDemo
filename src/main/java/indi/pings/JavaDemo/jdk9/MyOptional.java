package indi.pings.JavaDemo.jdk9;

import java.util.List;
import java.util.Optional;

import static java.util.stream.Collectors.toList;

/**
 *********************************************************
 ** @desc  ： Optional新增方法
 ** @author  Pings
 ** @date    2020年08月28日
 ** @version v1.0
 * *******************************************************
 */
public class MyOptional {

    public static void main(String[] args) {
        //**Optional.stream
        List<Optional<String>> list = List.of(Optional.of("A"), Optional.empty(), Optional.of("B"));
        list.stream().flatMap(Optional::stream).forEach(System.out::println);

        //**Optional.ifPresentOrElse
        List<Integer> list1 = List.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        Optional<Integer> optional = list1.stream().filter(i -> i > 5).findAny();
        optional.ifPresentOrElse(System.out::println, () -> { throw new IllegalArgumentException(); });

        //**Optional.or，与orElseGet区别：orElseGet参数返回Optional包装的类型，or仍然返回Optional
        optional = list1.stream().filter(i -> i > 10).findAny();
        Optional optional1 = optional.or(() -> Optional.of(100));
        int rst = optional.orElseGet(() -> 100);
        System.out.println(optional1.get());
        System.out.println(rst);
    }
}
