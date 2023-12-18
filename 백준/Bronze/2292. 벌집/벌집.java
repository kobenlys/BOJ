import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException { // 값 입력
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));

        int N = Integer.parseInt(br.readLine());
        if (N == 1) {
            System.out.println(1);
            System.exit(0);
        }
        // 육각형 1 -> 6 -> 12 -> 18 -> 24
        // 누적 합 1 -> 7 -> 19 -> 37 -> 61
        // 칸수 1 ->  2 -> 3->  4
        long res = 1, cnt = 1;
        while (true) {
            res += cnt * 6;
            if (N <= res) {
                System.out.println(cnt + 1);
                break;
            }
            cnt++;
        }
    }
}