import java.util.Scanner;
 
public class Main {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        int K = sc.nextInt();
        int D1 = sc.nextInt();
        int D2 = sc.nextInt();
        
        double heightSquared = calculateCupHeightSquared(K, D1, D2);
        
        System.out.println(heightSquared);
    }

    private static double calculateCupHeightSquared(int K, int D1, int D2) {
        double r1 = D1 / 2.0;
        double r2 = D2 / 2.0;
        double height = Math.sqrt(K * K - Math.pow(r1 - r2, 2));
        
        return height * height;
    }
}