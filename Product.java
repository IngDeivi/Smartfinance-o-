package product;

public class Product {
    private String sku;
    private String name;
    private int cost;
    private int price;

    public Product(String sku, String name, int cost, int price) {
        if (sku == null || sku.isEmpty() || name == null || name.isEmpty()) {
            throw new IllegalArgumentException("SKU or nombre de producto invalido");
        }
        if (cost <= 0 || price <= 0) {
            throw new IllegalArgumentException("El precio y el costo del producto no puede ser negativo o igual a 0");
        }
        this.sku = sku;
        this.name = name;
        this.cost = cost;
        this.price = price;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        if (sku == null || sku.isEmpty()) {
            throw new IllegalArgumentException("SKU no puede ser nulo ni vacio");
        }
        this.sku = sku;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Nombre del producto no puede ser nulo");
        }
        this.name = name;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        if (cost <= 0) {
            throw new IllegalArgumentException("No se puede modificar el costo del producto por un valor negativo o igual a 0");
        }
        this.cost = cost;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        if (price <= 0) {
            throw new IllegalArgumentException("No se puede modificar el precio del producto por un valor negativo o igual a 0");
        }
        this.price = price;
    }

    @Override
    public String toString() {
        return String.format("[%s]: %s\nCosto: %d\nPrecio: %d", sku, name, cost, price);
    }
}
