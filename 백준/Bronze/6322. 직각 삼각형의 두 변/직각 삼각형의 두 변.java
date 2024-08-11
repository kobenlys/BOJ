import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int tc = 1;
        while (true) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            double res = 0;
            if (A == 0) break;

            sb.append("Triangle #" + tc).append("\n");

            if (A == -1) {

                res = C * C - B * B;

                if (res <= 0) {
                    sb.append("Impossible.").append("\n\n");
                } else {
                    res = Math.sqrt(res);
                    sb.append('a').append(" = ");
                    sb.append(String.format("%.3f", res)).append("\n\n");
                }

            } else if (B == -1) {
                res = C * C - A * A;
                if (res <= 0) {
                    sb.append("Impossible.").append("\n\n");
                } else {
                    res = Math.sqrt(res);
                    sb.append('b').append(" = ");
                    sb.append(String.format("%.3f", res)).append("\n\n");
                }

            } else {
                res = A * A + B * B;
                res = Math.sqrt(res);

                sb.append('c').append(" = ");
                sb.append(String.format("%.3f", res)).append("\n\n");
            }
            tc++;
        }
        System.out.print(sb);
    }
}