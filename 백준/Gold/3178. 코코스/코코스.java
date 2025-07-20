import java.io.*;
import java.util.*;

public class Main {

    public static int N, M;
    public static List<String> start, end;

    public static int cmp(String a, String b, String c, String d) {
        int i = 0, j = 0;

        if (a.equals(b)) {
            i = M;
        }else{
            while (i < M && a.charAt(i) == b.charAt(i)) {
                i++;
            }
        }

        if (c.equals(d)) {
            j = M;
        } else {
            while (j < M && c.charAt(j) == d.charAt(j)) {
                j++;
            }
        }

        return i + j;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int answer = N * M * 2;
        start = new ArrayList<>();
        end = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            start.add(str.substring(0, M));
            sb = new StringBuilder(str.substring(M, 2 * M));
            sb.reverse();
            end.add(sb.toString());
        }

        Collections.sort(start);
        Collections.sort(end);

        for (int i = 1; i < N; i++) {
            answer -= cmp(start.get(i - 1), start.get(i), end.get(i - 1), end.get(i));
        }
        System.out.println(answer);
    }
}