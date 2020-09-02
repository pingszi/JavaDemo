package indi.pings.JavaDemo.jdk10;

import java.util.List;

import static java.util.stream.Collectors.toList;

/**
 *********************************************************
 ** @desc  ： 局部变量类型推断
 **           引入var作为局部变量类型推断标识符，仅适用于局部变量
 *            不能使用于方法形式参数，构造函数形式参数，方法返回类型，catch形式参数或任何其他类型的变量声明
 ** @author  Pings
 ** @date    2020年09月02日
 ** @version v1.0
 * *******************************************************
 */
public class MyVar {

    public static void main(String[] args) {
        var ss = 123;

        //**可以推断出泛型的类型
        var list = List.of(1, 2, 3, 4);
        var newList = list.stream().map(Integer::getClass).collect(toList());
        System.out.println(newList);
    }
}
