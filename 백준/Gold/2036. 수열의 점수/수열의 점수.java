import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Integer> Postive = new PriorityQueue<>(Comparator.reverseOrder());
        PriorityQueue<Integer> Negative = new PriorityQueue<>();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        long answer = 0;

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            if (num > 0) {
                Postive.add(num);
            } else {
                Negative.add(num);
            }
        }

        while (!Postive.isEmpty()) {

            long A = Postive.poll();
            if (Postive.isEmpty()) {
                answer += A;
                break;
            }
            long B = Postive.poll();
            answer += Math.max(A * B, A + B);
        }

        while (!Negative.isEmpty()) {

            long A = Negative.poll();
            if (Negative.isEmpty()) {
                answer += A;
                break;
            }
            long B = Negative.poll();
            answer += A * B;
        }

        System.out.print(answer);
    }
}