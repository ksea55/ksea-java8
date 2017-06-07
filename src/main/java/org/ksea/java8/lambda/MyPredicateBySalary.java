package org.ksea.java8.lambda;

/**
 * Created by ksea on 2017/6/7.
 * 按薪资进行过滤
 */
public class MyPredicateBySalary implements MyPredicate<Employee> {
    @Override
    public boolean test(Employee employee) {
        return employee.getSalary() >= 5000;
    }
}
