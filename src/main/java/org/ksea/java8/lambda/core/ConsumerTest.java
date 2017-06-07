package org.ksea.java8.lambda.core;

import org.junit.Test;

import java.util.function.Consumer;

/**
 * Created by ksea on 2017/6/7.
 */
public class ConsumerTest {


    /*

    @FunctionalInterface 消费型函数式接口
    public interface Consumer<T> {
        void accept(T t);
    }
*/
    @Test
    public void test1() {
        //此处用lmabda函数进行实现
        consumerFuntion(1000,x-> System.out.println("共消费金额:"+x)); //共消费金额:1000.0
    }


    //消费多少金额
    public void consumerFuntion(double money, Consumer<Double> consumer) {
        consumer.accept(money);
    }

}
