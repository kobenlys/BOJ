import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int min, N, M;
    public static boolean[] vi;
    public static ArrayList<node> home = new ArrayList<>(); // 집
    public static ArrayList<node> store = new ArrayList<>(); // 치킨 집
    public static ArrayList<node> pick = new ArrayList<>(); // M 만큼 치킨집 선택

    // 좌표를 담기 위한 객체
    public static class node {
        int x;
        int y;

        public node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void dfs(int start, int k) { // DFS 알고리즘

        if (start == M) {
            int sum = 0;
            // 하나의 집 좌표 와 선택된 치킨집들과 가장 가까운 거리 저장!
            // 문제 조건 1. 가장 가까운 치킨집과의 거리를 센다. 를 만족한다.
            for (int i = 0; i < home.size(); i++) {
                int res = Integer.MAX_VALUE;
                node nd1 = home.get(i);
                for (int j = 0; j < pick.size(); j++) {
                    node nd2 = pick.get(j);
                    int flag = Math.abs(nd2.y - nd1.y) + Math.abs(nd2.x - nd1.x);
                    res = Math.min(res, flag);
                }
                sum += res;
            }
            // 치킨집 위치 경우의 수들의 치킨거리 최솟값 도출
            min = Math.min(min, sum);
            return;
        }


        for (int i = k; i < store.size(); i++) {
            if (!vi[i]) {
                node nd = store.get(i);

                vi[i] = true;
                pick.add(new node(nd.x, nd.y));
                // 치킨집 선택 / 제외
                dfs(start + 1, i + 1);
                pick.remove(pick.size() - 1);
                vi[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                if (num == 2) {
                    // 치킨집 좌표 저장
                    store.add(new node(j, i));
                } else if (num == 1) {
                    // 집 좌표 저장
                    home.add(new node(j, i));
                }
            }
        }

        vi = new boolean[store.size()];
        min = Integer.MAX_VALUE;
        //함수 호출
        dfs(0, 0);

        System.out.println(min);
    }
}
