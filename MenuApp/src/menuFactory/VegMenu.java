package menuFactory;

import DessertFactory.DessertFactory;
import mainDishFactory.MainDishFactory;
import menuItems.*;

public class VegMenu extends MenuFactory {

    private MainDishFactory mainDish;
    private DessertFactory dessert;


    public VegMenu(MainDishFactory mainDishFactory ,  DessertFactory dessertFactory) {
        this.mainDish = mainDishFactory;
        this.dessert = dessertFactory;
    }

    @Override
    public MenuItem createAppetizer() {
        return new Fries();
    }

    @Override
    public MenuItem createMainDish(String type) {

        if (type.equalsIgnoreCase("chicken") || type.equalsIgnoreCase("beef") || type.equalsIgnoreCase("pasta")) {
            System.out.println("Vegetarian menu does not allow meat. Switching to Pasta.");
            return new Pasta();
        }

        MenuItem mi = mainDish.createDish(type);
        return mi != null ? mi : new Pasta();
    }

    @Override
    public MenuItem createDessert(String type) {
        if(type == null) return null;
        MenuItem mi = dessert.createDessert(type);
        return mi != null ? mi : null;
    }
}
