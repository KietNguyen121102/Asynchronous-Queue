import java.util.LinkedList;
import java.util.Queue;
import java.util.*;

public class populationSimulation {
    
    public static double populationSimulation(int onlineBaristas, int physicalBaristas, double processingTime, int ArrivalRate, double alpha, int totalCustomers, double physicalPopulation, double onlinePopulation){
         // every minute in an 8-hour working day
        int numCustomer = 0;
        int numPhysicalCustomerServed = 0;
        int numOnlineCustomerServed = 0;
        ArrayList<Double> totalTimeArray = new ArrayList<Double>();
        HashMap<Integer, ArrayList<Double>> bartenderWorkSlot = new HashMap<Integer, ArrayList<Double>>();

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
                    // System.out.println("Customer " + customer.getArrivalTime() + " chooses physical line");

                } else if(i >= physicalPopulation && i <= (onlinePopulation + physicalPopulation) && numCustomer < totalCustomers) {
                    onlineQueue.add(customer);
                    customer.beOnline();
                    // System.out.println("Customer " + customer.getArrivalTime() + " chooses online line");

                } else {
                    double queueWaitingTime = (float)(AvgCostCalculation.calculatingQueueCost(onlineQueue.size(), physicalLine.size(), onlineBaristas, physicalBaristas, alpha, processingTime));
                    double lineWaitingTime = (float) AvgCostCalculation.calculatingLineCost(onlineQueue.size(), physicalLine.size(), onlineBaristas, physicalBaristas, alpha, processingTime);
                    if(onlineQueue.size() == 0){queueWaitingTime = processingTime*alpha;}
                    if(physicalLine.size() == 0){lineWaitingTime = processingTime;}
                    if(physicalBaristas == 0){lineWaitingTime = 1000000;}
                    if(onlineBaristas == 0){queueWaitingTime = 1000000;}

                    if (lineWaitingTime < queueWaitingTime && numCustomer <= totalCustomers) {
                        physicalLine.add(customer);
                        // System.out.println("Customer " + customer.getArrivalTime() + " chooses physical line");
                    } else if(numCustomer <= totalCustomers){
                        onlineQueue.add(customer);
                        customer.beOnline();
                        // System.out.println("Customer " + customer.getArrivalTime() + " chooses online line");
                    }
                }
            }


          
          
           Customer servingCustomer = new Customer(0);
        

            for (int num = 0; num < onlineBaristas; num++) {
                if ((time >= onlineBarista[num]) && ((physicalLine.size() != 0) || (onlineQueue.size() != 0))) {
                    if ((onlineQueue.size() != 0)) {
                        servingCustomer = onlineQueue.peek();
                        onlineQueue.poll();
                        numOnlineCustomerServed++;
                    } else if (physicalLine.size() != 0) {
                        servingCustomer = physicalLine.peek();
                        physicalLine.poll();
                        numPhysicalCustomerServed++;
                    }

                    // System.out.println("Breakpoint 2");

                    // System.out.println("Customer serving by online barista number " + num + " is customer:" + servingCustomer.getArrivalTime());
                    // if(servingCustomer.online == true){System.out.println("This customer is from the online queue");}
                    // else{System.out.println("This customer is from the in person line");}

                    onlineBarista[num] = (time + processingTime);
                    if(bartenderWorkSlot.get(num) != null)
                    {bartenderWorkSlot.get(num).add(onlineBarista[num]);}
                    else{
                        ArrayList<Double> workTime  = new ArrayList<Double>();
                        bartenderWorkSlot.put(num, workTime);
                    }

                    servingCustomer.setDepartureTime(onlineBarista[num]);
                    // System.out.println("Customer serving: " + servingCustomer.getArrivalTime());
                    if (servingCustomer.online == true) {
                        totalTime += (servingCustomer.totalTime() * alpha);
                        totalTimeArray.add(servingCustomer.totalTime() * alpha);
                    } else {
                        totalTime += servingCustomer.totalTime();
                        totalTimeArray.add(servingCustomer.totalTime());
                    }
                }

            }

            Customer servingCustomer2 = new Customer(0);
           

            for (int num = 0; num < physicalBaristas; num++) {
                if ((time >= physicalBarista[num]) && ((physicalLine.size() != 0) || (onlineQueue.size() != 0))) {
                    if ((physicalLine.size() != 0)) {
                        servingCustomer2 = physicalLine.peek();
                        physicalLine.poll();
                        numPhysicalCustomerServed++;
                    } else if (onlineQueue.size() != 0) {
                        servingCustomer2 = onlineQueue.peek();
                        onlineQueue.poll();
                        numOnlineCustomerServed++;
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

        averageTime = totalTime / (numOnlineCustomerServed + numPhysicalCustomerServed);
        // System.out.println("Total time of each is: " + totalTimeArray);
        System.out.println(bartenderWorkSlot.get(1));
        System.out.println("Total time is: " + totalTime);
        System.out.println("Total customer served: " + (numOnlineCustomerServed + numPhysicalCustomerServed));
        // System.out.println("Number of customer served: " + numCustomerServed);
        //System.out.println("Total time: " + totalTime + " minutes");
        // System.out.println("Number of online customer served: " + numOnlineCustomerServed);
        // System.out.println("Number of physical customer served: " + numPhysicalCustomerServed);
        return averageTime;
    }
}
