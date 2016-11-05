package java8features;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Streams {

    public static void main(String[] args) {

        List<String> strings = new ArrayList<>();
        strings.add("aaa");
        strings.add("a2a");
        strings.add("bbb2");
        strings.add("ddd2");
        strings.add("ccc2");
        strings.add("ccc");
        strings.add("bbb2");
        strings.add("ddd1");

        // Filter and printing some by stream features

        strings.stream()
                .filter(s -> s.startsWith("a"))
                .forEach(System.out::println);


        // Sort by stream feat.

        strings.stream()
                .sorted()
                .filter(s -> s.startsWith("a"))
                .forEach(System.out::println);

        // Map - converting all elements at Stream by given function
        // Sort by given Comparator @FunctionalInterface

        strings.stream()
                .map(String::toUpperCase)
                .sorted((a, b) -> b.compareTo(a))
                .forEach(System.out::println);


        // Terminal operations of matching

        boolean anyStartsWithA = strings.stream()
                .anyMatch((s) -> s.startsWith("a"));

        boolean allStartsWithA = strings.stream()
                .allMatch((s) -> s.startsWith("a"));

        boolean noneStartsWithA = strings.stream()
                .noneMatch((s) -> s.startsWith("z"));


        // Count - terminal operation that returning the number of el in stream as long
        long startsWithB =
                strings.stream().filter((s) -> s.startsWith("b")).count();

        Optional<String> reduced = strings.stream().sorted()
                .reduce((s1, s2) -> s1 + "#" + s2);

        reduced.ifPresent(System.out::println);

    }

}
