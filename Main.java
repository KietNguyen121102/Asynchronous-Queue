import java.util.*;
public class Main{





    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        System.out.println("What is your barista's process time?");
        int PROCESS_TIME = sc.nextInt();
        System.out.println("How many baristas are there?");
        int NUM_BARISTAS = sc.nextInt();
        System.out.println("How many customers will be?");
        int NUM_CUSTOMERS = sc.nextInt();

        




        Line<Customer> physicalLine = new Line<Customer>();
        double[] baristaTime = new double[NUM_BARISTAS];
        ArrayList<Double> customerTime = new ArrayList<Double>();
        double totalTime, averageTime;
        double departs;
        double start;

        //set each baristas' time to zero initially
        for(int i = 0; i < NUM_BARISTAS; i++){
            baristaTime[i] = 0;
        }

        //load customer queue
        for(int i = 1; i <= NUM_CUSTOMERS; i++){
            physicalLine.enqueue(new Customer(i*1));
        }

        totalTime = 0;

        while(!(physicalLine.empty())){
            for(int i = 0; i < NUM_BARISTAS && (!(physicalLine.empty())); i++){
                Customer customer = physicalLine.rear();
                physicalLine.dequeue();
                if(customer.getArrivalTime() > baristaTime[i]){
                    start = customer.getArrivalTime();
                }
                else{
                    start = baristaTime[i];
                }

                departs = start + PROCESS_TIME;
                customer.setDepartureTime(departs);
                baristaTime[i] = departs;
                customerTime.add(customer.totalTime());
                
                totalTime += customer.totalTime();



            }
        }

        averageTime = totalTime / NUM_CUSTOMERS;
        System.out.println("Number of cashiers: " + (NUM_BARISTAS));
        System.out.println("Average time: " + averageTime/60 + " minutes");
        System.out.println("Each customer wait time: " + customerTime);





 


    }
}