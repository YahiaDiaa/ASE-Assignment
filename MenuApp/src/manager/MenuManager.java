package manager;

import menuFactory.*;
import menuItems.*;

public class MenuManager {

    private MenuFactory menuFactory;

    public MenuManager(MenuFactory menuFactory){
        this.menuFactory = menuFactory;
    }
    
    public void displayMenu(String prefferedMainDishType){
        MenuItem appetizer = menuFactory.createAppetizer();
        MenuItem mainDish = menuFactory.createMainDish(prefferedMainDishType);
        MenuItem dessert = menuFactory.createDessert();

        System.out.println("Menu:");
        System.out.println("Appetizer: " + appetizer.getDescription() + " - $" + appetizer.getCost());
        System.out.println("Main Dish: " + mainDish.getDescription() + " - $" + mainDish.getCost());
        System.out.println("Dessert: " + dessert.getDescription() + " - $" + dessert.getCost());
    }

    public void createOrder() {
        System.out.println("Order created using factory: " + menuFactory.getClass().getSimpleName());
    }
}
