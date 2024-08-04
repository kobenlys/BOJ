import java.io.*;
import java.util.*;

public class Main {
    public static int N, peopleCnt, answer = Integer.MAX_VALUE;
    public static int[] people;
    public static int[] parent;
    public static boolean[] vi;
    public static boolean[][] arr1;
    public static ArrayList<Integer> nCrBlueNode;
    public static ArrayList<Integer> nCrRedNode;

    public static int find(int x) {
        if (parent[x] == x) return x;
        return parent[x] = find(parent[x]);
    }

    public static void union(int x, int y) {
        int from = find(x);
        int to = find(y);

        if (from != to) {
            parent[to] = from;
        }
    }

    public static void garryMendering() {
        nCrRedNode = new ArrayList<>();
        parent = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            parent[i] = i;
        }

        int sum1 = 0;
        int sum2 = 0;

        for (int i = 1; i <= N; i++) {
            if (!nCrBlueNode.contains(i)) {
                nCrRedNode.add(i);
            }
        }

        for (int i = 0; i < nCrRedNode.size(); i++) {
            sum1 += people[nCrRedNode.get(i)];
            int n = nCrRedNode.get(i);
            for (int j = i + 1; j < nCrRedNode.size(); j++) {
                int r = nCrRedNode.get(j);
                if (find(n) != find(r) && arr1[n][r] && arr1[r][n]) {
                    union(n, r);
                }
            }
        }


        for (int i = 0; i < nCrBlueNode.size(); i++) {
            sum2 += people[nCrBlueNode.get(i)];
            int n = nCrBlueNode.get(i);
            for (int j = i + 1; j < nCrBlueNode.size(); j++) {
                int r = nCrBlueNode.get(j);
                if (find(n) != find(r) && arr1[n][r] && arr1[r][n]) {
                    union(n, r);
                }
            }
        }

        HashSet<Integer> set = new HashSet<>();
        for (int i = 1; i <= N; i++) {
            set.add(find(i));
        }

        if (set.size() == 2) {
            answer = Math.min(answer, Math.abs(sum1 - sum2));
        }
    }


    public static void dfs(int node, int cnt) {

        if (cnt == 0) {
            garryMendering();
            return;
        }

        for (int i = node; i <= N; i++) {
            nCrBlueNode.add(i);
            dfs(i + 1, cnt - 1);
            nCrBlueNode.remove(nCrBlueNode.size() - 1);
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;


        N = Integer.parseInt(br.readLine());
        arr1 = new boolean[N + 1][N + 1];
        people = new int[N + 1];
        nCrBlueNode = new ArrayList<>();


        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            people[i] = Integer.parseInt(st.nextToken());
            peopleCnt += people[i];
        }

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());

            for (int j = 0; j < time; j++) {
                int e = Integer.parseInt(st.nextToken());
                arr1[i][e] = arr1[e][i] = true;
            }
        }

        for (int i = 1; i <= N / 2; i++) {
            vi = new boolean[N + 1];
            dfs(1, i);
        }

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }
}