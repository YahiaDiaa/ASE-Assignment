package manager;

import menuFactory.*;
import menuItems.*;

public class MenuManager {

    private MenuFactory menuFactory;

    public MenuManager(MenuFactory menuFactory){
        this.menuFactory = menuFactory;
    }
    
    // Display complete menu with all options
    public void displayFullMenu(String preferredMainDishType){
        System.out.println("\n" + "=".repeat(40));
        System.out.println("         RESTAURANT MENU");
        System.out.println("=".repeat(40));
        
        MenuItem appetizer = menuFactory.createAppetizer();
        MenuItem mainDish = menuFactory.createMainDish(preferredMainDishType);
        MenuItem dessert = menuFactory.createDessert();

        System.out.println("\nAPPETIZER:");
        System.out.println("  " + appetizer.getDescription() + " - $" + 
                          String.format("%.2f", appetizer.getCost()));
        
        System.out.println("\nMAIN DISH:");
        if (mainDish != null) {
            System.out.println("  " + mainDish.getDescription() + " - $" + 
                              String.format("%.2f", mainDish.getCost()));
        } else {
            System.out.println("  Available: Chicken, Beef");
        }
        
        System.out.println("\nDESSERT:");
        System.out.println("  " + dessert.getDescription() + " - $" + 
                          String.format("%.2f", dessert.getCost()));
        
        System.out.println("=".repeat(40) + "\n");
    }
    
    // Quick menu preview
    public void displayMenuPreview() {
        System.out.println("Menu Type: " + menuFactory.getClass().getSimpleName());
    }
    
    // Get menu items for order building
    public MenuItem getAppetizer() {
        return menuFactory.createAppetizer();
    }
    
    public MenuItem getMainDish(String type) {
        return menuFactory.createMainDish(type);
    }
    
    public MenuItem getDessert() {
        return menuFactory.createDessert();
    }
}