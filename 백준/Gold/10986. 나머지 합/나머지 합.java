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
        int sum  = 0;
        long answer = 0;
        int[] mod = new int[M];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            sum = (sum + Integer.parseInt(st.nextToken())) % M;
            mod[sum]++;
        }

        answer = mod[0];
        for (int i = 0; i < M; i++) {
            answer += (long) mod[i] * (mod[i] - 1) / 2;
        }
        System.out.println(answer);
    }
}
