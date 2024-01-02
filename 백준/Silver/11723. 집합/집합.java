import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int M = Integer.parseInt(br.readLine());
        int S = 0;

        for (int i = 0; i < M; i++) {

            st = new StringTokenizer(br.readLine(), " ");

            String input = st.nextToken();

            if (input.equals("all")) {
                S = (1 << 21) - 1;
            } else if (input.equals("empty")) {
                S = 0;
            } else {

                int x = Integer.parseInt(st.nextToken());
                if (input.equals("add")) {
                    S = S | (1 << x);
                } else if (input.equals("remove")) {
                    S = S & ~(1 << x);
                } else if (input.equals("check")) {
                    sb.append((S & (1 << x)) != 0 ? 1 : 0).append("\n");
                } else if (input.equals("toggle")) {
                    S = S ^ (1 << x);
                }
            }
        }
        System.out.println(sb);
    }
}