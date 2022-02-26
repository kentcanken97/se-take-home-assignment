package obj;

import java.time.LocalDateTime;

public class Order {
    int intOrderID;
    LocalDateTime ldtOrderPlaceTime;
    boolean intLevel; // false represent Normal, true represent VIP
    int intStatus; // 0 represent pending, 1 represent complete

    public Order(int ID, boolean level, int status){
        this.intOrderID = ID;
        this.ldtOrderPlaceTime = LocalDateTime.now();
        this.intLevel = level;
        this.intStatus = status;
    }
}
