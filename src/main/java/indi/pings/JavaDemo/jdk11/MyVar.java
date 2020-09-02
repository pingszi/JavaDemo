package indi.pings.JavaDemo.jdk11;

import java.util.function.Consumer;

/**
 *********************************************************
 ** @desc  ： 局部变量类型推断增强
 **           var可引用到lambda表达式的参数上
 ** @author  Pings
 ** @date    2020年09月02日
 ** @version v1.0
 * *******************************************************
 */
public class MyVar {

    public static void main(String[] args) {
        //**var可引用到lambda表达式的参数上，本来lambda表达式的参数不用写类型也可以，那这样写有什么作用了
        Consumer<String> consumer1 = (var t) -> System.out.println(t.toUpperCase());
        consumer1.accept("pings");

        //**lambda表达式的参数如果要加注解，就必须有类型
        //**作用就是要在lambda表达式的参数加注解时定义参数类型
        Consumer<String> consumer2 = (@Deprecated var t) -> System.out.println(t.toUpperCase());
        consumer2.accept("pings");
    }
}
