import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int time = 0;
        int cnt = 0;
        int piro = 0;

        while (time != 24) {

            if (piro + A <= M) {
                piro += A;
                cnt += B;
            }else{
                piro -= C;
                piro = Math.max(0, piro);
            }

            time++;
        }

        System.out.println(cnt);
    }
}
