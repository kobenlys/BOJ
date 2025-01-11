

import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;


        int N = Integer.parseInt(br.readLine());
        int nowTime = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int tmp = Integer.parseInt(st.nextToken());
            int active = Integer.parseInt(st.nextToken());

            int score = nowTime % (tmp + active);
            nowTime += Math.max(0, active - score);

            nowTime++;
        }

        System.out.println(nowTime);
    }
}