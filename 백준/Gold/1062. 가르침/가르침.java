import java.io.*;
import java.util.*;

public class Main {
    public static int N, K, max;
    public static String[] arr1;
    public static HashSet<Character> set = new HashSet<>();

    public static void dfs(int idx) {

        if (set.size() == K) {
            int res = N;
            
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < arr1[i].length(); j++) {
                    if (!set.contains(arr1[i].charAt(j))) {
                        res--;
                        break;
                    }
                }
            }
            max = Math.max(max, res);
            return;
        }

        for (int i = idx; i < 26; i++) {
            if (i == 0 || i == 2 || i == 8 || i == 13 || i == 19) continue;
            set.add((char) (i + 'a'));
            dfs(i + 1);
            set.remove((char) (i + 'a'));
        }
    }

    // anta rc tica
    // a n t i c,
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        arr1 = new String[N];

        for (int i = 0; i < N; i++) {
            arr1[i] = br.readLine();
        }

        if (K < 5) {
            System.out.println(0);
            System.exit(0);
        }

        set.add('a');
        set.add('n');
        set.add('t');
        set.add('i');
        set.add('c');

        dfs(0);
        System.out.println(max);

    }
}