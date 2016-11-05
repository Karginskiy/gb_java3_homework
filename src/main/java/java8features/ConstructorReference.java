package java8features;

public class ConstructorReference {

    static class Person {

        String firstName;
        String lastName;

        Person() {

        }

        Person(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

    }

    private static interface PersonFactory<P extends Person> {
        P create(String firstName, String lastName);
    }

    public static void main(String[] args) {
        // Constructor reference
        PersonFactory<Person> personFactory = Person::new;
        Person person = personFactory.create("Peter", "Parker");
    }

}
