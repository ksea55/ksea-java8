package org.ksea.java8.stream;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by mexican on 2017/6/8.
 * stream api之 收集操作 Collection
 * Collect--将流stream转换成其他形式，接受一个Collector接口的实现，用于给stream中元素作汇总的方法
 */
public class StreamApiTest6 {


    /**
     * collect(Collector c)
     * 将流转换为其他形式。接收一个Collector接口的实现，用于给Stream中元素做汇总的方法
     * Collector 接口中方法的实现决定了如何对流执行收集操作(如收集到List、Set、Map)。
     * 但是Collectors 实用类提供了很多静态方法，可以方便地创建常见收集器实例，具体方法与实例如下表：
     * <p>
     * <p>
     * 方法                             返回类型                                     作用
     * <p>
     * toList                           List<T>                                   把流中元素收集到List
     * 案例: List<Employee>emps=list.stream().collect(Collectors.toList());
     * <p>
     * toSet                            Set<T>                                    把流中元素收集到Set
     * 案例：Set<Employee>emps=list.stream().collect(Collectors.toSet());
     * <p>
     * toCollection                    Collection<T>                              把流中元素收集到创建的集合
     * 案例:Collection<Employee>emps=list.stream().collect(Collectors.toCollection(ArrayList::new));
     * <p>
     * counting                         Long                                      计算流中元素的个数
     * 案例：longcount=list.stream().collect(Collectors.counting());
     * <p>
     * summingInt                       Integer                                   对流中元素的整数属性求和
     * 案例： inttotal=list.stream().collect(Collectors.summingInt(Employee::getSalary));
     * <p>
     * averagingInt                     Double                                   计算流中元素Integer属性的平均值
     * 案例： doubleavg=list.stream().collect(Collectors.averagingInt(Employee::getSalary));
     * <p>
     * summarizingInt                 IntSummaryStatistics                       收集流中Integer属性的统计值。如：平均值
     * 案例： IntSummaryStatisticsiss=list.stream().collect(Collectors.summarizingInt(Employee::getSalary));
     * <p>
     * joining                           String                                  连接流中每个字符串
     * 案例： Stringstr=list.stream().map(Employee::getName).collect(Collectors.joining());
     * <p>
     * maxBy                            Optional<T>                               根据比较器选择最大值
     * 案例：Optional<Emp>max=list.stream().collect(Collectors.maxBy(comparingInt(Employee::getSalary)));
     * <p>
     * minBy                            Optional<T>                               根据比较器选择最小值
     * 案例： Optional<Emp>min=list.stream().collect(Collectors.minBy(comparingInt(Employee::getSalary)));
     * <p>
     * reducing                        归约产生的类型                               从一个作为累加器的初始值开始，利用BinaryOperator与流中元素逐个结合，从而归约成单个值
     * 案例：inttotal=list.stream().collect(Collectors.reducing(0,Employee::getSalar,Integer::sum));
     * <p>
     * collectingAndThen               转换函数返回的类型                             包裹另一个收集器，对其结果转换函数
     * 案例：inthow=list.stream().collect(Collectors.collectingAndThen(Collectors.toList(),List::size));
     * <p>
     * groupingBy                       Map<K,List<T>>                              根据某属性值对流分组，属性为K，结果为V
     * 案例：Map<Emp.Status, List<Emp>> map= list.stream().collect(Collectors.groupingBy(Employee::getStatus));
     * <p>
     * partitioningBy                  Map<Boolean,List<T>>                        根据true或false进行分区
     * 案例：Map<Boolean,List<Emp>>vd=list.stream().collect(Collectors.partitioningBy(Employee::getManage));
     */
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


    /**
     * 对结果进行收集 toList,toSet,toCollection
     */
    @Test
    public void test1() {

        //需求1 将员工信息的name收集到List集合中
        List<String> list = employees.stream().map(Employee::getName).collect(Collectors.toList());
        list.forEach(System.out::println); // 打印结果:张学友  张学友  张国荣  刘德华  林青霞  黎明 郭富城 王思聪  刘涛

        System.out.println("--------------------------------------------------------------------------------");

        //需求2 将员工信息的name收集到Set集合中
        Set<String> set = employees.stream().map(Employee::getName).collect(Collectors.toSet());
        set.forEach(System.out::println);// 打印结果: 张学友  张国荣  刘德华  林青霞  黎明 郭富城 王思聪  刘涛 此处set集合将去除重复数据的name


        System.out.println("--------------------------------------------------------------------------------");
        //需求3 将员工的name信息收集到HashSet中
        HashSet<String> hashSet = employees.stream().map(Employee::getName).collect(Collectors.toCollection(HashSet::new));
        hashSet.forEach(System.out::println);// 打印结果: 张学友  张国荣  刘德华  林青霞  黎明 郭富城 王思聪  刘涛 此处set集合将去除重复数据的name

        //注意其他类型的数据结构 如 HashMap，LinkedXXXX之类的 均使用.collect(Collectors.toCollection(HashSet::new));雷同

    }


