package org.ksea.java8.timer;

import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * Created by mexican on 2017/6/11.
 * Java8 提出了新的API
 * 对时间的操作是更方便
 */
public class TimerTest {
    /**
     * 时间日期主要涉及到这三个类
     * <p>
     * <p>
     * LocalDate 日期类
     * <p>
     * LocalTime 时间类
     * <p>
     * LocalDateTime 日期时间类
     * 它们三个其用法以及语法一样，这里就只用LocalDateTime作为讲解
     */
    @Test
    public void test1() {
        //获取当前时间
        LocalDateTime localDateTime = LocalDateTime.now();

        System.out.println(localDateTime); //2017-06-11T22:34:31.365


        //时间格式化
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日 HH:mm:ss");
        String timer = dateTimeFormatter.format(localDateTime);
        System.out.println(timer); //2017年06月11日 22:37:44


        //或则
        String format = localDateTime.format(dateTimeFormatter);
        System.out.println(format);//2017年06月11日 22:38:29


        //将一个字符串时间转换成时间
        String timerStr = "2017-06-11 22:39:11";
        LocalDateTime parseTime = LocalDateTime.parse(timerStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        System.out.println(parseTime); //2017-06-11T22:39:11

    }


    //计算时间戳
    @Test
    public void test2() throws InterruptedException {
        Instant start = Instant.now();
        System.out.println(start); //2017-06-11T14:43:19.119Z


        Thread.sleep(3000);

        Instant end = Instant.now();

        //计算2个时间戳的值
        Duration duration = Duration.between(start, end);
       // Period period = Period.between(); 用于计算2个日期之间的差值
        long seconds = duration.getSeconds();//获取秒
        long hours = duration.toHours();//获取小时
        long minutes = duration.toMinutes();//获取分
        long millis = duration.toMillis();//获取毫秒

        System.out.println("时:" + hours + "分:" + minutes + "秒:" + seconds + "毫秒:" + millis);//时:0分:0秒:3毫秒:3058
    }
}
