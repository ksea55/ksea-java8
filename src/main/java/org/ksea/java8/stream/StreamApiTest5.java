package org.ksea.java8.stream;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.function.BinaryOperator;

/**
 * Created by mexican on 2017/6/8.
 * stream API之归约 reduce
 * <p>
 * reduce(T iden, BinaryOperator b) 可以将流中元素反复结合起来，得到一个值。返回T
 * reduce(BinaryOperator b) 可以将流中元素反复结合起来，得到一个值。 返回Optional<T>
 * 备注：map 和reduce 的连接通常称为map-reduce 模式，因Google 用它来进行网络搜索而出名。
 */
public class StreamApiTest5 {


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

    //reduce(T iden, BinaryOperator b) 可以将流中元素反复结合起来，得到一个值。返回T
    @Test
    public void test1() {

        List<Integer> list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);

        /**
         * @FunctionalInterface public interface BinaryOperator<T> extends BiFunction<T,T,T>
         */
        Integer sum = list.stream().reduce(0, (x, y) -> x + y);
        /**
         * 计算原理：
         * 第一次 x=0,y=1 x+y=1
         * 第二次 将第一次的计算的结果作为x的值:x=1,y=2  x+y=3
         * 第三次 将第二次的计算的结果作为x的值:x=3 y=3 x+y=6
         * 依次类推
         *
         */
        System.out.println(sum); //结果是55
    }


    //reduce(BinaryOperator b) 可以将流中元素反复结合起来，得到一个值。 返回Optional<T>
    @Test
    public void test2() {
        //需求计算所有员工的薪资的总和
        Optional<Integer> optional = employees.stream().map(Employee::getSalary).reduce(Integer::sum);
        Integer sum = optional.get();
        System.out.println(sum); //44000


        /**
         * Adds two integers together as per the + operator.
         *
         * @param a the first operand
         * @param b the second operand
         * @return the sum of {@code a} and {@code b}
         * @see java.util.function.BinaryOperator
         * @since 1.8

        public static int sum(int a, int b) {
            return a + b;
        }

         我们可以看到 sum方法是在1.8中才提供的
         */

    }
}
