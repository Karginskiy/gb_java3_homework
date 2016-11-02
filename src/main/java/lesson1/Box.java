package lesson1;

import java.util.ArrayList;
import java.util.Iterator;

class Box<T extends AbstractFruit> implements Iterable<T> {

    private final ArrayList<T> list = new ArrayList<T>();

    public float getWeight() {
        Float result = 0.0f;
        for (T fruit : this) {
            result += fruit.getWeight();
        }

        System.out.println(result);
        return result;
    }

    public void addFruit(T fruit) {
        list.add(fruit);
    }

    public boolean compare(Box<?> box) {
        return this.getWeight() == box.getWeight();
    }

    public void dropToAnotherBox(final Box<T> box) {
        forEach(box::addFruit);
    }

    @Override
    public String toString() {
        return list.toString();
    }

    public Iterator<T> iterator() {
        return list.iterator();
    }


}
