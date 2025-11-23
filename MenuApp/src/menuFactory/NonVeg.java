package menuFactory;
import mainDishFactory.MainDishFactory;
import menuItems.Fries;
import menuItems.FruitSalad;
import menuItems.MenuItem;

public class NonVeg extends MenuFactory{
    private   MainDishFactory mainDish;

    public NonVeg(MainDishFactory mainDishFactory) {
        this.mainDish = mainDishFactory;
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
    public MenuItem createDessert() {
        return new FruitSalad();
    }
}
