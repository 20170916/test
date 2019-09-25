package com.lo.generic;

public class GenericInterface {
    public interface GenericInterfaceTest<T>{
        void test(T t);
        T test();
    }

    public static class GenericInterfaceTest1<T> implements GenericInterfaceTest<T>{

        @Override
        public void test(T t) {

        }

        @Override
        public T test() {
            return null;
        }
    }

    public static class GenericInterfaceTest2 implements GenericInterfaceTest<String>{

        @Override
        public void test(String s) {

        }

        @Override
        public String test() {
            return null;
        }
    }

    public static void main(String[] args) {
        GenericInterfaceTest<Integer> genericInterfaceTest1 =new GenericInterfaceTest1<>();

        /**
         * GenericInterfaceTest2定义时实现了具体的泛型，所以这里只能用String类型
         */
        GenericInterfaceTest<String> genericInterfaceTest2 = new GenericInterfaceTest2();
    }
}
