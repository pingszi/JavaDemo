package indi.pings.JavaDemo.jdk9;

/**
 *********************************************************
 ** @desc  ： 接口私有方法
 **           现在接口和抽象方法的区别：
 **             1.抽象方法可以定义各种作用域的属性，接口只能定义公有的静态属性
 **             2.抽象方法可以定义各种作用域的方法，接口只能定义私有方法和公用方法的默认实现
 ** @author  Pings
 ** @date    2020年08月28日
 ** @version v1.0
 * *******************************************************
 */
public interface MyInterface {

    default void a(){
        //**可使用this关键字
        System.out.println("a: " + this.getClass().getSimpleName());
    }

    private void b() {
        //**可使用this关键字
        System.out.println("b: " + this.getClass().getSimpleName());
    }

    class MyInterfaceImpl implements MyInterface { }

    static void main(String[] args) {
        MyInterface myInterface = new MyInterfaceImpl();
        myInterface.a();
        myInterface.b();
    }
}
