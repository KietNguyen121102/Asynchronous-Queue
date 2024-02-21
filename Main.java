import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.io.*;
import java.net.URL;
import java.nio.file.Path;

public class Main {

    


    

    public static void main(String[] args) throws FileNotFoundException {

        //Initializing the system

        List<String[]> inputIntoCSV = new ArrayList<String[]>();

        Scanner scanner = new Scanner(System.in);

        ArrayList<Double> greedyCost = GreedySimulation.greedyCustomerInHybrid(3, 2, 8, 1, 0.9, 1000);
        ArrayList<Double> pessGreedyCost = GreedySimulation.pessGreedyCustomerInHybrid(3, 2, 8, 1, 0.9, 1000);

        // ArrayList<Integer> SPNE =  SPNEFinder.CostAnalysis(1, 1, 6, 1, 0.85, 15);
        // SlotSimulation.slotSimulation(1,1,3,1,0.9,SPNE);
        // System.out.println(SPNE);

    //Structural Greedy
        // Map<String, ArrayList<Integer>> result = GreedySimulation.greedyCustomerInHybrid(2, 2, 6, 1, 0.75, 100);
        // try(FileWriter f = new FileWriter("greedyLineSizeData.txt", true); 
        //     BufferedWriter b = new BufferedWriter(f); 
        //     PrintWriter p = new PrintWriter(b);) {
        //         p.println(result.get("queueSize"));
        //         p.println(result.get("lineSize"));
        //         System.out.println("Add sucessfully");
        //     }
        //     catch (IOException i){
        //         i.printStackTrace();
        //     }





    //Greedy with surge of customers
        // System.out.println(GreedySimulation.greedyCustomerWithSurge(1, 1, 3, 1, 0.4, 100));
      

    //Finding structure of SPNE

        // for(double processingTime = 3; processingTime <= 10; processingTime = processingTime + 1){
        //     int numOnlineBarista = 3; 
        //     int numPhysicalBarista = 3;
        //     int arrivalRate = 1;
        //     double alpha = 0.75;
        //     int customers = 20;

        //     System.out.println("Processing time is: " + processingTime);
        //     ArrayList<Double> result = SPNEFinder.CostAnalysis(numOnlineBarista, numPhysicalBarista, processingTime, arrivalRate, alpha, customers);
        // }
        

    //SPNE vs Greedy
        // for(double alphaValue = 0; alphaValue < 1; alphaValue = alphaValue + 0.1){
            // int numOnlineBarista = 1;
     
            // int numPhysicalBarista = 1;
        
            // double processingTime = 3;
        
            // int arrivalRate = 1;
                        
            // int cust = 100;

            // double alphaValue = 0.25;

            
    //Greedy experiment
        //     ArrayList<Double> costComparisonAnswer = SPNEFinder.CostAnalysis(numOnlineBarista, numPhysicalBarista, processingTime, arrivalRate, alphaValue, cust);

            // Map<String, ArrayList<Double>> result = GreedySimulation.greedyCustomerInHybrid(numOnlineBarista, numPhysicalBarista, processingTime, arrivalRate, alphaValue, cust);
        
            // try(FileWriter f = new FileWriter("greedyExpectedCostData.txt", true); 
            // BufferedWriter b = new BufferedWriter(f); 
            // PrintWriter p = new PrintWriter(b);) {
            //     p.println(result.get("queueExpectedCost"));
            //     p.println(result.get("lineExpectedCost"));
            //     System.out.println("Add sucessfully");
            // }
            // catch (IOException i){
            //     i.printStackTrace();
            // }

        
        // }

  

    //Greedy Population vs Fixed Population    
        // for(int numOnlineBarista = 5;  numOnlineBarista >= 0; numOnlineBarista--){
        //     for(double alpha = 0.1; alpha <= 0.9; alpha = alpha + 0.1){
                    
                  
        //             // if(physicalPop + onlinePop > 1){continue;}
        //             // int numOnlineBarista = 1;
     
        //             int numPhysicalBarista = 5 - numOnlineBarista;
        
        //             double processingTime = 8;
        
        //             int arrivalRate = 1;
                        
        //             int cust = 15;

       
        //             // double testGreedy = GreedySimulation.greedyCustomerInHybrid(numOnlineBarista, numPhysicalBarista, processingTime, arrivalRate, alpha, cust);


        //             // ArrayList<String> header = new ArrayList<String>(Arrays.asList("OnlineBarista", "PhysicalBarista", "ProcessingTime", "ArrivalRate", "Alpha", "NumOfCustomers", "AllQueueCost", "SPEStrategyCost", "GreedyCost", "Random", "AllLine"));
        //             // System.out.println(header);
        //             SPNEFinder.CostAnalysis(numOnlineBarista, numPhysicalBarista, processingTime, arrivalRate, alpha, cust);
        //             String[] data = new String[10];
        //              // for(int j = 0; j < answer.size();j++){data[j] = answer.get(j).toString();}
        //             System.out.println(data);

        //             double totalCost = 0;
                    // double testCost = populationSimulation.populationSimulation(numOnlineBarista, numPhysicalBarista, processingTime, arrivalRate, alphaValue, cust, 0, 0.1);
                    // System.out.println(testCost);
                    //ArrayList<Double> preferenceAnswer = new ArrayList<Double>(Arrays.asList((double) numOnlineBarista, (double)numPhysicalBarista, processingTime, (double) arrivalRate, alphaValue, physicalPopulation, totalCost));

       
        
                //     try(FileWriter f = new FileWriter("populationData" + alpha + ".txt", true); 
                //     BufferedWriter b = new BufferedWriter(f); 
                //     PrintWriter p = new PrintWriter(b);) {
                //     // p.println(populationAnswer);
                //     System.out.println("Add sucessfully");
                // }
                //     catch (IOException i){
                //     i.printStackTrace();
                // }



        }

    // }}

}




// }