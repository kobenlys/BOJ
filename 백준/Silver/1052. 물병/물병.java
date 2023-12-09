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

        // 문제 "같은 양 물병 2개씩 합칠 수 있음" -> 2진수 패턴
        // 비트마스킹 풀이 가능하다
        
        // ans++ 해주면서 이진수의 '1'의 수를 카운팅하고
        // K이하 라면 break 후 출력한다
        // 답이 나오기 전까지 무한반복한다.
        while (true) {
            int cnt = 0;
            String bitN = Integer.toBinaryString(N+ans);
            for (int i = 0; i < bitN.length(); i++) {
                if (bitN.charAt(i) == '1') {
                    cnt++;
                }
            }
            if (cnt <= K) {
                break;
            }
            ans++;
        }


        System.out.println(ans);
    }
}