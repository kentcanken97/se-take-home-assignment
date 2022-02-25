package obj;

import java.util.LinkedList;
import java.util.Stack;

public class Manager {
    
    private static Manager single_instance = null;

    private LinkedList<Order> listNormalOrder = new LinkedList<Order>();
    private LinkedList<Order> listVIPOrder = new LinkedList<Order>(); 
    private Stack<Bot> stkBot = new Stack<Bot>();

    private Manager(){   }

    //Declare the manager for order if the variable is not declared.
    //This is a singleton class.
    public static Manager getInstance(){
        if(single_instance == null){
            single_instance = new Manager();
        }

        return single_instance;
    }

    public void addNormalOrder(){
        Order order = new Order(0,0);
        listNormalOrder.add(order);
        System.out.println("Add Normal Order to list. Current number of normal order pending is: " + listNormalOrder.size());
    }

    public void addVIPOrder(){
        Order order = new Order(1,0);
        listVIPOrder.add(order);
        System.out.println("Add Vip Order to list. Current number of VIP order pending is: " + listVIPOrder.size());
    }
}


