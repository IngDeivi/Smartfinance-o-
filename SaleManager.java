package sales;

import inventory.InventoryManager;
import product.Product;

import java.util.ArrayList;
import java.util.List;

public class SaleManager {
    private final List<Sale> sales;
    private int identifier = 1;
    private final InventoryManager inventoryManager;

    public SaleManager(InventoryManager inventoryManager) {
        this.sales = new ArrayList<>();
        this.inventoryManager = inventoryManager;
    }

    public List<Sale> getSales() {
        return this.sales;
    }

    public void addSale(Product product, int quantity) {
        this.sales.add(new Sale(this.identifier++, product, quantity));
        this.inventoryManager.getItem(product.getSku()).subtractStock(quantity);
    }

    public boolean removeSale(int saleId) {
        for (Sale sale : this.sales) {
            if (sale.getId() == saleId) {
                this.sales.remove(sale);
                this.inventoryManager.getItem(sale.getProduct().getSku()).addStock(sale.getQuantity());
                return true;
            }
        }
        return false;
    }

    public Sale findSaleById(int id) {
        for (Sale sale : this.sales) {
            if (sale.getId() == id) {
                return sale;
            }
        }
        return null;
    }

    public int getProfits() {
        int profits = 0;
        for (Sale sale : this.sales) {
            profits += sale.getProfits();
        }
        return profits;
    }

    public int minProfit() {
        int minProfit = 999999999;
        for (Sale sale : this.sales) {
            if (sale.getProfits() < minProfit) {
                minProfit = sale.getProfits();
            }
        }
        return minProfit;
    }

    public int maxProfit() {
        int maxProfit = 0;
        for (Sale sale : this.sales) {
            if (sale.getProfits() > maxProfit) {
                maxProfit = sale.getProfits();
            }
        }
        return maxProfit;
    }

    public float averageProfit() {
        var averageProfit = 0f;
        for (Sale sale : this.sales) {
            averageProfit += sale.getProfits();
        }
        return averageProfit / sales.size();
    }
}
