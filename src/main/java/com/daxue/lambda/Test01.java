package com.daxue.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class Test01 {
    public static String[] strings = {"a", "b", "c", "d", "e"};

    public static List<String> list = Arrays.asList(strings);
    private static Stream<String> stream = Stream.of(strings);

    private static String listWay(List<String> list) {
        String result = null;
        for (String s: list) {
            System.out.println("list+" + s);
            if (s.equals("c")) {
                System.out.println("done!");
                result = "c";
                break;
            }
        }
        return result;
    }

    private static String streamWay(Stream<String> stream) {
        return stream.peek(s1 -> System.out.println("steam+" + s1))
                .filter(s -> s.equals("c"))
                .peek(s2 -> System.out.println("done!"))
                .findFirst().orElse(null);
    }

    public static void main(String[] args) {
        ArrayList arrayList = new ArrayList(list);
        arrayList.add("f");
        arrayList.add("g");


        String s = streamWay(stream);
        System.out.println(s);
    }

}
