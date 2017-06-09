package org.ksea.java8.optional;

import org.junit.Test;

import java.util.Optional;

/**
 * Created by ksea on 2017/6/9.
 * Optional容器常用类的测试
 */
public class OptionalTest {


    //Optional.of(T t) : 创建一个Optional 实例
    @Test
    public void test1() {
        //创建一个Teacher类并用Optional容器进行包装
        Optional<Teacher> t = Optional.of(new Teacher());
        Teacher teacher = t.get();//通过Optional容器的get来获取刚才创建的Teacher类
        System.out.println(teacher); //打印结果:Teacher{name='null', age=null}
    }

    //Optional.empty() : 创建一个空的Optional 实例
    @Test
    public void test2() {
        Optional<Teacher> teacher = Optional.empty();
        System.out.println(teacher); //Optional.empty
        Teacher t = teacher.get(); //此时这里将抛出 java.util.NoSuchElementException: No value present 将更快定位到空指针异常
        System.out.println(t);

    }


    //Optional.ofNullable(T t):若t 不为null,创建Optional 实例,否则创建空实例
    @Test
    public void test3() {

        Optional<Teacher> teacherOptional = Optional.ofNullable(new Teacher());
        Teacher teacher = teacherOptional.get(); //此时不为空 就创建了 Optinal实例
        System.out.println(teacher); //Teacher{name='null', age=null}

        System.out.println("--------------------------------------------------");

        Optional<Teacher> teacherOptional_ = Optional.ofNullable(null); //此时t为空，创建了一个empty的Optional实例

        Teacher teacher_ = teacherOptional_.get(); //此处调用将抛出 java.util.NoSuchElementException: No value present异常
        System.out.println(teacher_);


    }

    //  isPresent() : 判断是否包含值
    @Test
    public void test4() {

        Optional<Teacher> teacherOptional = Optional.of(new Teacher());
        boolean present = teacherOptional.isPresent(); //判定Optional容器是否有值
        System.out.println(present); //true 有值

        System.out.println("---------------------------------------------------------");

        //现在创建的是一个空的Optional容器
        Optional<Teacher> optional = Optional.ofNullable(null);
        boolean present1 = optional.isPresent();
        System.out.println(present1); //false 没值

    }


    // orElse(T t) : 如果调用对象包含值，返回该值，否则返回t
    @Test
    public void test5() {

        //此时Optional容器是有值的
        Optional<Teacher> teacherOptional = Optional.ofNullable(new Teacher("k.sea", 28));
        Teacher teacher = teacherOptional.get();
        System.out.println(teacher); //运行结果Teacher{name='k.sea', age=28}

        //此刻调用
        Teacher teacher1 = teacherOptional.orElse(new Teacher("默认管理员", 99));
        System.out.println(teacher1);// 打印结果:Teacher{name='k.sea', age=28}


        System.out.println("---------------------------------------------------");
        teacherOptional = Optional.ofNullable(null);//此刻创建了一个empty的Optinal容器
        Teacher teacher2 = teacherOptional.orElse(new Teacher("默认管理员", 99));
        System.out.println(teacher2);//打印结果：Teacher{name='默认管理员', age=99}


        /**
         *
         * 此案例就更能说明 Optinal容器中的 orElse 当Optional不为空的时候，就取的是自己的
         * 当Optional为空就取的是orElse给的值 其实就相当于 if else的感觉
         *
         *
         */


    }

    // orElseGet(Supplier s) :如果调用对象包含值，返回该值，否则返回s 获取的值
    @Test
    public void test6() {
        Optional<Teacher> teacherOptional = Optional.ofNullable(new Teacher("jacky", 21));
        Teacher teacher = teacherOptional.orElseGet(() -> new Teacher("defaut", 24));
        System.out.println(teacher); //打印结果:Teacher{name='jacky', age=21}

        System.out.println("-------------------------------------------------");
        teacherOptional = Optional.ofNullable(null);
        Teacher defaut = teacherOptional.orElseGet(() -> new Teacher("defaut", 24));
        System.out.println(defaut); //打印结果:Teacher{name='defaut', age=24}

    }

    //map(Function f): 如果有值对其处理，并返回处理后的Optional，否则返回Optional.empty()
    @Test
    public void test7() {

        Optional<Teacher> teacherOptional = Optional.ofNullable(new Teacher("ksea55", 28));
        Optional<String> nameOptional = teacherOptional.map(Teacher::getName);
        String name = nameOptional.get();
        System.out.println(name); //ksea55

        System.out.println("---------------------------------------------------------------------");


        teacherOptional = Optional.ofNullable(null);
        Optional<String> optional = teacherOptional.map(Teacher::getName);
        System.out.println(optional.isPresent()); //打印结果：false 说明这里返回的是一个empty optional
        System.out.println(optional.get());//此处调用将 抛出  java.util.NoSuchElementException: No value present


    }

    //flatMap(Function mapper):与map 类似，要求返回值必须是Optional
    @Test
    public void test8() {
        Optional<Teacher> teacherOptional = Optional.ofNullable(new Teacher("ksea55", 28));
        Optional<String> optional = teacherOptional.flatMap(teacher -> Optional.of(teacher.getName()));//这里flatMap的返回值也必须用Optional进行包装，这就是与map的区别
        System.out.println(optional.get()); //ksea55

    }
}
