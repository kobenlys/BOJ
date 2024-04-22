import java.io.*;
import java.util.*;

public class Main {

    public static class node {
        int n, cnt;
        public node(int n, int cnt) {
            this.n = n;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Stack<node> stk = new Stack<>();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        long ans = 0; // 500000!의 짝이 생길 수 있다.

        int[] arr1 = new int[N];

        for (int i = 0; i < N; i++) {
            arr1[i] = Integer.parseInt(br.readLine());
        }

        for (int i = 0; i < N; i++) {
            int num = arr1[i];

            node now = new node(num, 1);

            while (!stk.isEmpty() && stk.peek().n <= num) {
                node nd = stk.pop();
                ans += nd.cnt;
                // 동일한 키를 가진 사람이 있다면, 다음에 짝이 생겼을때
                // 동일한 사람 수 만큼 짝이 생김, 동일한 인물이 몇명 있는지 스텍에 함께 넣는다.
                if (nd.n == num){
                    now.cnt += nd.cnt;
                }
            }
            // now를 넣기전에 스텍에 사람이 존재한다면 새로운 짝이 생겼다는 의미 -> ans++
            if (!stk.isEmpty()) {
                ans++;
            }

            stk.push(now);
        }

        System.out.print(ans);
    }
}