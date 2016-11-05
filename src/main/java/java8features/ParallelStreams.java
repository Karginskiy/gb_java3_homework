package java8features;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class ParallelStreams {

    public static void main(String[] args) {

        int max = 1_000_000;
        List<String> values = new ArrayList<>();
        for (int i = 0; i < max; i++) {
            UUID uuid = UUID.randomUUID();
            values.add(uuid.toString());
        }

        // Sequential Sort
        long t0 = System.nanoTime();

        long count = values.stream().sorted().count();
        System.out.println(count);

        long t1 = System.nanoTime();

        long millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("sequential sort took: %d ms", millis));

        // Parallel Sort

        long t01 = System.nanoTime();

        long count1 = values.parallelStream().sorted().count();
        System.out.println(count1);

        long t02 = System.nanoTime();

        millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
        System.out.println(String.format("parralel sort took: %d ms", millis));

    }

}
