package payment;

public class WalletPayment implements  PaymentStrategy {
    public boolean pay(double amount){
        System.out.println("paid with Wallet");
        return true;
    }
}
