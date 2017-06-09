package org.ksea.java8.stream;

import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by ksea on 2017/6/9.
 * Stream Api 练习
 */
public class StreamApiDemo {
    @Test
    public void test1() {
        /**
         * 1：给定一个数字列表，如何返回一个由每个数的平方构成的列表呢?
         * 如 给定:【1，2，3，4，5】 应该返回【1，4，9，16，25】
         */

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5);

        //这里是由Lambda来实现的
        List<Integer> list1 = pf(list, x -> x * x);
        list1.forEach(System.out::println); //1 4 9 16 25

        System.out.println("--------------------------------------------------");
        //stream api 实现
        List<Integer> map = list.stream().map(x -> x * x).collect(Collectors.toList());
        map.forEach(System.out::println); //1 4 9 16 25

    }

    public List<Integer> pf(List<Integer> list, Function<Integer, Integer> function) {

        List<Integer> l = new ArrayList<>();
        for (Integer i : list) {
            l.add(function.apply(i));
        }
        return l;

    }


    /**
     * 怎样用map与reduce方法数一数流中有多少个Employee
     */
    @Test
    public void test2() {
        List<Employee> employees = Arrays.asList(
                new Employee("张学友", 45, 5000, Employee.Status.BULY),
                new Employee("张学友", 45, 5000, Employee.Status.BULY),
                new Employee("张国荣", 35, 2000, Employee.Status.FREE),
                new Employee("刘德华", 25, 3000, Employee.Status.VOCATION),
                new Employee("林青霞", 29, 9000, Employee.Status.VOCATION),
                new Employee("黎明", 15, 6000, Employee.Status.BULY),
                new Employee("郭富城", 34, 7000, Employee.Status.BULY),
                new Employee("王思聪", 28, 8000, Employee.Status.FREE),
                new Employee("刘涛", 32, 4000, Employee.Status.FREE)
        );

        //第 一种写法
        Integer count = employees.stream().map(employee -> 1).reduce(0, (x, y) -> x + y);
        System.out.println(count); //9

        System.out.println("--------------------------------------------------------------------");
        //第二种写法
        Optional<Integer> optional = employees.stream().map(employee -> 1).reduce(Integer::sum);
        count = optional.get();
        System.out.println(count);//9
    }


    List<Transaction> transactions = null;

    @Before
    public void before() {
        Trader raoul = new Trader("Raoul", "Cambridge"); //Cambridge 剑桥
        Trader mario = new Trader("Mario", "Milan"); //Milan 米兰
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );
    }

    //1. 找出2011年发生的所有交易， 并按交易额排序（从低到高）
    @Test
    public void test3() {
        transactions.stream()
                .filter(transaction -> transaction.getYear() == 2011) //此处过滤出year是2011的交易记录
                .sorted((x, y) -> Double.compare(x.getValue(), y.getValue())) //按交易金额进行排序
                .forEach(System.out::println);
        /**
         * 打印结果:
         * Transaction [trader=Trader [name=Brian, city=Cambridge], year=2011, value=300]
         * Transaction [trader=Trader [name=Raoul, city=Cambridge], year=2011, value=400]
         *
         *
         */

    }

    //2. 交易员都在哪些不同的城市工作过？
    @Test
    public void test4() {

        String citys = transactions.stream()
                .map(transaction -> transaction.getTrader().getCity())//提取交易员工作过的城市数据信息
                .distinct() //抽出来的交易员城市进行去重复数据
                .collect(Collectors.joining(","));//将抽取出来的城市用,进行连接
        System.out.println(citys); //打印结果:Cambridge,Milan

    }

    //3. 查找所有来自剑桥的交易员，并按姓名排序
    @Test
    public void test5() {
        transactions.stream()
                .map(Transaction::getTrader) //抽取出来所有员工信息
                .filter(trader -> trader.getCity().equals("Cambridge")) //过滤出所有来自剑桥的员工
                .sorted((t1, t2) -> t1.getName().compareTo(t2.getName())) //按照姓名排序
                .distinct()//去除重复的交易员
                .forEach(System.out::println);

        /**
         *
         * 打印结果:
         *Trader [name=Alan, city=Cambridge]
         *Trader [name=Brian, city=Cambridge]
         *Trader [name=Raoul, city=Cambridge]
         *
         */

    }

    //4. 返回所有交易员的姓名字符串，按字母顺序排序
    @Test
    public void test6() {

        String names = transactions.stream()
                .map(transaction -> transaction.getTrader().getName()) //抽取所有交易员的姓名
                .sorted((x, y) -> x.compareTo(y)) //姓名排序
                .distinct()//去除重复的姓名
                .collect(Collectors.joining(","));//将姓名用,进行连接成字符串
        System.out.println(names); //打印结果:Alan,Brian,Mario,Raoul,Raoul
    }


    //5. 有没有交易员是在米兰工作的？
    @Test
    public void test7() {

        boolean match = transactions.stream()
                .anyMatch(transaction -> transaction.getTrader().getCity().equals("Milan"));
        System.out.println(match);
    }


    //6. 打印生活在剑桥的交易员的所有总交易额
    @Test
    public void test8() {
        //利用reduce统计
        Optional<Integer> optional = transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge")) //过滤出生在剑桥的交易员的交易信息
                .map(Transaction::getValue) //提取出交易金额
                .reduce(Integer::sum);
        Integer sum = optional.get();
        System.out.println(sum);//2650

        System.out.println("----------------------------------------------------------------");

        //collect
        Integer sum_ = transactions.stream()
                .filter(transaction -> transaction.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .collect(Collectors.summingInt(value -> value));
        System.out.println(sum_); //2650

    }


    //7. 所有交易中，最高的交易额是多少
    @Test
    public void test9() {
        //根据
        Optional<Integer> optional = transactions.stream()
                .map(Transaction::getValue)//抽取所有交易金额
                .collect(Collectors.maxBy((x, y) -> x.compareTo(y)));

        int max = optional.get();
        System.out.println(max); //1000

        System.out.println("-----------------------------------------------------------");

        Optional<Integer> optional1_ = transactions.stream()
                .map(Transaction::getValue)
                .max(Integer::compareTo);

        max = optional1_.get();
        System.out.println(max);

    }

    //8. 找到交易额最小的交易
    @Test
    public void test10() {

        Optional<Transaction> optional = transactions.stream()
                .min((t1, t2) -> Integer.compare(t1.getValue(), t2.getValue()));
        Transaction min = optional.get();
        System.out.println(min); //Transaction [trader=Trader [name=Brian, city=Cambridge], year=2011, value=300]
        System.out.println("-------------------------------------------------------");

        Optional<Transaction> optional1 = transactions.stream()
                .collect(Collectors.minBy((t1, t2) -> Integer.compare(t1.getValue(), t2.getValue())));


        Transaction min_ = optional1.get();
        System.out.println(min_);//Transaction [trader=Trader [name=Brian, city=Cambridge], year=2011, value=300]

    }

}
