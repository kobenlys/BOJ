import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        // 편집 거리 알고리즘
        String str1 = br.readLine();
        String str2 = br.readLine();
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];

        // 초기세팅, 1,2,3,... 인덱스 0번자리에 넣는다
        for (int i = 1; i <= str1.length(); i++) {
            dp[i][0] = i;
        }

        for (int i = 1; i <= str2.length(); i++) {
            dp[0][i] = i;
        }
        
        // lca 알고리즘 처럼 탐색
        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    // 같은 문자라면 좌측 상단 대각선 숫자 가져오기
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    // 다른 문자라면 좌, 상, 좌측상단, 숫자 중 최솟값 가져오기 후 +1
                    dp[i][j] = Math.min(dp[i - 1][j - 1],
                            Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
            }
        }

        System.out.println(dp[str1.length()][str2.length()]);
    }
}