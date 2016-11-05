package java8features;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LambdaExpressions {

    public static void main(String[] args) {

        List<String> names = Arrays.asList("peter", "anna", "mike", "xenia");


        // Without lambda
        Collections.sort(names, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });

        // With lambda - bad
        Collections.sort(names, (String a, String b) -> {
            return b.compareTo(a);
        });

        // With lambda - better
        Collections.sort(names, (String a, String b) -> b.compareTo(a));

        // With lambda - best
        Collections.sort(names, (a, b) -> b.compareTo(a));

    }

}
