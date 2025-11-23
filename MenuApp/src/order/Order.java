package order;

import menuItems.MenuItem;
import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<MenuItem> items;
    private String orderType; // "dine-in", "delivery", "takeaway"
    
    public Order(String orderType) {
        this.items = new ArrayList<>();
        this.orderType = orderType;
    }
    
    public void addItem(MenuItem item) {
        items.add(item);
    }
    
    public List<MenuItem> getItems() {
        return items;
    }
    
    public String getOrderType() {
        return orderType;
    }
    
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
    
    public String getOrderDetails() {
        StringBuilder details = new StringBuilder();
        details.append("Order Type: ").append(orderType).append("\n");
        details.append("Items:\n");
        for (MenuItem item : items) {
            details.append("  - ").append(item.getDescription())
                   .append(" ($").append(String.format("%.2f", item.getCost())).append(")\n");
        }
        return details.toString();
    }
}