import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static int gcd(int a, int b) {
        while (b != 0) {
            int tmp = b;
            b = a % b;
            a = tmp;
        }
        return a;
    }

    public static int lcm(int a, int b) {
        return (a * b) / gcd(a, b);
    }

    public static void main(String[] args) throws IOException { // 값 입력
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        StringBuilder sb = new StringBuilder();
        HashSet<Integer> set = new HashSet<>();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine(), " ");
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int size = lcm(M, N);

            while (true) {

                if (x > size && y > size) {
                    sb.append(-1).append("\n");
                    break;
                }

                if (!set.contains(x)) {
                    set.add(x);
                } else {
                    sb.append(x).append("\n");
                    break;
                }

                if (!set.contains(y)) {
                    set.add(y);
                } else {
                    sb.append(y).append("\n");
                    break;
                }
                x += M;
                y += N;
            }
            set.clear();
        }
        System.out.print(sb);
    }
}