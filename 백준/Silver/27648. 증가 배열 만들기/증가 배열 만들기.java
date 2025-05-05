import java.io.*;
import java.util.*;
 
public class Main { // S5 증가 배열 만들기
    static StringBuilder sb = new StringBuilder();
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
 
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
 
        int[][] arr = new int[N][M];
        String answer = "YES";
 
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                arr[i][j] = (i + 1) + (j + 1) - 1;
                if (arr[i][j] > K) {
                    answer = "NO";
                    break;
                }
            }
        }
        System.out.println(answer);
        if(answer.equals("YES")) {
            for (int[] s : arr) {
                for (int k : s) {
                    sb.append(k).append(" ");
                }
                sb.append("\n");
            }
            System.out.println(sb);
        }
    }
}