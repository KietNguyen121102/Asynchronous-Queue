import java.util.LinkedList;
import java.util.Queue;
import java.util.*;

public class GreedySimulation {

    public static double greedyCustomerInHybrid(int onlineBaristas, int physicalBaristas, double processingTime, int ArrivalRate, double alpha, int totalCustomers) {
        // every minute in an 8-hour working day
        ArrayList<Integer> greedyStrategy = new ArrayList<Integer>();
        double[] costOfGreedy = new double[20];
        int numCustomer = 0;
        int numCustomerServed = 0;
        int totalCustomer = totalCustomers;
        

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

            Customer customer = new Customer(0);

            // Customer arrives at a fixed interval
            timeToNextCust++;
            // System.out.println("Time to next customer is:" + timeToNextCust);
            if (timeToNextCust == customerArrivalRate) {
                customer.setArrivalTime(time);
                timeToNextCust = 0;
                numCustomer++;
            }



            if (customer.getArrivalTime() != 0) {
                // System.out.println("Customer waiting in queue is " + onlineQueue.size());
                // System.out.println("Customer waiting in physical line is " + physicalLine.size());

                double queueWaitingTime = (float)(AvgCostCalculation.calculatingQueueCost(onlineQueue.size(), physicalLine.size(), onlineBaristas, physicalBaristas, alpha, processingTime));
                double lineWaitingTime = (float) AvgCostCalculation.calculatingLineCost(onlineQueue.size(), physicalLine.size(), onlineBaristas, physicalBaristas, alpha, processingTime);
                if(onlineQueue.size() == 0){queueWaitingTime = processingTime*alpha;}
                if(physicalLine.size() == 0){lineWaitingTime = processingTime;}
                
                
                // System.out.println("Online queue expected waiting time is: " + queueWaitingTime + ", physical line expected waiting time is: " + lineWaitingTime);
                if (lineWaitingTime < queueWaitingTime && numCustomer <= totalCustomer ) {
                    physicalLine.add(customer);
                    greedyStrategy.add(0);
                    // System.out.println("Customer chooses physical line");
                } else if(lineWaitingTime >= queueWaitingTime && numCustomer <= totalCustomer){
                    onlineQueue.add(customer);
                    customer.beOnline();
                    greedyStrategy.add(1);
                    // System.out.println("Customer chooses online queue");
                }
            }


          
          
           Customer servingCustomer = new Customer(0);
        

            for (int num = 0; num < onlineBaristas; num++) {
                if ((time >= onlineBarista[num]) && ((physicalLine.size() != 0) || (onlineQueue.size() != 0))) {
                    if ((onlineQueue.size() != 0)) {
                        servingCustomer = onlineQueue.peek();
                        onlineQueue.poll();
                        numCustomerServed++;
                    } else if (physicalLine.size() != 0) {
                        servingCustomer = physicalLine.peek();
                        physicalLine.poll();
                        numCustomerServed++;
                    }

                    // System.out.println("Breakpoint 2");

                    // System.out.println("Customer serving by online barista number " + num + " is customer:" + servingCustomer.getArrivalTime());
                    // if(servingCustomer.online == true){System.out.println("This customer is from the online queue");}
                    // else{System.out.println("This customer is from the in person line");}

                    onlineBarista[num] = (time + processingTime);
                    servingCustomer.setDepartureTime(onlineBarista[num]);
                    if (servingCustomer.online == true) {
                        totalTime += (servingCustomer.totalTime() * alpha);
                    } else {
                        totalTime += servingCustomer.totalTime();
                    }

                    costOfGreedy[(int) (servingCustomer.arrivalTime - 1)] = servingCustomer.totalTime();

                }

            }

            Customer servingCustomer2 = new Customer(0);
        

           

            for (int num = 0; num < physicalBaristas; num++) {
                if ((time >= physicalBarista[num]) && ((physicalLine.size() != 0) || (onlineQueue.size() != 0))) {
                    if ((physicalLine.size() != 0)) {
                        servingCustomer2 = physicalLine.peek();
                        physicalLine.poll();
                        numCustomerServed++;
                    } else if (onlineQueue.size() != 0) {
                        servingCustomer2 = onlineQueue.peek();
                        onlineQueue.poll();
                        numCustomerServed++;
                    }

                    // System.out.println("Breakpoint 2");

                    // System.out.println("Customer serving by physical barista num " + num + " is customer:" + servingCustomer2.getArrivalTime());
                    // if(servingCustomer2.online == true){System.out.println("This customer is from the online queue");}
                    // else{System.out.println("This customer is from the in person line");}

                    physicalBarista[num] = (time + processingTime);
                    servingCustomer2.setDepartureTime(physicalBarista[num]);
                    if (servingCustomer2.online == true) {
                        totalTime += (servingCustomer2.totalTime() * alpha);
                    } else {
                        totalTime += servingCustomer2.totalTime();
                    }
                    costOfGreedy[(int) (servingCustomer2.arrivalTime - 1)] = servingCustomer2.totalTime();

                }

            }
        }

