import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int tc = Integer.parseInt(br.readLine());

        for (int T = 0; T < tc; T++) {
            st = new StringTokenizer(br.readLine(), " ");

            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dist = end - start;
            int max = (int) Math.sqrt(dist);


            if (max == Math.sqrt(dist)) {
                sb.append(max * 2 - 1).append("\n");
            } else if (dist <= max* max + max) {
                sb.append(max * 2).append("\n");
            } else {
                sb.append(max * 2 + 1).append("\n");
            }
        }
        System.out.print(sb);
    }
}
