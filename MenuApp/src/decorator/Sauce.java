package decorator;

import menuItems.MenuItem;

public class Sauce extends addOnDecorator {
    public Sauce(MenuItem menuItem){
        super(menuItem);
    }

    @Override
    public String getDescription(){
        return menuItem.getDescription() + " + Sauce";
    }

    @Override
    public double getCost(){
        return menuItem.getCost() + 0.3;
    }
}
