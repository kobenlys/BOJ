import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static ArrayList<ArrayList<Integer>> arr1;
    public static boolean[] vi;
    public static int[] ans;

    public static void findParents(int node) { // 구현

        for (int i = 0; i < arr1.get(node).size(); i++) {
            int nd = arr1.get(node).get(i);
            if (!vi[nd]) { // 한번 방문한 노드는 재방문 하지 않는다.
                vi[nd] = true;
                // ans 배열의 다음노드(nd)인덱스 위치에  현재노드(부모노드) 저장한다.
                ans[nd] = node;
                findParents(nd); // 다음노드로 이동
            }
        }
    }

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        arr1 = new ArrayList<>(); // 인접 리스트 생성
        vi = new boolean[N]; // 방문 처리
        ans = new int[N]; // 부모노드 저장

        for (int i = 0; i < N; i++) {
            arr1.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            int s = Integer.parseInt(st.nextToken()) - 1;
            int e = Integer.parseInt(st.nextToken()) - 1;
            // 트리의 경로 양방향으로 저장한다.
            // 가장 처음 입력받는 숫자가 부모노드라는 보장이 없다.
            arr1.get(s).add(e);
            arr1.get(e).add(s);
        }

        findParents(0);
        for (int i = 1; i < N; i++) {
            sb.append(++ans[i]).append("\n");
        }
        System.out.print(sb);
    }
}
