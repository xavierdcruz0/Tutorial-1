/**
 * Created by xavi on 08/10/15.
 */
import java.util.Map;
import java.util.HashMap;
import java.util.Scanner;

public class Ex4 {
    public static void main(String[] args){

        // Part 1: output converted values for all units

        Scanner sc = new Scanner(System.in);
        System.out.println("Please enter a number of bytes to convert");
        int input = sc.nextInt();
        final String[] UNITS = { "B","kB","MB", "GB"};

        Map exponentMap = new HashMap();
        exponentMap.put("B", 0);
        exponentMap.put("kB", 10);
        exponentMap.put("MB", 20);
        exponentMap.put("GB", 30);

        for(String unit: UNITS){
            int power = (int) exponentMap.get(unit);
            double p = (double) power;
            double ans;
            ans = input / Math.pow(2, p);
            System.out.printf("%d Bytes in %s = %f \n", input, unit, ans);
        }

        // Part 2: output converted value for a unit of the user's choosing

        System.out.println("\nPlease enter another number of bytes to convert");
        input = sc.nextInt();
        System.out.println("Please choose from the following options by entering a number");
        System.out.println("1. Byte (B) \n2. Kilobyte (kB) \n3. Megabyte (MB) \n4. Gigabyte (GB)");
        int choice = sc.nextInt();
        double answer;

        switch(choice){
            case 1:
                answer = toByte(input);
                System.out.printf("%d Bytes is equal to %.2f %s", input, answer, UNITS[choice-1]);
                break;
            case 2:
                answer = toKiloByte(input);
                System.out.printf("%d Bytes is equal to %.2f %s", input, answer, UNITS[choice-1]);
                break;
            case 3:
                answer = toMegaByte(input);
                System.out.printf("%d Bytes is equal to %.2f %s", input, answer, UNITS[choice-1]);
                break;
            case 4:
                answer = toGigaByte(input);
                System.out.printf("%d Bytes is equal to %.2f %s", input, answer, UNITS[choice-1]);
                break;
        }

    }
    public static double toByte(int a){
        double A = a;
        return A;
    }
    public static double toKiloByte(int a){
        double A = a;
        double ans;
        ans = Math.pow(2, 10) / A;
        return ans;
    }
    public static double toMegaByte(int a){
        double A = a;
        double ans;
        ans = Math.pow(2, 20) / A;
        return ans;
    }
    public static double toGigaByte(int a){
        double A = a;
        double ans;
        ans = Math.pow(2, 30) / A;
        return ans;
    }
}
