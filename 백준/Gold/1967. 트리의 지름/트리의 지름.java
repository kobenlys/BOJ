import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int V, max, site;
    public static ArrayList<node>[] arr1;
    public static boolean[] vi;

    public static class node{
        int goal, len;

        public node(int goal, int len) {
            this.goal = goal;
            this.len = len;
        }
    }

    public static void dfs(int start, int val) {

        if (max < val) {
            max = val;
            site = start;
        }

        for (int i = 0; i < arr1[start].size(); i++) {
            node tmp = arr1[start].get(i);
            if (!vi[tmp.goal]) {
                vi[tmp.goal] = true;
                dfs(tmp.goal, val+tmp.len);
            }
        }
    }

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        V = Integer.parseInt(br.readLine());
        arr1 = new ArrayList[V];

        for (int i = 0; i < V; i++) {
            arr1[i] = new ArrayList<>();
        }

        for (int i = 0; i < V-1; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken())-1;
            int e = Integer.parseInt(st.nextToken())-1;
            int l = Integer.parseInt(st.nextToken());

            arr1[s].add(new node(e, l));
            arr1[e].add(new node(s, l));
        }

        vi = new boolean[V];
        vi[0] = true;
        dfs(0, 0);

        vi = new boolean[V];
        vi[site] = true;
        dfs(site, 0);
        System.out.print(max);
    }
}
