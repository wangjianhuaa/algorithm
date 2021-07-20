package com.wang;

import jdk8.IConverter;
import jdk8.IFormula;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

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

        System.out.println(iConverter.convert("88").getClass().getName());
    }
}
