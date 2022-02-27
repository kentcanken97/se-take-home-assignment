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

        if(assignOrder(order)){
            listPendingOrder.remove(order);
        }
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

        if(assignOrder(order)){
            listPendingOrder.remove(order);
        }
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

        //Whole list is VIP or list completely empty it will be -1
        pointer = -1;
        return pointer;
    }

    public void addBot(){
        Bot bot = new Bot();
        stkBot.push(bot);
        System.out.println("Added new bot to the workforce.");
        requestOrder(bot);
    }

    public void removeBot(){
        if(stkBot.empty()){
            System.out.println("The workforce is empty no bot can be removed.");
        }else{
            Bot botRemoved = stkBot.peek();
            if(botRemoved.intWorkingStatus == 1){
                botRemoved.botForceStop();
            }else{
                botRemoved.botShutdown();
            }
            stkBot.pop();
            System.out.println("Removed the newest bot from workforce.");
        } 
    }

    public void taskComplete(Order order){
        listCompletedOrder.add(order);
        System.out.println("OrderID " 
                            + order.intOrderID
                            + " completed cooking.");
    }

    public boolean assignOrder(Order order){
        Bot bot = chkPendingBot();
        if(bot != null){
            bot.taskCookOrder(order);
            System.out.println("OrderID " + order.intOrderID + " assigned to bot.");
            return true;

        }else{
            System.out.println("No pending bot available.");
        }
        return false;
    }

    public void assignOrder(Order order, Bot bot){
        bot.taskCookOrder(order);
        System.out.println("OrderID " + order.intOrderID + " assigned to bot.");
    }

    //Bot with no job request for job in the listPendingOrder
    public void requestOrder(Bot bot){
        if(listPendingOrder.isEmpty()==false){
            Order order = listPendingOrder.removeFirst();
            assignOrder(order,bot);
        }
        
    }

    private Bot chkPendingBot(){
        Bot bot = null;

        for(int i = 0; i < stkBot.size(); i++ ){
            if(stkBot.elementAt(i).intWorkingStatus == 0){
                bot = stkBot.elementAt(i);
                break;
            }
        }

        return bot;
    }

    public void restoreOrderToList(Order order){
        int index=0;
        int intFirstNmlOrder = findIndexOfFirstNormalOrder();

        // if list completely empty just add
        if(listPendingOrder.size() == 0) { 
            listPendingOrder.add(order);
            return;
        }

        if (order.intLevel){    //if order is vip
            while(index < intFirstNmlOrder){
                if(listPendingOrder.get(index).intOrderID < order.intOrderID ){
                    index++;
                }else{
                    listPendingOrder.add(index,order);
                    return;
                } 
            }
            //if after going through whole list this order still largest ID than
            //then add at last of vip
            listPendingOrder.add(intFirstNmlOrder,order); 
        }
        else{   //if order is normal
            index = intFirstNmlOrder;

            while(index < listPendingOrder.size()){
                if(listPendingOrder.get(index).intOrderID < order.intOrderID ){
                    index++;
                }else{
                    listPendingOrder.add(index,order);
                    return;
                }
            }
            //if after going through whole list this order still largest ID than
            //then add at last of normal
            listPendingOrder.add(listPendingOrder.size()-1,order);
        }
    }
}


