package obj;

import java.util.LinkedList;
import java.util.Stack;

public class Controller {
    
    private static Controller single_instance = null;

    private LinkedList<Order> listNormalOrder = new LinkedList<Order>();
    private LinkedList<Order> listVIPOrder = new LinkedList<Order>(); 
    private Stack<Bot> stkBot = new Stack<Bot>();

    private Controller(){   }

    //Declare the manager for order if the variable is not declared.
    //This is a singleton class.
    public static Controller getInstance(){
        if(single_instance == null){
            single_instance = new Controller();
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

    public void addBot(){
        Bot bot = new Bot();
        stkBot.push(bot);
        System.out.println("Added new bot to the workforce.");
    }

    public void removeBot(){
        if(stkBot.empty()){
            System.out.println("The workforce is empty no bot can be removed.");
        }else{
            stkBot.pop();
            System.out.println("Removed the newest bot from workforce.");
        } 
    }
}