    /**
     * 求总数:counting
     * 求平均值:averagingDouble
     * 求总和:summingDouble
     * 求最大值:maxBy
     * 求最小值:minBy
     */
    @Test
    public void test2() {
        //需求1 收集员工列表的总数
        Long count = employees.stream().collect(Collectors.counting());
        System.out.println(count); //打印结果：9


        //需求2 收集员工列表的薪资的平均值
        Double avg = employees.stream().collect(Collectors.averagingDouble(Employee::getSalary));
        System.out.println(avg); //5444.444444444444


        //需求3 求员工薪资的总和
        Double sum = employees.stream().collect(Collectors.summingDouble(Employee::getSalary));
        System.out.println(sum); //49000.0


        //需求4 求员工薪资的最大值
        Optional<Integer> optionalMax = employees.stream().map(Employee::getSalary).collect(Collectors.maxBy(Integer::compare));
        Integer max = optionalMax.get();
        System.out.println(max); //9000

        //需求5 求员工薪资最小的员工信息
        Optional<Employee> employeeOptional = employees.stream().collect(Collectors.minBy((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary())));
        Employee employee = employeeOptional.get();
        System.out.println(employee); //Employee{name='张国荣', age=35, salary=2000, status=FREE}

    }

    @Test //分组 groupingBy
    public void test3() {
        //需求1 按照状态status 分组
        Map<Employee.Status, List<Employee>> map = employees.stream().collect(Collectors.groupingBy(Employee::getStatus)); //注意此处 分组之后 是将条件作为key value是list集合
        System.out.println(map);
        /**
         * 打印结果:
         * { VOCATION=[Employee{name='刘德华', age=25, salary=3000, status=VOCATION}, Employee{name='林青霞', age=29, salary=9000, status=VOCATION}],
         *   FREE=[Employee{name='张国荣', age=35, salary=2000, status=FREE}, Employee{name='王思聪', age=28, salary=8000, status=FREE}, Employee{name='刘涛', age=32, salary=4000, status=FREE}],
         *   BULY=[Employee{name='张学友', age=45, salary=5000, status=BULY}, Employee{name='张学友', age=45, salary=5000, status=BULY}, Employee{name='黎明', age=15, salary=6000, status=BULY}, Employee{name='郭富城', age=34, salary=7000, status=BULY}]}
         *
         *
         */


        //需求2 先按照状态进行分组 在根据年龄分组 其中年龄<=35的为青年  年龄<=50的为中年  其它为老年

        Map<Employee.Status, Map<String, List<Employee>>> map1 = employees.stream().collect(Collectors.groupingBy(Employee::getStatus, Collectors.groupingBy(e -> {
            if (e.getAge() <= 35) return "青年";
            else if (e.getAge() <= 50) return "中年";
            else return "老年";
        })));

        System.out.println(map1);
        /**
         *
         * 打印结果:
         * {
         *  VOCATION={ 青年=[Employee{name='刘德华', age=25, salary=3000, status=VOCATION}, Employee{name='林青霞', age=29, salary=9000, status=VOCATION}]},
         *  FREE={ 青年=[Employee{name='张国荣', age=35, salary=2000, status=FREE}, Employee{name='王思聪', age=28, salary=8000, status=FREE}, Employee{name='刘涛', age=32, salary=4000, status=FREE}]},
         *  BULY={ 青年=[Employee{name='黎明', age=15, salary=6000, status=BULY}, Employee{name='郭富城', age=34, salary=7000, status=BULY}],
         *         中年=[Employee{name='张学友', age=45, salary=5000, status=BULY}, Employee{name='张学友', age=45, salary=5000, status=BULY}]}}
         *
         *
         */


    }

    @Test //分区 partitioningBy
    public void test4() {
        //需求1 将员工信息进行分区 按照薪资>=6000的为一区域 其它的为一区域
        Map<Boolean, List<Employee>> map = employees.stream().collect(Collectors.partitioningBy(e -> e.getSalary() >= 6000));
        System.out.println(map);
        /**
         *
         * 打印结果:
         * { false=[
         *          Employee{name='张学友', age=45, salary=5000, status=BULY},
         *          Employee{name='张学友', age=45, salary=5000, status=BULY},
         *          Employee{name='张国荣', age=35, salary=2000, status=FREE},
         *          Employee{name='刘德华', age=25, salary=3000, status=VOCATION},
         *          Employee{name='刘涛', age=32, salary=4000, status=FREE}],
         *
         *   true=[
         *          Employee{name='林青霞', age=29, salary=9000, status=VOCATION},
         *          Employee{name='黎明', age=15, salary=6000, status=BULY},
         *          Employee{name='郭富城', age=34, salary=7000, status=BULY},
         *          Employee{name='王思聪', age=28, salary=8000, status=FREE}]
         *  }
         *
         *
         */

    }

    //join 连接字符串
    @Test
    public void  test5(){
        //需求1将员工信息的名称连接出来
        String s = employees.stream().map(Employee::getName).collect(Collectors.joining()); //
        System.out.println(s); //张学友张学友张国荣刘德华林青霞黎明郭富城王思聪刘涛


        //需求2 将员工的信息的名称连接出来按逗号,分隔
        String s1 = employees.stream().map(Employee::getName).collect(Collectors.joining(","));
        System.out.println(s1); //张学友,张学友,张国荣,刘德华,林青霞,黎明,郭富城,王思聪,刘涛

        //需求三 将员工信息的名称连接出来 前后用★符号 中间名称用 |分隔

        String s2 = employees.stream().map(Employee::getName).collect(Collectors.joining("|", "★", "★"));
        System.out.println(s2); //★张学友|张学友|张国荣|刘德华|林青霞|黎明|郭富城|王思聪|刘涛★

    }
}
