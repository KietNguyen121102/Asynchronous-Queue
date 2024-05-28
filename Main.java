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
        for(double alphaValue = 0.1; alphaValue <= 0.9; alphaValue = alphaValue + 0.2){
           
                for(int numOnlineBarista = 0; numOnlineBarista <= 5; numOnlineBarista++){
            
            
            int cust = 500;
            int numPhysicalBarista = 5 - numOnlineBarista;
        
            double processingTime = 8;
        
            int arrivalRate = 1;
                        
            
    //Greedy experiment

            double greedyChoice = GreedySimulation.greedyCustomerInHybrid(numOnlineBarista, numPhysicalBarista, processingTime, arrivalRate, alphaValue, cust);
            // double pessGreedyCost = GreedySimulation.pessGreedyCustomerInHybrid(numOnlineBarista, numPhysicalBarista, processingTime, arrivalRate, alphaValue, cust);

            // ArrayList<Double> result = new ArrayList<Double>();

            // result.add((double)numOnlineBarista); 
            // result.add((double)numPhysicalBarista);
            // result.add(processingTime);
            // result.add((double)arrivalRate);
            // result.add(alphaValue);
            // result.add((double) cust);
            // result.add(greedyChoice);

            // int totalBarista = numOnlineBarista + numPhysicalBarista;
            // double m = Math.floor(cust/totalBarista);

            // double allQueueCost = alphaValue*processingTime + (alphaValue/cust)*(processingTime-totalBarista*arrivalRate)*((m/2)*(totalBarista*(m-1)+2*(cust%totalBarista)));

            // result.add(allQueueCost);
            // result.add(greedyCost/cust);
            // result.add(pessGreedyCost/cust);
            // result.add((allQueueCost/alphaValue));

            // try(FileWriter f = new FileWriter("GreedyChoice" + alphaValue +".txt", true); 
            // BufferedWriter b = new BufferedWriter(f); 
            // PrintWriter p = new PrintWriter(b);) {
            //     p.println(result);
            //     System.out.println("Add sucessfully");
            // }
            // catch (IOException i){
            //     i.printStackTrace();
            // }
        
    }
}
        // }

  

    //Greedy Population vs Fixed Population    
        // for(int numOnlineBarista = 5;  numOnlineBarista >= 0; numOnlineBarista--){
        //     for(double alpha = 0.1; alpha <= 0.9; alpha = alpha + 0.2){
               
                  
        //             // if(physicalPop + onlinePop > 1){continue;}
        //             // int numOnlineBarista = 1;
                    
        //             int numPhysicalBarista = 5 - numOnlineBarista;
        
        //             double processingTime = 8;
        
        //             int arrivalRate = 1;
       
        //             int cust = 16;
        //             // double testGreedy = GreedySimulation.greedyCustomerInHybrid(numOnlineBarista, numPhysicalBarista, processingTime, arrivalRate, alpha, cust);
        //             SPNEFinder.CostAnalysis(numOnlineBarista, numPhysicalBarista, processingTime, arrivalRate, alpha, cust);


        // }}
    

    // }}

        }}
    




// }