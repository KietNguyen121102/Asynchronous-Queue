import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.io.*;
import java.net.URL;
import java.nio.file.Path;


public class NoReallocationGreedy {

public static void CostAnalysis(int numOnlineBarista, int numPhysicalBarista, double processingTime, int arrivalRate, double alpha, int customers){

    ConcurrentHashMap<ArrayList<Integer>, ArrayList<Double>> costMatrix = new ConcurrentHashMap<ArrayList<Integer>, ArrayList<Double>>();
    HashMap<Integer, ArrayList<Double>> possibleCostOfEachCustomer = new HashMap<Integer, ArrayList<Double>>();
    int[] arr = new int[customers];

    BinaryString binaryString = new BinaryString();
    binaryString.generateAllBinaryStrings(customers, arr, 0);

    ArrayList<int[]> allPossibleDecisions = binaryString.result;
    ArrayList<int[]> combinations = new ArrayList<int[]>();

    for(int[] decision: allPossibleDecisions){
        {combinations.add(decision);}
    }

    //Construcinng all strategies profile
    for (int[] decisions : combinations) {
        ArrayList<Double> currentCostMatrix = noReSchedulerSimulation.schedulerSimulation(numOnlineBarista, numPhysicalBarista, processingTime, arrivalRate, alpha, decisions);
        ArrayList<Integer> decision = new ArrayList<Integer>();
        for (int i = 0; i < decisions.length; i++) {
            decision.add(decisions[i]);
        }
        costMatrix.put(decision, currentCostMatrix);

    }
    
    // System.out.println("Cost matrix size is: " + costMatrix.size());



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
        int tieCount = 0;
        //System.out.println("Size of cost matrix is: " + costMatrix.size());
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

            if(alternativeStrategy == null){
                System.out.println("Alternative strategy is null");

            }

            ArrayList<Double> alternativeCost = costMatrix.get(alternativeStrategy);

            // if(alternativeCost == null){
            //     System.out.println("Alternative cost is null ");;

            // }
            


            if(strategy.equals(new ArrayList<>(Arrays.asList(1,1,1,1,1,1))))
            {System.out.println("Alternative strategy of " + strategy + " is " + alternativeStrategy + " at customer " + customer +" with cost being " + costMatrix.get(strategy) + " and " + costMatrix.get(alternativeStrategy));
        };

            if(alternativeCost != null){
            if(cost.get(customer) < alternativeCost.get(customer)){
                costMatrix.remove(alternativeStrategy, alternativeCost);
            }
            else if(cost.get(customer) > alternativeCost.get(customer)){
                costMatrix.remove(strategy, cost);
            }
            else{
                if(cost.get(customer) == 1){costMatrix.remove(alternativeStrategy, alternativeCost);}
                else{costMatrix.remove(strategy, cost); tieCount++;}
            }
        }
        }
        
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

   




    //Calculate cumulative cost for each strategy



   


        ArrayList<Double> result = new ArrayList<Double>();
        
        result.add((double)numOnlineBarista); 
        result.add((double)numPhysicalBarista);
        result.add(processingTime);
        result.add((double)arrivalRate);
        result.add(alpha);
        result.add((double) customers);
    
        result.add(allQueueCost);
        result.add(SPEStrategyCost); 

        result.add(allQueueCost/alpha);

        try(FileWriter f = new FileWriter("OverTimeCostData" + alpha + ".txt", true); 
                BufferedWriter b = new BufferedWriter(f); 
                PrintWriter p = new PrintWriter(b);) {
                p.println(result);
                System.out.println("Add sucessfully");
        }
                catch (IOException e){
                e.printStackTrace();
        }
    



    // for(int i = 0; i < customers; i++){
    //     ArrayList<Double> result = new ArrayList<Double>();
    //     result.add((double)numOnlineBarista); 
    //     result.add((double)numPhysicalBarista);
    //     result.add(processingTime);
    //     result.add((double)arrivalRate);
    //     result.add(alpha);
    //     result.add((double) customers);
    //     result.add((double)i+1);
    //     result.add(alQueueCost.get(i));
    //     result.add(SPECost.get(i)); 
    //     result.add(greedyCost.get(i));
    //     result.add(pessGreedyCost.get(i));
    //     result.add(alQueueCost.get(i)/alpha);

    //     try(FileWriter f = new FileWriter("costData" + alpha + ".txt", true); 
    //             BufferedWriter b = new BufferedWriter(f); 
    //             PrintWriter p = new PrintWriter(b);) {
    //             p.println(result);
    //             System.out.println("Add sucessfully");
    //     }
    //             catch (IOException e){
    //             e.printStackTrace();
    //     }





    // // double random1 = GreedySimulation.randomCustomerInHybrid(numOnlineBarista, numPhysicalBarista, processingTime, arrivalRate, alpha, customers+1);
    // // double random2 = GreedySimulation.randomCustomerInHybrid(numOnlineBarista, numPhysicalBarista, processingTime, arrivalRate, alpha, customers+1);
    // // double random3 = GreedySimulation.randomCustomerInHybrid(numOnlineBarista, numPhysicalBarista, processingTime, arrivalRate, alpha, customers+1);
    // // result.add((random1 + random2 + random3)/3);
    // // result.add(allQueueCost/alpha);


    // }
   
    System.out.println("SPE strategy is: " + SPEStrategy);

    return; 



}
}
