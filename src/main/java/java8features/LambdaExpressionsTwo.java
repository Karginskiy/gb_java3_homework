package java8features;

@FunctionalInterface
interface Converter<F, T> {
    T convert(F from);
}

class Something {

    String startsWith(String s) {
        return String.valueOf(s.charAt(0));
    }

}

public class LambdaExpressionsTwo {

    public static void main(String[] args) {

        // Not so perfect
        Converter<String, Integer> converter1 = (from) -> Integer.valueOf(from);

        // Better (method reference)
        Converter<String, Integer> converter = Integer::valueOf;
        Integer converted = converter.convert("123");

        System.out.println(converted);

        Something something = new Something();
        Converter<String, String> converter2 = something::startsWith;
        String convert = converter2.convert("Java");
        System.out.println(convert);

    }

}
