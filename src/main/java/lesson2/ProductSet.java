package lesson2;

import java.util.ArrayList;
import java.util.Iterator;

class ProductSet implements Iterable<Product> {

    private final ArrayList<Product> products = new ArrayList<>();

    @Override
    public Iterator<Product> iterator() {
        return products.iterator();
    }

    void add(Product product) {
        products.add(product);
    }

    public int size() {
        return products.size();
    }

    public boolean contains(Product product) {
        return products.contains(product);
    }

    public Product get(int index) {
        return products.get(index);
    }

    public void set(int index, Product product) {
        products.set(index, product);
    }

    public ProductSet getChangedSet(ProductSet setFromDb) {
        ProductSet productsSet = new ProductSet();
        for (int i = 0; i < products.size(); i++) {
            if (!products.get(i).equals(setFromDb.get(i))) {
                Product product = products.get(i);
                product.setId(setFromDb.get(i).getId());
                productsSet.add(product);
                System.out.println("Changed item - " + product);
            }
        }

        return productsSet;
    }




}
