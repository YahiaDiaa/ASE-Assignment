package decorator;

import menuItems.MenuItem;

public class ExtraCheese extends addOnDecorator {
   
    public ExtraCheese (MenuItem menuItem){
        super(menuItem);
    }

    @Override
    public String getDescription(){
        return menuItem.getDescription() + " + Extra Cheese";
    }

    @Override
    public double getCost() {
        return menuItem.getCost() + 0.5;
    }
}
