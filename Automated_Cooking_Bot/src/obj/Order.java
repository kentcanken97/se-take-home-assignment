package obj;

import java.time.LocalDateTime;
import java.util.UUID;

public class Order {
    String strOrderID;
    LocalDateTime ldtOrderPlaceTime;
    int intLevel; // 0 represent Normal, 1 represent VIP
    int intStatus; // 0 represent pending, 1 represent complete

    public Order(int level, int status){
        this.strOrderID = UUID.randomUUID().toString();
        this.ldtOrderPlaceTime = LocalDateTime.now();
        this.intLevel = level;
        this.intStatus = status;
    }
}
