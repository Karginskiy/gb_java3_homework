package lesson2;

class Product {

    private int id;
    private String group1 = "";
    private String group2 = "";
    private String group3 = "";
    private String group4 = "";
    private String group5 = "";

    private String shortName = "";
    private long code;
    private String articul;
    private String fullName = "";
    private int price;

    private Product(ProductBuilder productBuilder) {
        this.id = productBuilder.id;
        this.group1 = productBuilder.group1;
        this.group2 = productBuilder.group2;
        this.group3 = productBuilder.group3;
        this.group4 = productBuilder.group4;
        this.group5 = productBuilder.group5;
        this.shortName = productBuilder.shortName;
        this.code = productBuilder.code;
        this.fullName = productBuilder.fullName;
        this.price = productBuilder.price;
        this.articul = productBuilder.articul;
    }

    public String getGroup1() {
        return group1;
    }

    public String getGroup2() {
        return group2;
    }

    public String getGroup3() {
        return group3;
    }

    public String getGroup4() {
        return group4;
    }

    public String getGroup5() {
        return group5;
    }

    public String getShortName() {
        return shortName;
    }

    public long getCode() {
        return code;
    }

    public String getArticul() {
        return articul;
    }

    public String getFullName() {
        return fullName;
    }

    public int getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Product{" +
                "price=" + price +
                ", fullName='" + fullName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (id != product.id) return false;
        if (code != product.code) return false;
        if (price != product.price) return false;
        if (group1 != null ? !group1.equals(product.group1) : product.group1 != null) return false;
        if (group2 != null ? !group2.equals(product.group2) : product.group2 != null) return false;
        if (group3 != null ? !group3.equals(product.group3) : product.group3 != null) return false;
        if (group4 != null ? !group4.equals(product.group4) : product.group4 != null) return false;
        if (group5 != null ? !group5.equals(product.group5) : product.group5 != null) return false;
        if (shortName != null ? !shortName.equals(product.shortName) : product.shortName != null) return false;
        if (articul != null ? !articul.equals(product.articul) : product.articul != null) return false;
        return fullName != null ? fullName.equals(product.fullName) : product.fullName == null;

    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (group1 != null ? group1.hashCode() : 0);
        result = 31 * result + (group2 != null ? group2.hashCode() : 0);
        result = 31 * result + (group3 != null ? group3.hashCode() : 0);
        result = 31 * result + (group4 != null ? group4.hashCode() : 0);
        result = 31 * result + (group5 != null ? group5.hashCode() : 0);
        result = 31 * result + (shortName != null ? shortName.hashCode() : 0);
        result = 31 * result + (int) (code ^ (code >>> 32));
        result = 31 * result + (articul != null ? articul.hashCode() : 0);
        result = 31 * result + (fullName != null ? fullName.hashCode() : 0);
        result = 31 * result + price;
        return result;
    }

    public static class ProductBuilder {

        private int id;

        private String group1 = "";
        private String group2 = "";
        private String group3 = "";
        private String group4 = "";
        private String group5 = "";

        private String shortName = "";
        private long code;
        private String articul;
        private String fullName = "";
        private int price;

        public ProductBuilder group1(String group1) {
            this.group1 = group1;
            return this;
        }

        public ProductBuilder group2(String group2) {
            this.group2 = group2;
            return this;
        }

        public ProductBuilder group3(String group3) {
            this.group3 = group3;
            return this;
        }

        public ProductBuilder group4(String group4) {
            this.group4 = group4;
            return this;
        }

        public ProductBuilder group5(String group5) {
            this.group5 = group5;
            return this;
        }

        public ProductBuilder shortName(String shortName) {
            this.shortName = shortName;
            return this;
        }

        public ProductBuilder code(long code) {
            this.code = code;
            return this;
        }

        public ProductBuilder articul(String articul) {
            this.articul = articul;
            return this;
        }

        public ProductBuilder fullName(String fullName) {
            this.fullName = fullName;
            return this;
        }

        public ProductBuilder price(int price) {
            this.price = price;
            return this;
        }

        public ProductBuilder id(int id) {
            this.id = id;
            return this;
        }

        public Product build() {
            return new Product(this);
        }


    }

}