        averageTime = totalTime / (numCustomerServed);
        // System.out.println("Greedy strategy total cost: " + totalTime);
        return totalTime;
    }

    public static double preferenceInHybrid(int onlineBaristas, int physicalBaristas, double processingTime, int ArrivalRate, double alpha, int totalCustomers, double physicalPopulation) {
        // every minute in an 8-hour working day
        int numCustomer = 0;
        int numCustomerServed = 0;

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

        for (int time = 1; time <= 10000; time = time + 1) {
            // System.out.println("At time t = " + time);

            Customer customer = new Customer(0);

            // Customer arrives at a fixed interval
            timeToNextCust++;
            // System.out.println("Time to next customer is:" + timeToNextCust);
            if (timeToNextCust == customerArrivalRate) {
                customer.setArrivalTime(time);
                timeToNextCust = 0;
                numCustomer++;
            }



            if (customer.getArrivalTime() != 0) {
                // System.out.println("Customer waiting in queue is " + onlineQueue.size());
                // System.out.println("Customer waiting in physical line is " + physicalLine.size());

                double i = Math.random();
                
                
                // System.out.println("Online queue expected waiting time is: " + queueWaitingTime + ", physical line expected waiting time is: " + lineWaitingTime);
                if (i < physicalPopulation && numCustomer < totalCustomers) {
                    physicalLine.add(customer);
                    // System.out.println("Customer chooses physical line");
                } else if(i >= physicalPopulation && numCustomer < totalCustomers) {
                    onlineQueue.add(customer);
                    customer.beOnline();
                    // System.out.println("Customer chooses online queue");
                }
            }


          
          
           Customer servingCustomer = new Customer(0);
        

            for (int num = 0; num < onlineBaristas; num++) {
                if ((time >= onlineBarista[num]) && ((physicalLine.size() != 0) || (onlineQueue.size() != 0))) {
                    if ((onlineQueue.size() != 0)) {
                        servingCustomer = onlineQueue.peek();
                        onlineQueue.poll();
                        numCustomerServed++;
                    } else if (physicalLine.size() != 0) {
                        servingCustomer = physicalLine.peek();
                        physicalLine.poll();
                        numCustomerServed++;
                    }

                    // System.out.println("Breakpoint 2");

                    // System.out.println("Customer serving by online barista number " + num + " is customer:" + servingCustomer.getArrivalTime());
                    // if(servingCustomer.online == true){System.out.println("This customer is from the online queue");}
                    // else{System.out.println("This customer is from the in person line");}

                    onlineBarista[num] = (time + processingTime);
                    servingCustomer.setDepartureTime(onlineBarista[num]);
                    if (servingCustomer.online == true) {
                        totalTime += (servingCustomer.totalTime() * alpha);
                    } else {
                        totalTime += servingCustomer.totalTime();
                    }

                }

            }

            Customer servingCustomer2 = new Customer(0);
           

            for (int num = 0; num < physicalBaristas; num++) {
                if ((time >= physicalBarista[num]) && ((physicalLine.size() != 0) || (onlineQueue.size() != 0))) {
                    if ((physicalLine.size() != 0)) {
                        servingCustomer2 = physicalLine.peek();
                        physicalLine.poll();
                        numCustomerServed++;
                    } else if (onlineQueue.size() != 0) {
                        servingCustomer2 = onlineQueue.peek();
                        onlineQueue.poll();
                        numCustomerServed++;
                    }

                    // System.out.println("Breakpoint 2");

                    // System.out.println("Customer serving by physical barista num " + num + " is customer:" + servingCustomer2.getArrivalTime());
                    // if(servingCustomer2.online == true){System.out.println("This customer is from the online queue");}
                    // else{System.out.println("This customer is from the in person line");}

                    physicalBarista[num] = (time + processingTime);
                    servingCustomer2.setDepartureTime(physicalBarista[num]);
                    if (servingCustomer2.online == true) {
                        totalTime += (servingCustomer2.totalTime() * alpha);
                    } else {
                        totalTime += servingCustomer2.totalTime();
                    }
                    

                }

            }
        }

        averageTime = totalTime / (numCustomerServed);
        //System.out.println("Total time: " + totalTime + " minutes");
        return averageTime;
    }


    public static void greedyCustomerInPerson(int numBaristas, int processingTime, int ArrivalRate) {
        // every minute in an 8-hour working day
        int numCustomer = 0;
        int numCustomerServed = 0;

        double[] baristas = new double[numBaristas];
        for (int i = 0; i < numBaristas; i++) {
            baristas[i] = 0;
        }

        Queue<Customer> physicalLine = new LinkedList<Customer>();

        double customerArrivalRate = ArrivalRate;
        int timeToNextCust = 0;
        double totalTime = 0;
        double averageTime;

        for (int time = 1; time <= 4800; time = time + 1) {

            Customer customer = new Customer(0);

            // Customer arrives at a fixed interval
            timeToNextCust++;
            // System.out.println("Time to next customer is:" + timeToNextCust);
            if (timeToNextCust == customerArrivalRate) {
                customer.setArrivalTime(time);
                timeToNextCust = 0;
                numCustomer++;
            }

            if (customer.getArrivalTime() != 0) {
                    physicalLine.add(customer);
            }
          
            Customer servingCustomer = new Customer(0);
        

            for (int num = 0; num < numBaristas; num++) {
                if ((time >= baristas[num]) && (physicalLine.size() != 0)) {
                       
                    servingCustomer = physicalLine.peek();
                    physicalLine.poll();
                    numCustomerServed++;
                        

                    // System.out.println("Breakpoint 2");

                    // System.out.println("Customer serving is customer:" + servingCustomer.getArrivalTime());
                   

                    baristas[num] = (time + processingTime);
                    servingCustomer.setDepartureTime(baristas[num]);
                        totalTime += servingCustomer.totalTime();
                    }

                }

            }    
      
        averageTime = totalTime / (numCustomerServed);
        System.out.println("Number of baristas: " + (numBaristas));
        System.out.println("Average time: " + averageTime/60 + " minutes");
        System.out.println("Number of customer unserved:" + (numCustomer - numCustomerServed));
    }
}
