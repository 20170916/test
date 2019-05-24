package com.lo.classloader;

import com.mysql.jdbc.Driver;
import org.apache.hive.jdbc.HiveDriver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * 测试jdbc驱动的加载流程。<br>
 * 1。DriverManager#getConnection方法执行前，会先执行DriverManager的静态块
 *   1.1：扫描jar包
 *       静态块的初始化方法回将classpath目录下所有jar包的META-INF/services/java.sql.Driver文件加载到内存。
 *       这是java的spi规范的jar包。
 *       该文件里存储了对应的jdbc驱动类Driver的全限定名。
 *   1.2：加载Driver
 *       DriverManager使用线程上下文类加载器或appClassLoader加载器来加载这个jdbc的Driver
 *       DriverManager是bootStrapClassLoader启动类加载器来加载的，但它并不使用启动类加载器加载jdbc驱动Driver。
 *       而是用线程上下文类加载器来加载Driver。如果线程上下文类加载器为null，会直接使用系统类加载器，即appClassLoader来加载driver。
 *   1.3：Driver执行静态块
 *       jdbc的Driver静态块会new一个自己并注册到java的DriverManager的registeredDrivers属性中
 * 2。DriverManager#getConection方法执行后
 *   DriverManager会遍历所有的registeredDrivers来获取对应url的jdbcDriver的connection。
 *   找到第一个就会直接return返回。
 */
public class JdbcTest {
    public static void main(String[] args) throws SQLException {
        Thread.currentThread().setContextClassLoader(new ClassLoader() {
            @Override
            public Class<?> loadClass(String name) throws ClassNotFoundException {
                return super.loadClass(name);
            }
        });
        final Connection connection = DriverManager.getConnection("jdbc:mysql2://192.168.56.3:3306/test?useUnicode=true&characterEncoding=utf8","root","123456");

        System.out.println(connection.getClass().getClassLoader());
        //com.mysql.jdbc.Driver的类加载器是AppClassLoader
        System.out.println(Driver.class.getClassLoader());
        System.out.println(HiveDriver.class.getClassLoader());
    }
}
