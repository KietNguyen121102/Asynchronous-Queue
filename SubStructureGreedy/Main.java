import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.io.*;
import java.net.URL;
import java.nio.file.Path;

public class Main {

    public static void main(String[] args){
    for(int customer = 1; customer <= 20; customer ++){
        double min = 10000000;
        ArrayList<ArrayList<Integer>> optimalDecision = new ArrayList<ArrayList<Integer>>();
        ConcurrentHashMap<ArrayList<Integer>, ArrayList<Double>> costMatrix = new ConcurrentHashMap<ArrayList<Integer>, ArrayList<Double>>();
        HashMap<Integer, ArrayList<Double>> possibleCostOfEachCustomer = new HashMap<Integer, ArrayList<Double>>();
        int[] arr = new int[customer];

        BinaryString binaryString = new BinaryString();
        binaryString.generateAllBinaryStrings(customer, arr, 0);

        ArrayList<int[]> allPossibleDecisions = binaryString.result;
        ArrayList<int[]> combinations = new ArrayList<int[]>();

        for(int[] decision: allPossibleDecisions){
            if(decision.length == customer){
                combinations.add(decision);
            }
        }

        //Construcinng all strategies profile
        for (int[] decisions : combinations) {
            ArrayList<Double> currentCostMatrix = noReSchedulerSimulation.noReSchedulerSimulation(2, 1, 10, 1, 0.700001, decisions);
            ArrayList<Integer> decision = new ArrayList<Integer>();
            for (int i = 0; i < decisions.length; i++) {
                decision.add(decisions[i]);
            }
            costMatrix.put(decision, currentCostMatrix);

        }

       

        for(Map.Entry<ArrayList<Integer>, ArrayList<Double>> waitingTime : costMatrix.entrySet()){
            double totalCost = 0;
            for(int i = 0; i < waitingTime.getValue().size(); i++){
                totalCost = totalCost + waitingTime.getValue().get(i);
            }
            if(totalCost < min){
                min = totalCost;
            }
        }

        

        for(Map.Entry<ArrayList<Integer>, ArrayList<Double>> waitingTime : costMatrix.entrySet()){
            double totalCost = 0;
            for(int i = 0; i < waitingTime.getValue().size(); i++){
                totalCost = totalCost + waitingTime.getValue().get(i);
            }
            // System.out.println("Total cost of " + waitingTime.getKey() + " is " + totalCost);
            if(totalCost == min){
               optimalDecision.add(waitingTime.getKey());
            }
        }

        // double greedyCost = NoReGreedySimulation.NoReGreedy(2, 1, 10, 1, 0.7 , customer);

        System.out.println("Optimal decision of " + customer + " customers is " + optimalDecision);
        // System.out.println("Ratio between optimal and greedy" + min/greedyCost);

        

        
    
    }
}
}


