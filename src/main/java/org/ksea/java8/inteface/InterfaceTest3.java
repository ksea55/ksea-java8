package org.ksea.java8.inteface;

import org.junit.Test;

/**
 * Created by ksea on 2017/6/9.
 */
public class InterfaceTest3 {
    @Test
    public void test() {
        //此处是调用接口中的静态方法
        String name = StaticInterface.getName();
        System.out.println(name); //这里是在接口中的静态方法!
    }
}
