import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N, S;
    public static ArrayList<Integer> arr1;

    public static void algorithm() {

        for (int i = 0; i < N - 1; i++) {
            int max = 0;
            int idx = 0;
            for (int j = i + 1; j <= i + S; j++) {
                if (j == N) break;
                if (max < arr1.get(j)) {
                    max = arr1.get(j);
                    idx = j;
                }
            }

            if (arr1.get(i) < max) {
                arr1.remove(idx);
                arr1.add(i, max);
                S -= idx - i;
            }

            if (S == 0) {
                break;
            }
        }
    }

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        arr1 = new ArrayList<>();

        st = new StringTokenizer(br.readLine(), " ");
        for (int i = 0; i < N; i++) {
            int n = Integer.parseInt(st.nextToken());
            arr1.add(n);
        }

        S = Integer.parseInt(br.readLine());
        algorithm();

        for (int i = 0; i < N; i++) {
            System.out.print(arr1.get(i) + " ");
        }
    }
}