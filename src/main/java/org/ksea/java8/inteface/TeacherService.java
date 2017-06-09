package org.ksea.java8.inteface;

/**
 * Created by ksea on 2017/6/9.
 */
public interface TeacherService {

    default String getName() {
        return "this is TeacherService and this is interface";
    }
}
