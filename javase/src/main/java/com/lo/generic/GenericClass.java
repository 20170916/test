package com.lo.generic;

/**
 * 泛型类：
 * 定义类时，定义泛型；使用类时（例如new 对象），指定泛型的具体参数。
 * 在范型类内部，方法的参数和返回值都可以使用泛型来定义
 */
public class GenericClass {
    public static class GenericsClassTest<A>{
        public void test(A a){
            System.out.println("定义了泛型参数"+" "+a);
        }
        public A test2(A a){
            System.out.println("定义了泛型返回值");
            return a;
        }


    }

    public static void main(String[] args) {
        GenericsClassTest<Integer> genericsClassTest=new GenericsClassTest<>();
        genericsClassTest.test(1);
        System.out.println(genericsClassTest.test2(2));
    }

}
