import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int a = sc.nextInt();
		
		if(a <= 100000 && a % 2024 == 0) {
			System.out.println("Yes");
		}else {
			System.out.println("No");
		}
		sc.close();
	}
}