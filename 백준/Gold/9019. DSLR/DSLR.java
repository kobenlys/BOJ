import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int goal;
    public static Queue<node> qu = new LinkedList<>();
    public static StringBuilder sb = new StringBuilder();
    public static boolean[] vi;

    public static class node { // 계산된 값과 어떤계산을 진행했는지 기록하는 객체
        int res;
        String trace;

        public node(int res, String trace) {
            this.res = res;
            this.trace = trace;
        }
    }

    public static int D(int num) { // D 계산, 숫자 두배로 변환
        int temp = num * 2;
        return temp % 10000;
    }

    public static int S(int num) { // S 계산, 숫자 -1 하기
        int temp = num - 1;
        // temp 가 -1 일경우 9999를 리턴한다
        return temp == -1 ? 9999 : temp;
    }

    public static int L(int num) { // L 계산 , 자릿수 왼쪽 회전
        return num % 1000 * 10 + num / 1000;
    }

    public static int R(int num) { // R 계산 , 자릿수 오른쪽 회전
        return num % 10 * 1000 + num / 10;
    }

    public static void bfs(int start) { // BFS 알고리즘
        qu.offer(new node(start, ""));
        vi[start] = true;
        while (!qu.isEmpty()) {
            node nd = qu.poll();

            if (nd.res == goal) { // 답 출력
                sb.append(nd.trace).append("\n");
                qu.clear();
                break;
            }

            int d = D(nd.res);
            int s = S(nd.res);
            int l = L(nd.res);
            int r = R(nd.res);
            // 이미 나온 결과값은 다시 계산할 필요없다
            if (!vi[d]) {
                qu.offer(new node(d, nd.trace + "D"));
                vi[d] = true;
            }
            if (!vi[s]) {
                qu.offer(new node(s, nd.trace + "S"));
                vi[s] = true;
            }
            if (!vi[l]) {
                qu.offer(new node(l, nd.trace + "L"));
                vi[l] = true;
            }
            if (!vi[r]) {
                qu.offer(new node(r, nd.trace + "R"));
                vi[r] = true;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken());
            goal = Integer.parseInt(st.nextToken());
            vi = new boolean[10000];
            bfs(start);
        }
        System.out.print(sb);
    }
}