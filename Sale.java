package sales;

import product.Product;

public class Sale {
    private final int id;
    private final Product product;
    private final int quantity;
    private final int cost;
    private final int price;

    public Sale(int id, Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.id = id;
        this.price = product.getPrice();
        this.cost = product.getCost();
    }

    public int getId() {
        return id;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getTotalPrice() {
        return this.product.getPrice() * this.quantity;
    }

    public int getProfits() {
        return (this.price - this.cost) * this.quantity;
    }

    @Override
    public String toString() {
        return String.format("""
                VENTA N-%d
                PRODUCTO:
                [%s] %s
                CANTIDAD: %d
                TOTAL DE VENTA: %d
                """, this.id, this.product.getSku(), this.product.getName(), this.quantity, this.getTotalPrice());
    }
}
