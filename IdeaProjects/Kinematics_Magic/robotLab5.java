

import java.util.Arrays;
import java.util.Random;


public class robotLab5 {//extends Application {
    /**
     * Calculates the resulting point coordinate based on the parameters.
     *
     * @param oriX   Origin X coordinate
     * @param oriY   Origin Y coordinate
     * @param length Length of the line
     * @param angle  Angle of the line according the Cartes Axis
     * @return a Pair of double {x,y}
     */
   public static double[] coordinateCalc(double oriX, double oriY, double length, double angle){
        double angleRadian = Math.toRadians(angle);
        double xComp = Math.cos(angleRadian) * length;
        double yComp = Math.sin(angleRadian) * length;
        double x = oriX + xComp;
        double y = oriY + yComp;

        return new double[]{x, y};
    }
    public static double[] coordinateCalcForJavaFX(double oriX, double oriY, double length, double angle){
        double angleRadian = Math.toRadians(angle);
        double xComp = Math.cos(angleRadian) * length; // Calculate the x component
        double yComp = Math.sin(angleRadian) * length; // Calculate the y component
        double x = oriX + xComp; // Add the component to get new coordinate
        double y = oriY - yComp; // Ditto as above but substract due to JavaFX pane coordinate being the way it is. Y coordinate positive is from top to bottom.

        return new double[]{x, y};
    }
     int[] getRandom(){
        Random r = new Random(); // Generate random value just for this lab
        var angle = r.nextInt(0,360);
        var length = r.nextInt(0,400);
        return new int[]{angle,length};
    }

	/* Produces the length and angle given coordinates, [0] and [1] is first point coordinate and [2] and [3] is for the second one. */
    public static double[] calculateEqn(int[] coordinates){
        var length = Math.sqrt(Math.pow(coordinates[0]-coordinates[2],2)+Math.pow(coordinates[1]-coordinates[3],2));
        var angle = Math.toDegrees(getRadian(coordinates));
        return new double[] {length,angle};
    }
	
	// Calculates someting i forgot, but something about the angle
    private static double getRadian(int[] coordinates) {
        return Math.atan2((double) coordinates[3] - coordinates[1] ,(double) coordinates[2] - coordinates[0]);
    }

    //@Override
    public static void main(String[] args) throws Exception {
         // Question A
        System.out.println("Question A");
        double[] realVal = calculateEqn(new int[]{0,0,2,4});

        // Question B
        System.out.println("Question B");
        RandomSearch rndSrch = new RandomSearch();
        rndSrch.findOptimal(realVal);
//        calculateEqn(RandomSearch.optimal);
        System.out.print("Set Target: 2, 4");
        rndSrch.printResult();
        System.out.println(Arrays.toString(realVal));
        // Question C
        System.out.println("Question C");
        ParallelRandomSearch pr = new ParallelRandomSearch();
        pr.syncOptimal(realVal,10);

    }
}
