package ru.clevertec.check.bean;

public class Product {

    public int id;
    public String name;
    public double priceProduct;
    public int count;
    public double priceTotal;

    public Product() {
    }

    public Product(int id, int count) {
        this.id = id;
        this.count = count;
    }

    public Product(int id, String name, double priceProduct) {
        this.id = id;
        this.name = name;
        this.priceProduct = priceProduct;
    }

    public Product(int id, int count, String name) {
        this.id = id;
        this.count = count;
        this.name = name;
    }

    public Product(int id, String name, double priceProduct, int count) {
        this.id = id;
        this.name = name;
        this.priceProduct = priceProduct;
        this.count = count;
    }

    public Product(int id, String name, double priceProduct, int count, double priceTotal) {
        this.id = id;
        this.name = name;
        this.priceProduct = priceProduct;
        this.count = count;
        this.priceTotal = priceTotal;
    }

    public int getID() {
        return id;
    }

    public void setID(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPriceProduct() {
        return priceProduct;
    }

    public void setPriceProduct(double priceProduct) {
        this.priceProduct = priceProduct;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getPriceTotal() {
        return priceTotal;
    }

    public void setPriceTotal(double priceTotal) {
        this.priceTotal = priceTotal;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Product product = (Product) o;

        if (id != product.id) return false;
        if (Double.compare(product.priceProduct, priceProduct) != 0) return false;
        if (count != product.count) return false;
        if (Double.compare(product.priceTotal, priceTotal) != 0) return false;
        return name != null ? name.equals(product.name) : product.name == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        temp = Double.doubleToLongBits(priceProduct);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + count;
        temp = Double.doubleToLongBits(priceTotal);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Product{" +
                "qty=" + id +
                ", name='" + name + '\'' +
                ", priceProduct=" + priceProduct +
                ", count=" + count +
                ", priceTotal=" + priceTotal +
                '}';
    }
}
