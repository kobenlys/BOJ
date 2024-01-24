import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N, cnt;
    public static ArrayList<ArrayList<Integer>> arr1;
    public static boolean[] vi;

    public static void dfs(int start) {

        if (vi[start]) return; // 시작노드 제거 하는경우

        if (arr1.get(start).isEmpty()) {
            // 더 이상 탐색불가 == 리프노드임.
            cnt++;
            return;
        }

        for (int i = 0; i < arr1.get(start).size(); i++) {
            // 이동가능한 노드 있을경우 이동
            int e = arr1.get(start).get(i);
            dfs(e);
        }
    }

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        arr1 = new ArrayList<>();
        vi = new boolean[N];

        for (int i = 0; i < N; i++) {
            arr1.add(new ArrayList<>());
        }

        int node = 0;
        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            int s = Integer.parseInt(st.nextToken());
            if (s == -1) {
                node = i;
                continue;
            }
            arr1.get(s).add(i);
        }

        int flag = Integer.parseInt(br.readLine());
        vi[flag] = true;

        // 삭제될 노드에 연결된 간선 인접리스트에서 삭제하기.
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < arr1.get(i).size(); j++) {
                if (arr1.get(i).get(j) == flag) {
                    arr1.get(i).remove(j);
                    j--;
                }
            }
        }

        dfs(node);
        System.out.print(cnt);
    }
}
