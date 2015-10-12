/**
 * Created by xavi on 08/10/15.
 */

//import java.lang.*;

public class Ex3 {
    public static void main(String[] args){

        // the "numbers" array
        int numbers[] = new int[20];
        numbers[0]=1;
        for(int i = 1; i < numbers.length; i++){
            numbers[i] = numbers[i-1] * 2;
        }
        // print it out to view elements
        for(int num: numbers){
            System.out.println(num);
        }

        // the "averages" array
        double averages[] = new double[20];
        averages[0] = numbers[0];
        averages[19] = numbers[19];

        for(int i = 1; i< numbers.length - 1; i++){
            double previous = numbers[i-1];
            double next = numbers[i+1];
            averages[i] = (previous + next) / 2;
        }
        for(double av: averages){
            System.out.println(av);
        }
    }
}
