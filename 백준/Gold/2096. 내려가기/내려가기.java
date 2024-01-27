import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] dp1 = new int[3];
        int[] dp2 = new int[3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int n3 = Integer.parseInt(st.nextToken());

            if (i == 0) {
                dp1[0] = dp2[0] = n1;
                dp1[1] = dp2[1] = n2;
                dp1[2] = dp2[2] = n3;
            } else {
                int tmp1 = dp1[0];
                int tmp2 = dp1[2];
                dp1[0] = Math.max(dp1[0], dp1[1]) + n1;
                dp1[2] = Math.max(dp1[1], dp1[2]) + n3;
                dp1[1] = Math.max(dp1[1], Math.max(tmp1, tmp2)) + n2;

                tmp1 = dp2[0];
                tmp2 = dp2[2];
                dp2[0] = Math.min(dp2[0], dp2[1]) + n1;
                dp2[2] = Math.min(dp2[1], dp2[2]) + n3;
                dp2[1] = Math.min(dp2[1], Math.min(tmp1, tmp2)) + n2;
            }
        }

        Arrays.sort(dp1);
        Arrays.sort(dp2);
        System.out.println(dp1[2] + " " + dp2[0]);
    }
}
