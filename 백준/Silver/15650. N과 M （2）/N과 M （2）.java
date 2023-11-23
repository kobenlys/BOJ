import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N, M;
    public static boolean[] vi;
    public static int[] arr1;
    public static ArrayList<Integer> res = new ArrayList<>();
    public static StringBuilder sb = new StringBuilder();

    public static void algorithm(int start, int k) { // 백트래킹 알고리즘

        if (start == M) { // M만큼 res배열에 입력된다면 출력
            for (int e : res) {
                sb.append(e).append(" ");
            }
            sb.append("\n");
            return;
        }
        
        // vi 배열로 방문한 노드는 다시 방문 하지 않는다.
        // i = k 로 함수 호출 할때 k+1 의 인자를 넘겨준다 = 중복이 사라진다.
        for (int i = k; i < N; i++) {
            if (!vi[i]) {
                vi[i] = true;
                res.add(arr1[i]);
                algorithm(start + 1, i + 1);
                res.remove(res.size() - 1);
                vi[i] = false;
            }
        }
    }


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr1 = new int[N];
        vi = new boolean[N];

        for (int i = 0; i < N; i++) {
            arr1[i] = i + 1;
        }

        // 함수 호출 & 출력
        algorithm(0, 0);
        System.out.print(sb);
    }
}
