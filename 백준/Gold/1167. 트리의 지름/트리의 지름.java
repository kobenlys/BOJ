import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int V, max, node;
    public static ArrayList<node>[] arr1;
    public static boolean[] vi;

    public static class node { // 간선, 가중치 담는 객체
        int goal, len;

        public node(int goal, int len) {
            this.goal = goal;
            this.len = len;
        }
    }

    public static void dfs(int start, int val) { //dfs 알고리즘 
        if (max < val) {
            // max값 도출, 가장 끝 노드 구하기.
            max = val;
            node = start;
        }

        for (int i = 0; i < arr1[start].size(); i++) {
            node tmp = arr1[start].get(i);
            if (!vi[tmp.goal]) {
                vi[tmp.goal] = true;
                dfs(tmp.goal, val + tmp.len);
                vi[tmp.goal] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        V = Integer.parseInt(br.readLine());
        // 인접리스트 생성
        arr1 = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            arr1[i] = new ArrayList<>();
        }

        for (int i = 0; i < V; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken()) - 1;

            while (true) {
                int e = Integer.parseInt(st.nextToken()) - 1;
                if (e == -2) break;
                int l = Integer.parseInt(st.nextToken());
                arr1[s].add(new node(e, l));
            }
        }
        // "트리"의 성질 이용
        vi = new boolean[V];
        vi[0] = true;
        dfs(0, 0);

        vi = new boolean[V];
        vi[node] = true;
        dfs(node, 0);

        System.out.print(max);
    }
}
