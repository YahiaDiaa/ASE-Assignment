package observer;

import java.util.ArrayList;
import java.util.List;

public class OrderSystem {
    private List<Observer> observers = new ArrayList<>();

    public void attach(Observer observer){
        observers.add(observer);
    }

    public void detach(Observer observer){
        observers.remove(observer);
    }
    
    public void notifyObservers(String orderDetails){
        for(Observer observer : observers){
            observer.update(orderDetails);
        }
    }

    public void placeOrder(String orderDetails){
        System.out.println("Order placed: "+ orderDetails);
        notifyObservers(orderDetails);
    }
}
