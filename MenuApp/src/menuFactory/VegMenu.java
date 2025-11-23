package menuFactory;

import mainDishFactory.MainDishFactory;
import menuItems.Fries;
import menuItems.IceCream;
import menuItems.MenuItem;

public class VegMenu extends MenuFactory{
    private MainDishFactory mainDish;
    public VegMenu(MainDishFactory mainDishFactory) {
        this.mainDish = mainDishFactory;
    }

    @Override
    public MenuItem createAppetizer() {
        return new Fries();
    }

    @Override
    public MenuItem createMainDish(String type) {
        MenuItem mi = mainDish.createDish(type);
        return mi != null ? mi : null;
    }

    @Override
    public MenuItem createDessert() {
        return new IceCream();
    }
}
