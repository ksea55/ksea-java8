package org.ksea.java8.inteface;

import org.junit.Test;

/**
 * Created by ksea on 2017/6/9.
 */
public class InterfaceTest2 implements UserService, TeacherService {
    @Override
    public String getName() {
        //调用UserService接口中的getName
        //  return UserService.super.getName();
        return TeacherService.super.getName();
    }

    @Test
    public void test() {
        System.out.println(this.getName());
        //this is UserService and this is interface
        //this is TeacherService and this is interface

        /**
         *
         *
         * 从结果中我们可以看出:
         * 当一个类同时实现多个接口的时候，在这几个接口中具有相同的默认方法，其该类必须重写该方法
         *
         * 如：InterfaceTest2 同时实现UserService，TeacherService接口
         *    而UserService，TeacherService接口均有之间相同的方法
         *    此时 InterfaceTest2就必须重写getName方法
         *
         *
         */
    }
}
