import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int a = sc.nextInt();
		int b = sc.nextInt();
		double k = 1 / (1 + Math.pow(10, (b - a) / 400.0));
		
		System.out.println(String.format("%.10f", k));
		
		System.out.println();
		sc.close();
	}
}