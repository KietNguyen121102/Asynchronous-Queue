import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Main {
    
    public static void main(String[] args) {

    for(int onlineBarista = 0 ; onlineBarista <= 5; onlineBarista++){
        for(double alpha = 0; alpha <=0.9 ; alpha = alpha + 0.2){
        int physicalBarista = 5 - onlineBarista;
        double processingTime = 8;
        int arrivalRate = 1;
      

        System.out.println("online barista: " + onlineBarista); 
        double omniscientGreedyCost = OminiscientGreedy.omniscientGreedy(1, 1, processingTime, arrivalRate, 0.5, 500);
        // double optGreedyCost = GreedySimulation.greedyCustomerInHybrid(onlineBarista, physicalBarista, processingTime, arrivalRate, alpha, customer); 
        // double pessGreedyCost = GreedySimulation.pessGreedyCustomerInHybrid(onlineBarista, physicalBarista, processingTime, arrivalRate, alpha, customer);

                    
        // ArrayList<Double> result = new ArrayList<Double>();
        // result.add((double) onlineBarista); 
        // result.add((double) physicalBarista);
        // result.add(processingTime);
        // result.add((double)arrivalRate);
        // result.add(alpha);
        // result.add((double) customer);
        // result.add(omniscientGreedyCost);
        // result.add(optGreedyCost);
        // result.add(pessGreedyCost);

        // try(FileWriter f = new FileWriter("greedyComparison" + alpha +".txt", true); 
        //     BufferedWriter b = new BufferedWriter(f); 
        //     PrintWriter p = new PrintWriter(b);) {
        //         p.println(result);
        //         System.out.println("Add sucessfully");
        //     }
        //     catch (IOException i){
        //         i.printStackTrace();
        // }
    }
}
    }

}



