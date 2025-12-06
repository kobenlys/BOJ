import java.io.*;
import java.util.*;

public class Main {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int answer = 0;
        Set<Integer> set = new HashSet<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int num = Integer.parseInt(st.nextToken());

            for (int j = num; j <= N; j+= num) {
                set.add(j);
            }
        }

        System.out.println(set.stream().mapToInt(Integer::intValue).sum());

    }
}