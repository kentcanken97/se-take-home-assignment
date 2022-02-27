package obj;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Bot{
    Controller ctrler = Controller.getInstance();

    int intWorkingStatus = 0; //0 for pending job, 1 for cooking
    Order odrReceivedOrder = null;

    ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();


    //Allocate the order to the thread of this bot
    //and delay the task for 10 second assuming it is cooking.
    public void taskCookOrder(Order order){
        Runnable taskCooking = () -> {
            order.intStatus = 1;    //Update Order to complete 
            this.intWorkingStatus = 0;
            ctrler.taskComplete(order);
            ctrler.requestOrder(this);
        };
        
        this.odrReceivedOrder = order;
        intWorkingStatus = 1;   //Current Bot status cooking
        //Execute the runnable task after 10 second
        executorService.schedule(taskCooking, 10, TimeUnit.SECONDS);
    }

    //This bot is currently using the thread to cook the order.
    //Forcefully stop thread of the bot from this task and restore the order back to pending list.
    public void botForceStop(){
        executorService.shutdownNow();

        ctrler.restoreOrderToList(odrReceivedOrder);
        System.out.println("Restored Order with ID " + odrReceivedOrder.intOrderID + " to pending list.");
    }
    
    //Shut down the thread for this bot. Prevent thread leak, since this bot is not needed.
    //Call this when removing bot.
    public void botShutdown(){
        executorService.shutdown();
    }
}
