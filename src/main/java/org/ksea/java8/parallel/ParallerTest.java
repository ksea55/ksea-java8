package org.ksea.java8.parallel;

import org.junit.Test;

import java.time.Duration;
import java.time.Instant;
import java.util.OptionalLong;
import java.util.stream.LongStream;

/**
 * Created by ksea on 2017/6/9.
 * <p>
 * Frok与Join在 1.8之前用起来比较麻烦，
 * 在JDK1.8 对其进行了优化 用起来更加简洁
 */
public class ParallerTest {

    @Test
    public void test1() {
        //其默认就是串行流
        Instant start = Instant.now();

        //如 计算0-1000000000之间的合
        LongStream longStream = LongStream.range(0, 100000000000L);//产生0-1000000000之间的一个数字流]
        OptionalLong optionalLong = longStream.reduce(Long::sum);
        long sum = optionalLong.getAsLong();
        System.out.println(sum);

        Instant end = Instant.now();


        System.out.println("总共花费计算时间:" + Duration.between(start, end).getSeconds() + "秒");//总共花费计算时间:42秒
    }

    @Test //串行流方式 与上面test1一样
    public void test2() {

        Instant start = Instant.now();
        OptionalLong sum = LongStream.range(0, 100000000000L)
                .sequential()//表示串行运行
                .reduce(Long::sum);
        long result = sum.getAsLong();
        System.out.println(result);

        Instant end = Instant.now();
        System.out.println("总共消费多少时间:" + Duration.between(start, end).getSeconds() + "秒"); //总共消费多少时间:42秒

    }


    //接下来用并行流的方式来进行计算
    @Test
    public void test3() {

        Instant start = Instant.now();

        OptionalLong optionalLong = LongStream.range(0, 100000000000L)
                .parallel()//使用并行流
                .reduce(Long::sum);

        long result = optionalLong.getAsLong();

        System.out.println(result);
        Instant end = Instant.now();
        System.out.println("总共消费多少时间:" + Duration.between(start, end).getSeconds() + "秒"); //总共消费多少时间:42秒
    }
}
