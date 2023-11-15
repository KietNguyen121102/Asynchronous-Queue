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
        
  

        
        for(int arrival = 1; arrival <= 3; arrival++){
            for(int process = 3; process <= 10; process++){
                for(double alpha = 0.1; alpha < 1; alpha = alpha + 0.05){
                  
                    
                    int numOnlineBarista = 1;
     
                    int numPhysicalBarista = 1;
        
                    double processingTime = process;
        
                    int arrivalRate = arrival;
        
                    double alphavalue = alpha;
                        
                    int cust = 11;

       
                    double testGreedy = GreedySimulation.greedyCustomerInHybrid(numOnlineBarista, numPhysicalBarista, processingTime, arrivalRate, alpha, cust);


        // ArrayList<String> header = new ArrayList<String>(Arrays.asList("OnlineBarista", "PhysicalBarista", "ProcessingTime", "ArrivalRate", "Alpha", "NumOfCustomers", "AllQueueCost", "SPEStrategyCost", "GreedyCost", "Random", "AllLine"));
        // System.out.println(header);
                    ArrayList<Double> answer = SPNEFinder.CostAnalysis(numOnlineBarista, numPhysicalBarista, processingTime, arrivalRate, alpha, cust);
                    String[] data = new String[11];
                     // for(int j = 0; j < answer.size();j++){data[j] = answer.get(j).toString();}
                    System.out.println(data);
       
        
                    try(FileWriter f = new FileWriter("Data.txt", true); 
                    BufferedWriter b = new BufferedWriter(f); 
                    PrintWriter p = new PrintWriter(b);) {
                    p.println(answer);
                    System.out.println("Add sucessfully");
                }
                    catch (IOException i){
                    i.printStackTrace();
                }



        }}}

    }
}



// }