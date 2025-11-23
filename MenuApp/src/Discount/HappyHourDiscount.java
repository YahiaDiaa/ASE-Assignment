package Discount;

public class HappyHourDiscount  implements DiscountStrategy {
    @Override
    public double applyDiscount(double amount){
        return amount*0.9;
    }
}
