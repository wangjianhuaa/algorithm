package com.wang;

import jdk8.IConverter;
import jdk8.IFormula;
import jdk8.Something;
import jdk8.entity.Person;
import jdk8.factory.IPersonFactory;
import jdk8.lambda.Lambda1;
import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/7/20/020 18:06
 */
public class JuintTest {

    @Test
    public void  test(){
        IFormula iFormula = a -> a*a;
        System.out.println(iFormula.calculate(2));
        System.out.println(iFormula.sqrt(2));
    }

    @Test
    public void test2(){
        List<String> names = Arrays.asList("bank","mike","peter","john");
        Collections.sort(names, Comparator.reverseOrder());
        System.out.println(names);
    }

    @Test
    public void test3(){
        IConverter<String,Integer> iConverter = Integer::valueOf;

        IConverter<Integer,String> iConverter1 = String::valueOf;

        Something something = new Something();
        IConverter<String,String> iConverter2 = something::startsWith;

        System.out.println(iConverter.convert("88").getClass().getName());
        System.out.println(iConverter1.convert(23).getClass().getName());

        System.out.println(iConverter2.convert("sdadasda").getClass().getName());
    }

    @Test
    public void test4(){
        IPersonFactory iPersonFactory = Person::new;
        Person person = iPersonFactory.create("张三","21");
        System.out.println(person);
    }

    @Test
    public void test5(){
        final int num = 1;
        IConverter<Integer,String> iConverter = from->
                String.valueOf(num+from);
        String convert = iConverter.convert(3);
        System.out.println(convert);
    }

    @Test
    public void test6(){
        Lambda1 lambda1 = new Lambda1();
        List<Object> objects = lambda1.testScopes(2, 5);
        for (Object object : objects) {
            System.out.println(object.toString());
        }
    }

    @Test
    public void test7(){
        Predicate<String> predicate = s->s.length()>0;
        boolean a = predicate.test("fo");
        boolean b = predicate.test("");
        boolean c = predicate.negate().test("");
        System.out.println("a:"+a+",b:"+b+",!b:"+c);

        Predicate<Object> nonNull = Objects::nonNull;
        Predicate<Object> isNull = Objects::isNull;

        boolean b1 = isNull.test(null);
        System.out.println("b1 = " + b1);

        Predicate<String> isEmpty = String::isEmpty;

        Predicate<String> isNotEmpty = isEmpty.negate();

        boolean test = isEmpty.test(" ");
        System.out.println("test = " + test);
        
    }

    @Test
    public void test8(){
        Function<String,Integer> toInteger = Integer::valueOf;

        Function<String,String> backToString = toInteger.andThen(String::valueOf);

        Function<String,String> afterToStartsWith = backToString.andThen(new Something()::startsWith);

        String apply = afterToStartsWith.apply("123");

        System.out.println(apply);
    }

    @Test
    public void test9(){
        Supplier<Person> personSupplier0 = Person::new;
        personSupplier0.get();

        Supplier<String> stringSupplier0 = Something::test1;
        System.out.println(stringSupplier0.get());
        System.out.println(personSupplier0.get());
    }
}
