package obj;

import java.time.LocalDateTime;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Bot{
    Controller ctrler = Controller.getInstance();

    int intWorkingStatus = 0; //0 for pending job, 1 for cooking
    LocalDateTime ldtOrderReceiveDateTime;
    Order odrReceivedOrder;

    public void taskCookOrder(Order order){
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

        Runnable taskCooking = () -> {
            order.intStatus = 1;    //Update Order to complete 
            this.intWorkingStatus = 0;
            ctrler.taskComplete(order);
            ctrler.requestOrder(this);
        };
        
        intWorkingStatus = 1;   //Current Bot status cooking
        //Execute the runnable task after 10 second
        executorService.schedule(taskCooking, 10, TimeUnit.SECONDS);
        executorService.shutdown();
    }

}
