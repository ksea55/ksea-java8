package org.ksea.java8.lambda.core;

import org.junit.Test;
import org.ksea.java8.lambda.Employee;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;

/**
 * Created by mexican on 2017/6/7.
 * 断言型函数式接口
 */
public class PredicateTest {


    /*@FunctionalInterface
    public interface Predicate<T> {

        boolean test(T t);
    }*/

    //初始化数据
    List<Employee> employees = Arrays.asList(
            new Employee("张学友", 45, 5000),
            new Employee("张国荣", 35, 2000),
            new Employee("刘德华", 25, 3000),
            new Employee("黎明", 15, 6000),
            new Employee("郭富城", 34, 7000),
            new Employee("王思聪", 28, 8000),
            new Employee("刘涛", 32, 4000)
    );

    @Test
    public void test() {
        //获取年龄>=30的员工信息
        List<Employee> nowList = filterList(employees, employee -> employee.getAge() >= 30);
        nowList.forEach(System.out::println);
        /**
         * 打印结果:
         *   Employee{name='张学友', age=45, salary=5000.0}
         Employee{name='张国荣', age=35, salary=2000.0}
         Employee{name='郭富城', age=34, salary=7000.0}
         Employee{name='刘涛', age=32, salary=4000.0}
         */

        System.out.println("---------------------------------------------------------------");
        //需求2获取薪资>=6000

        employees.stream()
                .filter(employee -> employee.getSalary() >= 6000)
                .forEach(System.out::println);
        /*
        *
        * 打印结果：
        * Employee{name='黎明', age=15, salary=6000.0}
            Employee{name='郭富城', age=34, salary=7000.0}
            Employee{name='王思聪', age=28, salary=8000.0}
        *
        *
        *
        * */
    }


    /**
     * 利用泛型实现通用型的集合过滤器
     *
     * @param srcList
     * @param predicate
     * @return
     */
    public List<Employee> filterList(List<Employee> srcList, Predicate<Employee> predicate) {

        List<Employee> list = new ArrayList<>();
        for (Employee t : srcList) {
            if (predicate.test(t)) {
                list.add(t);
            }
        }

        return list;
    }

}