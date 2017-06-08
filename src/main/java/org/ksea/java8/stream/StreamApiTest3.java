package org.ksea.java8.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ksea on 2017/6/8.
 * <p>
 * Stream api中间操作之排序
 * <p>
 * sorted()产生一个新流，其中按自然顺序排序（自然排序就是按照Comparable）
 * sorted(Comparator comp) 产生一个新流，其中按比较器顺序排序 定制化排序
 */
public class StreamApiTest3 {


    @Test
    public void test() {
        List<String> list = Arrays.asList("eee", "ccc", "bbb", "aaa");
        //使用自然排序
        list.stream()
                .sorted()//进行自然排序
                .forEach(System.out::println);
        /*
        *
        * 打印结果:
        *   aaa
        *   bbb
        *   ccc
        *   eee
        *
        * */

    }

    //自定义排序
    @Test
    public void test2() {

        List<Person> personList = Arrays.asList(
                new Person("张学友", 45, "zxy@163.com"),
                new Person("郭富城", 30, "gfc@163.com"),
                new Person("林青霞", 30, "lqx@163.com"),
                new Person("刘德华", 34, "ldh@163.com"),
                new Person("黎明", 14, "lm@163.com"),
                new Person("孙悟空", 21, "swk@163.com"),
                new Person("猪八戒", 27, "zbj@163.com"),
                new Person("唐僧", 31, "ts@163.com"),
                new Person("白龙马", 51, "blm@163.com"),
                new Person("沙僧", 8, "ss@163.com"),
                new Person("白骨精", 70, "bgj@163.com")
        );

        //需求按年龄排序，如果年龄相等就姓名排序

        personList.stream()
                .sorted((person1, person2) -> {
                    if (person1.getAge().equals(person2.getAge())) {

                        return person1.getName().compareTo(person2.getName());
                    }

                    return person1.getAge().compareTo(person2.getAge());

                })
                .forEach(System.out::println);


        /**
         * 打印结果:
         *
         * Person{name='沙僧', age=8, email='ss@163.com'}
         Person{name='黎明', age=14, email='lm@163.com'}
         Person{name='孙悟空', age=21, email='swk@163.com'}
         Person{name='猪八戒', age=27, email='zbj@163.com'}
         Person{name='林青霞', age=30, email='lqx@163.com'}
         Person{name='郭富城', age=30, email='gfc@163.com'}
         Person{name='唐僧', age=31, email='ts@163.com'}
         Person{name='刘德华', age=34, email='ldh@163.com'}
         Person{name='张学友', age=45, email='zxy@163.com'}
         Person{name='白龙马', age=51, email='blm@163.com'}
         Person{name='白骨精', age=70, email='bgj@163.com'}
         *
         *
         *
         */

    }


}
