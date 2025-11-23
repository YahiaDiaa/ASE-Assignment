package manager;

import menuFactory.*;
import menuItems.*;

public class MenuManager {

    private MenuFactory menuFactory;

    public MenuManager(MenuFactory menuFactory){
        this.menuFactory = menuFactory;
    }

    public void displayFullMenu(String preferredMainDishType, String preferredDessertType){
        System.out.println("\n" + "=".repeat(40));
        System.out.println("         RESTAURANT MENU");
        System.out.println("=".repeat(40));

        MenuItem appetizer = menuFactory.createAppetizer();
        MenuItem mainDish = menuFactory.createMainDish(preferredMainDishType);
        MenuItem dessert = menuFactory.createDessert(preferredDessertType);

        System.out.println("\nAPPETIZER:");
        System.out.println("  " + appetizer.getDescription() + " - $" +
                String.format("%.2f", appetizer.getCost()));

        System.out.println("\nMAIN DISH:");
        if (mainDish != null)
            System.out.println("  " + mainDish.getDescription() + " - $" +
                    String.format("%.2f", mainDish.getCost()));
        else
            System.out.println("  Invalid choice for this menu.");

        System.out.println("\nDESSERT:");
        if (dessert != null)
            System.out.println("  " + dessert.getDescription() + " - $" +
                    String.format("%.2f", dessert.getCost()));
        else
            System.out.println("  Invalid dessert option.");

        System.out.println("=".repeat(40) + "\n");
    }

    public MenuItem getAppetizer() { return menuFactory.createAppetizer(); }
    public MenuItem getMainDish(String type) { return menuFactory.createMainDish(type); }
    public MenuItem getDessert(String type) { return menuFactory.createDessert(type); }
}
