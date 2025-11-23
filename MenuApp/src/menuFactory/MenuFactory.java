package menuFactory;
import menuItems.MenuItem;

public abstract class MenuFactory {
    public abstract MenuItem createAppetizer();
    public abstract MenuItem createMainDish(String type);
    public abstract MenuItem createDessert();



}
