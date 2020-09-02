package cn.pings.module1;

public class User {

    private String id;
    private String name;
    private String pwd;

    public static User getInstance() {
        User rst = new User();
            rst.id = "10";
            rst.name = "pings";
            rst.pwd = "123456";

        return rst;
    }

    @Override
    public String toString() {
        return "User{id='" + id + ", name='" + name + "', pwd='" + pwd + "'}";
    }
}
