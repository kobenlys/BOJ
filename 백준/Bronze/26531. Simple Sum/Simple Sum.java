import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		String a = sc.nextLine();
		int num1 = (int)(a.charAt(0) - 48);
		int num2 = (int)(a.charAt(4) - 48);
		int num3 = (int)(a.charAt(8) - 48);
		
		if(num1 + num2 == num3) {
			System.out.println("YES");
		}else {
			System.out.println("NO");
		}
		sc.close();
	}
}