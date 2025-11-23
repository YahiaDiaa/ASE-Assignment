package payment;

public class CardPayment implements PaymentStrategy {
    public boolean pay(double amount){
        System.out.println(amount+"$ paid with Card");
        return true;
    }
}
