package org.ksea.java8.lambda.core;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

/**
 * Created by ksea on 2017/6/7.
 * 供给型函数式接口
 */
public class SupplierTest {

    /* @FunctionalInterface
     public interface Supplier<T> {

         T get(); //Gets a result. @return a result
     }
 */
    @Test
    public void test1() {

           //产生100之间的5个随机数
        List<Integer> integerList = produceList(5, () -> (int) (Math.random() * 100));

        integerList.stream().forEach(System.out::println);
    }


    //如需求创建指定数量的随机数

    public List<Integer> produceList(Integer num, Supplier<Integer> supplier) {

        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < num; i++) {
            //获取供给的结果
            Integer result = supplier.get();
            list.add(result);
        }

        return list;
    }


}
