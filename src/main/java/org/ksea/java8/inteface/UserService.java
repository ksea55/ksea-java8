package org.ksea.java8.inteface;

/**
 * Created by ksea on 2017/6/9.
 */
public interface UserService {

    //在jdk1.8中是可以有方法实现体的，并用default来进行修饰，表示默认方法
    default String getName() {
        return "this is UserService and this is interface";
    }
}
