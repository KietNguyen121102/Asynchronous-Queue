public class GreedySimulation {

    public static void greedyCustomer(int numBaristas, int processingTime, int ArrivalRate) {
        // every minute in an 8-hour working day
        int numCustomer = 0;

        double[] baristas = new double[numBaristas];
        for (int i = 0; i < numBaristas; i++) {
            baristas[i] = 0;
        }

        Line<Customer> physicalLine = new Line<Customer>();
        Line<Customer> onlineQueue = new Line<Customer>();

        double customerArrivalRate = ArrivalRate;
        int timeToNextCust = 0;
        double totalTime = 0;
        double averageTime;

        for (int time = 1; time <= 10; time = time + 1) {

            Customer customer = new Customer(0);

            // Customer arrives at a fixed interval
            timeToNextCust++;
            // System.out.println("Time to next customer is:" + timeToNextCust);
            if (timeToNextCust == customerArrivalRate) {
                customer.setArrivalTime(time);
                timeToNextCust = 0;
                numCustomer++;
            }

            if (customer.getArrivalTime() != 0) {
                if (physicalLine.size() < onlineQueue.size() / 2) {
                    physicalLine.enqueue(customer);
                } else {
                    onlineQueue.enqueue(customer);
                    customer.beOnline();
                }
            }

          
            
            for (int num = 0; num < numBaristas; num++) {
                if ((time >= baristas[num]) && (!physicalLine.empty() || !onlineQueue.empty())) {
                    if ((!physicalLine.empty()) && (!onlineQueue.empty())) {
                        if (physicalLine.rear().getArrivalTime() <= onlineQueue.rear().getArrivalTime()) {
                            customer = physicalLine.rear();
                            physicalLine.dequeue();
                        } else {
                            customer = onlineQueue.rear();
                            onlineQueue.dequeue();
                        }
                    } else if (physicalLine.empty()) {
                        customer = onlineQueue.rear();
                        onlineQueue.dequeue();
                    } else {
                        customer = physicalLine.rear();
                        physicalLine.dequeue();
                    }

                 

                    System.out.println("Customer serving is customer:" + customer.getArrivalTime());

                    baristas[num] = (time + processingTime);
                    customer.setDepartureTime(baristas[num]);
                    if (customer.online == true) {
                        totalTime += (customer.totalTime() * 8) / 10;
                    } else {
                        totalTime += customer.totalTime();
                    }

                }

            }

        }

        averageTime = totalTime / (numCustomer);
        System.out.println("Number of baristas: " + (numBaristas));
        System.out.println("Average time: " + averageTime + " minutes");
    }
}
