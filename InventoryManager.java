package inventory;

import java.util.ArrayList;
import java.util.List;

public class InventoryManager {
    private final List<InventoryItem> items;

    public InventoryManager() {
        this.items = new ArrayList<>();
    }

    public List<InventoryItem> getItems() {
        return items;
    }

    public void addItem(InventoryItem item) {
        items.add(item);
    }

    public void removeItem(String sku) {
        for (InventoryItem item : items) {
            if (item.equals(sku)) {
                items.remove(item);
            }
        }
    }

    public InventoryItem getItem(String sku) {
        for (InventoryItem item : items) {
            if (item.equals(sku)) {
                return item;
            }
        }
        return null;
    }
}
