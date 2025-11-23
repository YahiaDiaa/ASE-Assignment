package DessertFactory;
import menuItems.*;

public class simpleDessert extends DessertFactory {

    @Override
    public MenuItem createDessert(String type) {
        if(type == null) return null;

        switch(type.toLowerCase()) {
            case "icecream": return new IceCream();
            case "fruitsalad": return new FruitSalad();
            default: return new IceCream();
        }
    }
}
