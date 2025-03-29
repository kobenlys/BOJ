import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		StringBuilder sb = new StringBuilder();

		int N = sc.nextInt();
		int teamMin = sc.nextInt();
		int perMin = sc.nextInt();

		int[] arr = new int[N * 3];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = sc.nextInt();
		}
		int cnt = 0;
		for (int i = 0; i < N; i++) {
			int a = arr[i * 3 + 0];
			int b = arr[i * 3 + 1];
			int c = arr[i * 3 + 2];

			if (a + b + c >= teamMin && a >= perMin && b >= perMin && c >= perMin) {
				cnt++;
				sb.append(a + " ");
				sb.append(b + " ");
				sb.append(c + " ");
			}
		}
		System.out.println(cnt + "\n" + sb);
	}
}