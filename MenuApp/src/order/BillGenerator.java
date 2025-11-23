package order;

public class BillGenerator {
    
    public void generateBill(Order order, double subtotal, double tax, double discount, double total) {
        System.out.println("\n" + "=".repeat(50));
        System.out.println("           RESTAURANT BILL");
        System.out.println("=".repeat(50));
        System.out.println("Order Type: " + order.getOrderType());
        System.out.println("-".repeat(50));
        
        System.out.println("ITEMS:");
        for (int i = 0; i < order.getItems().size(); i++) {
            var item = order.getItems().get(i);
            System.out.printf("%d. %-30s $%.2f%n", 
                (i + 1), item.getDescription(), item.getCost());
        }
        
        System.out.println("-".repeat(50));
        System.out.printf("%-35s $%.2f%n", "Subtotal:", subtotal);
        System.out.printf("%-35s $%.2f%n", "Tax (14%):", tax);
        
        if (discount > 0) {
            System.out.printf("%-35s -$%.2f%n", "Discount:", discount);
        }
        
        System.out.println("=".repeat(50));
        System.out.printf("%-35s $%.2f%n", "TOTAL:", total);
        System.out.println("=".repeat(50) + "\n");
    }
}