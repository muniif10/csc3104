

import java.util.Arrays;
import java.util.Random;

public class ParallelRandomSearch {
     double[] optimal = {0,0,0};
    public static double[] generate(){
        Random rand = new Random();
//        double randomVal = rand.nextDouble(0,1);
        double length = (0 + (6)*rand.nextDouble(0,1));
        double angle =  (0 + (90)*rand.nextDouble(0,1));
        return new double[]{length,angle};
    }

    public void printResult(){
//        System.out.println("Best result:\nX: "+ optimal[0] + "\nY: " + optimal[1] + "\nDistance to real value: " + optimal[2] + "\n");
        System.out.printf("Best result from the thread = X coordinate: %.2f | Y coordinate: %.2f | Distance to real value: %.2f%n",optimal[0],optimal[1],optimal[2]);
    }
    public  void findOptimal(double[] realVal){
        var candidate = generate();
        var candidateResult = robotLab5.coordinateCalc(0,0,candidate[0],candidate[1]);
        optimal[1] = candidateResult[1];
        optimal[0] = candidateResult[0];
        optimal[2] = 200;
        var realResult = robotLab5.coordinateCalc(0,0,realVal[0],realVal[1]);

        for (int i = 0; i < 300; i++) {
            candidate = generate();
            candidateResult = robotLab5.coordinateCalc(0,0,candidate[0],candidate[1]);
            var difference = Math.sqrt(Math.pow(candidateResult[0]-realResult[0],2)+Math.pow(candidateResult[1]-realResult[1],2));
//            System.out.println(difference);
            while (difference < optimal[2]){
                //System.out.println("added");
                optimal[1] = candidateResult[1];
                optimal[0] = candidateResult[0];
                optimal[2] = difference;
            }
        }

    }
    synchronized void syncOptimal(double[] realVal,int loopCount) throws InterruptedException {
        Runnable rn = () -> {
//            System.out.println("Starting thread");
            findOptimal(realVal);
            printResult();

//            System.out.println("Ending thread.");
        };
        for (int i = 0; i < loopCount; i++) {
            new Thread(rn).start();
        }

    }
}
