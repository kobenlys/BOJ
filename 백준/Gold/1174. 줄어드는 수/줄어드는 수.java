import java.io.*;
import java.util.*;

public class Main {

    public static List<Long> list;

    public static void dfs(int start, int idx, long number, int target) {
        if (start == target) {
            list.add(number);
            return;
        }

        for (int i = idx; i >= 0; i--) {
            if (number % 10 > i || start == 0) {
                dfs(start + 1, i - 1, number * 10 + i, target);
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add((long) i);
        }

        for (int i = 2; i <= 10; i++) {
            dfs(0, 9, 0, i);
        }
        Collections.sort(list);

        if (list.size() < N) {
            System.out.println(-1);
        } else {
            System.out.println(list.get(N - 1));
        }
    }
}
