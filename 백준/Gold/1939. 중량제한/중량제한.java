import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static boolean[] vi;
    public static ArrayList<ArrayList<node>> arr1 = new ArrayList<>();


    public static class node {
        int goal, val;

        public node(int goal, int val) {
            this.goal = goal;
            this.val = val;
        }
    }

    public static boolean dfs(int start, int target, int limit) {

        boolean isPossible = false;

        if (start == target) {
            return true;
        }

        vi[start] = true;

        for (int i = 0; i < arr1.get(start).size(); i++) {
            node tmp = arr1.get(start).get(i);
            if (!vi[tmp.goal] && tmp.val >= limit) {
                vi[tmp.goal] = true;
                isPossible |= dfs(tmp.goal, target, limit);
            }
        }
        return isPossible;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        HashSet<Integer> set = new HashSet<>();
        ArrayList<Integer> road;

        

        for (int i = 0; i < N; i++) {
            arr1.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            int v = Integer.parseInt(st.nextToken());
            set.add(v);

            arr1.get(s).add(new node(e, v));
            arr1.get(e).add(new node(s, v));
        }

        for (ArrayList<node> nd : arr1) {
            Collections.sort(nd, (o1, o2) -> o2.val - o1.val);
        }


        st = new StringTokenizer(br.readLine(), " ");
        int start = Integer.parseInt(st.nextToken()) - 1;
        int end = Integer.parseInt(st.nextToken()) - 1;


        road = new ArrayList<>(set);
        Collections.sort(road);
        int left = 0, right = road.size() - 1;
        int answer = 0;

        while (left <= right) {
            vi = new boolean[N];
            int mid = (left + right) / 2;

            if (!dfs(start, end, road.get(mid))) {
                right = mid - 1;
            } else {
                answer = mid;
                left = mid + 1;
            }
        }
        System.out.println(road.get(answer));
    }
}