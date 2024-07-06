import java.io.*;
import java.util.*;

public class Main {
    // 최대 공약수 구하기 - 유클리드 호제법
    public static int GCD(int x, int y) {
        if (y == 0) return x;
        return GCD(y, x % y);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        HashMap<String, Integer> map = new HashMap<>();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int max = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            String res;
            if (x != 0 && y != 0) {
                int gcd = GCD(Math.abs(x), Math.abs(y));
                // (y2 - y1) / (x2 - x1) 후 기약분수로 변환 (최대공약수로 나눠야함)
                res = y / gcd + " / " + x / gcd;
            } else {
                int gcd = Math.max(Math.abs(x), Math.abs(y));
                res = y / gcd + " / " + x / gcd;
            }

            map.put(res, map.getOrDefault(res, 0) + 1);
            max = Math.max(max, map.get(res));
        }
        System.out.println(max);
    }
}