package Discount;

public class FirstTimeDiscount  implements DiscountStrategy{
    @Override
    public double applyDiscount(double amount){
        return amount * 0.85; //15% 
    }

}