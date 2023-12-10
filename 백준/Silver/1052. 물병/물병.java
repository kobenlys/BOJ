import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int ans = 0;

        // bitCount를 통해 이진수의 '1' 을 센다
        // while문으로 N++를 '1'개수가 K 보다 이하일때 탈출
        while (Integer.bitCount(N) > K) {
            N++;
            ans++;
        }

        // 증가한 수 만큼 출력
        System.out.println(ans);
    }
}
