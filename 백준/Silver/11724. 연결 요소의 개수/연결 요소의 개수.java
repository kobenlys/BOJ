import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static boolean[] vi;
    public static ArrayList<Integer>[] node;

    public static void dfs(int start) { // dfs 알고리즘

        // 간선으로 연결된 노드를 차례대로 방문 한다
        for (int i = 0; i < node[start].size(); i++) {
            int flag = node[start].get(i);

            if (vi[flag]) { // 이미 방문하였다면 continue
                continue;
            }
            vi[flag] = true;
            // 다음 연결된 노드 방문 위해 재귀적 실행 한다.
            dfs(flag);
        }
    }

    public static void main(String[] args) throws IOException { // 값 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int cnt = 0;

        // 인접리스트를 담기 위해 2차원 동적배열 생성
        vi = new boolean[N];
        node = new ArrayList[N];

        for (int i = 0; i < N; i++) {
            node[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int start = Integer.parseInt(st.nextToken()) - 1;
            int end = Integer.parseInt(st.nextToken()) - 1;
            // "방향 없는 그래프" = "양방향", 즉 1 -> 2, 2 -> 1 둘 다 가능
            node[start].add(end);
            node[end].add(start);
        }


        // dfs 함수 반복 실행 -> 실행 끝난다면 cnt++ 해준다
        for (int i = 0; i < N; i++) {
            if (!vi[i]) {
                dfs(i);
                cnt++;
            }
        }
        System.out.println(cnt);
    }
}