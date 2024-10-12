import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int t1 = sc.nextInt();
		int s1 = sc.nextInt();
		
		if(s1 == 0 && 12 <= t1 && t1 <= 16) {
			System.out.println("320");
		}else {
			System.out.println("280");
		}
		sc.close();
	}
}