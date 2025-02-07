import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        String answer = "";
        int result = 0;
        int max = 0;


        for (int i = 0; i < N; i++) {
            String name = br.readLine();
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            int food = Integer.parseInt(st.nextToken());
            int tmp = 0;

            while (food >= cnt) {
                tmp++;
                food -= cnt;
                food += 2;
            }

            if (max < tmp) {
                max = tmp;
                answer = name;
            }
            result += tmp;
        }

        System.out.println(result);
        System.out.println(answer);
    }
}