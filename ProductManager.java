package product;

import inventory.InventoryItem;
import inventory.InventoryManager;

import java.util.ArrayList;
import java.util.List;

public class ProductManager {
    private final List<Product> products;
    private final InventoryManager inventoryManager;

    public ProductManager(InventoryManager inventoryManager) {
        this.products = new ArrayList<>();
        this.inventoryManager = inventoryManager;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        var temp = this.findProduct(product.getSku());
        if (temp != null) {
            throw new IllegalArgumentException("El producto existe actualmente, por favor cambie el código del producto!");
        }
        this.products.add(product);
        this.inventoryManager.addItem(new InventoryItem(product, 0));
    }

    public boolean removeProduct(String sku) {
        for (Product product : this.products) {
            if (product.getSku().equals(sku)) {
                this.products.remove(product);
                this.inventoryManager.removeItem(product.getSku());
                return true;
            }
        }
        return false;
    }

    public Product findProduct(String sku) {
        for (Product product : this.products) {
            if (product.getSku().equals(sku)) {
                return product;
            }
        }
        return null;
    }
}
