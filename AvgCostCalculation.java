public class AvgCostCalculation {
// This is for an even split of baristas
    public static double calculatingQueueCost(int queueSize, int lineSize, double numBarista, double alpha, double processingTime){

        double queueWaitingTime = 0;
        double totalCustomer = queueSize + lineSize;
       

        if(lineSize > queueSize){
            queueWaitingTime = (float) processingTime*alpha*((queueSize+1)/numBarista);
        } 
        else{
            queueWaitingTime = (float) processingTime*alpha*(1/(numBarista))*(2*(totalCustomer)-queueSize-(totalCustomer*(totalCustomer-1))/(2*queueSize));
        }

        return queueWaitingTime;
    }

    public static double calculatingLineCost(int queueSize, int lineSize, double numBarista, double alpha, double processingTime){
        double lineWaitingTime = 0;
        double totalCustomer = queueSize + lineSize;

        if(lineSize > queueSize){
            lineWaitingTime = (float) processingTime*(1/(numBarista))*(2*(totalCustomer)-lineSize-(totalCustomer*(totalCustomer-1))/(2*lineSize));      
        }
        else{
            lineWaitingTime = (float) processingTime*(lineSize+1)/numBarista;
        }

        return lineWaitingTime;
    }
    
}
