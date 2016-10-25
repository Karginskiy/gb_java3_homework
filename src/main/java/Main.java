import java.util.*;

public class Main {

    public static void main(String[] args) {


        // First task check
        String[] strings = new String[2];
        strings[0] = "Hello";
        strings[1] = "world!";
        ArrayUtils.swap(strings, 0, 1);
        System.out.println(Arrays.toString(strings));

        // Second task
        ArrayList<String> list = ArrayUtils.asArrayList(strings);
        System.out.println(list);

        // Third task
        Box<Orange> oranges = new Box<>();
        oranges.addFruit(new Orange(1.2f));
        oranges.addFruit(new Orange(1.5f));
        oranges.addFruit(new Orange(1.3f));

        Box<Apple> apples = new Box<>();
        apples.addFruit(new Apple(1.0f));
        apples.addFruit(new Apple(.8f));
        apples.addFruit(new Apple(.6f));

        System.out.println(oranges.compare(apples));

        Box<Apple> apples1 = new Box<>();
        apples1.addFruit(new Apple(1.5f));

        System.out.println(apples);
        System.out.println(apples1);
        apples.dropToAnotherBox(apples1);
        System.out.println(apples1);

    }

}
