package org.ksea.java8.lambda.method.quote;

import org.junit.Test;
import org.ksea.java8.lambda.Employee;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Created by ksea on 2017/6/8.
 * Lmabda的另外一种形式方法引用：
 * <p>
 * 若Lmabda体中的内容有方法已经实现了，我们就可以使用"方法引用"
 * (其可以理解为方法引用是Lambda表达式的另一种表现形式)
 * <p>
 * <p>
 * 方法引用主要有三种语法格式:
 * <p>
 * 1:对象::实例方法名
 * 2:类::静态方法名
 * 3:类::实例方法名
 * <p>
 * <p>
 * 注意：
 * ①Lmabda体中调用方法的参数列表与返回值类型，要与函数式接口中的抽象方法的函数列表和返回值类型保持一致！在这种情况下就可以使用方法引用
 *
 * ②若Lmabda参数列表中的第一个参数是实例方法的调用，而第二个参数是实例方法的参数时，可以使用其ClassName::MethodName方法引用语法
 *
 */
public class LmabdaMethodQuoteTest {


    /**
     * 第一种方法引用
     * 对象::实例方法名
     */
    @Test
    public void test1() {

        //如以下案例
        Consumer<String> consumer = x -> System.out.println(x);
        consumer.accept("hello Lmabda");

        /**
         * 分析上面我们用的是Lambda的内置消费型函数式接口
         * @FunctionalInterface
         * public interface Consumer<T> {
         *    void accept(T t);
         *  }
         *  我们可以看到 消费型函数式接口 Consumer只有一个参数 并且没有返回值
         *
         * 另外 System.out其实是返回的是 PrintStream对象，在PrintStream对象中
         *  public void println(String x) {
         *      synchronized (this) {
         *      print(x);
         *      newLine();
         *      }
         *      }
         * Println打印方法也只有一个参数并没有返回值
         */


        //进行改造
        PrintStream out = System.out;
        consumer = x -> out.println(x);
        consumer.accept("改造之后的Lmabda表达式");

        //使用方法引用进行改造
        consumer = out::println;
        consumer.accept("使用Lmabda方法引用进行改造");
        //此处就等同于
        consumer = System.out::println;
        consumer.accept("使用Lmabda方法引用进行改造........");

        /**
         * 此处分析 这里开始使用了Lmabda方法引用
         * 语法 对象::方法名
         * 需注意的是: 在使用的时候 println()方法与 accept()方法的参数个数以及类型，返回值类型必须保持一致
         *
         */

        //在来一个案例

        Employee employee = new Employee("张学友", 25, 12000);

        //通过方法引用的方式获取该员工的姓名
        Supplier<String> supplier = employee::getName;
        String name = supplier.get();
        System.out.println(name);

        //通过方法引用获取该员工的年龄

        Supplier<Integer> supplier1 = employee::getAge;
        Integer age = supplier1.get();
        System.out.println(age);


    }


    /**
     * 方法引用的第二种语法方式:
     * 类::静态方法名
     */

    @Test
    public void test2() {
        //如一下案例,用Lmabda表达式来实现其 Comparator对2个整数的比较
        Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);

        //其中Integer中的compare方法与Comparator函数式接口中的方法的参数列表以及返回值类型保持一致，此刻我们就使用方法引用进行改造
        comparator = Integer::compare; //这个就非常简洁了


    }


    /**
     * 方法引用的第三种语法方式:
     * 类::实例方法名
     */
    @Test
    public void test3() {

        //如比较2字符串是否相等
        BiPredicate<String, String> biPredicate = (x, y) -> x.equals(y); //Lmabda表达式
        boolean r = biPredicate.test("a", "b");
        System.out.println(r);

        BiPredicate<String, String> biPredicate1=String::equals; //Lmabda方法引用
        boolean r1 = biPredicate1.test("a", "a");
        System.out.println(r1);

    }

}
