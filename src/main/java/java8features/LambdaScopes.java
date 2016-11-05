package java8features;

public class LambdaScopes {

    public static void main(String[] args) {

        final int num = 1;
        Converter<Integer, String> stringConverter =
                from -> String.valueOf(from + num);

        stringConverter.convert(2);

        int num2 = 1;
        Converter<Integer, String> stringConverter1 =
                (from -> String.valueOf(from + num));

        stringConverter.convert(2);
    }

}

class Lambda {

    static int outerStaticNum;
    int outerNum;

    void testScopes() {

        Converter<Integer, String> stringConverter1 = (from -> {
            outerNum = 23;
            return String.valueOf(from);
        });

        Converter<Integer, String> stringConverter2 = (from -> {
            outerStaticNum = 72;
            return String.valueOf(from);
        });

    }

}
