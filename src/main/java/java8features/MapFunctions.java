package java8features;

import java.util.HashMap;
import java.util.Map;

public class MapFunctions {

    public static void main(String[] args) {

        Map<Integer, String> map = new HashMap<>();

        for (int i = 0; i < 10; i++) {
            map.putIfAbsent(i, "val" + i);
        }

        map.forEach(((integer, s) -> System.out.println(s)));

        map.computeIfPresent(3, ((integer, s) -> integer + s));
        map.get(3);

        map.computeIfPresent(9, (integer, s) -> null);
        map.containsKey(9);

        map.computeIfAbsent(23, integer -> "val" + integer);
        map.containsKey(23);

        map.computeIfAbsent(3, integer -> "bam");
        map.get(3);

        map.getOrDefault(42, "not found");

        map.merge(9, "val9", String::concat);
        map.get(9);

        map.merge(9, "concat", String::concat);
        map.get(9);



    }

}
