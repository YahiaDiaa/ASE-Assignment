package decorator;
import menuItems.MenuItem;

public abstract class addOnDecorator implements MenuItem {
    protected MenuItem menuItem;
    public addOnDecorator(MenuItem menuitem){
        this.menuItem = menuItem;
    }

    @Override
    public abstract String getDescription();

    @Override
    public abstract double getCost();
}
