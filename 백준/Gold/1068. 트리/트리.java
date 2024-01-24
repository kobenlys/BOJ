import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N, cnt;
    public static ArrayList<ArrayList<Integer>> arr1;
    public static boolean[] vi;

    public static void dfs(int start) {

        if(vi[start]) return;

        if (arr1.get(start).isEmpty()) {
            cnt++;
            return;
        }

        for (int i = 0; i < arr1.get(start).size(); i++) {
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
