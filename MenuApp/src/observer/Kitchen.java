package observer;

public class Kitchen implements Observer {
    @Override
    public void update(String orderDetails) {
        System.out.println("Kitchen received order: " + orderDetails);
    }
}
