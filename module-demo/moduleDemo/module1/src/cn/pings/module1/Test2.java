package cn.pings.module1;

public interface Test2 {

    void hello(String name);

    class Test2Impl implements Test2 {

        @Override
        public void hello(String name) {
            System.out.println(name);
        }
    }
}
