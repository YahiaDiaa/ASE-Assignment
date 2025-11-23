package observer;

public class Waiter implements Observer {
    @Override 
    public void update(String orderDetails){
        System.out.println("Waiter notified about order: "+ orderDetails);
    }
}
