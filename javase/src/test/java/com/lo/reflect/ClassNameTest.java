package com.lo.reflect;

/**
 * 父类调用this.getClass().getName()等其他获取类名的方法，都是可以获取到子类的名称，但匿名内部类不能。
 * 匿名内部类需要使用类名.this来指定具体类的this。
 * lambda中可通过this获取所在类的名称
 */
public class ClassNameTest {
    public static void main(String[] args) throws InterruptedException {
        Dao dao=new DaoImpl();
        dao.echoClassName();

        Thread.sleep(1000);
    }
}
abstract class  Dao{
    Runnable runnable;
    public Dao(){
        System.out.println("in constructor "+this.getClass().getSimpleName());
        runnable = new Runnable(){
            @Override
            public void run() {
                /**
                 * 匿名内部类不能获取到类名称。
                 */
                System.out.println("in thread " + this.getClass().getSimpleName());
                /**
                 * 获取所在类名称应该使用类名.this
                 */
                System.out.println("in thread " + Dao.this.getClass().getSimpleName());
            }
        };

    }

    void echoClassName(){
        System.out.println(this.getClass().getSimpleName());
        new Thread(runnable).start();
        new Thread(()->{
            System.out.println("in lambda "+this.getClass().getSimpleName());
        }).start();
    }

}
class DaoImpl extends Dao{


}