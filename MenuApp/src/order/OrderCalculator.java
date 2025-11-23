package order;

import menuItems.MenuItem;

public class OrderCalculator {
    private static final double TAX_RATE = 0.14; // 14% tax
    
    public double calculateSubtotal(Order order) {
        double subtotal = 0.0;
        for (MenuItem item : order.getItems()) {
            subtotal += item.getCost();
        }
        return subtotal;
    }
    
    public double calculateTax(double subtotal) {
        return subtotal * TAX_RATE;
    }
    
    public double calculateTotal(double subtotal, double tax, double discount) {
        return subtotal + tax - discount;
    }
}