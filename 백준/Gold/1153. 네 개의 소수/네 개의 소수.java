import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N;
    public static boolean[] arr1;
    public static ArrayList<Integer> prime = new ArrayList<>();
    public static Stack<Integer> stk = new Stack<>();

    public static void e_Sieve() {

        arr1[0] = arr1[1] = true;
        // 2 ~ N 까지 소수판별
        for (int i = 2; i <= N; i++) {
            for (int j = i+i; j <= N ; j +=i) {
                if (!arr1[j]) {
                    arr1[j] = true;
                }
            }
        }
    }

    public static void dfs(int start, int sum, int idx) {
        
        // 4개의 조합이 되기 전에 sum이 N보다 클때 리턴
        if (start < 4 && sum > N) {
            return;
        }
        
        if (start == 4) {
            if (sum == N) {
                // 모든 조건 성립시 출력
                StringBuilder sb = new StringBuilder();
                while (!stk.isEmpty()) {
                    sb.append(stk.pop()).append(" ");
                }
                System.out.print(sb);
                System.exit(0);
            }
            //sum != N 이라면 리턴
            return;
        }
        // 가장 큰 소수 부터 찾는게 효율적임.
        for (int i = idx; i >= 0 ; i--) {
            stk.push(prime.get(i));
            dfs(start + 1, sum + prime.get(i), i);
            stk.pop();
        }
    }


    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr1 = new boolean[N + 1];
        
        // 에라토스테네스의 체 알고리즘 실행
        e_Sieve();
        // 시간 단축위해 소수로 이루어진 배열 생성
        for (int i = 2; i <=N; i++) {
            if (!arr1[i]) {
                prime.add(i);
            }
        }

        dfs(0, 0, prime.size()-1);
        System.out.println(-1);
    }
}