package java8features;

public interface DefaultMethods {

    double calculate(int a);

    default double sqrt(int a) {
        return Math.sqrt(a);
    }

}

class Test {

    public static void main(String[] args) {

        DefaultMethods methods = new DefaultMethods() {
            @Override
            public double calculate(int a) {
                return sqrt(a * 100);
            }
        };

        System.out.println(methods.calculate(100));
        System.out.println(methods.sqrt(16));

    }

}
