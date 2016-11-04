package lesson2;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

class QueryController {

    private Connector connector;
    private PreparedStatement statement;

    QueryController(Connector connector) {
        this.connector = connector;
    }


    ProductSet getProducts() {

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

    void openBatch() {
        connector.connect();
        try {
            statement = connector.getConnection()
                    .prepareStatement("INSERT INTO products (group1, group2, group3, group4, group5, short_name, code, articul, full_name, price) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    void addProduct(Product product) {

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

    void executeBatch() {

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

    public int updateProducts(ProductSet set) {

        if (set == null) {
            return 0;
        }

        try {

            connector.connect();

            PreparedStatement statement = statement = connector.getConnection().prepareStatement("UPDATE products SET group1 = ?, group2 = ?, group3 = ?, " +
                    "group4 = ?, group5 = ?, short_name = ?, code = ?, articul = ?, full_name = ?, price = ? WHERE id = ?");


            for (Product product : set) {

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
                statement.setInt(11, product.getId());

                statement.addBatch();
            }

            statement.executeBatch();
            connector.getConnection().commit();


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connector.disconnect();
        }

        return set.size();

    }



}
