import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < T; tc++) {
            st = new StringTokenizer(br.readLine(), " ");
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int res = 1;
            // 소숫점 1의 자리만 필요하기때문에 10으로 나눠준다.
            for (int i = 0; i < b; i++) {
                res = res * a % 10;
            }
            sb.append(res == 0? 10 : res).append("\n");
        }
        System.out.println(sb);
    }
}