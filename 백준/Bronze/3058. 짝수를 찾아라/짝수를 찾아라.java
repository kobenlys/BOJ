import java.util.Scanner;
public class Main {
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		int n = sc.nextInt();
		int[] a = new int[7];
		
		for(int i = 0; i < n; i++) {
			int total = 0;
			int min = 999999999;
			for(int j = 0; j < 7; j++) {
				a[j] = sc.nextInt();
				if(a[j] % 2 == 0) {
					total += a[j];
					if(min > a[j]) {
						min = a[j];
					}
				}
			}
			System.out.println(total + " " + min);
		}
		sc.close();
	}
}