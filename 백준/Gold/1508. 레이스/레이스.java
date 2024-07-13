import java.io.*;
import java.util.*;

public class Main {
    public static int N, M, K;
    public static int[] arr1;
    public static boolean[] vi;
    public static ArrayList<String> list = new ArrayList<>();

    public static void makeArray() {
        String res = "";
        for (int i = 0; i < K; i++) {
            if (vi[i]) {
                res += 1;
            } else {
                res += 0;
            }
        }
        list.add(res);
    }

    public static boolean isOK(int len) {
        vi = new boolean[K];
        int cnt = 1;

        for (int i = 0; i < K; i++) {
            for (int j = i + 1; j < K; j++) {
                if (arr1[i] + len <= arr1[j]) {
                    vi[i] = true;
                    vi[j] = true;
                    i = j - 1;
                    cnt++;
                    if (cnt == M) return true;
                    break;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); // 트랙 수
        M = Integer.parseInt(st.nextToken()); // 트랙 수
        K = Integer.parseInt(st.nextToken()); // 심판 수

        arr1 = new int[K];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            arr1[i] = Integer.parseInt(st.nextToken());
        }


        int left = 1, right = 1_000_000;
        int max = 0;

        while (left <= right) {

            int mid = (left + right) / 2;
            if (!isOK(mid)) {
                right = mid - 1;
            } else {
                if (max < mid) {
                    max = mid;
                    list.clear();
                }
                left = mid + 1;
                makeArray();
            }
        }

        System.out.println(list.get(list.size() - 1));
    }
}