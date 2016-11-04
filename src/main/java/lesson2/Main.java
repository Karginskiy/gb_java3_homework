package lesson2;

public class Main {

    public static void main(String[] args) {

        FileParser fileParser = new FileParser("E:\\Projects\\gb_java3_homework\\src\\main\\java\\lesson2\\files\\List.csv");
        ProductSet productSet = fileParser.parse();
        ProductService service = new ProductService(new Connector(
                "jdbc:sqlite:src\\main\\java\\lesson2\\files\\lesson3.db"));

        service.cleanDatabase();
        System.out.println("Cleaning database..");

        System.out.println();

        service.openBatch();
        productSet.forEach(service::addProduct);
        service.executeBatch();
        System.out.println("Added elements from file to the database.");

        System.out.println();

        ProductSet productsInDB = service.getProducts();
        System.out.println("Getting elements from dataBase.");

        fileParser.watchForChanges();
        System.out.println();

        ProductSet set = fileParser.parse();
        System.out.println("Getting files from updated file");
        ProductSet changedSet = set.getChangedSet(productsInDB);
        System.out.println("Comparing the elements from updated file and database");

        System.out.println();

        System.out.println("Changed elements: ");
        changedSet.forEach(System.out::println);

        int updateCount = service.updateProducts(changedSet);
        System.out.println("Updated lines - " + updateCount);

    }
}
