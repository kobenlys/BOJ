import java.io.*;
import java.util.*;

public class Main {
    public static int N, K;
    public static int[][] arr1;

    public static int CalcMax(int idx1, int idx2) {
        int answer = 0;

        for (int i = 0; i < K; i++) {
            answer += arr1[i][idx1];
            answer += arr1[i][idx2];
        }

        return answer;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> pq1 = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> pq2 = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> pq3 = new PriorityQueue<>(Comparator.reverseOrder());
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        int answer1 = 0;
        int answer2 = 0;
        int answer3 = 0;
        arr1 = new int[N][3];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr1[i][0] = Integer.parseInt(st.nextToken());
            arr1[i][1] = Integer.parseInt(st.nextToken());
            arr1[i][2] = Integer.parseInt(st.nextToken());

            int A = arr1[i][0] + arr1[i][1];
            int B = arr1[i][0] + arr1[i][2];
            int C = arr1[i][1] + arr1[i][2];
            pq1.offer(A);
            pq2.offer(B);
            pq3.offer(C);
        }

        while (K-- > 0) {
            answer1 += pq1.poll();
            answer2 += pq2.poll();
            answer3 += pq3.poll();
        }
        System.out.println(Math.max(answer1, Math.max(answer2, answer3)));
    }
}