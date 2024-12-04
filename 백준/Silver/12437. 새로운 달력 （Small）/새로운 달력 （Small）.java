import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int tc = 1; tc <= T; tc++) {

            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            int res = 0, tmp = 0;
            for (int i = 0; i < A; i++) {
                res += (b + tmp) / c;
                if ((b + tmp) % c != 0) {
                    res++;
                    tmp = (b + tmp) % c;
                } else {
                    tmp = 0;
                }
            }
            sb.append("Case #").append(tc).append(": ").append(res).append("\n");
        }
        System.out.println(sb);
    }
}