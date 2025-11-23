import decorator.*;
import Discount.*;
import mainDishFactory.*;
import manager.*;
import menuFactory.*;
import menuItems.*;
import observer.*;
import order.*;
import payment.*;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static OrderSystem orderSystem;
    private static OrderCalculator calculator;
    private static BillGenerator billGenerator;
    
    public static void main(String[] args) {
        // Setup system components
        setupSystem();
        
        // Welcome message
        displayWelcome();
        
        // Main ordering flow
        boolean continueOrdering = true;
        while (continueOrdering) {
            processOrder();
            continueOrdering = askToContinue();
        }
        
        System.out.println("\n========================================");
        System.out.println("   Thank you for visiting!");
        System.out.println("========================================");
        scanner.close();
    }
    
    private static void setupSystem() {
        orderSystem = new OrderSystem();
        Kitchen kitchen = new Kitchen();
        Waiter waiter = new Waiter();
        orderSystem.attach(kitchen);
        orderSystem.attach(waiter);
        calculator = new OrderCalculator();
        billGenerator = new BillGenerator();
        
        System.out.println("===== System initialized successfully! =====\n");
    }
    
    private static void displayWelcome() {
        System.out.println("========================================");
        System.out.println("       WELCOME TO OUR RESTAURANT       ");
        System.out.println("========================================\n");
    }
    
    private static void processOrder() {
        // Step 1 Choose order type
        String orderType = chooseOrderType();
        Order order = new Order(orderType);
        
        // Step 2 Choose menu type (MenuManager displays the menu)
        MenuFactory menuFactory = chooseMenuType();
        
        // Step 3 Display menu and select items (MenuManager helps)
        displayAndSelectItems(menuFactory, order);
        
        // Step 4 Apply customizations
        applyCustomizations(order);
        
        // Step 5 Calculate totals
        double subtotal = calculator.calculateSubtotal(order);
        double tax = calculator.calculateTax(subtotal);
        
        // Step 6 Apply discount
        double discount = applyDiscount(order, subtotal);
        
        // Step 7 Calculate final total
        double total = calculator.calculateTotal(subtotal, tax, discount);
        
        // Step 8 Generate bill
        System.out.println("\n>>> Generating your bill...");
        billGenerator.generateBill(order, subtotal, tax, discount, total);
        
        // Step 9 Process payment
        processPayment(total);
        
        // Step 10 Notify kitchen and waiter
        System.out.println("\n>>> Notifying kitchen and waiter...");
        orderSystem.placeOrder(order.getOrderDetails());
        
        System.out.println("\n✓ Order placed successfully!");
    }
    
    private static String chooseOrderType() {
        System.out.println("\n=== SELECT ORDER TYPE ===");
        System.out.println("1. Dine-in");
        System.out.println("2. Delivery");
        System.out.println("3. Takeaway");
        System.out.print("Enter your choice (1-3): ");
        
        int choice = getIntInput(1, 3);
        String[] types = {"dine-in", "delivery", "takeaway"};
        System.out.println("=> Order type: " + types[choice - 1].toUpperCase());
        return types[choice - 1];
    }
    
    private static MenuFactory chooseMenuType() {
        System.out.println("\n=== SELECT MENU TYPE ===");
        System.out.println("1. Kids Menu");
        System.out.println("2. Vegetarian Menu");
        System.out.println("3. Non-Vegetarian Menu");
        System.out.print("Enter your choice (1-3): ");
        
        int choice = getIntInput(1, 3);
        MainDishFactory mainDishFactory = new simpleDish();
        
        MenuFactory factory = null;
        switch (choice) {
            case 1:
                factory = new KidsMenu(mainDishFactory);
                System.out.println("=> Selected: Kids Menu");
                break;
            case 2:
                factory = new VegMenu(mainDishFactory);
                System.out.println("=> Selected: Vegetarian Menu");
                break;
            case 3:
                factory = new NonVeg(mainDishFactory);
                System.out.println("=> Selected: Non-Vegetarian Menu");
                break;
        }
        
        // Use MenuManager to display the menu 
        System.out.println("\nHere's what we're serving today:");
        MenuManager menuManager = new MenuManager(factory);
        menuManager.displayFullMenu("chicken");
        
        return factory;
    }
    
    private static void displayAndSelectItems(MenuFactory menuFactory, Order order) {
        System.out.println("\n=== BUILD YOUR ORDER ===");
        
        // Create MenuManager for easy access
        MenuManager menuManager = new MenuManager(menuFactory);
        
        // Display and select appetizer
        System.out.println("\n--- APPETIZER ---");
        MenuItem appetizer = menuManager.getAppetizer();
        System.out.println("Available: " + appetizer.getDescription() + " - $" + 
                          String.format("%.2f", appetizer.getCost()));
        System.out.print("Add to order? (y/n): ");
        if (getYesNo()) {
            order.addItem(appetizer);
            System.out.println("=> Added: " + appetizer.getDescription());
        }
        
        // Display and select main dish
        System.out.println("\n--- MAIN DISH ---");
        String mainDishType = chooseMainDishType();
        MenuItem mainDish = menuManager.getMainDish(mainDishType);
        if (mainDish != null) {
            System.out.println("Selected: " + mainDish.getDescription() + " - $" + 
                              String.format("%.2f", mainDish.getCost()));
            order.addItem(mainDish);
            System.out.println("=> Added to order");
        } else {
            System.out.println("Sorry, this main dish is not available.");
        }
        
        // Display and select dessert
        System.out.println("\n--- DESSERT ---");
        MenuItem dessert = menuManager.getDessert();
        System.out.println("Available: " + dessert.getDescription() + " - $" + 
                          String.format("%.2f", dessert.getCost()));
        System.out.print("Add to order? (y/n): ");
        if (getYesNo()) {
            order.addItem(dessert);
            System.out.println("=> Added: " + dessert.getDescription());
        }
    }
    
    private static String chooseMainDishType() {
        System.out.println("Select Main Dish Type:");
        System.out.println("1. Chicken");
        System.out.println("2. Beef");
        System.out.print("Enter your choice (1-2): ");
        
        int choice = getIntInput(1, 2);
        String type = (choice == 1) ? "chicken" : "beef";
        return type;
    }
    
    private static void applyCustomizations(Order order) {
        if (order.getItems().isEmpty()) {
            return;
        }
        
        System.out.println("\n=== CUSTOMIZE YOUR ITEMS ===");
        System.out.print("Would you like to add customizations? (y/n): ");
        
        if (!getYesNo()) {
            return;
        }
        
        System.out.println("\nSelect item to customize:");
        for (int i = 0; i < order.getItems().size(); i++) {
            System.out.println((i + 1) + ". " + order.getItems().get(i).getDescription());
        }
        System.out.print("Enter item number (or 0 to skip): ");
        
        int itemChoice = getIntInput(0, order.getItems().size());
        if (itemChoice == 0) {
            return;
        }
        
        MenuItem selectedItem = order.getItems().get(itemChoice - 1);
        MenuItem customized = selectedItem;
        
        System.out.println("\nAvailable Add-ons:");
        System.out.println("1. Extra Cheese (+$0.50)");
        System.out.println("2. Sauce (+$0.30)");
        System.out.println("3. Both");
        System.out.print("Enter your choice (1-3): ");
        
        int addonChoice = getIntInput(1, 3);
        
        switch (addonChoice) {
            case 1:
                customized = new ExtraCheese(customized);
                break;
            case 2:
                customized = new Sauce(customized);
                break;
            case 3:
                customized = new ExtraCheese(customized);
                customized = new Sauce(customized);
                break;
        }
        
        // Replace the item in order
        order.getItems().set(itemChoice - 1, customized);
        System.out.println("=> Customization applied: " + customized.getDescription());
    }
    
    private static double applyDiscount(Order order, double subtotal) {
        System.out.println("\n=== APPLY DISCOUNT ===");
        System.out.println("Available Discounts:");
        System.out.println("1. Chicken Discount (15% off)");
        System.out.println("2. Pizza Discount (10% off)");
        System.out.println("3. No Discount");
        System.out.print("Enter your choice (1-3): ");
        
        int choice = getIntInput(1, 3);
        
        if (choice == 3) {
            System.out.println("No discount applied.");
            return 0.0;
        }
        
        DiscountStrategy discountStrategy = null;
        String discountType = "";
        
        switch (choice) {
            case 1:
                discountStrategy = new ChickenDiscount();
                discountType = "Chicken (15% off)";
                break;
            case 2:
                discountStrategy = new PizzaDiscount();
                discountType = "Pizza (10% off)";
                break;
        }
        
        // Apply discount to subtotal for demonstration
        double discountedAmount = discountStrategy.applyDiscount(subtotal);
        double discountValue = subtotal - discountedAmount;
        
        System.out.println("=> Applied: " + discountType);
        System.out.println("Discount amount: $" + String.format("%.2f", discountValue));
        
        return discountValue;
    }
    
    private static void processPayment(double total) {
        System.out.println("\n=== SELECT PAYMENT METHOD ===");
        System.out.println("1. Cash");
        System.out.println("2. Credit/Debit Card");
        System.out.println("3. Mobile Wallet");
        System.out.print("Enter your choice (1-3): ");
        
        int choice = getIntInput(1, 3);
        
        PaymentStrategy paymentStrategy = null;
        
        switch (choice) {
            case 1:
                paymentStrategy = new CashPayment();
                break;
            case 2:
                paymentStrategy = new CardPayment();
                break;
            case 3:
                paymentStrategy = new WalletPayment();
                break;
        }
        
        System.out.println("\n====> Processing payment...");
        boolean success = paymentStrategy.pay(total);
        
        if (success) {
            System.out.println("=> successful Payment!");
        } 
    }
    
    private static boolean askToContinue() {
        System.out.println("\n\nWould you like to place another order? (y/n): ");
        return getYesNo();
    }
    
    // Utility methods
    private static int getIntInput(int min, int max) {
        while (true) {
            try {
                int input = Integer.parseInt(scanner.nextLine());
                if (input >= min && input <= max) {
                    return input;
                }
                System.out.print("Please enter a number between " + min + " and " + max + ": ");
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number: ");
            }
        }
    }
    
    private static boolean getYesNo() {
        while (true) {
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("y") || input.equals("yes")) {
                return true;
            } else if (input.equals("n") || input.equals("no")) {
                return false;
            }
            System.out.print("Please enter 'y' or 'n': ");
        }
    }
}