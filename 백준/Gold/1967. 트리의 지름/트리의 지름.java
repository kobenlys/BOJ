import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int max, site;
    public static ArrayList<node>[] arr1;
    public static boolean[] vi;

    public static class node { // 간선의 도착노드, 길이 담는 객체
        int goal, len;

        public node(int goal, int len) {
            this.goal = goal;
            this.len = len;
        }
    }

    public static void dfs(int start, int val) { // DFS 알고리즘
        // max 값 변경 시 도착 노드를 저장한다.
        if (max < val) {
            max = val;
            site = start;
        }

        for (int i = 0; i < arr1[start].size(); i++) {
            node tmp = arr1[start].get(i);
            if (!vi[tmp.goal]) {
                // 트리의 경로는 하나이다, 다시 false 처리 해줄 필요 없음.
                vi[tmp.goal] = true;
                dfs(tmp.goal, val + tmp.len);
            }
        }
    }

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int V = Integer.parseInt(br.readLine());
        // 인접리스트 만들기.
        arr1 = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            arr1[i] = new ArrayList<>();
        }

        for (int i = 0; i < V - 1; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            int l = Integer.parseInt(st.nextToken());
            // 트리의 성질 -> 간선이 무방향 이다.
            arr1[s].add(new node(e, l));
            arr1[e].add(new node(s, l));
        }
        
        // 트리의 성질
        // 어떤 노드에서 시작해도 가장 멀리있는 노드로 갈 수 있다. 이 점을 이용하자.

        // 임의 노드에서 출발하여 가장 먼 노드(site) 구하기.
        vi = new boolean[V];
        vi[0] = true;
        dfs(0, 0);
        
        // site노드에서 가장 먼 노드 구하기
        // 가장 멀리 떨어진 두 노드의 가중치 합 -> 트리의 지름임.
        vi = new boolean[V];
        vi[site] = true;
        dfs(site, 0);

        System.out.print(max);
    }
}
