import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int ans = N;
        int cnt = 0;

        while (true) {
            cnt++;

            int a = ans / 10;
            int b = ans % 10;
            ans = (b * 10) + (a + b) % 10;
            if (ans == N) {
                break;
            }
        }

        System.out.print(cnt);
    }
}