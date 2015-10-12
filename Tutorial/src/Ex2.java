/**
 * Created by xavi on 08/10/15.
 */
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;

public class Ex2 {
    public static void main(String[] args){
        System.out.println("CALCULATOR");
        System.out.println("Please choose from the following options by entering a number");
        System.out.println("1. Addition \n2. Subtraction \n3. Multiplication \n4. Division \n5. Factorial");

        Map mp = new HashMap();
        mp.put(1, "Addition");
        mp.put(2, "Subtraction");
        mp.put(3, "Multiplication");
        mp.put(4, "Division");
        mp.put(5, "Factorial");

        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        System.out.println("You chose: " + mp.get(choice));

        double[] ins = new double[2];
        double result;

        switch(choice){
            case 1:
                ins = binaryOperation();
                result = add(ins[0], ins[1]);
                System.out.printf("Answer: %.2f + %.2f = %.2f", ins[0], ins[1], result);
                break;
            case 2:
                ins = binaryOperation();
                result = subtract(ins[0], ins[1]);
                System.out.printf("Answer: %.2f - %.2f = %.2f", ins[0], ins[1], result);
                break;
            case 3:
                ins = binaryOperation();
                result = multiply(ins[0], ins[1]);
                System.out.printf("Answer: %.2f x %.2f = %.2f", ins[0], ins[1], result);
                break;
            case 4:
                ins = binaryOperation();
                result = divide(ins[0], ins[1]);
                System.out.printf("Answer: %.2f / %.2f = %.2f", ins[0], ins[1], result);
                break;
            case 5:
                int in;
                int re;
                in = unaryOperation();
                re = factorial(in);
                System.out.printf("Answer: Factorial of %d: %d! = %d", in, in, re);
                break;

        }
    }

    public static double add(double a, double b){
        double ans;
        ans = a + b;
        return ans;
    }

    public static double subtract(double a, double b){
        double ans;
        ans = a - b;
        return ans;
    }

    public static double multiply(double a, double b){
        double ans;
        ans = a * b;
        return ans;
    }

    public static double divide(double a, double b){
        double ans;
        ans = a / b;
        return ans;
    }

    public static int factorial(int a){
        int ans;
        if(a==0){
            ans = 1;
        }else{
            ans = factorial(a-1) * a;
        }
        return ans;
    }

    public static double[] binaryOperation(){
        Scanner sca = new Scanner(System.in);
        double[] ret = new double[2];
        System.out.println("Please enter the first number");
        double A = sca.nextDouble();
        ret[0] = A;
        System.out.println("Please enter the second number");
        double B = sca.nextDouble();
        ret[1] = B;
        return ret;
    }

    public static int unaryOperation(){
        Scanner sca = new Scanner(System.in);
        int ret;
        System.out.println("Please enter the number");
        ret = sca.nextInt();
        return ret;
    }
}
