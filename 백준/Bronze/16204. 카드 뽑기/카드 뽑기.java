import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int O = Integer.parseInt(st.nextToken());
        int answer = 0;

        answer += Math.min(K, O);
        answer += Math.min(N - K, N - O);
        System.out.println(answer);
    }
}