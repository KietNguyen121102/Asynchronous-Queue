import java.util.ArrayList;
import java.util.Arrays;

class AvgTimeCalculation{


    public static int customerGreedy(int TOTAL_CUSTOMER, int NUM_BARISTAS, int PROCESS_TIME){
        
       
       
        Line<Customer> physicalLine = new Line<Customer>();
        Line<Customer> onlineQueue = new Line<Customer>();

        double customer_count = 1;
        double physical_waiting_line = 0;
        double online_waiting_queue = 0;
        double current_physical_order = 1;
        double current_online_order = 1;
        double total_online_order = 1;
        double total_physical_order = 1;

        double[] baristaTime = new double[NUM_BARISTAS];
        ArrayList<Double> customerTime = new ArrayList<Double>();
        double averageTime;
        double departs;
        double start = 0;
        double totalTime = 0;
        Customer customer  = new Customer(0);


        while(customer_count <= TOTAL_CUSTOMER){
            

           


            System.out.println("Current physical order is order " + current_physical_order);
            System.out.println("Current online order is order " + current_online_order);



            if(current_physical_order < current_online_order*(0.5)){
                customer.setArrivalTime(total_physical_order*5.0);
                physicalLine.enqueue(customer);
                System.out.println("Customer chooses physical line");
                System.out.println("Current order in line:" + physicalLine.size());
              
                total_physical_order++;
            }
            else{
                customer.setArrivalTime(total_online_order*3.0);
                customer.beOnline();
                onlineQueue.enqueue(customer);
                System.out.println("Customer chooses online queue");
                System.out.println("Current order in queue:" + onlineQueue.size());
              
                total_online_order++;
            }

            for(int i = 0; i < NUM_BARISTAS && (!physicalLine.empty())||(!onlineQueue.empty()); i++){
               if((!physicalLine.empty())&&(!onlineQueue.empty())){
                    if(physicalLine.rear().getArrivalTime() <= onlineQueue.rear().getArrivalTime()){
                        customer = physicalLine.rear();
                        physicalLine.dequeue();
                       
                       
                    }
                    else{
                        customer = onlineQueue.rear();
                        onlineQueue.dequeue();
                       
                       
      
                    }
                }
                    else if(physicalLine.empty()){
                    customer = onlineQueue.rear(); onlineQueue.dequeue();
                
                  
                
                }
                else{
                    customer = physicalLine.rear(); physicalLine.dequeue();  
                 
                    
                }

                int barista_for_order = -1;
                System.out.println("Current customer arrival time is:" + customer.getArrivalTime());
                for(int j = i; j < NUM_BARISTAS; j++)
                {
                    
                    if(customer.getArrivalTime() >= baristaTime[j]){
                    start = customer.getArrivalTime();
                    System.out.println("Current customer start time is " + start);
                    barista_for_order = j;
                    break;
                }else if(baristaTime[j] < baristaTime[i]){
                    start = baristaTime[j];
                    barista_for_order = j;
                    break;
                    
                }
                else{
                    start = baristaTime[i];
                    barista_for_order = i;
                }     
                }

                

                departs = start + PROCESS_TIME;
                customer.setDepartureTime(departs);
                
                baristaTime[barista_for_order] = departs;
                if(customer.online == true)    
                {totalTime += (customer.totalTime()*8)/10;}
                else{totalTime += customer.totalTime();}

                System.out.println(Arrays.toString(baristaTime));

                System.out.println("Current customer total waiting time is:" + customer.totalTime());


            }     

            customer_count += 1;
        }
        averageTime = totalTime / (TOTAL_CUSTOMER);
        System.out.println("Number of cashiers: " + (NUM_BARISTAS));
        System.out.println("Average time: " + averageTime/60 + " minutes");

        return 0;


    }

//Single in-person line

    public static void calculateAvgWaitTime(int NUM_CUSTOMERS, int NUM_BARISTAS, int PROCESS_TIME)
    {   Line<Customer> totalLine = new Line<Customer>();
        Line<Customer> physicalLine = new Line<Customer>();
        Line<Customer> onlineQueue = new Line<Customer>();


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
            physicalLine.enqueue(new Customer(i*5));
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

    }
//Half-half online and in-person line
    public static void hybridAvgTime(int NUM_ONLINE_CUSTOMERS, int NUM_IN_PERSON_CUSTOMERS, int NUM_BARISTAS, int PROCESS_TIME){
        Line<Customer> totalLine = new Line<Customer>();
        Line<Customer> physicalLine = new Line<Customer>();
        onlineQueue<Customer> onlineQueue = new onlineQueue<Customer>();


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
        for(int i = 1; i <= NUM_ONLINE_CUSTOMERS; i++){
            Customer customer_1 = new Customer(i*5);
            customer_1.beOnline();
            onlineQueue.enqueue(customer_1);
           
        }

        for(int i = 1; i <= NUM_IN_PERSON_CUSTOMERS; i++){
            Customer customer_2 = new Customer(i*5);
            physicalLine.enqueue(customer_2);
        }

        totalTime = 0;

        while((!physicalLine.empty()) || (!onlineQueue.empty())){
            // System.out.println("Online queue now consists of " + onlineQueue.toString());
            // System.out.println("Physical line now consists of " + physicalLine.toString());
    
            for(int i = 0; (i < NUM_BARISTAS) && ((!physicalLine.empty()) || (!onlineQueue.empty())) ; i++){

                // System.out.println("Current barista is "+i);

                Customer customer = new Customer(0);

                if((!physicalLine.empty())&&(!onlineQueue.empty())){
                    if(physicalLine.rear().getArrivalTime() <= onlineQueue.rear().getArrivalTime()){
                        customer = physicalLine.rear();
                        physicalLine.dequeue();
                    }
                    else{
                        customer = onlineQueue.rear();
                        onlineQueue.dequeue();
      
                    }
                }
                    else if(physicalLine.empty()){
                    customer = onlineQueue.rear(); onlineQueue.dequeue();
                
                }
                else{
                    customer = physicalLine.rear(); physicalLine.dequeue();  
                }

                // System.out.println("Next customer in line is: " + customer.getArrivalTime());
                
                
                if(customer.getArrivalTime() > baristaTime[i]){
                    start = customer.getArrivalTime();
                    // System.out.println("Current customer start time is " + start);
                }
                else{
                    start = baristaTime[i];
                }

                departs = start + PROCESS_TIME;
                customer.setDepartureTime(departs);
                
                baristaTime[i] = departs;
                if(customer.online == true)    
                {totalTime += (customer.totalTime()*8)/10;}
                else{totalTime += customer.totalTime();}
                // System.out.println("Total time is: " + totalTime);
            }
        }

        averageTime = totalTime / (NUM_ONLINE_CUSTOMERS + NUM_IN_PERSON_CUSTOMERS);
        System.out.println("Number of cashiers: " + (NUM_BARISTAS));
        System.out.println("Average time: " + averageTime/60 + " minutes");

    }

}