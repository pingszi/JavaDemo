package indi.pings.JavaDemo.jdk11;

/**
 *********************************************************
 ** @desc  ： 字符串添加方法
 ** @author  Pings
 ** @date    2020年09月02日
 ** @version v1.0
 * *******************************************************
 */
public class MyString {

    public static void main(String[] args) {
        String str = "pings";

        boolean isBlank = str.isBlank();  //**判断字符串是空白
        System.out.println(isBlank);
        //str = null;
        //System.out.println(str.isBlank()); //**不能判断非null，我还以为可以替换StringUtils.isBlank()方法了

        boolean isEmpty = str.isEmpty();  //判断字符串是否为空
        System.out.println(isEmpty);

        String result1 = str.strip();    //**首位空白
        String result2 = str.stripTrailing();  //**去除尾部空白
        String result3 = str.stripLeading();  //**去除首部空白

        System.out.println("12".repeat(5));  //**复制几遍字符串
        str.lines().forEach(System.out::println);  //**按行读取
    }
}
