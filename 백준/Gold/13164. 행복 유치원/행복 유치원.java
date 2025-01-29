import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb;
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        long answer = 0;

        int[] arr1 = new int[N];
        List<Integer> list = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < N; i++) {
            list.add(arr1[i] - arr1[i - 1]);
        }

        Collections.sort(list);

        for (int i = 0; i < N - K; i++) {
            answer += list.get(i);
        }

        System.out.println(answer);

    }
}