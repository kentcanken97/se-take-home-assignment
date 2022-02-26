package obj;

import java.util.LinkedList;
import java.util.Stack;

public class Controller {
    
    private static Controller single_instance = null;

    private int intOdrID = 1;

    private LinkedList<Order> listPendingOrder = new LinkedList<Order>(); 
    private LinkedList<Order> listCompletedOrder = new LinkedList<Order>();

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
        Order order = new Order(intOdrID, false, 0);
        listPendingOrder.add(order);
        this.intOdrID++;
        System.out.println("Add Normal Order to list. " 
                            + "Your OrderID: "
                            + order.intOrderID
                            + ". "
                            + (listPendingOrder.size()-1)
                            + " order infront of your order.\n");
    }

    public void addVIPOrder(){
        Order order = new Order(intOdrID, true, 0);
        int intQueueNum = addVIPOdrInfrontList(order);
        this.intOdrID++;
        System.out.println("Add Vip Order to list. "
                            + "Your OrderID: "
                            + order.intOrderID
                            + ". "
                            + intQueueNum
                            + " order infront of your order.\n");
    }

    private int addVIPOdrInfrontList(Order order){
        int intFirstNumNormalOrder = findIndexOfFirstNormalOrder();
       
        if(intFirstNumNormalOrder == -1){
            listPendingOrder.addLast(order);
            return listPendingOrder.indexOf(order);
        } //Since whole list is VIP order just add to last
        else{
            listPendingOrder.add(intFirstNumNormalOrder, order);
        } //Add according to index
        
        return intFirstNumNormalOrder;
    }

    private int findIndexOfFirstNormalOrder(){
        int pointer = 0;

        while(pointer < listPendingOrder.size()){
            
            if(listPendingOrder.get(pointer).intLevel){
                pointer++;
            }//if the order with this index is VIP
            else {
                return pointer;
            }//else if encounter first normal order
        }

        //if finish whole loop is VIP
        pointer = -1;
        return pointer;
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


