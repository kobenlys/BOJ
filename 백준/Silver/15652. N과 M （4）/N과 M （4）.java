import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static int N, M;
    public static ArrayList<String> aar = new ArrayList<>();

    public static void dfs(int start, String val, int num) {

        if (start == M) {
            aar.add(val);
            return;
        }

        for (int i = num; i <= N; i++) {
            String tmp = val;
            if (val.isEmpty()) {
                val = val.concat(String.valueOf(i));
            } else {
                val = val.concat(" ").concat(String.valueOf(i));
            }
            dfs(start + 1, val,i);
            val = tmp;
        }
    }

    public static void main(String[] args) throws IOException { //조건 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        dfs(0,"",1);

        Collections.sort(aar);
        for (String s : aar) {
            System.out.println(s);
        }
    }
}