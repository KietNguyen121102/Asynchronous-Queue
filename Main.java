import java.util.*;


public class Main{

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

//Initiate Line and Queue

        onlineQueue<Customer> onlineQueue = new onlineQueue<Customer>();
        int onlineQueueCustomer = 1;
        Line<Customer> physicalLine = new Line<Customer>();
        int physicalLineCustomer = 1; 

//Initiate barista's line:

        System.out.println("How many employees are working for the physical line: ");
        int numEmployee = sc.nextInt();
        ArrayList<Barista> physicalLineEmployees = new ArrayList<Barista>();
        for(int i = 0; i < numEmployee; i++){
            Barista barista = new Barista();
            physicalLineEmployees.add(barista);
        }

        System.out.println("How many employees are working for the online queue: ");
        int numOnlineEmployee = sc.nextInt();
        ArrayList<Barista> onlineQueueEmployees = new ArrayList<Barista>();
        for(int i = 0; i < numEmployee; i++){
            Barista barista = new Barista();
            onlineQueueEmployees.add(barista);
        }





//Customer arrive

        System.out.println("How many customers will be entering: ");
        int timer = 0; 
        int numCustomer = sc.nextInt();
        for(int i = 0; i < numCustomer; i++)
    {
            Customer customer = new Customer(100);
            customer.index = i + 1;

            if(onlineQueueCustomer <= customer.preferences){
                customer.inQueue = true;
            }
            else{
                customer.inLine = true;
            }

            if(customer.inQueue == true){onlineQueue.enqueue(customer); onlineQueueCustomer++;}
            else{physicalLine.enqueue(customer); physicalLineCustomer++;}
              
        


    }
}}
// }