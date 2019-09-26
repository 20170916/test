package com.lo.generic;

/**
 * 泛型方法。
 * 是调用方法时指定指明泛型的具体类型。
 * 可独立于类产生变化。
 */
public class GenericMethod {


    /**
     * 返回值前<T>声明泛型，T可使用任意标识符。
     * @param tClass
     * @param <T>
     * @return
     * @throws IllegalAccessException
     * @throws InstantiationException
     */
    public <T> T test(Class<T> tClass) throws IllegalAccessException, InstantiationException {
        final T t = tClass.newInstance();
        return t;
    }


    /**
     * 泛型方法与可变参数
     * @param args
     * @param <T>
     */
    public <T> void printMsg( T... args){
        for(T t : args){
            System.out.println("泛型测试"+"t is " + t);
        }
    }

    /**
     * 静态方法使用泛型时，必须定义成泛型方法
     * @param t
     * @param <T>
     */
    public static <T> void show(T t){

    }
}
