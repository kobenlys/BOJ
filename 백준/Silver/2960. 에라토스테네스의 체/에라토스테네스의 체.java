import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N, K;

    public static int SieveCnt() {
        int[] arr1 = new int[N+1];

        for (int i = 2; i <= N; i++) {
            arr1[i] = i;
        }

        for (int i = 2; i <= N ; i++) {
            for (int j = i; j <= N ; j = j+i) {
                if (arr1[j] == 0) {
                    continue;
                }
                arr1[j] = 0;
                K--;
                if (K == 0) {
                    return j;
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");


        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        System.out.print(SieveCnt());

    }
}