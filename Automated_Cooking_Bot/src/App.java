import java.util.Scanner;

public class App {

    public static void main(String[] args) throws Exception {
        Scanner scan = new Scanner (System.in);
        String strCommand = "";
    
        System.out.print("*********************************\n" 
                        + "* Automated Cooking Robot Start *\n"
                        + "*********************************\n"
                        + "\n\n");

        //While loop for cli command
        lblInputLoop: while(true) {
            System.out.print("Please enter one of the below command as input: \n"
                                + "New Normal Order   add a new normal order.\n"
                                + "New VIP Order      add a new VIP order.\n"
                                + "+ Bot              add a new Bot.\n"
                                + "- Bot              remove a bot.\n"
                                + "Quit               quit cooking bot program.\n"
                                + "Your Input:\n\n");
            strCommand = scan.nextLine();

            switch(strCommand) {
                case "New Normal Order":
                    System.out.print("add Normal Order\n\n");
                    break;

                case "New Vip Order":
                    System.out.print("add Vip Order\n\n");
                    break;

                case "+ Bot":
                    System.out.print("add bot\n\n");
                    break;

                case "- Bot":
                    System.out.print("remove bot\n\n");
                    break;

                case "Quit":
                    break lblInputLoop;

                default:
                    System.out.print("The command you had enter is not in the system.\n");
            }
        }//while end

        System.out.print("Program terminated\n");
    }
}
