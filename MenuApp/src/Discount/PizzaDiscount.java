package Discount;

public class PizzaDiscount implements DiscountStrategy {
    @Override
    public double applyDiscount(double amount){
        return amount*0.9;
    }
}
