import java.util.Scanner;
 
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int T = sc.nextInt();
        sc.nextLine();
        
        for (int i = 0; i < T; i++) {
            String[] input = sc.nextLine().split(" ");
            
            long operand1 = Long.parseLong(input[0]);
            char operator = input[1].charAt(0);
            long operand2 = Long.parseLong(input[2]);
            long answer = Long.parseLong(input[4]);
            
            long result = Calculator.calculate(operand1, operator, operand2);
            
            if (result == answer) {
                System.out.println("correct");
            } else {
                System.out.println("wrong answer");
            }
        }
    }
}
 
class Calculator {
    public static long calculate(long operand1, char operator, long operand2) {
        long result;
        switch (operator) {
            case '+':
                result = operand1 + operand2;
                break;
            case '-':
                result = operand1 - operand2;
                break;
            case '*':
                result = operand1 * operand2;
                break;
            case '/':
                result = operand1 / operand2;
                break;
            default:
                result = 0;
        }
        return result;
    }
}