package org.ksea.java8.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

/**
 * Created by mexican on 2017/6/8.
 * Stream API终止操作 查找与匹配
 * <p>
 * <p>
 * <p>
 * allMatch(Predicate p) 检查是否匹配所有元素
 * anyMatch(Predicate p) 检查是否至少匹配一个元素
 * noneMatch(Predicatep) 检查是否没有匹配所有元素
 * findFirst() 返回第一个元素
 * findAny() 返回当前流中的任意元素
 * count() 返回流中元素总数
 * max(Comparator c) 返回流中最大值
 * min(Comparator c) 返回流中最小值
 * forEach(Consumerc) 内部迭代(使用Collection 接口需要用户去做迭代，称为外部迭代。相反，Stream API 使用内部迭代——它帮你把迭代做了)
 */
public class StreamApiTest4 {

    List<Employee> employees = Arrays.asList(
            new Employee("张学友", 45, 5000, Employee.Status.BULY),
            new Employee("张国荣", 35, 2000, Employee.Status.FREE),
            new Employee("刘德华", 25, 3000, Employee.Status.VOCATION),
            new Employee("林青霞", 29, 9000, Employee.Status.VOCATION),
            new Employee("黎明", 15, 6000, Employee.Status.BULY),
            new Employee("郭富城", 34, 7000, Employee.Status.BULY),
            new Employee("王思聪", 28, 8000, Employee.Status.FREE),
            new Employee("刘涛", 32, 4000, Employee.Status.FREE)
    );


    //allMatch
    @Test
    public void test1() {
        //需求 查看当前员工是否所有都处于空闲状态
        boolean r = employees.stream()
                .allMatch(employee -> employee.getStatus().equals(Employee.Status.FREE));
        System.out.println(r);//false
        //如果全部都匹配则返回true
    }


    //anyMatch(Predicate p) 检查是否至少匹配一个元素
    @Test
    public void test2() {
        //需求 查看当前员工列表中是否有正在状态是休假的 VOCATION
        boolean b = employees.stream()
                .anyMatch(employee -> employee.getStatus().equals(Employee.Status.VOCATION));
        System.out.println(b); //打印结果 true
    }


    //noneMatch(Predicatep) 检查是否没有匹配所有元素
    @Test
    public void test3() {
        //需求 匹配是否有在没有休假的
        boolean b = employees.stream().noneMatch(employee -> employee.getStatus().equals(Employee.Status.VOCATION));
        System.out.println(b); //打印结果 false  如果这里没有匹配上任何一个 则返回true

    }


    //findFirst() 返回第一个元素
    @Test
    public void test4() {
        //返回第一个元素，该元素被Optional容器进行包装，降低空指针异常,在返回不明确的情况下，都是返回Optional容器包装的对象
        Optional<Employee> optional = employees.stream().sorted((x, y) -> Double.compare(x.getSalary(), y.getSalary())).findFirst();
        Employee employee = optional.get(); //获取该员工信息 此处按员工薪资的升序排序，得到第一个员工就是薪资最低的员工信息
        System.out.println(employee); //打印结果:Employee{name='张国荣', age=35, salary=2000, status=FREE}

    }


    //findAny() 返回当前流中的任意元素
    @Test
    public void test5() {
        //需求 找出员工列表中处于空闲状态的任意一个员工
        Optional<Employee> optional = employees.stream().filter(employee -> employee.getStatus().equals(Employee.Status.FREE)).findAny();
        Employee employee = optional.get();
        System.out.println(employee); //打印结果:Employee{name='张国荣', age=35, salary=2000, status=FREE}

    }


    //count() 返回流中元素总数
    @Test
    public void test6() {

        //需求查看当前员工信息表中的总数
        long count = employees.stream().count();
        System.out.println(count); // 8

        //需求查看当前员工信息表中处于休假状态的员工个数
        long count1 = employees.stream().filter(employee -> employee.getStatus().equals(Employee.Status.VOCATION)).count();
        System.out.println(count1); //2
    }

    //max(Comparator c) 返回流中最大值
    @Test
    public void test7() {

        //需求 获取员工信息列表中薪资最大的人员信息

        Optional<Employee> max = employees.stream().max((x, y) -> Double.compare(x.getSalary(), y.getSalary()));
        Employee employee = max.get();
        System.out.println(employee); //Employee{name='林青霞', age=29, salary=9000, status=VOCATION}
    }


    //min(Comparator c) 返回流中最小值
    @Test
    public void test8() {
        //获取最小工资
        Optional<Integer> min = employees.stream().map(Employee::getSalary).min((x, y) -> Double.compare(x, y));
        System.out.println(min.get());
    }
}
