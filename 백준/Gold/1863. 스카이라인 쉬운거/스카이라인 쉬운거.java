import java.io.*;
import java.util.*;

public class Main {

    public static class node {
        int height, idx;

        public node(int height, int idx) {
            this.height = height;
            this.idx = idx;
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        Stack<node> stk = new Stack<>();
        int answer = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int height = Integer.parseInt(st.nextToken());

            if (!stk.isEmpty() && stk.peek().height > height) {
                int cnt = 0;
                int tmp = 0;
                while (!stk.isEmpty() && stk.peek().height > height) {
                    node now = stk.pop();

                    if (tmp != now.height) {
                        tmp = now.height;
                        cnt++;
                    }
                }
                answer += cnt;
            }


            if (height == 0) continue;
            stk.push(new node(height, idx));
        }

        if (!stk.isEmpty()) {
            int target = stk.pop().height;
            answer++;
            while (!stk.isEmpty() && stk.peek().height <= target) {

                if (stk.peek().height < target) {
                    target = stk.peek().height;
                    answer++;
                }
                stk.pop();
            }
        }
        System.out.println(answer);
    }
}