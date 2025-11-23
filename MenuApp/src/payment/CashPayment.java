package payment;

public class CashPayment implements PaymentStrategy {
    public boolean pay(double amount){
        System.out.println(amount + " paid in cash");
        return true;
    }
}
