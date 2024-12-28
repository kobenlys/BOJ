import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;


        int N = Integer.parseInt(br.readLine());
        int[] dp1 = new int[N + 1];
        int[] dp2 = new int[N + 1];

        if (N == 1) {
            System.out.println("0 1");
            return;
        }
        dp1[1] = 0;
        dp1[2] = 1;
        dp2[1] = 1;
        dp2[2] = 1;
        for (int i = 3; i <= N; i++) {
            dp1[i] = dp1[i - 1] + dp1[i - 2];
            dp2[i] = dp2[i - 1] + dp2[i - 2];
        }
        System.out.println(dp1[N] + " " + dp2[N]);
    }
}