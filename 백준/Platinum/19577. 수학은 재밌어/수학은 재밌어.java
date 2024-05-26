import java.io.*;
import java.util.*;

public class Main {
    // 오일러의 피 함수
    public static int eulerPhi(int N) {
        int res = N;
        
        for (int i = 2; i <= Math.sqrt(N); i++) {
            // i가 N의 소인수라면
            if (N % i == 0) {
                // N에서 i의 배수 제거
                while (N % i == 0) {
                    N /= i;
                }
                res -= res / i;
            }
        }
        if (N > 1) res -= res / N;
        return res;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        HashSet<Integer> set = new HashSet<>();
        List<Integer> list;
        int answer = -1;

        // N의 약수 구하기 O(logN);
        for (int i = 1; i <= Math.sqrt(N); i++) {
            if (N % i == 0) {
                set.add(i); // i
                set.add(N / i); // i의 짝
            }
        }

        list = new ArrayList<>(set);
        Collections.sort(list); // 최솟값부터 탐색하기 위해 정렬.

        for (int num : list) {
            int tmp = num * eulerPhi(num);
            // num * eulerphi(num) == N 이라면 출력
            if (tmp == N) {
                answer = num;
                break;
            }
        }
        System.out.println(answer);
    }
}