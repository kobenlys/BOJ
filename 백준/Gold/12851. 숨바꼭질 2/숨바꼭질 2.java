import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N, K, min = Integer.MAX_VALUE, cnt;
    public static int[] vi; // 시간체크
    public static Queue<Integer> qu = new LinkedList<>();

    public static void bfs() { // 너비우선탐색 응용
        qu.offer(N);
        vi[N] = 1;
        while (!qu.isEmpty()) {

            int now = qu.poll();
            // K까지의 최소시간보다 더 넘게 시간 걸린것들은 큐에서 제외
            if(min < vi[now]) continue;

            for (int i = 0; i < 3; i++) {
                int nVal;

                if (i == 0) { // 3가지 움직임 방향
                    nVal = now * 2;
                } else if (i == 1) {
                    nVal = now - 1;
                } else {
                    nVal = now + 1;
                }
                // 범위체크
                if (nVal < 0 || nVal > 100000) continue;

                if (nVal == K) {
                    // K에 도달했다면
                    //가장 처음 도착한 시간이 최소시간임을 보장
                    min = vi[now];
                    // 중복 방문시 cnt++
                    cnt++;
                }
                // 방문하지 않거나 || 이번시간 시간턴에 중복 방문한다면
                // 두 경우다 통과
                if (vi[nVal] == 0 || vi[nVal] == vi[now] + 1) {
                    qu.offer(nVal);
                    vi[nVal] = vi[now] + 1;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if (N >= K) {
            System.out.println(N-K+"\n"+1);
            return;
        }

        vi = new int[100_001];
        bfs();
        System.out.print(min + "\n" + cnt);
    }
}