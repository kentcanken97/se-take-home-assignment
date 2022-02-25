package obj;

public class Manager {
    
    private static Manager single_instance = null;

    private Manager(){   }

    public static Manager getInstance(){
        if(single_instance == null){
            single_instance = new Manager();
        }

        return single_instance;
    }

    public void addNormalOrder(){
        System.out.println("Add Normal Order in manager");
    }

    public void addVIPOrder(){
        System.out.println("Add Vip Order in manager");
    }
}


