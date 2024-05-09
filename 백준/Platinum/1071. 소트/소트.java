import java.io.*;
import java.util.*;

public class Main {
    public static int N;
    public static int[] arr1 = new int[1001];
    public static ArrayList<Integer> res = new ArrayList<>();

    public static void dfs() {

        // 첫번째로 만들어진 수열이 사전순으로 앞서는 답임.
        if (N == res.size()) {
            StringBuilder sb = new StringBuilder();
            for (int e : res) {
                sb.append(e).append(" ");
            }
            System.out.print(sb);
            System.exit(0); // 출력 및 종료
        }

        // 사전순 앞서는 수열 만들기
        for (int i = 0; i <= 1000; i++) {
            if (arr1[i] > 0) {
                arr1[i]--; // 사용 시
                if (res.isEmpty()) {
                    // res배열이 비었다면, 그냥 넣고 dfs
                    res.add(i);
                    dfs();
                    res.remove(res.size() - 1);
                } else {
                    // 문제 조건에 부합하면 res배열에 입력후 dfs
                    if (res.get(res.size() - 1) + 1 != i) {
                        res.add(i);
                        dfs();
                        res.remove(res.size() - 1);
                    }
                }
                arr1[i]++; // 사용 후 반납
            }
        }
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            arr1[n]++; // 사용된 숫자 카운트
        }
        dfs(); // dfs탐색
    }
}