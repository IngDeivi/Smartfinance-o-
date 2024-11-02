package inventory;

import product.Product;

public class InventoryItem {
    private final Product product;
    private int stock;

    public InventoryItem(Product product, int stock) {
        if (product == null) {
            throw new IllegalArgumentException("El producto no puede ser nulo.");
        }
        if (stock < 0) {
            throw new IllegalArgumentException("El número de existencias no puede tener un valor negativo.");
        }
        this.product = product;
        this.stock = stock;
    }

    public Product getProduct() {
        return product;
    }

    public int getStock() {
        return stock;
    }

    public void addStock(int stock) {
        this.stock += stock;
    }

    public void subtractStock(int stock) {
        if (stock > this.stock) {
            throw new IllegalArgumentException("No se puede restar menos de la cantidad existente del producto.");
        }
        this.stock -= stock;
    }

    public boolean equals(String sku) {
        if (sku == null) {
            return false;
        }
        return sku.equals(this.product.getSku());
    }

    @Override
    public String toString() {
        return String.format("[%s] %s: %d", this.product.getSku(), this.product.getName(), this.stock);
    }
}
