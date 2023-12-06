import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());


        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            int flag = Integer.parseInt(st.nextToken());
            // 배열 없이 푸는 법
            if (flag < M) {
                sb.append(flag).append(" ");
            }
        }
        System.out.println(sb);
    }
}