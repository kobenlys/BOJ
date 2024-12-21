import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());


        while (T-- > 0) {

            st = new StringTokenizer(br.readLine());
            int W = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());

            int[][] arr1 = new int[N][2];

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                arr1[i][0] = Integer.parseInt(st.nextToken());
                arr1[i][1] = Integer.parseInt(st.nextToken());
            }

            int capacity = 0;
            int answer = 0;
            int tmp = 0;
            for (int i = 0; i < N; i++) {

                capacity += arr1[i][1];
                tmp = arr1[i][0];

                if (capacity >= W) {
                    answer += tmp * 2;
                    tmp = 0;
                    if (capacity > W) {
                        i--;
                    }
                    capacity = 0;
                }
            }

            if (tmp > 0) {
                answer += tmp * 2;
            }

            sb.append(answer).append("\n");

        }
        System.out.println(sb);

    }
}