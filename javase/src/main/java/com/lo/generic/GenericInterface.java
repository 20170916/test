package com.lo.generic;

/**
 * 泛型接口：
 * 与泛型类类似。常用与各种类的构造器中。
 * 一个类实现泛型接口时，可以实现具体的泛型，也可以继续使用泛型。
 */
public class GenericInterface {
    /**
     * 定义一个泛型接口
     * @param <T>
     */
    public interface GenericInterfaceTest<T>{
        void test(T t);
        T test();
    }

    /**
     * 实现类继续使用泛型接口定义的泛型，这个实现类就是一个泛型类，在使用这个泛型类时再指定具体的泛型。
     * 这个实现类的方法参数和返回值可继续使用泛型。
     * @param <T>
     */
    public static class GenericInterfaceTest1<T> implements GenericInterfaceTest<T>{

        @Override
        public void test(T t) {

        }

        @Override
        public T test() {
            return null;
        }
    }

    /**
     * 在实现泛型接口时指定泛型。
     */
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
