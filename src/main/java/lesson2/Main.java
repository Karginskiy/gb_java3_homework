package lesson2;

public class Main {

    public static void main(String[] args) {

        FileParser fileParser = new FileParser("F:\\Projects\\gb_java3_homework\\src\\main\\java\\lesson2\\files\\List.csv");
        ProductSet productSet = fileParser.parse();
        QueryController controller = new QueryController(new Connector(
                "jdbc:sqlite:src\\main\\java\\lesson2\\files\\lesson3.db"));

        controller.openBatch();
        productSet.forEach((controller::addProduct));

        controller.executeBatch();

        ProductSet productsInDB = controller.getProducts();

        fileParser.watchForChanges();
        ProductSet set = fileParser.parse();



    }
}
