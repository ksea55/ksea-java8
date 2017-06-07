package org.ksea.java8.lambda.core;

import org.junit.Test;

import java.util.function.Function;

/**
 * Created by mexican on 2017/6/7.
 * 函数型接口
 */
public class FunctionTest {

    /*
     * @FunctionalInterface
     * public interface Function<T, R> {
     *   R apply(T t);
     * }
     * T是参数类型
     * R是返回值类型
     */

    @Test
    public void test1() {

        //此处用Lambda来进行实现

        //需求1将小写字母转换成大写字母
        String s1=handlerString("Hello World",x -> x.toUpperCase());
        System.out.println(s1); //打印结果:HELLO WORLD

        //需求2截取字符串
        String s2=handlerString("嘿嘿我爱你",x->x.substring(2));
        System.out.println(s2); //打印结果:我爱你


    }

    //字符串处理
    public String handlerString(String srcString, Function<String, String> function) {
        return function.apply(srcString);
    }

}
