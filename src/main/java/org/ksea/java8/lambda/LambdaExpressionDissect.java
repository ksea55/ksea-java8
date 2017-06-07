package org.ksea.java8.lambda;

import org.junit.Test;

import java.util.Comparator;
import java.util.function.Consumer;

/**
 * Created by ksea on 2017/6/7.
 * Lambda语法剖析
 * 一、Lambda 表达式的基础语法：
 * java8中引入了一个新的操作符"->"该操作符称为箭头操作符或者 Lambda操作符
 * <p>
 * 箭头操作符将Lmabda表达式拆分成两部分:
 * 左侧：Lambda 表达式的参数列表
 * 右侧：Lmabda 表达式中所需要执行的功能  既 Lmabda体
 * <p>
 * <p>
 * <p>
 * 二、Lmabda语法格式
 * 语法格式1:无参数，无返回值
 * ()-> System.out.println("hello") 案例参考test1()
 * <p>
 * <p>
 * <p>
 * 语法格式2：有1个参数并且无返回值
 * (x) -> System.out.println("这里是Lambda对 Consumer接口中的accept(T t)方法的实现:" + x); 案例参考test2()
 * <p>
 * <p>
 * 语法格式3：有1个参数并且无返回值
 * x -> System.out.println("左侧Lmabda的小括号没写:" + x);
 * 语法格式3与语法格式2是一样的，在小括号去掉的前提是左侧参数只有一个，但是一般情况下建议写上小括号
 * <p>
 * <p>
 * <p>
 * 语法格式4：有2个以上的参数，有返回值，并且在Lmabda体中有多条语句 案例参考 test4()
 * <p>
 * (x, y) -> {
 * System.out.println("执行Lmabda函数");
 * return Integer.compare(x, y);//Lambda返回值
 * };
 * 当Lmabda表达式有多个参数的时候，左侧必须写小括号(x, y)
 * 当Lmabda体有多个执行语句的时候，右侧必须写大括号{};
 * <p>
 * <p>
 * <p>
 * 语法格式5：有2个以上的参数，有返回值，并且在Lmabda体中只有一条语句，其return与大括号均可以不写  案例参考test5()
 * Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);
 * <p>
 * <p>
 * 二、Lambda 表达式需要"函数式接口"的支持
 * 函数式接口：接口中只有一个抽象方法的接口，也就是在该接口只有一个方法签名，这种就称为函数式接口
 * 函数式接口可以使用注解@FunctionalInterface来进行修饰，用于检查是否是函数式接口，
 * 当用@FunctionalInterface修饰的接口，如果里面有多个抽象方法，其编译将不会通过
 */
public class LambdaExpressionDissect {
    @Test
    public void test1() {

        Runnable r = new Runnable() {
            @Override
            public void run() {
                System.out.println("Hello 匿名内部类");
            }
        };

        r.run();
        /**
         * 这里是我们通过new Runnable接口，实现其匿名内部类
         * 通过匿名内部类来调用其 run()方法
         */


        //------------------------------------------------------------------


        /*--------此处我们通过lambda来进行改造
        * 这里使用的是:无参数，无返回值
        *  ()-> System.out.println("hello")
        * */
        Runnable r1 = () -> System.out.println("hello Lmabda");
        r1.run();

    }

    @Test
    public void test2() {
        /**
         *
         * public interface Consumer<T>{
         *   void accept(T t);
         *   }
         *
         */

        Consumer consumer = (x) -> System.out.println("这里是Lambda对 Consumer接口中的accept(T t)方法的实现:" + x);

        consumer.accept("Lambda已经实现!");//打印结果：这里是Lambda对 Consumer接口中的accept(T t)方法的实现:Lambda已经实现!

    }

    @Test
    public void test3() {
        /**
         *
         * public interface Consumer<T>{
         *   void accept(T t);
         *   }
         *
         */

        Consumer consumer = x -> System.out.println("左侧Lmabda的小括号没写:" + x);

        consumer.accept("Lambda已经实现!");//打印结果：左侧Lmabda的小括号没写:Lambda已经实现!

    }

    @Test
    public void test4() {
        /*
        当Lmabda表达式有多个参数的时候，左侧必须写小括号(x, y)
        当Lmabda体有多个执行语句的时候，右侧必须写大括号{};
        * */
        Comparator<Integer> comparator = (x, y) -> {
            System.out.println("执行Lmabda函数");
            return Integer.compare(x, y);//Lambda返回值
        };

    }

    @Test
    public void test5() {
        Comparator<Integer> comparator = (x, y) -> Integer.compare(x, y);
    }

    /**
     * 对Lambda案例的应用
     */
    @Test
    public void test6() {
        /*这里由Lmabda来实行了Myfun的方法，并对x进行求平方*/
        System.out.println(opteration(200, x -> x * x));


        //对传入的值加5
        System.out.println(opteration(100,x -> x+5));
    }

    /**
     * 这里 刚方法将传入了MyFun接口，并为进行实现
     *
     * @param num
     * @param myFun
     * @return
     */
    public Integer opteration(Integer num, MyFun myFun) {
        return myFun.getValue(num);
    }
}

/**
 * FunctionalInterface 注解标记其 MyFun接口是函数式接口
 */
@FunctionalInterface
interface MyFun {
    Integer getValue(Integer x);
}