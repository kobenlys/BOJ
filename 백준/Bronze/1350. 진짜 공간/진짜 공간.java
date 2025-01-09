import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        long answer = 0L;

        st = new StringTokenizer(br.readLine());
        int cluster = Integer.parseInt(br.readLine());

        while (st.hasMoreTokens()) {

            long number = Integer.parseInt(st.nextToken());
            if(number == 0) continue;
            if (cluster >= number) {
                answer += cluster;
            } else {
                answer += (number / cluster) * cluster;

                if (number % cluster > 0) {
                    answer += cluster;
                }
            }
        }

        System.out.println(answer);
    }
}