import java.io.*;
import java.util.*;

public class Main {
    public static int answer;
    public static Deque<Integer> left = new ArrayDeque<>();
    public static Deque<Integer> right = new ArrayDeque<>();

    public static void dfs(int sum) {

        if (left.isEmpty() || right.isEmpty()) {
            answer = Math.max(answer, sum);
            return;
        }


        if (left.peekFirst() > right.peekFirst()) {
            int tmp = right.pollFirst();
            dfs(sum + tmp);
            right.offerFirst(tmp);

        } else {
            int tmpLeft = left.pollFirst();
            dfs(sum);

            int tmpRight = right.pollFirst();
            dfs(sum);

            left.offerFirst(tmpLeft);
            right.offerFirst(tmpRight);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st1;
        StringTokenizer st2;

        int N = Integer.parseInt(br.readLine());
        answer = 0;

        st1 = new StringTokenizer(br.readLine());
        st2 = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            left.offerLast(Integer.parseInt(st1.nextToken()));
            right.offerLast(Integer.parseInt(st2.nextToken()));
        }

        dfs(0);
        System.out.println(answer);
    }
}