import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int grade12 = 0;
        int grade34M = 0;
        int grade34W = 0;
        int grade56M = 0;
        int grade56W = 0;


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());

            if (n == 1 || n == 2) {
                grade12++;
                continue;
            }

            if (n == 3 || n == 4) {
                if (s == 0) grade34W++;
                if (s == 1) grade34M++;
                continue;
            }

            if (s == 0) grade56W++;
            if (s == 1) grade56M++;
        }

        int answer = grade12 % K == 0 ? grade12 / K : grade12 / K + 1;

        answer += grade34W % K == 0 ? grade34W / K : grade34W / K + 1;
        answer += grade34M % K == 0 ? grade34M/ K : grade34M / K + 1;

        answer += grade56W % K == 0 ? grade56W / K : grade56W / K + 1;
        answer += grade56M % K == 0 ? grade56M/ K : grade56M / K + 1;
        System.out.println(answer);
    }
}