import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<Character> stk = new Stack<>();
        StringBuilder sb = new StringBuilder();

        char[] arr1 = br.readLine().toCharArray();
        char[] arr2 = br.readLine().toCharArray();

        int[][] dp = new int[arr2.length + 1][arr1.length + 1];

        for (int i = 1; i <= arr1.length; i++) {
            for (int j = 1; j <= arr2.length; j++) {

                if (arr1[i-1] == arr2[j-1]) {
                    dp[j][i] = dp[j - 1][i - 1] + 1;
                } else {
                    dp[j][i] = Math.max(dp[j - 1][i], dp[j][i - 1]);
                }
            }
        }


        //1 4 5 7 12
        //LHJOJ
        //2 7 8 9 11
        //LJWOA
        int x = arr1.length;
        int y = arr2.length;
        while (dp[y][x] > 0) {

            if (dp[y][x] == dp[y][x - 1]) {
                x--;
            } else if (dp[y][x] != dp[y][x - 1] && dp[y][x] == dp[y - 1][x]) {
                y--;
            } else {
                stk.push(arr1[x - 1]);
                y--;
                x--;
            }
        }
        //LNALKLJLK
        // 진짜 정답 -> LNLKNLKLK
        //LGNALKNLWJKALDMNLK
        //GL:NLKJENALKJLWK
        while (!stk.isEmpty()) {
            sb.append(stk.pop());
        }
        System.out.println(dp[arr2.length][arr1.length]);
        System.out.println(sb);
    }
}
