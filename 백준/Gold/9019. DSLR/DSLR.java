import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int start, goal;
    public static char[] flag = {'D', 'S', 'L', 'R'};
    public static boolean[] vi;
    public static Queue<Integer> qu = new LinkedList<>();
    public static StringBuilder sb = new StringBuilder();
    public static StringBuilder res = new StringBuilder();


    public static int instDSLR(int i, int num) {
        if (i == 0) { // D 계산
            return (num * 2) % 10000;
        } else if (i == 1) { // S 계산
            return num == 0 ? 9999 : num - 1;
        } else if (i == 2) {
            // L 계산 , 자릿수 왼쪽 회전
            return num % 1000 * 10 + num / 1000;
        } else {
            // R 계산 , 자릿수 오른쪽 회전
            return num % 10 * 1000 + num / 10;
        }
    }


    public static void bfs(int start) { // BFS 알고리즘
        int[] from = new int[10000];
        char[] Calc = new char[10000];
        qu.offer(start);
        vi[start] = true;

        while (!qu.isEmpty()) {
            int key = qu.poll();

            if (key == goal) { // 답 출력
                // 역 추적하기.
                int tmp = goal;
                while (start != tmp) {
                    // 가장 마지막에 나오는 답이 첫번째 명령어이기 때문에 insert 메서드 사용하여 첫번째 자리에 입력
                    sb.insert(0, Calc[tmp]);
                    tmp = from[tmp];
                }
                res.append(sb).append("\n");
                sb.setLength(0);
                qu.clear();
                break;
            }

            for (int i = 0; i < 4; i++) {
                int num = instDSLR(i, key);

                if (!vi[num]) { // 이미 나왔던 결과값은 다시 계산할 필요 없다.
                    qu.offer(num);
                    // 역추적 위한 현재 계산된 값에 대응하는 배열자리에 전 값 저장
                    from[num] = key;
                    Calc[num] = flag[i]; // 현재 값에 대응하는 자리에 명령어 입력
                    vi[num] = true;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            start = Integer.parseInt(st.nextToken());
            goal = Integer.parseInt(st.nextToken());
            vi = new boolean[10000];
            bfs(start);
        }
        System.out.print(res);
    }
}