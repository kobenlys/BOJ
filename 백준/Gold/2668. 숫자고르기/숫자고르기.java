import java.io.*;
import java.util.*;

public class Main {
    public static int N, cnt;
    public static boolean[] vi;
    public static int[] arr1;
    public static HashSet<Integer> set = new HashSet<>();

    public static void dfs(int start, int target, List<Integer> tmp) {
        
        // 출발점을 되돌아 왔으면 집합 가능
        if (vi[start] && start == target) {
            cnt++;
            set.addAll(tmp); // 중복제거 + 거쳐왔던 노드 저장
            return;
        }
        // 되돌아 가기전에 방문한곳 또 방문시 target번호는 집합 불가능
        if (vi[start]) {
            return;
        }


        tmp.add(start);
        int idx = arr1[start];
        vi[start] = true;
        dfs(idx, target, tmp);
        vi[start] = false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        List<Integer> tmp = new ArrayList<>();
        StringTokenizer st;


        int N = Integer.parseInt(br.readLine());
        arr1 = new int[101];
        vi = new boolean[101];

        for (int i = 1; i <= N; i++) {
            int a = Integer.parseInt(br.readLine());
            arr1[i] = a;
        }


        for (int i = 1; i <= N; i++) {
            dfs(i, i, tmp);
            tmp.clear(); // 얕은 복사 제거
        }


        tmp = new ArrayList<>(set);
        Collections.sort(tmp);
        sb.append(cnt).append("\n");

        for (int e : tmp) {
            sb.append(e).append("\n");
        }

        System.out.print(sb);
    }
}