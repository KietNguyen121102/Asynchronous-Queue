import java.util.*;


public class NashEquilibrium {
    

    public static void NashEquilibriumFinder(int customer, int numOnlineBarista, int numPhysicalBarista, double processingTime, int arrivalRate, double alpha, ArrayList<Integer> SPNE) {
        
        
        HashMap<ArrayList<Integer>, ArrayList<Double>> costMatrix = new HashMap<ArrayList<Integer>, ArrayList<Double>>();
        int[] arr = new int[customer];

        BinaryString binaryString = new BinaryString();
        binaryString.generateAllBinaryStrings(customer, arr, 0);

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

        HashMap<ArrayList<Integer>, ArrayList<Double>> NashEquilibrium = new HashMap<ArrayList<Integer>, ArrayList<Double>>();
       

        for (Map.Entry<ArrayList<Integer>, ArrayList<Double>> waitingTime : costMatrix.entrySet()) {
                ArrayList<Integer> strategy = waitingTime.getKey();
                ArrayList<Double> cost = waitingTime.getValue();
                boolean isNash = true;

                //finding single deviation profiles
                for(int i = 0; i < strategy.size(); i++){
                    ArrayList<Integer> singleDeviationStrategy = (ArrayList) strategy.clone();
                    if(singleDeviationStrategy.get(i) == 0){
                        singleDeviationStrategy.set(i, 1);
                    }else{
                        singleDeviationStrategy.set(i, 0);
                    }

                //checking Nash Equilibrium conditions
                    
                    if(costMatrix.get(singleDeviationStrategy).get(i) < costMatrix.get(strategy).get(i)){
                        isNash = false;
                        if(strategy.equals(SPNE)){
                            System.out.println(strategy + " is not a Nash equibrium when compared to " + singleDeviationStrategy + " at customer " + (i+1) + " because SPE cost is " + costMatrix.get(strategy) 
                            + " while SDS cost is " + costMatrix.get(singleDeviationStrategy));
                        }
                        if(strategy.equals(new ArrayList<>(Arrays.asList(1,1,1,1,1,1)))){
                            System.out.println(strategy + " is not a Nash equibrium when compared to " + singleDeviationStrategy + " at customer " + (i+1) + " because SPE cost is " + costMatrix.get(strategy) 
                            + " while SDS cost is " + costMatrix.get(singleDeviationStrategy));

                        }
                    
                        
                    };
                }

                if(isNash == true){
                    NashEquilibrium.put(strategy,cost);}

        }


            System.out.println(NashEquilibrium);
            System.out.println(combinations.size());
            
        }
    }

// }

