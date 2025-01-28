import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb;
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        sb = new StringBuilder();

        for (int i = 1; i <= Math.sqrt(K); i++) {


            int size = i + K / i;

            for (int j = 0; j < i; j++) {
                sb.append("A");
            }
            for (int j = 0; j < K / i; j++) {
                sb.append("B");
            }



            int target = K - (i * (K / i));

            if(sb.length() > N){
                sb.setLength(0);
                continue;
            }

            //AAAAABBBBAB
            if (target != 0) {
                sb.setCharAt((sb.length()) - target, 'A');
                sb.append("B");
            }

            for (int j = sb.length(); j < N; j++) {
                sb.insert(0, 'B');
            }

            if(sb.length() == N){
                System.out.println(sb);
                return;
            }
            sb.setLength(0);
        }

        // BAABBAB

        if (K == 0) {
            sb.setLength(0);
            for (int i = 0; i < N; i++) {
                sb.append("A");
            }
        }

        if (sb.length() == 0) {
            System.out.println(-1);
        } else {
            System.out.println(sb);
        }

    }
}