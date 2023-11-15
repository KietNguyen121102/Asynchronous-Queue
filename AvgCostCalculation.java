import java.util.*;

public class AvgCostCalculation {
// This is for an even split of baristas
    public static double calculatingQueueCost(int queueSize, int lineSize, double queueBarista, double lineBarista, double alpha, double processingTime){

        double queueWaitingTime = 0;
        double totalCustomer = queueSize + lineSize;
       

        if(lineSize/lineBarista >= queueSize/queueBarista){
            queueWaitingTime = (float) (Math.ceil((queueSize + 1)/queueBarista)*processingTime)*alpha;
        } 
        else{
            if(((queueSize - queueBarista*Math.floor(lineSize/lineBarista)+1) % (queueBarista + lineBarista)) > ((lineBarista + queueBarista) - (lineSize % lineBarista)))
            {queueWaitingTime = (float) alpha*(Math.floor(lineSize/lineBarista)*processingTime + Math.ceil((queueSize - queueBarista*Math.floor(lineSize/lineBarista) + 1 )/(queueBarista + lineBarista))*processingTime + processingTime);}
            else{queueWaitingTime = (float) alpha*(Math.floor(lineSize/lineBarista)*processingTime + Math.ceil((queueSize - queueBarista*Math.floor(lineSize/lineBarista) + 1 )/(queueBarista + lineBarista))*processingTime);}
        }

        return queueWaitingTime;
    }

    public static double calculatingLineCost(int queueSize, int lineSize, double queueBarista, double lineBarista, double alpha, double processingTime){
        double lineWaitingTime = 0;
        double totalCustomer = queueSize + lineSize;

        if(lineSize/lineBarista >= queueSize/queueBarista){
            if(((lineSize - lineBarista*Math.floor(queueSize/queueBarista)+1) % (queueBarista + lineBarista)) > ((lineBarista + queueBarista) - (queueSize % queueBarista)))
            {lineWaitingTime = (float) (Math.floor(queueSize/queueBarista)*processingTime + Math.ceil((lineSize - lineBarista*Math.floor(queueSize/queueBarista) + 1 )/(queueBarista + lineBarista))*processingTime + processingTime);}
            else{lineWaitingTime = (float) (Math.floor(queueSize/queueBarista)*processingTime + Math.ceil((lineSize - lineBarista*Math.floor(queueSize/queueBarista) + 1 )/(queueBarista + lineBarista))*processingTime);}     
        }
        else{
            lineWaitingTime = (float) processingTime*Math.ceil((lineSize+1)/lineBarista);
        }

        return lineWaitingTime;
    }
    
}
