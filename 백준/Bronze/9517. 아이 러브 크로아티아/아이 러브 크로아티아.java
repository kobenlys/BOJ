import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int K = Integer.parseInt(br.readLine());
        int N = Integer.parseInt(br.readLine());
        int time = 0;

        while (N-- > 0) {

            st = new StringTokenizer(br.readLine());
            int T = Integer.parseInt(st.nextToken());
            String control = st.nextToken();

            if (time + T <= 210) {

                time += T;
                if (control.equals("T")) {
                    K++;
                }
                if (K == 9) {
                    K = 1;
                }
            } else {
                break;
            }
        }
        System.out.println(K);
    }
}