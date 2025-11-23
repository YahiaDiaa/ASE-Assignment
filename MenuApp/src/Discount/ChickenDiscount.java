package Discount;

public class ChickenDiscount implements DiscountStrategy{
    @Override
    public double applyDiscount(double amount){
        return amount * 0.85; //15% 
    }

}