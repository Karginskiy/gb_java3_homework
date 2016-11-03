package lesson2;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryController {

    Connector connector;
    PreparedStatement statement;

    QueryController(Connector connector) {
        this.connector = connector;
    }


    public ProductSet getProducts() {

        ProductSet products = new ProductSet();

        connector.connect();

        try {
            Statement statement = connector.getConnection().createStatement();
            ResultSet set = statement.executeQuery("SELECT * FROM products");
            while (set.next()) {
                products.add(new Product.ProductBuilder()
                        .id(set.getInt("id"))
                        .group1(set.getString("group1"))
                        .group2(set.getString("group2"))
                        .group3(set.getString("group3"))
                        .group4(set.getString("group4"))
                        .group5(set.getString("group5"))
                        .shortName(set.getString("short_name"))
                        .code(set.getInt("code"))
                        .articul(set.getString("articul"))
                        .fullName(set.getString("full_name"))
                        .price(set.getInt("price"))
                        .build());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.disconnect();
        }

        return products;

    }

    public void openBatch() {
        connector.connect();
        try {
            statement = connector.getConnection()
                    .prepareStatement("INSERT INTO products (group1, group2, group3, group4, group5, short_name, code, articul, full_name, price) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addProduct(Product product) {

        try {

            statement.setString(1, product.getGroup1());
            statement.setString(2, product.getGroup2());
            statement.setString(3, product.getGroup3());
            statement.setString(4, product.getGroup4());
            statement.setString(5, product.getGroup5());
            statement.setString(6, product.getShortName());
            statement.setLong(7, product.getCode());
            statement.setString(8, product.getArticul());
            statement.setString(9, product.getFullName());
            statement.setInt(10, product.getPrice());

            statement.addBatch();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void executeBatch() {

        if (statement != null) {
            try {
                statement.executeBatch();
                connector.getConnection().commit();
                connector.getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public void cleanDatabase() {

        connector.connect();
        try {
            Statement statement = connector.getConnection().createStatement();
            statement.execute("DELETE FROM products");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.disconnect();
        }

    }

    public void updateProduct(String fullName, Product product) {

        connector.connect();
        try {
            PreparedStatement statement = connector.getConnection().prepareStatement("UPDATE products SET group1 = ?, group2 = ?, group3 = ?, " +
                    "group4 = ?, group5 = ?, short_name = ?, code = ?, articul = ?, full_name = ?, price = ? WHERE full_name = ?");
            statement.setString(1, product.getGroup1());
            statement.setString(2, product.getGroup2());
            statement.setString(3, product.getGroup3());
            statement.setString(4, product.getGroup4());
            statement.setString(5, product.getGroup5());
            statement.setString(6, product.getShortName());
            statement.setLong(7, product.getCode());
            statement.setString(8, product.getArticul());
            statement.setString(9, product.getFullName());
            statement.setInt(10, product.getPrice());
            statement.setString(11, fullName);

            statement.executeUpdate();
            System.out.println("Updated " + statement.getUpdateCount() + " element.");
            connector.getConnection().commit();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.disconnect();
        }

    }



}
