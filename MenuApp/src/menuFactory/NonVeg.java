package menuFactory;
import DessertFactory.DessertFactory;
import mainDishFactory.MainDishFactory;
import menuItems.Fries;
import menuItems.MenuItem;

public class NonVeg extends MenuFactory{
    private   MainDishFactory mainDish;
    private DessertFactory dessert;

    public NonVeg(MainDishFactory mainDishFactory ,  DessertFactory dessertFactory) {
        this.mainDish = mainDishFactory;
        this.dessert = dessertFactory;
    }

    @Override
    public MenuItem createAppetizer() {
        return new Fries();
    }

    @Override
    public MenuItem createMainDish(String type) {
        if(type == null) return null;
        MenuItem mi = mainDish.createDish(type);
        return mi != null ? mi : null;
    }

    @Override
    public MenuItem createDessert(String type) {
        if(type == null) return null;
        MenuItem mi = dessert.createDessert(type);
        return mi != null ? mi : null;
    }
}
