import java.io.*;
import java.util.*;

public class Main {
    public static ArrayList<Long> arr1 = new ArrayList<>();

    public static void dfs(int start, long num) {

        if (start > 10) {
            return;
        }

        arr1.add(num);
        for (int i = 0; i < num % 10; i++) {
            dfs(start + 1, num * 10 + i);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());


        if (N > 1022) {
            System.out.println(-1);
        } else if (N <= 10) {
            System.out.println(N);
        } else {
            for (int i = 0; i <= 9; i++) {
                dfs(1, i);
            }
            Collections.sort(arr1);
            System.out.println(arr1.get(N));
        }
    }
}