package org.ksea.java8.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by ksea on 2017/6/8.
 * Stream Api中间操作之映射
 * <p>
 * <p>
 * <p>
 * map(Function f) 接收一个函数作为参数，该函数会被应用到每个元素上，并将其映射成一个新的元素。
 * mapToDouble(ToDoubleFunction f) 接收一个函数作为参数，该函数会被应用到每个元素上，产生一个新的DoubleStream。
 * mapToInt(ToIntFunction f) 接收一个函数作为参数，该函数会被应用到每个元素上，产生一个新的IntStream。
 * mapToLong(ToLongFunction f) 接收一个函数作为参数，该函数会被应用到每个元素上，产生一个新的LongStream。
 * flatMap(Function f)接收一个函数作为参数，将流中的每个值都换成另一个流，然后把所有流连接成一个流
 */
public class StreamApiTest2 {
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


    //map映射
    @Test
    public void test() {
        List<String> stringList = Arrays.asList("aaa", "bbb", "ccc", "ddd");
        stringList.stream()
                .map(x -> x.toUpperCase())
                .forEach(System.out::println);
        /**
         * AAA
         BBB
         CCC
         DDD
         这里我们可以看到结果是大写的
         *
         * */

        System.out.println("------------------------------------------");
        //在如下面提取person数据信息的name
        personList.stream()
                .map(Person::getName)
                .forEach(System.out::println);

        /**
         *
         * 张学友
         郭富城
         刘德华
         黎明
         孙悟空
         猪八戒
         唐僧
         白龙马
         沙僧
         白骨精

         *
         *
         */
    }

    //flatMap
    @Test
    public void test2() {
        List<String> stringList = Arrays.asList("aaa", "bbb", "ccc", "ddd");
        Stream<Stream<Character>> stream = stringList.stream().map(StreamApiTest2::filterCharacter);
        stream.forEach(s -> { //这里的s其实就是Stream<Character>
            s.forEach(System.out::println);
            /*
            * 打印结果:
            *   a
                a
                a
                b
                b
                b
                c
                c
                c
                d
                d
                d
            *
            * */
        });

        //这里的map就等同与List中的add(list)如:
        List<String> list1 = new ArrayList<>();
        list1.add("a");
        list1.add("a");
        list1.add("c");

        List list2 = new ArrayList<>();
        list2.add("ABC");

        list2.add(list1);
        System.out.println(list2); //[ABC, [a, a, c]]


        System.out.println("-----------------------------------------------------");

        Stream<Character> characterStream = stringList.stream().flatMap(StreamApiTest2::filterCharacter);
        characterStream.forEach(System.out::println);
        /*
        *
        *   a
            a
            a
            b
            b
            b
            c
            c
            c
            d
            d
            d

        *
        * */


        //flatMap扁平化stream 这个就相当于list中的addAll


        List list3 = new ArrayList();
        list3.add("FGHB");
        list3.addAll(list1);
        System.out.println(list3); //[FGHB, a, a, c]

    }


    //这里返回的是一个字符stream
    public static Stream<Character> filterCharacter(String str) {
        List<Character> list = new ArrayList<>();
        for (char c : str.toCharArray()) {
            list.add(c);
        }
        return list.stream();
    }

}
