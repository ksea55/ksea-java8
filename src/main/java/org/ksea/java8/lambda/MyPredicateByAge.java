package org.ksea.java8.lambda;

/**
 * Created by ksea on 2017/6/7.
 * 按年龄进行过滤
 */
public class MyPredicateByAge implements MyPredicate<Employee> {
    @Override
    public boolean test(Employee employee) {
        return employee.getAge() >= 35;
    }
}
