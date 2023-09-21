import java.util.*;

public class Main {

    public static void main(String[] args) {

        //Initializing the system
        int numOnlineBarista = 1;
        int numPhysicalBarista = 1;
        double processingTime = 5;
        int arrivalRate = 1;
        double alpha = 0.1;
        int customers = 10;
        



        HashMap<ArrayList<Integer>, ArrayList<Double>> costMatrix = new HashMap<ArrayList<Integer>, ArrayList<Double>>();
        HashMap<Integer, ArrayList<Double>> possibleCostOfEachCustomer = new HashMap<Integer, ArrayList<Double>>();
        int[] arr = new int[customers];

        BinaryString binaryString = new BinaryString();
        binaryString.generateAllBinaryStrings(customers, arr, 0);

        ArrayList<int[]> combinations = binaryString.result;
        //Construcinng all strategies profile
        for (int[] decisions : combinations) {
            ArrayList<Double> currentCostMatrix = SchedulerSimulation.schedulerSimulation(numOnlineBarista, numPhysicalBarista, processingTime, arrivalRate, alpha, decisions);
            ArrayList<Integer> decision = new ArrayList<Integer>();
            for (int i = 0; i < decisions.length; i++) {
                decision.add(decisions[i]);
            }
            costMatrix.put(decision, currentCostMatrix);

        }


        //Finding the possible costs of all customers
        for(Map.Entry<ArrayList<Integer>, ArrayList<Double>> waitingTime : costMatrix.entrySet()){
            ArrayList<Integer> listOfCustomer = waitingTime.getKey();
            ArrayList<Double> lostOfCost = waitingTime.getValue();
            for(int i = 0; i < listOfCustomer.size(); i++){
                if(!possibleCostOfEachCustomer.containsKey(i)){
                    possibleCostOfEachCustomer.put(i, new ArrayList<Double>());
                }
                else{
                    if(!possibleCostOfEachCustomer.get(i).contains(lostOfCost.get(i))){
                        possibleCostOfEachCustomer.get(i).add(lostOfCost.get(i));
                    }
                }
            }
        }


        // Finding the all-queue cost
        ArrayList<Integer> allQueueStrategy = new ArrayList<Integer>();
        for(int i = 0; i < combinations.get(1).length; i++){
            allQueueStrategy.add(1);
        }
        ArrayList<Double> alQueueCost = costMatrix.get(allQueueStrategy);
        double allQueueCost = 0;
        for(int i = 0; i < alQueueCost.size(); i ++){
            allQueueCost += alQueueCost.get(i);
        }

        for (int customer = customers-1; customer >= 0; customer--) {
             HashMap<ArrayList<Integer>, ArrayList<Double>> toRemove = new HashMap<ArrayList<Integer>, ArrayList<Double>>();

            for (Map.Entry<ArrayList<Integer>, ArrayList<Double>> waitingTime : costMatrix.entrySet()) {
                ArrayList<Integer> strategy = waitingTime.getKey();
                ArrayList<Double> cost = waitingTime.getValue();
                ArrayList<Integer> alternativeStrategy = new ArrayList<Integer>();

                //finding the alternative node

               for(Map.Entry<ArrayList<Integer>, ArrayList<Double>> waitingTime2 : costMatrix.entrySet()){
                    boolean potentialAlternative = true;
                    for(int i = 0; i < customer; i++){
                        if(waitingTime2.getKey().get(i) != strategy.get(i)){
                            potentialAlternative = false;
                        }
                    }
                    if(potentialAlternative == true && (!waitingTime2.getKey().equals(strategy))){
                        alternativeStrategy = waitingTime2.getKey();                  
                     }

               }

                //comparing two alternative nodes

                ArrayList<Double> alternativeCost = costMatrix.get(alternativeStrategy);


                if(strategy.equals(new ArrayList<>(Arrays.asList(1,1,1,1,1,1))))
                {System.out.println("Alternative strategy of " + strategy + " is " + alternativeStrategy + " at customer " + customer +" with cost being " + costMatrix.get(strategy) + " and " + costMatrix.get(alternativeStrategy));
            };
                if(cost.get(customer) < alternativeCost.get(customer)){
                    toRemove.put(alternativeStrategy, alternativeCost);
                }
                else{
                    toRemove.put(strategy, cost);
                }
   
            }

            for (Map.Entry<ArrayList<Integer>, ArrayList<Double>> waitingTime : toRemove.entrySet()) {
                costMatrix.remove(waitingTime.getKey(), waitingTime.getValue());
            }

            // System.out.println(costMatrix);
            
        }

        // Finding the Subgame Perfect Equilibrium cost
        ArrayList<Integer> SPEStrategy = new ArrayList<Integer>();
        Map.Entry<ArrayList<Integer>, ArrayList<Double>> firstEntry = costMatrix.entrySet().iterator().next();
        SPEStrategy = firstEntry.getKey();
        ArrayList<Double> SPECost = costMatrix.get(SPEStrategy);
        double SPEStrategyCost = 0;
        for(int i = 0; i < SPECost.size(); i ++){
            SPEStrategyCost += SPECost.get(i);
        }

        


        System.out.println("Current system has: ");
        System.out.println(numOnlineBarista + " online barista");
        System.out.println(numPhysicalBarista + " physical barista");
        System.out.println("Arrival rate is: " + arrivalRate + " per minute");
        System.out.println("Processing time is: " + processingTime + " minutes");
        System.out.println("Alpha is " + alpha);
        System.out.println("SPNE is: " + SPEStrategy);
        System.out.println("SPNE cost of each customer is: " + costMatrix.get(SPEStrategy));
        System.out.println("All queue cost of each customer is: " + alQueueCost);

        System.out.println("All queue total cost in this case is: " + allQueueCost);
        System.out.println("SPNE total cost in this case is: " + SPEStrategyCost);

        System.out.println("Greedy strategy is: " + GreedySimulation.greedyCustomerInHybrid(1,1,processingTime,1,alpha));

        // System.out.println("Possible cost for each customer: " + possibleCostOfEachCustomer);

        
        // NashEquilibrium.NashEquilibriumFinder(6, numOnlineBarista,numPhysicalBarista,processingTime,arrivalRate,alpha,SPEStrategy);
       


    }
}
// }