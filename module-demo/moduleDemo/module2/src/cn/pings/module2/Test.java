package cn.pings.module2;

import cn.pings.module1.Test1;
import cn.pings.module1.Test2;

import java.util.ServiceLoader;


public class Test {

    public static void main(String[] args) {
        Test1 test1 = new Test1("pings");
        System.out.println(test1.getName());

        //**获取到所有能引用到的Test2实现
        ServiceLoader<Test2> load = ServiceLoader.load(Test2.class);
        load.stream().forEach(t -> t.get().hello("pings" + (int)(Math.random() * 10)));
    }
}
