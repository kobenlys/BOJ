import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int ch = sc.nextInt();
		int co = sc.nextInt();
		int be = sc.nextInt();
		int count = 0;
		
		count += co / 2;
		count += be;
		
		if(ch > count) {
			System.out.println(count);
		}else {
			System.out.println(ch);
		}
		sc.close();
	}
}