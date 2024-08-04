import java.io.*;
import java.util.*;

public class Main {
    public static int N, answer = Integer.MAX_VALUE;
    public static int[] parent;
    public static int[] people;
    public static boolean[][] vi;
    public static ArrayList<Integer> redTeam = new ArrayList<>();
    
    // 집합찾기
    public static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }
    
    // 집합 합치기
    public static void union(int x, int y) {
        int from = find(x);
        int to = find(y);
        
        if (from != to) {
            parent[to] = from;
        }
    }
    
    // 지역구 별 두팀 인원수 체킹 + 그래프모두 연결되었는지 판단.
    public static void gerryMandering() {
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }
        
        ArrayList<Integer> blueTeam = new ArrayList<>();
        int sum1 = 0;
        int sum2 = 0;
        
        // 만들어진 지역구 조합, 제외 나머지 지역구 조합 만들기
        for (int i = 1; i <= N; i++) {
            if (!redTeam.contains(i)) {
                blueTeam.add(i);
            }
        }

        // 빨간팀 지역구 합치기
        for (int i = 0; i < redTeam.size(); i++) {
            int n = redTeam.get(i);
            sum1 += people[n];
            for (int j = i + 1; j < redTeam.size(); j++) {
                int r = redTeam.get(j);
                if (vi[n][r] && vi[r][n]) { // 간선이 이어져있다면
                    union(n, r); // 집합에 넣기
                }
            }
        }
        
        // 파란팀 지역구 합치기
        for (int i = 0; i < blueTeam.size(); i++) {
            int n = blueTeam.get(i);
            sum2 += people[n];
            for (int j = i + 1; j < blueTeam.size(); j++) {
                int r = blueTeam.get(j);
                if (vi[n][r] && vi[r][n]) { // 간선이 이어져있다면
                    union(n, r); // 집합에 넣기
                }
            }
        }

        HashSet<Integer> set = new HashSet<>();
        for (int i = 1; i <= N; i++) {
            set.add(find(i));
        }
        
        // 부모노드가 2개 == 집합이 2개 == 지역구가 잘 나눠짐.
        if (set.size() == 2) {
            answer = Math.min(answer, Math.abs(sum1 - sum2));
        }
    }
    
    // nCr, 레드팀 지역구 조합 생성
    public static void dfs(int node, int cnt) {
        if (cnt == 0) {
            gerryMandering();
            return;
        }

        for (int i = node; i <= N; i++) {
            redTeam.add(i);
            dfs(i + 1, cnt - 1);
            redTeam.remove(redTeam.size() - 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        people = new int[N + 1];
        parent = new int[N + 1];
        vi = new boolean[N + 1][N + 1];


        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            people[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            for (int j = 0; j < time; j++) {
                int e = Integer.parseInt(st.nextToken());
                vi[i][e] = vi[e][i] = true; // 양방향 간선 처리
            }
        }
        
        // 두 팀으로 나누며 6C2, 6C4는 팀만 다를 뿐 본질은 같다.
        for (int i = 1; i <= N / 2; i++) {
            dfs(1, i);
        }

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }
}