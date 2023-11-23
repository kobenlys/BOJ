import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.*;

public class Main {
    public static int N, K, res;
    public static int[] vi;
    public static Queue<Integer> qu = new LinkedList<>();

    public static void algorithm() { // BFS로 모든경로 탐색 경로 =(+1, -1, *2)
        qu.offer(N);
        
        st: while (!qu.isEmpty()) { // break 지점 st
            int flag = qu.poll();

            for (int i = 0; i < 3; i++) { // 모든 경로 탐색 for 문
                int next;
                if (i == 0) {
                    next = flag + 1;
                } else if (i == 1) {
                    next = flag - 1;
                }else{
                    next = flag * 2;
                }

                if (range(next) && vi[next] == 0) { //range + 방문 체크 (방문하지 않았다면 당연히 0이다.)
                    if (next == K) {
                        // next = K 값이면 전 값만 저장 이유 = 시작할때 vi[N] = 1 했음.
                        vi[next] = vi[flag];
                        break st;
                    }
                    qu.offer(next);
                    vi[next] = vi[flag] + 1;

                }
            }
        }
    }

    public static boolean range(int N) {
        return N >= 0 && 100000 >= N;
    }


    public static void main(String[] args) throws IOException { //조건 입력

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        res = 0;
        vi = new int[100001];

        if (N == K) {
            System.out.println(0);
        } else  {
            vi[N] = 1;
            algorithm();
            System.out.println(vi[K]);

        }
    }
}
