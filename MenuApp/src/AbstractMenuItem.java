public class AbstractMenuItem implements MenuItem {
    protected String name;
    protected double price;

    @Override
    public String getDescription() {
        return name;
    }

    @Override
    public double getCoast() {
        return price;
    }
}
