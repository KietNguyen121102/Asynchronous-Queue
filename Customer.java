public class Customer {
    double arrivalTime;
    double departureTime;
    boolean inLine = false;
    boolean inQueue = false;
    int preferences; 
    int index;


    public Customer(int arrives){
        arrivalTime = arrives;
    }

    public double getArrivalTime(){
        return this.arrivalTime;
    }

    public double getDepartureTime(){
        return this.departureTime;
    }

    public double totalTime(){
        return departureTime - arrivalTime;
    }

    public void setDepartureTime(double departs){
        departureTime = departs;
    }



}
