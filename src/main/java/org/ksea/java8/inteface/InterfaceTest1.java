package org.ksea.java8.inteface;

import org.junit.Test;

/**
 * Created by ksea on 2017/6/9.
 * <p>
 * 此处InterfaceTest继承了BaseService实现其UserService接口
 */
public class InterfaceTest1 extends BaseService implements UserService {

    @Test
    public void  test(){
        String name = this.getName();
        System.out.println(name); //打印结果:this is BaseService

        /**
         * 总结从打印结果:this is BaseService
         * 我们可以看出
         * 当一个类的父类与接口的默认方法，其方法签名一样以及返回值一样的时候
         * 其子类在调用该方法的时候，优先调用类中的方法，这就是类优先原则
         *
         * 如当前案例
         *
         * InterfaceTest1 继承了 BaseService
         *                实现了 UserService
         * 同时BaseService中的getName方法与 UserService接口中的默认实现方法 getName一样
         *
         * 此刻在InterfaceTest1调用getName方法的时候，优先调用的是BaseService类中的getName方法
         * 因此其打印结果是:this is BaseService
         *
         *
         *
         *
         *
         *
         */


    }

}
