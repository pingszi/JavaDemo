/**
 *********************************************************
 ** @desc  ：默认继承自Object，一个文件只能有一个公有类(内部类也可为公有)，且类名和文件相同。
 **          java的作用域：  私有：private;保护：protected;默认 ：空;公有：public                                    
 ** @author  Pings                                                                               
 * *******************************************************
 */
public class Test {

    public static String p1 = ""; //**公有静态变量
    int p2 = 18;                  //**默认变量，同包可以访问
    protected String p3 = "男";   //**受保护变量，类和子类可以访问
    private String p4 = "张三";   //**私有变量，类内部可访问

    public Test(String str){
        /**构造方法，在创建对象的时候调用*/
        this.p4 = str;
    }

    public void m1(String str){
        /**成员方法，存在this关键字代表类的实例*/
        this.p4 = str;
        System.out.println(str);
    }

    public static void m2(String str){
        /**静态方法。只能通过类调用*/
        System.out.println(str);
    }

    public static void main(String[] args) {
        System.out.println(Test1.s);
        System.out.println(new Test1("").s);
    }
}

class Test1 extends Test {
    /**继承自Test*/

    public static String s = "test";

    public Test1(String str){
        /**构造方法，在创建对象的时候调用*/
        super(str);   //**父类没有无参构造方法时，需要显示调用
    }

    public void m1(String str){
        /**重写父类的m1成员方法*/
        System.out.println(str);
    }
}