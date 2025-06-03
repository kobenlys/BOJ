import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        String str = br.readLine();
        int N = Integer.parseInt(br.readLine());
        Set<String> set = new TreeSet<>();

        int[] dp = new int[101];
        dp[str.length()] = 1;

        for (int i = 0; i < N; i++) {
            set.add(br.readLine());
        }

        for (int i = str.length()-1; i >= 0; i--) {
            for (int j = i+1; j <= str.length(); j++) {
                if (dp[j] == 1 && set.contains(str.substring(i,j))) {
                    dp[i] = 1;
                }
            }
        }

        System.out.println(dp[0]);
    }
}