package lesson2;

import java.util.Iterator;
import java.util.LinkedHashSet;

public class ProductSet implements Iterable<Product> {

    private final LinkedHashSet<Product> products = new LinkedHashSet<>();

    @Override
    public Iterator<Product> iterator() {
        return products.iterator();
    }

    public void add(Product product) {
        products.add(product);
    }

    public int size() {
        return products.size();
    }

    public boolean contains(Product product) {
        return products.contains(product);
    }


}
