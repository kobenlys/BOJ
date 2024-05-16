import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] arr1 = new int[N];

        String str = br.readLine();
        for (int i = 0; i < N; i++) {
            arr1[i] = str.charAt(i);
        }

        while (M-- > 0) {
            st = new StringTokenizer(br.readLine());
            int dir = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());

            int cnt = 1;

            if (dir == 1) {
                for (int i = s - 1; i < e - 1; i++) {
                    if (arr1[i] != arr1[i + 1]) {
                        cnt++;
                    }
                }
                sb.append(cnt).append("\n");

            } else {
                for (int i = s-1; i < e; i++) {
                    arr1[i]++;
                    if (arr1[i] == 91) {
                        arr1[i] = 'A';
                    }
                }
            }
        }
        System.out.print(sb);
    }
}