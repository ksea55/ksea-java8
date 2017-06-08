package org.ksea.java8.lambda.method.quote;

import org.junit.Test;

import java.util.function.BiFunction;
import java.util.function.BiPredicate;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * Created by ksea on 2017/6/8.
 * Lmabda构造函数引用:
 * 格式:
 * ClassName:new
 * 注意:
 * 需要调用的构造函数的参数列表要与函数式接口中的抽象方法的参数列表保持一致
 */
public class LmabdaConstructorQuoteTest {

    @Test
    public void test1() {
        //Lmabda表达式
        Supplier<Student> supplier = () -> new Student();
        //这里是通过Lmabda表达式来实现其供给函数式接口
        Student student = supplier.get();
        System.out.println(student);


        /*通过构造函数引用语法来改造
         * 这里的Student::new具体调用那个构造函数，是需要根据
         * 这里的供给函数式接口Supplier的参数列表，因此之类调用的是无参构造函数
         *
         *
         */

        Supplier<Student> s = Student::new;
        Student student1 = s.get();
        System.out.println(student1);


    }

    @Test
    public void test2() {
        /*
        * 此处我们是通过 Lmabda表达式来进行函数型接口的实现，
        * 调用了 public Student(Integer sno){}构造函数
        *
        * */

        Function<Integer, Student> function = x -> new Student(x);
        Student student = function.apply(2110);
        System.out.println(student); //打印结果 ：Student{id=null, sno=2110, name='null', age=null, hobby='null'}

        //函数构造引用来改造
        function = Student::new;
        Student student1 = function.apply(33421);
        System.out.println(student1); //Student{id=null, sno=33421, name='null', age=null, hobby='null'}

        //可以看出 构造函数引用与函数式接口列表参数进行匹配

    }

    @Test
    public void test3() {

        BiFunction<Integer, String, Student> biFunction = (x, y) -> new Student(x, y);
        Student student = biFunction.apply(22331, "小明");
        System.out.println(student);


        //构造函数引用

        biFunction = Student::new;
        Student student1 = biFunction.apply(12345, "张三");
        System.out.println(student1); //Student{id=null, sno=12345, name='张三', age=null, hobby='null'}
    }
}

class Student {

    private Integer id;
    private Integer sno;
    private String name;
    private Integer age;
    private String hobby;

    //无参构造函数
    public Student() {
    }

    //带有一个参数的构造函数
    public Student(Integer sno) {
        this.sno = sno;
    }

    public Student(Integer sno, String name) {
        this.sno = sno;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSno() {
        return sno;
    }

    public void setSno(Integer sno) {
        this.sno = sno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", sno=" + sno +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", hobby='" + hobby + '\'' +
                '}';
    }
}

