package indi.pings.JavaDemo.jdk9;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

/**
 *********************************************************
 ** @desc  ： try-with-resources改进
 ** @author  Pings
 ** @date    2020年08月28日
 ** @version v1.0
 * *******************************************************
 */
public class MyTryWithResources {

    public static void main(String[] args) throws IOException {
        Reader reader = new StringReader("pings");
        BufferedReader br = new BufferedReader(reader);

        //**相对于jdk7的改进，可引用外部变量br，br必须是final
        try (reader; br) {
            System.out.println(br.readLine());
        }
    }
}
