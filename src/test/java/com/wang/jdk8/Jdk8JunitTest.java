package com.wang.jdk8;

import jdk8.IConverter;
import jdk8.IFormula;
import jdk8.Something;
import jdk8.entity.BeanA;
import jdk8.entity.BeanB;
import jdk8.entity.Person;
import jdk8.factory.IPersonFactory;
import jdk8.lambda.Lambda1;
import org.junit.Test;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.temporal.ChronoField;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * @author wangjianhua
 * @Description
 * @date 2021/7/20/020 18:06
 */
public class Jdk8JunitTest {

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

    @Test
    public void test10(){
        Consumer<Person> personConsumer = new Consumer<Person>() {
            @Override
            public void accept(Person person) {
                System.out.println("hello"+ person.getName());
            }
        };
        Consumer<Person> personConsumer1 = p-> System.out.println("hello"+p.getName());
        personConsumer.accept(new Person("tom","1356151"));
        personConsumer1.accept(new Person("john",""));

    }

    @Test
    public void test11(){
        Comparator<Person> comparator = (p1,p2) -> p1.getName().compareTo(p2.getName());
        Comparator<Person> comparator1 = Comparator.comparing(Person::getName);
        Person p1 = new Person("Bob","");
        Person p2 = new Person("Alice","");
        System.out.println(comparator.compare(p1, p2));
        System.out.println(comparator1.reversed().compare(p1, p2));
    }

    @Test
    public void test12(){
        Optional<String> stringOptional = Optional.of("bob");
        System.out.println(stringOptional.isPresent());
        System.out.println(stringOptional.get());
        System.out.println(stringOptional.orElse("pip"));
        stringOptional.ifPresent(s-> System.out.println(s.charAt(0)));
        Optional<Person> personOptional = Optional.of(new Person());
        personOptional.ifPresent(p -> System.out.println(p.getName()));
    }

    @Test
    public void test13(){
        List<String> stringCollection = new ArrayList<>();
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");
        stringCollection.stream().filter(s -> s.startsWith("b")).forEach(System.out::println);
        System.out.println("====================================");
        stringCollection.stream().sorted().forEach(System.out::println);
        System.out.println("====================================");
        stringCollection.stream().map(String::toUpperCase).sorted(Comparator.comparing(s -> s)).forEach(System.out::println);
        boolean a = stringCollection.stream().anyMatch(s -> s.startsWith("a"));
        boolean b = stringCollection.stream().allMatch(s -> s.startsWith("a"));
        boolean c = stringCollection.stream().noneMatch(s -> s.startsWith("z"));
        System.out.println(a +"" +b + "" + c);

        long a1 = stringCollection.stream().filter(s -> s.startsWith("a")).count();
        System.out.println(a1);
        Optional<String> reduce = stringCollection.stream().sorted().reduce((s1, s2) -> s1 + "#" + s2);
        System.out.println(reduce);
    }

