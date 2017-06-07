package org.ksea.java8.lambda;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by ksea on 2017/6/7.
 */

public class LambdaTest {

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


    //引出Lambda
    @Test
    public void test1() {

        //jdk1.7之前,这里创建一个匿名内部类来创建compartor比较器
        Comparator<Integer> com = new Comparator<Integer>() {
            @SuppressWarnings("Since15")
            public int compare(Integer x, Integer y) {
                //核心方法
                return Integer.compare(x, y);
            }
        };

        TreeMap<Integer, Integer> treeMap = new TreeMap<Integer, Integer>(com);


        //接下来使用lambda表达式

        Comparator<Integer> c = (x, y) -> Integer.compare(x, y);
        TreeMap<Integer, Integer> t = new TreeMap<>(c);

        //两者相比较Lambda更加的简洁
    }


    //需求1查找年龄>=35岁的员工信息
    public List<Employee> getEmployeesByAge(List<Employee> employees) {
        List<Employee> tempList = new ArrayList<>();
        for (Employee e : employees) {
            if (e.getAge() >= 35) {
                tempList.add(e);
            }
        }
        return tempList;
    }

    //需求2查找薪资>=5000的员工信息
    public List<Employee> getEmployeesBySalary(List<Employee> employees) {
        List<Employee> tempList = new ArrayList<>();
        for (Employee e : employees) {
            if (e.getSalary() >= 5000) {
                tempList.add(e);
            }
        }
        return tempList;
    }

    @Test
    public void test2() {
        //查找员工信息中年龄>=35的员工
        List<Employee> employees1 = getEmployeesByAge(employees);
        for (Employee e : employees1) {
            System.out.println(e);
        }


        System.out.println("--------------------------------------");
        //查找员工信息中薪资>=5000员工
        List<Employee> employees2 = getEmployeesBySalary(employees);
        for (Employee e : employees2) {
            System.out.println(e);
        }

        /*
        * 从上面代码我们可以看出，根据需求1和需求2 就会单独增加一个方法，这个造成了代码的冗余，以及重复的代码
        *
        * */
    }


    //条件过滤方法
    public List<Employee> filterEmployee(List<Employee> srcList, MyPredicate<Employee> predicate) {
        List<Employee> tempList = new ArrayList<>();
        for (Employee e : srcList) {
            if (predicate.test(e)) {
                tempList.add(e);
            }
        }

        return tempList;
    }

    /*针对上面test2 提出的问题我们使用设计模式进行优化，
      优化方案1：策略模式
      这里涉及到接口 MyPredicate
      实现:MyPredicateByAge,MyPredicateBySalary
    */
    @Test
    public void test3() {
        //按照年龄进行过滤
        List<Employee> employeeList = filterEmployee(employees, new MyPredicateByAge());
        for (Employee e : employeeList) {
            System.out.println(e);
        }

        System.out.println("--------------------------------------");

        //按照薪资进行过滤
        employeeList = filterEmployee(employees, new MyPredicateBySalary());
        for (Employee e : employeeList) {
            System.out.println(e);
        }

        /*总结 策略模式，在这里，对每一个条件，就的去实现一个自己的策略类，增加了多的实现类**/
    }


    //优化方案2：匿名内部类
    @Test
    public void test4() {
        //按照年龄过滤
        List<Employee> employeeList = filterEmployee(employees, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getAge() >= 35;
            }
        });
        for (Employee e : employeeList) {
            System.out.println(e);
        }

        System.out.println("---------------------------------------");

        //按照薪资过滤
        employeeList = filterEmployee(employees, new MyPredicate<Employee>() {
            @Override
            public boolean test(Employee employee) {
                return employee.getSalary() >= 5000;
            }
        });
        for (Employee e : employeeList) {
            System.out.println(e);
        }
    }


    /*
       优化方案3：Lambda
    *   针对无论是策略模式还是匿名内部类的方式，他们的核心点都是 employee.getXXX 进行比较
    * */
    @Test
    public void test5() {
        /*
        * 注意这里filterEmployee(employees, (e) -> e.getAge() >= 35);就是使用的lambda的表达式
        * 这里我们就更能理解:Lambda 是一个匿名函数，我们可以把Lambda 表达式理解为是一段可以传递的代码（将代码像数据一样进行传递）。
        * */
        List<Employee> employeeList = filterEmployee(employees, (e) -> e.getAge() >= 35);
        employeeList.forEach(System.out::println);//遍历并打印到控制台

        System.out.println("--------------------------------------------------------");

        employeeList = filterEmployee(employees, (employee -> employee.getSalary() >= 5000));
        employeeList.forEach(System.out::println);
    }


    /*优化方案4：stream Api*/
    @Test
    public void test6() {
        /*
        * 强大的stream api
        * .filter(employee -> employee.getAge() >= 35) 过滤 里面是lambda表达式
        * .collect(Collectors.toList()); 获取stream的结果并转换成list
        * */
        List<Employee> employeeList = employees.stream().filter(employee -> employee.getAge() >= 35).collect(Collectors.toList());
        employeeList.forEach(System.out::println);

        System.out.println("---------------------------------");
        //等同
        employees.stream().filter(employee -> employee.getAge() >= 35).forEach(System.out::println);
        System.out.println("------------------------------------------------------------------------");
        //在结果中只取1条数据
        employees.stream()
                 .filter(employee -> employee.getAge() >= 30)
                 .limit(1)
                 .forEach(System.out::println);


        //取出薪资>=6000的员工的名称
        employees.stream()
                 .filter(employee -> employee.getSalary()>=6000)
                 .map(employee -> employee.getName())
                 .forEach(System.out::println);

    }
}
