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
        int[] arr1 = new int[N];
        int answer = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

        for (int i = 0; i < N; i++) {
            arr1[i] = Integer.parseInt(br.readLine());
            answer = arr1[i];
        }

        for (int i = 1; i < N; i++) {
            pq.offer(arr1[i] - arr1[i - 1]);
        }

        K--;
        answer -= arr1[0];
        answer++;

        while (K > 0) {
            int num = pq.poll();
            answer -= num;
            answer++;
            K--;
        }

        System.out.println(answer);
    }
}