    @Test
    public void test14(){
        int  max = 1000000;
        List<String> list = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            list.add(uuid.toString());
        }
        //纳秒
        long t0 = System.nanoTime();
        long count = list.stream().sorted().count();
        System.out.println(count);
        long t1 = System.nanoTime();
        //纳秒转微秒
        long millis = TimeUnit.NANOSECONDS.toMillis(t1-t0);
        //顺序流排序耗时: 1233 ms
        System.out.println(String.format("顺序流排序耗时: %d ms",millis));

    }

    @Test
    public void test15(){
        int  max = 1000000;
        List<String> list = new ArrayList<>(max);
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            list.add(uuid.toString());
        }
        //纳秒
        long t0 = System.nanoTime();
        //并行流排序
        long count = list.parallelStream().sorted().count();
        System.out.println(count);
        long t1 = System.nanoTime();
        //纳秒转微秒
        long millis = TimeUnit.NANOSECONDS.toMillis(t1-t0);
        //并行流排序耗时: 594 ms
        System.out.println(String.format("并行流排序耗时: %d ms",millis));
    }

    @Test
    public void test16(){
        Map<Integer,String> map = new HashMap<>(15);
        for (int i = 0; i < 10; i++) {
            map.putIfAbsent(i,"value"+i);
        }
        map.forEach((k,v) -> System.out.println("key:"+k+",value:"+v));
    }

    @Test
    public void test17(){
        Map<Integer, BeanA> map = new HashMap<>(15);
        for (int i = 0; i < 10; i++) {
            map.putIfAbsent(i,new BeanA(i,"明明"+i,i+20,"89021839021830912809"+i));
        }
        Stream<BeanB> beanBStream0 = map.values().stream().map(beanA -> new BeanB(beanA.getName(), beanA.getAge()));
        beanBStream0.forEach(System.out::println);
    }

    @Test
    public void test18(){
        Map<Integer,String> map = new HashMap<>(15);
        for (int i = 0; i < 10; i++) {
            map.putIfAbsent(i,"value"+i);
        }
        System.out.println(map);
        //computeIfPresent 判断存入的key是否存在 若存在则做相应操作
        map.computeIfPresent(3, (num, val) -> val+num);
        System.out.println("改变之后的map:"+map);

        map.computeIfAbsent(23,num -> "val"+num);
        System.out.println("加了23的map:"+map);

        map.computeIfPresent(9,(num,val) -> null);
        System.out.println("删除key为9之后的map:"+map);

        map.remove(3,"val3");
        System.out.println(map.containsKey(3));
        map.remove(3,"value33");
        System.out.println(map.containsKey(3));

        String notfound = map.getOrDefault(42, "notfound");
        System.out.println(notfound);

        map.merge(9,"value9", String::concat);
        System.out.println(map);
        map.merge(9,"value9", String::concat);
        System.out.println(map);
    }

    @Test
    public void test19(){
        Clock clock = Clock.systemDefaultZone();
        long millis = clock.millis();
        System.out.println(millis);
        Instant instant = clock.instant();
        Date from = Date.from(instant);
        System.out.println(from);
    }

    @Test
    public void test20(){
        for (String availableZoneId : ZoneId.getAvailableZoneIds()) {
            if(availableZoneId.contains("Beijing") || availableZoneId.contains("Shanghai")){
                System.out.println(availableZoneId);
            }

        }
        ZoneId zone1 = ZoneId.of("Europe/Berlin");
        ZoneId zone2 = ZoneId.of("Brazil/East");
        System.out.println(zone1.getRules());
        System.out.println(zone2.getRules());

    }

    @Test
    public void test21(){
        ZoneId zone1 = ZoneId.of("Europe/Berlin");
        ZoneId zone2 = ZoneId.of("Brazil/East");
        LocalTime now1 = LocalTime.now(zone1);
        LocalTime now2 = LocalTime.now(zone2);
        System.out.println(now1.isBefore(now2));
        long hoursBetween = ChronoUnit.HOURS.between(now1, now2);
        System.out.println("hoursBetween = " + hoursBetween);
        long minutesBetween = ChronoUnit.MINUTES.between(now1, now2);
        System.out.println("minutesBetween = " + minutesBetween);

    }

    @Test
    public void test22(){
        LocalTime late = LocalTime.of(23, 59, 59);
        System.out.println(late);
        DateTimeFormatter germanFormatter =
                DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT)
                .withLocale(Locale.GERMAN);
        LocalTime localTime = LocalTime.parse("13:37", germanFormatter);
        System.out.println(localTime);

    }

    @Test
    public void test23(){
        LocalDate today = LocalDate.now();
        //加一天
        LocalDate tomorrow = today.plus(1, ChronoUnit.DAYS);
        //明天减两天
        LocalDate yesterday = tomorrow.minusDays(2);
        LocalDate localDate = LocalDate.of(2021, 7, 22);
        DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        System.out.println(dayOfWeek);


    }

    @Test
    public void test24(){
        DateTimeFormatter germanFormatter =
                DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)
                        .withLocale(Locale.GERMAN);
        LocalDate parse = LocalDate.parse("22.07.2021", germanFormatter);
        System.out.println(parse);
    }

    @Test
    public void test25(){
        LocalDateTime localDateTime = LocalDateTime.of(2021, Month.JULY, 22, 15, 51, 59);
        DayOfWeek dayOfWeek = localDateTime.getDayOfWeek();
        System.out.println(dayOfWeek);
        System.out.println(localDateTime.getMonth());
        long aLong = localDateTime.getLong(ChronoField.MINUTE_OF_DAY);
        System.out.println(aLong);
    }

    @Test
    public void test26(){
        LocalDateTime localDateTime = LocalDateTime.of(2021, Month.JULY, 22, 15, 51, 59);
        Instant toInstant = localDateTime.atZone(ZoneId.systemDefault()).toInstant();
        System.out.println(Date.from(toInstant));
    }

    @Test
    public void test27(){
        //看起来没吊用。 spring可以使用jackson自带注解
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime dateTime = LocalDateTime.parse("2021-07-22 16:05:59", dtf);
        String s = dtf.format(dateTime);
        System.out.println(s);
    }
//注解建议使用传统方式
//    @Test
//    public void test28(){
//        @Hints({@Hint("hint1"),@Hint("hint2")})
//        class Person{
//
//        }
//    }
//
//    @Test
//    public void test29(){
//        @Hints({@Hint("hint1"),@Hint("hint2")})
//        class Person{
//
//        }
//    }
//
//    @Test
//    public void test30(){
//        @Hint("hint1")
//        @Hint("hint2")
//        class Person{
//
//        }
//    }
//
//    @Test
//    public void test31(){
//        @Hint("hint1")
//        @Hint("hint2")
//        class Person1{
//
//        }
//        Hint hint = Person1.class.getAnnotation(Hint.class);
//        System.out.println(hint);
//        Hints hints1 = Person1.class.getAnnotation(Hints.class);
//        System.out.println(hints1.value().length);
//        Hint[] hints2 = Person1.class.getAnnotationsByType(Hint.class);
//        System.out.println(hints2.length);
//
//    }
}
