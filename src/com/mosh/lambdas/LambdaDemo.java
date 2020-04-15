package com.mosh.lambdas;

import java.util.Arrays;
import java.util.List;
import java.util.function.*;

public class LambdaDemo {

    public static void showConsumer() {
        Consumer<String> print = s -> System.out.println(s);
        Consumer<String> printUppercase = s -> System.out.println(s.toUpperCase());

        List<String> list = Arrays.asList("a","b","c");

        list.forEach(print.andThen(printUppercase));
    }

    private static void showSupply() {
        Supplier<Double> getRandom = Math::random;
        System.out.println(getRandom.get());
    }

    public static void showFunc() {
        Function<String, Integer> map = str -> str.length();
        System.out.println(map.apply("hello"));
    }

    public static void showComposeFunc() {
        Function<String, String> replaceColumn = s -> s.replace(":","=");
        Function<String, String> addCurly = s -> "{"+s+"}";

//        String result = replaceColumn
//                            .andThen(addCurly)
//                            .apply("key:value");

        String result = addCurly
                            .compose(replaceColumn)
                            .apply("key:value");

        System.out.println(result);
    }

    public static void showPredicate() {
        Predicate<String> isLongerThan5 = s -> s.length() > 5;
        Predicate<String> hasLeftBrace = s -> s.startsWith("{");
        Predicate<String> hasRightBrace = s -> s.endsWith("}");
        boolean test = isLongerThan5
                        .and(hasLeftBrace.or(hasRightBrace))
                        .test("{helo1");

        System.out.println(test);

    }

    public static void showBinaryOpr() {
        BinaryOperator<Integer> add = (x,y) -> x+y;
        UnaryOperator<Integer> square = x -> x*x;
        System.out.println(add.andThen(square).apply(1,2));
    }



    public static void main(String[] args) {
//        showSupply();
//        showFunc();
//        showConsumer();
//        showComposeFunc();
//        showPredicate();
        showBinaryOpr();
    }


}
