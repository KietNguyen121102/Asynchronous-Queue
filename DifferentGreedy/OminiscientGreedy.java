import java.util.*;


public class OminiscientGreedy {

    public static double omniscientGreedy(int onlineBaristas, int physicalBaristas, double processingTime, int ArrivalRate, double alpha, int totalCustomers) {
        boolean equal = true;
       ArrayList<ArrayList<Integer>> possibleGreedyStrategy = new ArrayList<ArrayList<Integer>>();
       ArrayList<Integer> firstGreedyStrategy = new ArrayList<Integer>();
       firstGreedyStrategy.add(1);
       possibleGreedyStrategy.add(firstGreedyStrategy);

       ArrayList<Integer> finalDecision = new ArrayList<Integer>();
       HashMap<Integer, ArrayList<Integer>> greedyAtEachCustomer = new HashMap<Integer, ArrayList<Integer>>();
       
        
        double[] costOfGreedy = new double[totalCustomers];
        int numCustomer = 0;
        int numCustomerServed = 0;
        


        int totalCustomer = totalCustomers;
        ArrayList<Integer> queueSize = new ArrayList<Integer>();
        ArrayList<Integer> lineSize = new ArrayList<Integer>();
        int chooseQueue = 0;

        double[] onlineBarista = new double[onlineBaristas];
        for (int i = 0; i < onlineBaristas; i++) {
            onlineBarista[i] = 0;
        }

        double[] physicalBarista = new double[physicalBaristas];
        for (int i = 0; i < physicalBaristas; i++) {
            physicalBarista[i] = 0;
        }



        Queue<Customer> physicalLine = new LinkedList<Customer>();
        Queue<Customer> onlineQueue = new LinkedList<Customer>();

        double customerArrivalRate = ArrivalRate;
        int timeToNextCust = 0;
        double totalTime = 0;
        double averageTime;

        for (int time = 1; time <= 5000; time = time + 1) {
            // System.out.println("At time t = " + time);

            Customer customer = new Customer(1);

            // Customer arrives at a fixed interval
            timeToNextCust++;
            // System.out.println("Time to next customer is:" + timeToNextCust);
            if (timeToNextCust == customerArrivalRate) {
                customer.setArrivalTime(time);
                timeToNextCust = 0;
                numCustomer++;
            }



            if (customer.getArrivalTime() != 0 && numCustomer <= totalCustomers) {
                // System.out.println("Customer waiting in queue is " + onlineQueue.size());
                // System.out.println("Customer waiting in physical line is " + physicalLine.size());
                queueSize.add(onlineQueue.size());
                lineSize.add(physicalLine.size());
                

                //comparing expected waiting time
                ArrayList<Integer> previousDecision = new ArrayList<Integer>();

               
                int[] previousDecisions = new int[previousDecision.size()];

                for(int i = 0; i < previousDecisions.length; i++){
                    previousDecisions[i] = previousDecision.get(i);
                }
                
                
              
            for(ArrayList<Integer> currentGreedyStrategy: possibleGreedyStrategy){
                int[] ifQueue = new int[currentGreedyStrategy.size()];
                int[] ifLine = new int[currentGreedyStrategy.size()];

                for(int i = 0; i < currentGreedyStrategy.size(); i++){
                    ifQueue[i] = currentGreedyStrategy.get(i);
                    ifLine[i] = currentGreedyStrategy.get(i);
                }

                ifQueue[ifQueue.length-1] = 1; 
                ifLine[ifLine.length-1] = 0; 

                


                double queueWaitingTime = SchedulerSimulation.schedulerSimulation(onlineBaristas, physicalBaristas, processingTime, ArrivalRate, alpha, ifQueue);

                System.out.println("queue waiting time for customer " + customer.getArrivalTime() + " is " + queueWaitingTime);

                double lineWaitingTime = SchedulerSimulation.schedulerSimulation(onlineBaristas, physicalBaristas, processingTime, ArrivalRate, alpha, ifLine);

                System.out.println("line waiting time for customer " + customer.getArrivalTime() + " is " + lineWaitingTime);

                // System.out.println("line waiting time: " + lineWaitingTime);
                // queueExpectedCost.add(queueWaitingTime);
                // lineExpectedCost.add(lineWaitingTime);


                
                
                // System.out.println("Online queue expected waiting time is: " + queueWaitingTime + ", physical line expected waiting time is: " + lineWaitingTime);
                if (lineWaitingTime < queueWaitingTime && numCustomer <= totalCustomer ) {
                     
                    physicalLine.add(customer);
                    finalDecision.add(0);
                    currentGreedyStrategy.add(0);
                    // System.out.println("Customer chooses physical line");
                } 
                else if(lineWaitingTime == queueWaitingTime){
                    ArrayList<Integer> branchCurrentChooseQueue = (ArrayList<Integer>) currentGreedyStrategy.clone();
                    ArrayList<Integer> branchCurrentChooseLine = (ArrayList<Integer>) currentGreedyStrategy.clone();
                    branchCurrentChooseQueue.add(1);
                    branchCurrentChooseLine.add(0);
                    possibleGreedyStrategy.add(branchCurrentChooseLine);
                    possibleGreedyStrategy.add(branchCurrentChooseQueue);
                
                }
                else if(numCustomer <= totalCustomer){

                    
                    if(lineWaitingTime == queueWaitingTime){
                        equal = true;
                    }
                    onlineQueue.add(customer);
                    customer.beOnline();
                    finalDecision.add(1);
                    currentGreedyStrategy.add(1);
                    chooseQueue++;
                    // System.out.println("Customer chooses online queue");
                }

            

               
    
                }

           Customer servingCustomer = new Customer(1);
        
            for (int num = 0; num < onlineBaristas; num++) {
                if ((time >= onlineBarista[num]) && ((physicalLine.size() != 0) || (onlineQueue.size() != 0))) {
                    if ((onlineQueue.size() != 0)) {
                        servingCustomer = onlineQueue.peek();
                        onlineQueue.poll();
                        numCustomerServed++;}
                    // } else if (physicalLine.size() != 0) {
                    //     servingCustomer = physicalLine.peek();
                    //     physicalLine.poll();
                    //     numCustomerServed++;
                    // }

                    // System.out.println("Breakpoint 2");

                    // System.out.println("Customer serving by online barista number " + num + " is customer:" + servingCustomer.getArrivalTime());
                    // if(servingCustomer.online == true){System.out.println("This customer is from the online queue");}
                    // else{System.out.println("This customer is from the in person line");}

                    onlineBarista[num] = (time + processingTime);
                    servingCustomer.setDepartureTime(onlineBarista[num]);
                    if (servingCustomer.online == true) {
                        totalTime += (servingCustomer.totalTime() * alpha);
                        costOfGreedy[(int) (servingCustomer.arrivalTime - 1)] = (servingCustomer.totalTime()*alpha);
                    } else {
                        totalTime += servingCustomer.totalTime();
                        costOfGreedy[(int) (servingCustomer.arrivalTime - 1)] = servingCustomer.totalTime();
                    }

        

                }

            }

            Customer servingCustomer2 = new Customer(1);

            for (int num = 0; num < physicalBaristas; num++) {
                if ((time >= physicalBarista[num]) && ((physicalLine.size() != 0) || (onlineQueue.size() != 0))) {
                    if ((physicalLine.size() != 0)) {
                        servingCustomer2 = physicalLine.peek();
                        physicalLine.poll();
                        numCustomerServed++;}
                    // } else if (onlineQueue.size() != 0) {
                    //     servingCustomer2 = onlineQueue.peek();
                    //     onlineQueue.poll();
                    //     numCustomerServed++;
                    // }

                    // System.out.println("Breakpoint 2");

                    // System.out.println("Customer serving by physical barista num " + num + " is customer:" + servingCustomer2.getArrivalTime());
                    // if(servingCustomer2.online == true){System.out.println("This customer is from the online queue");}
                    // else{System.out.println("This customer is from the in person line");}

                    physicalBarista[num] = (time + processingTime);
                    servingCustomer2.setDepartureTime(physicalBarista[num]);
                    if (servingCustomer2.online == true) {
                        totalTime += (servingCustomer2.totalTime() * alpha);
                        costOfGreedy[(int) (servingCustomer2.arrivalTime - 1)] = (servingCustomer2.totalTime() * alpha);
                    } else {
                        totalTime += servingCustomer2.totalTime();
                        costOfGreedy[(int) (servingCustomer2.arrivalTime - 1)] = (servingCustomer2.totalTime());
                    }
                   

                }

            }

           
        }

       
        System.out.println("Cost of omniscient greedy is: " + totalTime);
        // HashMap<String, ArrayList<Integer>> map = new HashMap();
        // map.put("queueSize", queueSize);
        // map.put("lineSize", lineSize);
        // System.out.println("Optimistic greedy queue chosen is:" + chooseQueue);
        // System.out.println("Greedy strategy total cost: " + totalTime);

        // System.out.println(costOfGreedy.length);
        ArrayList<Double> greedyCost = new ArrayList<Double>();
        for(int i = 0; i < costOfGreedy.length; i++){
            greedyCost.add(costOfGreedy[i]);
        }
        System.out.println("Omniscient Greedy: " + finalDecision);
      
        
        greedyAtEachCustomer.put(totalCustomer, finalDecision);
        // System.out.println(greedyAtEachCustomer);
        return totalTime;

}
}
}