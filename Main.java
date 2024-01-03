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
                    // int numOnlineBarista = 5;
     
                    // int numPhysicalBarista = 5 - numOnlineBarista;
        
                    // double processingTime = 8;
        
                    // int arrivalRate = 1;
        
                    // double alphaValue = 0.75;
                        
                    // int cust = 500;

                    // double totalCost = 0;
                    // double greedyTotalCost = 0; 
                    // // for(int j = 0; j < 1000; j++){
                    //     greedyTotalCost += GreedySimulation.preferenceInHybrid(numOnlineBarista, numPhysicalBarista, processingTime, arrivalRate, alphaValue, cust, 0.3);
                    //     totalCost += populationSimulation.populationSimulation(numOnlineBarista, numPhysicalBarista, processingTime, arrivalRate, alphaValue, cust, 0, 0.3);
                    // // }

                    // greedyTotalCost = greedyTotalCost/1000;
                    // totalCost = totalCost/1000;
                    // System.out.println("Total cost is: " + totalCost + ",while greedy cost is: " + greedyTotalCost);
        

  

        
        for(int numOnlineBarista = 5;  numOnlineBarista >= 0; numOnlineBarista--){
                for(double physicalPop = 0; physicalPop <= 1; physicalPop = physicalPop + 0.1){
                    for(double onlinePop = 0; onlinePop <= 1; onlinePop = onlinePop + 0.1){
                  
                    if(physicalPop + onlinePop > 1){continue;}
                    // int numOnlineBarista = 1;
     
                    int numPhysicalBarista = 5 - numOnlineBarista;
        
                    double processingTime = 8;
        
                    int arrivalRate = 1;
        
                    double alphaValue = 0.25;
                        
                    int cust = 500;

       
                    // double testGreedy = GreedySimulation.greedyCustomerInHybrid(numOnlineBarista, numPhysicalBarista, processingTime, arrivalRate, alpha, cust);


                    // ArrayList<String> header = new ArrayList<String>(Arrays.asList("OnlineBarista", "PhysicalBarista", "ProcessingTime", "ArrivalRate", "Alpha", "NumOfCustomers", "AllQueueCost", "SPEStrategyCost", "GreedyCost", "Random", "AllLine"));
                    // System.out.println(header);
                    // ArrayList<Double> answer = SPNEFinder.CostAnalysis(numOnlineBarista, numPhysicalBarista, processingTime, arrivalRate, alpha, cust);
                    String[] data = new String[11];
                     // for(int j = 0; j < answer.size();j++){data[j] = answer.get(j).toString();}
                    System.out.println(data);

                    double totalCost = 0;
                    double testCost = populationSimulation.populationSimulation(numOnlineBarista, numPhysicalBarista, processingTime, arrivalRate, alphaValue, cust, 0, 0.1);
                    System.out.println(testCost);

                    ArrayList<Double> totalCostArray = new ArrayList<Double>();
                    for(int j = 0; j < 1000; j++){
                        // totalCost += GreedySimulation.preferenceInHybrid(numOnlineBarista, numPhysicalBarista, processingTime, arrivalRate, alphaValue, cust, physicalPopulation);
                        double currentCost = populationSimulation.populationSimulation(numOnlineBarista, numPhysicalBarista, processingTime, arrivalRate, alphaValue, cust, physicalPop, onlinePop);
                        totalCost += currentCost;
                        totalCostArray.add(currentCost);
                        // totalCostArray.add(GreedySimulation.preferenceInHybrid(numOnlineBarista, numPhysicalBarista, processingTime, arrivalRate, alphaValue, cust, physicalPopulation));

                    }
                    totalCost = totalCost/1000;
                    System.out.println(totalCost);
                    // System.out.println(totalCostArray);

                    //ArrayList<Double> preferenceAnswer = new ArrayList<Double>(Arrays.asList((double) numOnlineBarista, (double)numPhysicalBarista, processingTime, (double) arrivalRate, alphaValue, physicalPopulation, totalCost));
                    
                    ArrayList<Double> populationAnswer = new ArrayList<Double>(Arrays.asList((double) numOnlineBarista, (double)numPhysicalBarista, processingTime, (double) arrivalRate, alphaValue, physicalPop, onlinePop, 1 - onlinePop - physicalPop, totalCost));


       
        
                    try(FileWriter f = new FileWriter("populationData" + alphaValue + ".txt", true); 
                    BufferedWriter b = new BufferedWriter(f); 
                    PrintWriter p = new PrintWriter(b);) {
                    p.println(populationAnswer);
                    System.out.println("Add sucessfully");
                }
                    catch (IOException i){
                    i.printStackTrace();
                }



        }}}
    }

    }




// }