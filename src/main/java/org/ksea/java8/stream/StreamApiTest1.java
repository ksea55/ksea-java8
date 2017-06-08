package org.ksea.java8.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ksea on 2017/6/8.
 * <p>
 * <p>
 * Java8 中的Collection 接口被扩展，提供了两个获取流的方法：
 * default Stream<E> stream() : 返回一个顺序流
 * default Stream<E> parallelStream() : 返回一个并行流
 */
public class StreamApiTest1 {

    /**
     * Stream API中间操作之:筛选与切片
     * filter(Predicate p) 接收Lambda,从流中过滤某些元素。
     * limit(long maxSize) 截断流，使其元素不超过给定的maxSize数量。
     * skip(long n) 跳过元素，返回一个扔掉了前n 个元素的流。若流中元素不足n 个，则返回一个空流。与limit(n) 互补
     * distinct()通过流所生成元素的hashCode() 和equals() 去除重复元素，其对象必须重写hashcode()与equals()
     * <p>
     * 这里的limit与skip与Mongodb中的分页limit、skip一样
     */


    List<Person> personList = Arrays.asList(
            new Person("张学友", 45, "zxy@163.com"),
            new Person("郭富城", 30, "gfc@163.com"),
            new Person("刘德华", 34, "ldh@163.com"),
            new Person("黎明", 14, "lm@163.com"),
            new Person("孙悟空", 21, "swk@163.com"),
            new Person("猪八戒", 27, "zbj@163.com"),
            new Person("唐僧", 31, "ts@163.com"),
            new Person("白龙马", 51, "blm@163.com"),
            new Person("沙僧", 8, "ss@163.com"),
            new Person("白骨精", 70, "bgj@163.com")
    );


    //filter过滤
    @Test
    public void test1() {
        /**
         * 通过filter 里面传入的Lmabda表达式 过滤出年龄大于35岁的人员信息
         */
        personList.stream()
                .filter(person -> person.getAge() > 35)
                .forEach(System.out::println);
        /*
        *输出结果:
        *   Person{name='张学友', age=45, email='zxy@163.com'}
        *   Person{name='白龙马', age=51, email='blm@163.com'}
        *   Person{name='白骨精', age=70, email='bgj@163.com'}
        *
        * */
    }

    //limit取出指定的条数
    @Test
    public void test2() {
        //需求过滤出年龄>35岁的人员信息并取出前2条数据
        personList.stream()
                .filter(person -> person.getAge() > 35)
                .limit(2)//此处取出2条数据
                .forEach(System.out::println);
        /**
         *
         * 打印结果:
         *
         * Person{name='张学友', age=45, email='zxy@163.com'}
         * Person{name='白龙马', age=51, email='blm@163.com'}
         *
         *
         */
    }


    //skip跳过结果集中的指定条数，取出之后的数据
    @Test
    public void test3() {
        //需求过滤出age>35的人员信息 并跳过前2条数据
        personList.stream()
                .filter(person -> person.getAge() > 35)
                .skip(2)
                .forEach(System.out::println);

        //打印结果:Person{name='白骨精', age=70, email='bgj@163.com'}

    }


    //distinct去除重复数据，目标对象必须重写其hashCode与equals方法，这里的Person对象就重写了hashCode与equals方法
    @Test
    public void test4() {

        List<Person> personList = Arrays.asList(
                new Person("张学友", 45, "zxy@163.com"),
                new Person("郭富城", 30, "gfc@163.com"),
                new Person("刘德华", 34, "ldh@163.com"),
                new Person("黎明", 14, "lm@163.com"),
                new Person("孙悟空", 21, "swk@163.com"),
                new Person("猪八戒", 27, "zbj@163.com"),
                new Person("唐僧", 31, "ts@163.com"),
                new Person("白龙马", 51, "blm@163.com"),
                new Person("沙僧", 8, "ss@163.com"),
                new Person("白骨精", 70, "bgj@163.com"),
                new Person("周星星", 53, "zxx@163.com"),
                new Person("周星星", 53, "zxx@163.com"),
                new Person("周星星", 53, "zxx@163.com"),
                new Person("周星星", 53, "zxx@163.com"),
                new Person("周星星", 21, "zxx@163.com")
        );

        //过滤出age>35岁的人员信息并去除重复数据
        personList.stream()
                .filter(person -> person.getAge() > 35)
                .forEach(System.out::println); //此处并未去除重复数据

        /**
         *
         *
         *
         * Person{name='张学友', age=45, email='zxy@163.com'}
         Person{name='白龙马', age=51, email='blm@163.com'}
         Person{name='白骨精', age=70, email='bgj@163.com'}
         Person{name='周星星', age=53, email='zxx@163.com'}
         Person{name='周星星', age=53, email='zxx@163.com'}
         Person{name='周星星', age=53, email='zxx@163.com'}
         Person{name='周星星', age=53, email='zxx@163.com'}
         *
         */

        System.out.println("-------------------------------------");

        //进行去除重复

        personList.stream()
                .filter(person -> person.getAge() > 35)
                .distinct() //去除重复
                .forEach(System.out::println);


        /***
         *
         *
         *
         * Person{name='张学友', age=45, email='zxy@163.com'}
         Person{name='白龙马', age=51, email='blm@163.com'}
         Person{name='白骨精', age=70, email='bgj@163.com'}
         Person{name='周星星', age=53, email='zxx@163.com'}

         *
         *
         */

    }

}
