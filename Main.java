import java.util.*;

public class Main {

    public static void main(String[] args) {

        HashMap<ArrayList<Integer>, ArrayList<Double>> costMatrix = new HashMap<ArrayList<Integer>, ArrayList<Double>>();

        int[] arr = new int[11];

        BinaryString binaryString = new BinaryString();
        binaryString.generateAllBinaryStrings(11, arr, 0);

        ArrayList<int[]> combinations = binaryString.result;
        for (int[] decisions : combinations) {
            ArrayList<Double> currentCostMatrix = SchedulerSimulation.schedulerSimulation(1, 1, 5, 1, 0.9, decisions);
            ArrayList<Integer> decision = new ArrayList<Integer>();
            for (int i = 0; i < decisions.length; i++) {
                decision.add(decisions[i]);
            }
            costMatrix.put(decision, currentCostMatrix);

        }

       
        for (int customer = 10; customer >= 0; customer--) {
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
                if(cost.get(customer) < alternativeCost.get(customer)){
                    toRemove.put(alternativeStrategy, alternativeCost);
                }
                else{
                    toRemove.put(strategy, cost);
                }
   
            }

            System.out.println(costMatrix);
            System.out.println(" ");

            for (Map.Entry<ArrayList<Integer>, ArrayList<Double>> waitingTime : toRemove.entrySet()) {
                costMatrix.remove(waitingTime.getKey(), waitingTime.getValue());
            }
            
        }

    }
}
// }